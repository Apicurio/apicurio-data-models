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
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.Server;

/**
 * A command used to modify a aaiserver.
 * @author c.desc2@gmail.com
 */
public class ChangeServerCommand_Aai20 extends AbstractCommand {

    public String _serverName;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _serverObj;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldServer;

    ChangeServerCommand_Aai20() {
    }

    ChangeServerCommand_Aai20(AaiServer server) {
        this._serverName = server.getName();
        this._serverObj = Library.writeNode(server);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangeServerCommand_Aai20] Executing.");
        this._oldServer  = null;

        Aai20Document parent = (Aai20Document) document;
        if (this.isNullOrUndefined(parent.servers)) {
            return;
        }

        AaiServer server = parent.servers.get(_serverName);
        if (this.isNullOrUndefined(server)) {
            return;
        }

        // Back up the old server info (for undo)
        this._oldServer = Library.writeNode(server);

        // Replace with new server info
        this.replaceServerWith(server, this._serverObj);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangeServerCommand_Aai20] Reverting.");
        if (this.isNullOrUndefined(this._oldServer)) {
            return;
        }

        Aai20Document parent = (Aai20Document) document;
        if (this.isNullOrUndefined(parent.servers)) {
            return;
        }

        AaiServer server = parent.servers.get(_serverName);
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
