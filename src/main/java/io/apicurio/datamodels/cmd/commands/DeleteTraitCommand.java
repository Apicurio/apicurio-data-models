/*
 * Copyright 2019 JBoss Inc
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
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Message;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20MessageTrait;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20OperationTrait;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;

public class DeleteTraitCommand extends AbstractCommand {

    public int _traitIdx;
    public NodePath _messagePath;
    public NodePath _operationPath;


    @JsonDeserialize(using = MarshallCompat.NullableJsonNodeDeserializer.class)
    public Object _oldNode;

    DeleteTraitCommand() {
    }

    DeleteTraitCommand(AaiMessage message, int traitIdx) {
        this._messagePath = Library.createNodePath(message);
        this._traitIdx = traitIdx;
    }

    DeleteTraitCommand(AaiOperation operation, int traitIdx) {
        this._operationPath = Library.createNodePath(operation);
        this._traitIdx = traitIdx;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteTraitCommand] Executing.");

        if (!this.isNullOrUndefined(this._messagePath)) {
            AaiMessage message = (AaiMessage) this._messagePath.resolve(document);

            if (this.isNullOrUndefined(message)) {
                return;
            }

            Node res = message.traits.remove(this._traitIdx);

            if (!this.isNullOrUndefined(res)) {
                this._oldNode = Library.writeNode(res);
            }
        } else if (!this.isNullOrUndefined(this._operationPath)) {
            AaiOperation operation = (AaiOperation) this._operationPath.resolve(document);

            if (this.isNullOrUndefined(operation)) {
                return;
            }

            Node res = operation.traits.remove(this._traitIdx);

            if (!this.isNullOrUndefined(res)) {
                this._oldNode = Library.writeNode(res);
            }
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteTraitCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldNode)) {
            return;
        }
        
        if (!this.isNullOrUndefined(this._messagePath)) {
            AaiMessage message = (AaiMessage) this._messagePath.resolve(document);
            
            if (this.isNullOrUndefined(message)) {
                return;
            }

            AaiMessageTrait trait = new Aai20MessageTrait(message);
            Library.readNode(this._oldNode, trait);
            message.addTrait(trait);
            
        } else if (!this.isNullOrUndefined(this._operationPath)) {
            AaiOperation operation = (AaiOperation) this._operationPath.resolve(document);

            if (this.isNullOrUndefined(operation)) {
                return;
            }

            AaiOperationTrait trait = new Aai20OperationTrait(operation);
            Library.readNode(this._oldNode, trait);
            operation.addTrait(trait);
        }
    }

}
