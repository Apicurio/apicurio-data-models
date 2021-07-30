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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A command used to delete all servers from an aai20document.
 * 
 * @author c.desc2@gmail.com
 */
public class DeleteAllServersCommand_Aai20 extends AbstractCommand {

    @JsonDeserialize(contentUsing=NullableJsonNodeDeserializer.class)
    public Map<String, Object> _oldServers;

    DeleteAllServersCommand_Aai20() {
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllServersCommand_Aai20] Executing.");
        this._oldServers = new LinkedHashMap<>();

        Aai20Document parent = (Aai20Document) document;
        if (this.isNullOrUndefined(parent.servers)) {
            return;
        }

        // Save the old servers (if any)
        Map<String, AaiServer> servers = parent.servers;
        if (!this.isNullOrUndefined(servers)) {
            servers.keySet().forEach( serverName -> {
                this._oldServers.put(serverName, Library.writeNode(servers.get(serverName)));
            });
        }

        NodeCompat.setProperty(parent, Constants.PROP_SERVERS, new LinkedHashMap<>());
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteAllServersCommand_Aai20] Reverting.");
        if (this._oldServers.size() == 0) {
            return;
        }

        Aai20Document parent = (Aai20Document) document;
        if (this.isNullOrUndefined(parent.servers)) {
            return;
        }

        Map<String, AaiServer> servers = parent.servers;
        if (this.isNullOrUndefined(servers)) {
            servers = new LinkedHashMap<>();
            NodeCompat.setProperty(parent, Constants.PROP_SERVERS, servers);
        }
        this._oldServers.keySet().forEach( serverName -> {
            AaiServer server = parent.createServer(serverName);
            Library.readNode(this._oldServers.get(serverName), server);
            parent.servers.put(serverName, server);
        });
    }
}
