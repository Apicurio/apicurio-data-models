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
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A command used to delete a single server from an aaidocument.
 * @author c.desc2@gmail.com
 */
public class DeleteServerCommand_Aai20 extends AbstractCommand {

    public String _serverName;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldServer;

    DeleteServerCommand_Aai20() {
    }

    DeleteServerCommand_Aai20(AaiServer server) {
        this._serverName = server.getName();
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteServerCommand_Aai20] Executing.");
        this._oldServer = null;

        Aai20Document parent = (Aai20Document) document;
        if (this.isNullOrUndefined(parent.servers)) {
            return;
        }

        AaiServer server = parent.servers.get(_serverName);
        if (this.isNullOrUndefined(server)) {
            return;
        }
        
        Map<String, AaiServer> servers = parent.servers;
        servers.remove(_serverName);
        if (servers.size() == 0) {
            NodeCompat.setProperty(parent, Constants.PROP_SERVERS, null);
        }

        this._oldServer = Library.writeNode(server);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteServerCommand_Aai20] Reverting.");
        if (this.isNullOrUndefined(this._oldServer)) {
            return;
        }

        Aai20Document parent = (Aai20Document) document;
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        AaiServer server = parent.createServer(_serverName);
        Library.readNode(this._oldServer, server);
        
        Map<String, AaiServer> servers = parent.servers;
        if (this.isNullOrUndefined(servers)) {
            servers = new LinkedHashMap<>();
            NodeCompat.setProperty(parent, Constants.PROP_SERVERS, servers);
        }
        servers.put(_serverName, server);
    }

}
