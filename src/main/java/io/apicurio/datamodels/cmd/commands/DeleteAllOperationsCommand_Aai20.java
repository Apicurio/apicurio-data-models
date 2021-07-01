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
import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20NodeFactory;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;

import java.util.ArrayList;
import java.util.List;

/**
 * A command used to delete all operations from an aai channel.
 * @author c.desc2@gmail.com
 */
public class DeleteAllOperationsCommand_Aai20 extends AbstractCommand {
    
    private static final String[] ALL_TYPES = new String[] {
        "publish", "subscribe"
    };

    public NodePath _parentPath;
    @JsonDeserialize(contentUsing=NullableJsonNodeDeserializer.class)
    public List<Object> _oldOperations;
    
    DeleteAllOperationsCommand_Aai20() {
    }
    
    DeleteAllOperationsCommand_Aai20(AaiChannelItem parent) {
        this._parentPath = Library.createNodePath(parent);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllOperationsCommand_Aai20] Executing.");
        this._oldOperations = new ArrayList<>();

        AaiChannelItem parentChannel = (AaiChannelItem) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parentChannel)) {
            return;
        }

        // Save the old operations (if any)
        for (String operationType : ALL_TYPES) {
            AaiOperation oldOp = (AaiOperation) NodeCompat.getProperty(parentChannel, operationType);
            if (!this.isNullOrUndefined(oldOp)) {
                Object oldOpData = JsonCompat.objectNode();
                JsonCompat.setPropertyString(oldOpData, "_type", operationType);
                JsonCompat.setProperty(oldOpData, "_operation", Library.writeNode(oldOp));
                this._oldOperations.add(oldOpData);
                NodeCompat.setProperty(parentChannel, operationType, null);
            }
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteAllOperationsCommand_Aai20] Reverting.");
        if (this._oldOperations == null || this._oldOperations.size() == 0) {
            return;
        }

        AaiChannelItem parentChannel = (AaiChannelItem) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parentChannel)) {
            return;
        }

        final Aai20NodeFactory nodeFactory = new Aai20NodeFactory();

        for (Object oldOperationData : this._oldOperations) {
            String operationType = JsonCompat.getPropertyString(oldOperationData, "_type");
            AaiOperation operation = nodeFactory.createOperation(parentChannel, operationType);
            Library.readNode(JsonCompat.getProperty(oldOperationData, "_operation"), operation);
            NodeCompat.setProperty(parentChannel, operationType, operation);
        }
    }

}
