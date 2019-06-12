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
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.IServerParent;
import io.apicurio.datamodels.core.models.common.Server;

/**
 * A command used to create a new server in a document.
 * @author eric.wittmann@gmail.com
 */
public class NewServerCommand extends AbstractCommand {

    public NodePath _parentPath;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _server;
    
    public boolean _serverExisted;
    
    NewServerCommand() {
    }
    
    NewServerCommand(IServerParent parent, Server server) {
        this._parentPath = Library.createNodePath((Node) parent);
        this._server = Library.writeNode(server);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewServerCommand] Executing.");
        
        IServerParent parent = (IServerParent) document;
        if (!this.isNullOrUndefined(this._parentPath)) {
            parent = (IServerParent) this._parentPath.resolve(document);
        }

        // If the parent doesn't exist, abort!
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        List<Server> servers = parent.getServers();
        
        if (this.isNullOrUndefined(servers)) {
            servers = new ArrayList<>();
            NodeCompat.setProperty(parent, Constants.PROP_SERVERS, servers);
        }

        Server server = (Server) parent.createServer();
        Library.readNode(this._server, server);
        if (this.serverAlreadyExists(parent, server)) {
            this._serverExisted = true;
            return;
        } else {
            servers.add(server);
            this._serverExisted = false;
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewServerCommand] Reverting.");
        if (this._serverExisted) {
            return;
        }

        IServerParent parent = (IServerParent) document;
        if (ModelUtils.isDefined(this._parentPath)) {
            parent = (IServerParent) this._parentPath.resolve(document);
        }

        // If the parent doesn't exist, abort!
        if (this.isNullOrUndefined(parent) || this.isNullOrUndefined(parent.getServers())) {
            return;
        }

        List<Server> servers = parent.getServers();

        String serverUrl = JsonCompat.getPropertyString(this._server, Constants.PROP_URL);
        Server server = parent.getServer(serverUrl);
        if (this.isNullOrUndefined(server)) {
            return;
        }
        servers.remove(servers.indexOf(server));
        if (servers.size() == 0) {
            NodeCompat.setProperty(parent, Constants.PROP_SERVERS, null);
        }
    }

    /**
     * Returns true if a server with the same url already exists in the parent.
     * @param parent
     * @param server
     */
    private boolean serverAlreadyExists(IServerParent parent, Server server) {
        return ModelUtils.isDefined(parent.getServer(server.url));
    }
}
