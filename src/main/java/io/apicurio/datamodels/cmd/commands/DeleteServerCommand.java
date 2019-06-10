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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.IServerParent;
import io.apicurio.datamodels.core.models.common.Server;

/**
 * A command used to delete a single server from an operation.
 * @author eric.wittmann@gmail.com
 */
public class DeleteServerCommand extends AbstractCommand {

    public String _serverUrl;
    public NodePath _parentPath;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldServer;
    
    DeleteServerCommand() {
    }
    
    DeleteServerCommand(Server server) {
        this._serverUrl = server.url;
        this._parentPath = Library.createNodePath(server.parent());
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteServerCommand] Executing.");
        this._oldServer = null;

        IServerParent parent = (IServerParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        Server server = parent.getServer(_serverUrl);
        if (this.isNullOrUndefined(server)) {
            return;
        }
        
        List<Server> servers = parent.getServers();
        servers.remove(servers.indexOf(server));
        if (servers.size() == 0) {
            NodeCompat.setProperty(parent, Constants.PROP_SERVERS, null);
        }

        this._oldServer = Library.writeNode(server);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteServerCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldServer)) {
            return;
        }

        IServerParent parent = (IServerParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        Server server = parent.createServer();
        Library.readNode(this._oldServer, server);
        
        List<Server> servers = parent.getServers();
        if (this.isNullOrUndefined(servers)) {
            servers = new ArrayList<>();
            NodeCompat.setProperty(parent, Constants.PROP_SERVERS, servers);
        }
        servers.add(server);
    }

}
