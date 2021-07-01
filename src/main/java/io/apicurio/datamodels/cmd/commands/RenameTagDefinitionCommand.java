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

package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Tag;
import io.apicurio.datamodels.core.util.VisitorUtil;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.core.visitors.TraverserDirection;
import io.apicurio.datamodels.openapi.models.OasOperation;

/**
 * A command used to rename a tag, along with all references to it.
 * @author eric.wittmann@gmail.com
 */
public class RenameTagDefinitionCommand extends AbstractCommand {

    public String _oldTag;
    public String _newTag;

    RenameTagDefinitionCommand() {
    }
    
    RenameTagDefinitionCommand(String oldTag, String newTag) {
        this._oldTag = oldTag;
        this._newTag = newTag;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[RenameTagDefinitionCommand] Executing.");
        this._doTagRename(document, this._oldTag, this._newTag);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[RenameTagDefinitionCommand] Reverting.");
        this._doTagRename(document, this._newTag, this._oldTag);
    }

    /**
     * Does the work of renaming a tag from one name to another.
     * @param document
     * @param from
     * @param to
     * @private
     */
    private void _doTagRename(Document document, String from, String to) {
        // If the "to" tag already exists, bail out before doing anything
        Tag existingTag = this._findTag(document, to);
        if (ModelUtils.isDefined(existingTag)) {
            return;
        }

        // Find the tag by name and rename it.
        Tag tagToRename = this._findTag(document, from);
        if (ModelUtils.isDefined(tagToRename)) {
            // Now rename the tag
            tagToRename.name = to;
            // Rename every **usage** of the tag in the document (all operations)
            IVisitor tagRenamer = new TagRenameVisitor(from, to);
            VisitorUtil.visitTree(document, tagRenamer, TraverserDirection.down);
        }
    }

    /**
     * Finds a tag in the document by name.  Returns null if not found.
     * @param document
     * @param tag
     * @private
     */
    private Tag _findTag(Document document, String tag) {
        if (ModelUtils.isDefined(document.tags)) {
            for (Tag tagDef : document.tags) {
                if (NodeCompat.equals(tagDef.name, tag)) {
                    return tagDef;
                }
            }
        }
        return null;
    }
    
    private static class TagRenameVisitor extends CombinedVisitorAdapter {
        
        private String from;
        private String to;
        
        /**
         * Constructor.
         */
        public TagRenameVisitor(String from, String to) {
            this.from = from;
            this.to = to;
        }
        
        /**
         * @see io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter#visitOperation(io.apicurio.datamodels.core.models.common.Operation)
         */
        @Override
        public void visitOperation(Operation node) {
            OasOperation op = (OasOperation) node;
            int idx = ModelUtils.isDefined(op.tags) ? op.tags.indexOf(this.from) : -1;
            if (idx != -1) {
                op.tags.set(idx, to);
            }

        }
    }
}
