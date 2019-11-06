/*
 * Copyright 2019 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.datamodels.cmd.ot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;

/**
 * This class is used to implement Operational Transformation support for OAI documents. For
 * reference:
 *
 * https://en.wikipedia.org/wiki/Operational_transformation
 *
 * Specifically, this implements a simple CC/CCI model, where changes to the document are
 * caused by executing commands against a document in a particular state.  This engine
 * ensures that commands are all executed in a consistent order.
 *
 * Note that all commands must have an "undo" operation that is integral to the proper
 * functioning of the OT algorithm.  The approach taken is to undo later commands when
 * inserting a command into a queue.  In other words, commands can be received and applied
 * asynchronously.  This can cause commands to be applied out of order.  When applying a
 * command out of order, it must be properly inserted into the command flow.  When this is
 * done, the document must be reverted to the proper state prior to executing the command.
 *
 * This is accomplished by "rewinding" the state of the document by invoking the "undo"
 * operation on all commands that have already been applied but temporally occur after the
 * command being inserted.  Once the document state has been rewound, the command being
 * inserted can be executed, and then all following commands can be executed.
 * 
 * @author eric.wittmann@gmail.com
 */
public class OtEngine {

    private List<OtCommand> pendingCommands;
    private List<OtCommand> commands;
    private Document document;
    private List<Long> pendingUndos;
    
    /**
     * Constructor.
     * @param document
     */
    public OtEngine(Document document) {
        this.document = document;
        this.pendingCommands = new ArrayList<>();
        this.pendingUndos = new ArrayList<>();
        this.commands = new ArrayList<>();
    }

    /**
     * Gets the current document.
     * @return {OasDocument}
     */
    public Document getCurrentDocument() {
        return this.document;
    }

    /**
     * Returns true if there is at least one pending command in the engine.  A pending command is one
     * that has not yet been finalized.  This typically means that the command has been applied to
     * the local document but not persisted in some remote store.
     * @return {boolean}
     */
    public boolean hasPendingCommands() {
        return this.pendingCommands.size() > 0;
    }

    /**
     * Returns true if the OT engine has already seen the given command.
     * @param command
     */
    public boolean hasCommand(OtCommand command) {
        for (OtCommand cmd : this.commands) {
            if (cmd.contentVersion == command.contentVersion) {
                return true;
            }
        }
        return false;
    }

    /**
     * Executes the given command in the correct sequence.  This command must have a valid
     * finalized contentVersion property.  This property will determine where in the sequence
     * of commands this one falls.  The engine will revert the document to an appropriate state
     * so that the command can be executed in the correct order.  During this process, existing
     * commands may need to be undone and then re-executed after.
     *
     * Here's what happens when executing a command:
     *
     * 1) "undo" all pending commands, since those are always executed last
     * 2) identify the insertion point of the command in the finalized command queue
     * 3) "undo" all finalized commands that fall AFTER this command in the finalized command queue
     * 4) execute this command and insert it into the finalized command queue
     * 5) re-execute all finalized commands that were undone in step #3
     * 6) re-execute all pending commands
     * 7) profit!
     *
     * A future optimization of this algorithm is to only undo/redo the commands that conflict
     * with this command.  This will avoid unnecessary work (why bother undoing/redoing when
     * there is no potential for a conflict in the document).  This optimization can be achieved
     * by ensuring that each command has a list of NodePaths that represent the affected parts of
     * the document tree.  These paths can be used to determine which commands conflict with
     * other commands.
     * 
     * Whenever a command is executed, any local commands that have been undone can now be
     * safely removed from the list of commands, because making a change (by definition) orphans
     * any existing reverted commands.  There is no way to recover them.
     *
     * @param command
     * @param pending
     */
    public void executeCommand(OtCommand command, boolean pending) {
        if (pending) {
            command.local = true;
            LoggerCompat.info("[OtEngine] Executing PENDING command with contentId: %o", command.contentVersion);
            command.execute(this.document);
            this.pendingCommands.add(command);
            this.pruneRevertedCommands();
            return;
        }

        // If the OT engine has already seen this command, then this is a duplicate and we can skip it
        if (this.hasCommand(command)) {
            LoggerCompat.info("[OtEngine] Detected duplicate OtCommand with content version: %o", command.contentVersion);
            return;
        }

        LoggerCompat.info("[OtEngine] Executing command with content version: %o", command.contentVersion);
        int pidx;

        // Check to see if this command was already "undone" - if so then there's much less
        // work to do - just insert it into the command list at the right place.
        pidx = this.pendingUndos.indexOf(command.contentVersion);
        if (pidx != -1) {
            this.pendingUndos.remove(pidx);
            command.reverted = true;
        }

        // Rewind any pending commands first.
        for (pidx = this.pendingCommands.size() - 1; pidx >= 0; pidx--) {
            if (!this.pendingCommands.get(pidx).reverted) {
                this.pendingCommands.get(pidx).command.undo(this.document);
            }
        }

        // Note: when finding the insertion point, search backwards since that will likely be the shortest trip

        // Find the insertion point of the new command (rewind any commands that should occur *after* the new command)
        int insertionIdx = this.commands.size() - 1;
        if (this.commands.size() > 0) {
            while (insertionIdx >= 0 && this.commands.get(insertionIdx).contentVersion > command.contentVersion) {
                if (!this.commands.get(insertionIdx).reverted) {
                    this.commands.get(insertionIdx).command.undo(this.document);
                }
                insertionIdx--;
            }
        }

        // Insert the new command into the correct location
        insertionIdx++;
        this.commands.add(insertionIdx, command);

        // Re-apply commands as necessary
        int idx = insertionIdx;
        while (idx < this.commands.size()) {
            this.commands.get(idx).execute(this.document);
            idx++;
        }

        // Now re-apply any pending commands
        for (pidx = 0; pidx < this.pendingCommands.size(); pidx++) {
            this.pendingCommands.get(pidx).execute(this.document);
        }
    }

    /**
     * Moves a commands from the "pending" queue to the "finalized" command queue.  This occurs
     * when a local (aka pending) command is acknowledged by the coordinating server and assigned
     * a final content version.  The engine must remove the command from the pending queue, update
     * its contentVersion, and then insert it at the correct location in the finalized queue.
     *
     * Here's what happens when finalizing a command:
     *
     * 1) "undo" all pending commands, shrinking the pending command queue to 0
     * 2) update the given pending command's contentVersion
     * 3) call "executeCommand()" with the newly finalized command
     * 4) re-execute all remaining pending commands
     *
     * @param pendingCommandId
     * @param finalizedContentVersion
     */
    public void finalizeCommand(long pendingCommandId, long finalizedContentVersion) {
        LoggerCompat.info("[OtEngine] Finalizing command with contentId: %o  and new contentVersion: %o", pendingCommandId, finalizedContentVersion);

        // Note: special case where the command being finalized is the first (or only) pending command in the list *AND* its
        // finalizedContentVersion > than the most recent finalized command.  This represents the case where a single user
        // is editing a document and results in a simple shifting of the pending command from one queue to another without
        // doing the unnecessary work of unwinding the pending commands and re-applying them.
        boolean isFirstPendingCmd = this.pendingCommands.size() > 0 && this.pendingCommands.get(0).contentVersion == pendingCommandId;
        boolean isLatestCmd = this.commands.size() == 0 || (this.commands.get(this.commands.size() - 1).contentVersion < finalizedContentVersion);
        if (isFirstPendingCmd && isLatestCmd) {
            LoggerCompat.info("[OtEngine] Pending command is 'next up', performing simple shift from pending to finalized.");
            OtCommand command = this.pendingCommands.remove(0);
            command.contentVersion = finalizedContentVersion;
            this.commands.add(command);
            return;
        }

        // Rewind all pending commands.
        int pidx;
        for (pidx = this.pendingCommands.size() - 1; pidx >= 0; pidx--) {
            if (!this.pendingCommands.get(pidx).reverted) {
                this.pendingCommands.get(pidx).command.undo(this.document);
            }
        }

        // Temporarily detach the pending commands (so we don't undo them twice).
        List<OtCommand> pending = this.pendingCommands;
        this.pendingCommands = new ArrayList<>();

        // Locate the pending command being finalized
        int idx;
        boolean found = false;
        for (idx = 0; idx < pending.size(); idx++) {
            if (pending.get(idx).contentVersion == pendingCommandId) {
                found = true;
                break;
            }
        }

        // If found, remove the pending command being finalized from the pending array
        if (found) {
            OtCommand command = pending.get(idx);
            pending.remove(idx);
            command.contentVersion = finalizedContentVersion;

            this.executeCommand(command, false);
        } else {
            LoggerCompat.info("[OtEngine] Attempted to finalize pending command %d but was not found.", pendingCommandId);
        }

        // Now re-apply and restore all remaining pending commands (if any)
        this.pendingCommands = pending;
        for (pidx = 0; pidx < this.pendingCommands.size(); pidx++) {
            this.pendingCommands.get(pidx).execute(this.document);
        }
    }

    /**
     * Called to undo the last local command.  Returns the command that was undone (on success)
     * or null if there was no command to undo.
     */
    public OtCommand undoLastLocalCommand() {
        int idx;
        // Check pending commands first (these are ALL local).  If found, undo immediately and return.
        for (idx = this.pendingCommands.size() - 1; idx >= 0; idx--) {
            OtCommand cmd = this.pendingCommands.get(idx);
            if (!cmd.reverted) {
                cmd.undo(this.document);
                return cmd;
            }
        }

        // Next check for local commands in the finalized command list.  Some of these are
        // local and some are remote (from collaborators).
        OtCommand undoneCmd = null;
        if (undoneCmd == null) {
            for (idx = this.commands.size() - 1; idx >= 0; idx--) {
                OtCommand cmd = this.commands.get(idx);
                // Only interested if the command is local and not already reverted
                if (cmd.local && !cmd.reverted) {
                    undoneCmd = cmd;
                    break;
                }
            }
        }

        if (undoneCmd != null) {
            this.undo(undoneCmd.contentVersion);
        }

        return undoneCmd;
    }

    /**
     * Called to redo the last "undone" local command.  Returns the command that was redone (on success)
     * or null if there was no command to redo.
     */
    public OtCommand redoLastLocalCommand() {
        int idx;
        // Check the most recent pending command.  If it's reverted, then immediately redo it and return.  If it
        // is NOT reverted, then do nothing and return (following the semantics of redo).
        if (this.pendingCommands.size() > 0) {
            OtCommand cmd = this.pendingCommands.get(this.pendingCommands.size() - 1);
            if (cmd.reverted) {
                cmd.redo(this.document);
                return cmd;
            } else {
                return null;
            }
        }

        // Next check for local commands in the finalized command list.  Some of these are
        // local and some are remote (from collaborators).
        OtCommand redoneCmd = null;
        if (redoneCmd == null) {
            for (idx = this.commands.size() - 1; idx >= 0; idx--) {
                OtCommand cmd = this.commands.get(idx);
                // Only interested if the command is local and previously reverted
                if (cmd.local && cmd.reverted) {
                    redoneCmd = cmd;
                }
                if (cmd.local && !cmd.reverted) {
                    break;
                }
            }
        }

        if (redoneCmd != null) {
            this.redo(redoneCmd.contentVersion);
        }

        return redoneCmd;
    }

    /**
     * Called to undo a specific command by its contentVersion identifier.  Note: this will never
     * be invoked for a pending command (pending commands don't have content versions yet).
     * @param contentVersion
     */
    public OtCommand undo(long contentVersion) {
        int idx;
        List<OtCommand> commandsToUndo = new ArrayList<>();

        LoggerCompat.info("[OtEngine] Undo command with content version: %o", contentVersion);

        // 1. Undo all pending commands
        // 2. Undo all commands (in reverse chronological order) up to and including the one referenced by "contentVersion"
        // 3. Mark the command as "reverted"
        // 4. Re-apply all previously undone commands *except* the one actually being undone (including pending commands)
        // 5. Profit!

        // Add all pending commands to the "commands to undo" list.
        for (idx = this.pendingCommands.size() - 1; idx >= 0; idx--) {
            OtCommand cmd = this.pendingCommands.get(idx);
            commandsToUndo.add(cmd);
        }

        // Search backwards through the list of commands until we find the one we're looking for.
        boolean found = false;
        OtCommand foundCmd = null;
        for (idx = this.commands.size() - 1; idx >= 0; idx--) {
            OtCommand cmd = this.commands.get(idx);
            commandsToUndo.add(cmd);
            if (cmd.contentVersion == contentVersion) {
                found = true;
                foundCmd = cmd;
                break;
            }
        }

        // Did we find it?  If not, log the CV and return.  Nothing to do now.  The assumption
        // is that we haven't received the command for this CV yet.  When we do, we'll immediately
        // mark it as "reverted" and not apply it.
        if (!found) {
            this.pendingUndos.add(contentVersion);
            return null;
        }

        // Now undo all the commands we found
        commandsToUndo.forEach(cmd -> {
            if (!cmd.reverted) {
                cmd.command.undo(this.document);
            }
        });

        // Mark the found command as reverted
        foundCmd.reverted = true;

        // Re-apply all previously undone commands (auto-skipping the one we just marked as reverted)
        for (idx = commandsToUndo.size() - 1; idx >= 0; idx--) {
            OtCommand cmd = commandsToUndo.get(idx);
            cmd.execute(this.document);
        }

        return foundCmd;
    }

    /**
     * Called to redo a specific command by its contentVersion identifier.
     * @param contentVersion
     */
    public OtCommand redo(long contentVersion) {
        int idx;
        List<OtCommand> commandsToUndo = new ArrayList<>();

        LoggerCompat.info("[OtEngine] Redo command with content version: %o", contentVersion);

        // 1. Undo all pending commands
        // 2. Undo all commands (in reverse chronological order) up to and including the one referenced by "contentVersion"
        // 3. Mark the command as "NOT reverted"
        // 4. Re-apply all previously undone commands *INCLUDING* the one being redone (including pending commands)

        // Add all pending commands to the "commands to undo" list.
        for (idx = this.pendingCommands.size() - 1; idx >= 0; idx--) {
            OtCommand cmd = this.pendingCommands.get(idx);
            commandsToUndo.add(cmd);
        }

        // Search backwards through the list of commands until we find the one we're looking for.
        boolean found = false;
        OtCommand foundCmd = null;
        for (idx = this.commands.size() - 1; idx >= 0; idx--) {
            OtCommand cmd = this.commands.get(idx);
            commandsToUndo.add(cmd);
            if (cmd.contentVersion == contentVersion) {
                found = true;
                foundCmd = cmd;
                break;
            }
        }

        // Did we find it?  If not, possibly remove the CV from the list of pending undos.
        if (!found) {
            idx = this.pendingUndos.indexOf(contentVersion);
            if (idx != -1) {
                this.pendingUndos.remove(idx);
            }
            return null;
        }

        // Now undo all the commands we found
        commandsToUndo.forEach(cmd -> {
            if (!cmd.reverted) {
                cmd.command.undo(this.document);
            }
        });

        // Mark the found command as reverted
        foundCmd.reverted = false;

        // Re-apply all previously undone commands (auto-skipping the one we just marked as reverted)
        for (idx = commandsToUndo.size() - 1; idx >= 0; idx--) {
            OtCommand cmd = commandsToUndo.get(idx);
            cmd.execute(this.document);
        }

        return foundCmd;
    }

    /**
     * Called to remove any (local only) commands that have been undone/reverted.  This is 
     * done whenever a new local command is executed.  It is done because when a new command
     * is executed, any existing reverted commands can no longer be "redone". 
     */
    protected void pruneRevertedCommands() {
        // Remove reverted commands
        List<OtCommand> cmds = this.commands.stream().filter(cmd -> cmd.reverted).collect(Collectors.toList());
        cmds.forEach(cmd -> this.commands.remove(cmd));
        if (!cmds.isEmpty()) {
            LoggerCompat.info("[OtEngine] Pruned %d reverted commands", cmds.size());
        }
        
        // Remove reverted pending commands
        cmds = this.pendingCommands.stream().filter(cmd -> cmd.reverted).collect(Collectors.toList());
        cmds.forEach(cmd -> this.pendingCommands.remove(cmd));
        if (!cmds.isEmpty()) {
            LoggerCompat.info("[OtEngine] Pruned %d reverted pending commands", cmds.size());
        }
    }
}
