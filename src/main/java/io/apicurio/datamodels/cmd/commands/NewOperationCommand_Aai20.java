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

import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20NodeFactory;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;

/**
 * A command used to create a new operation in a channel.
 * @author laurent.broudoux@gmail.com
 */
public class NewOperationCommand_Aai20 extends AbstractCommand {

    public String _channel;
    public String _opType;

    public boolean _created;

    NewOperationCommand_Aai20() {
    }

    NewOperationCommand_Aai20(String _channel, String opType) {
        this._channel = _channel;
        this._opType = opType;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewOperationCommand_Aai20] Executing.");

        AaiDocument adoc = (AaiDocument) document;

        this._created = false;

        if (this.isNullOrUndefined(adoc.channels)) {
            return;
        }

        AaiChannelItem channel = adoc.channels.get(this._channel);
        if (this.isNullOrUndefined(channel)) {
            return;
        }

        if (!"subscribe".equals(this._opType) && !"publish".equals(this._opType)) {
            return;
        }

        Aai20NodeFactory nodeFactory = new Aai20NodeFactory();
        AaiOperation operation = nodeFactory.createOperation(channel, this._opType);
        operation.message = nodeFactory.createMessage(operation, null);
        operation.message.payload = JsonCompat.objectNode();
        switch (this._opType) {
            case "subscribe":
                channel.subscribe = operation;
                break;
            case "publish":
                channel.publish = operation;
                break;
        }
        this._created = true;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewOperationCommand_Aai20] Reverting.");
        if (!this._created) {
            return;
        }

        AaiDocument adoc = (AaiDocument) document;

        if (this.isNullOrUndefined(adoc.channels)) {
            return;
        }

        AaiChannelItem channel = adoc.channels.get(this._channel);
        if (this.isNullOrUndefined(channel)) {
            return;
        }

        NodeCompat.setProperty(channel, this._opType, null);
    }
}
