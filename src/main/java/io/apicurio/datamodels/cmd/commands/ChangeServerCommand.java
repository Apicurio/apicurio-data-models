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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.IServerParent;
import io.apicurio.datamodels.core.models.common.Server;

/**
 * A command used to modify a server.
 * @author eric.wittmann@gmail.com
 */
public class ChangeServerCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _serverUrl;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _serverObj;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldServer;
    
    ChangeServerCommand() {
    }

    ChangeServerCommand(Server server) {
        this._parentPath = Library.createNodePath(server.parent());
        this._serverUrl = server.url;
        this._serverObj = Library.writeNode(server);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangeServerCommand] Executing.");
        this._oldServer  = null;

        IServerParent parent = (IServerParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        Server server = parent.getServer(_serverUrl);
        if (this.isNullOrUndefined(server)) {
            return;
        }

        // Back up the old server info (for undo)
        this._oldServer = Library.writeNode(server);

        // Replace with new server info
        this.replaceServerWith(server, this._serverObj);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangeServerCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldServer)) {
            return;
        }

        IServerParent parent = (IServerParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        Server server = parent.getServer(_serverUrl);
        if (this.isNullOrUndefined(server)) {
            return;
        }

        this.replaceServerWith(server, this._oldServer);
    }

    /**
     * Replaces the content of a server with the content from another server.
     * @param toServer
     * @param fromServer
     */
    protected void replaceServerWith(Server toServer, Object fromServer) {
        toServer.getServerVariables().forEach( var_ -> {
            toServer.removeServerVariable(var_.getName());
        });
        Library.readNode(fromServer, toServer);
    }

}
