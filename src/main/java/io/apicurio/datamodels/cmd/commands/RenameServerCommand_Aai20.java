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

import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;

/**
 * A command used to rename a security scheme, along with all references to it.
 * @author c.desc2@gmail.com
 */
public class RenameServerCommand_Aai20 extends AbstractCommand {

    public String _oldServerName;
    public String _newServerName;

    RenameServerCommand_Aai20() {
    }

    RenameServerCommand_Aai20(String oldServerName, String newServerName) {
        this._oldServerName = oldServerName;
        this._newServerName = newServerName;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[RenameServerCommand_Aai20] Executing.");
        this._doServerRename((Aai20Document) document, this._oldServerName, this._newServerName);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[RenameServerCommand_Aai20] Reverting.");
        this._doServerRename((Aai20Document) document, this._newServerName, this._oldServerName);
    }

    /**
     * Does the work of renaming a path from one name to another.
     * @param document
     * @param from
     * @param to
     * @private
     */
    private void _doServerRename(Aai20Document document, String from, String to) {
        AaiServer aaiServer = null;

        if (ModelUtils.isDefined(document.servers)) {
            // If the "to" aaiServer already exists, do nothing!
            if (!this.isNullOrUndefined(document.servers.get(to))) {
                return;
            }
            aaiServer = document.servers.remove(from);
        }

        // If we didn't find a aaiServer with the "from" name, then nothing to do.
        if (this.isNullOrUndefined(aaiServer)) {
            return;
        }

        // Now we have the aaiServer - rename it!
        aaiServer.rename(to);
        document.servers.put(to, aaiServer);

        // No document visit since servers are only used in #/servers
    }
}
