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
import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Server;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.Server;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A command used to create a new server in an aaidocument.
 * @author c.desc2@gmail.com
 */
public class NewServerCommand_Aai20 extends AbstractCommand {

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _server;

    public String _serverName;

    public boolean _serverExisted;

    NewServerCommand_Aai20() {
    }

    NewServerCommand_Aai20(Aai20Document parent, Aai20Server server) {
        this._serverName = server.getName();
        this._server = Library.writeNode(server);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewServerCommand_Aai20] Executing.");

        Aai20Document parent = (Aai20Document) document;

        // If the parent doesn't exist, abort!
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        Map<String, AaiServer> servers = parent.servers;
        
        if (this.isNullOrUndefined(servers)) {
            servers = new LinkedHashMap<>();
            NodeCompat.setProperty(parent, Constants.PROP_SERVERS, servers);
        }

        AaiServer server = parent.createServer(this._serverName);
        Library.readNode(this._server, server);
        if (this.serverAlreadyExists(parent)) {
            this._serverExisted = true;
            return;
        } else {
            servers.put(_serverName, server);
            this._serverExisted = false;
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewServerCommand_Aai20] Reverting.");
        if (this._serverExisted) {
            return;
        }

        Aai20Document parent = (Aai20Document) document;

        // If the parent doesn't exist, abort!
        if (this.isNullOrUndefined(parent) || this.isNullOrUndefined(parent.servers)) {
            return;
        }

        Map<String, AaiServer> servers = parent.servers;

        Server server = servers.get(this._serverName);
        if (this.isNullOrUndefined(server)) {
            return;
        }
        servers.remove(this._serverName);
        if (servers.size() == 0) {
            NodeCompat.setProperty(parent, Constants.PROP_SERVERS, null);
        }
    }

    /**
     * Returns true if a server with the same name already exists in the parent.
     * @param parent
     */
    private boolean serverAlreadyExists(Aai20Document parent) {
        return ModelUtils.isDefined(parent.servers.get(this._serverName));
    }
}
