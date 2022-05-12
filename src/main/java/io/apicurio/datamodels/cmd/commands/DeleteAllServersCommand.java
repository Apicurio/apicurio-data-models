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
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.IServerParent;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.openapi.v3.models.Oas30Server;

/**
 * A command used to delete all servers from a document.
 * 
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllServersCommand extends AbstractCommand {
    // TODO: Ordering on undo
    public NodePath _parentPath;
    @JsonDeserialize(contentUsing=NullableJsonNodeDeserializer.class)
    public List<Object> _oldServers;

    DeleteAllServersCommand() {
    }
    
    DeleteAllServersCommand(IServerParent parent) {
        this._parentPath = Library.createNodePath((Node) parent);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllServersCommand] Executing.");
        this._oldServers = new ArrayList<>();

        IServerParent parent = (IServerParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        // Save the old servers (if any)
        List<Server> servers = parent.getServers();
        if (!this.isNullOrUndefined(servers)) {
            servers.forEach( server -> {
                this._oldServers.add(Library.writeNode(server));
            });
        }

        NodeCompat.setProperty(parent, Constants.PROP_SERVERS, new ArrayList<>());
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteAllServersCommand] Reverting.");
        if (this._oldServers.size() == 0) {
            return;
        }

        IServerParent parent = (IServerParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        List<Server> servers = parent.getServers();
        if (this.isNullOrUndefined(servers)) {
            servers = new ArrayList<>();
            NodeCompat.setProperty(parent, Constants.PROP_SERVERS, servers);
        }
        for (Object oldServer : this._oldServers) {
            Oas30Server server = (Oas30Server) parent.createServer();
            Library.readNode(oldServer, server);
            servers.add(server);
        }
    }
}
