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
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * A command used to delete all operations from a path
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllOperationsCommand extends AbstractCommand {
    
    private static final String[] ALL_METHODS = new String[] {
        "get", "put", "post", "delete", "head", "patch", "options", "trace"
    };

    public NodePath _parentPath;
    @JsonDeserialize(contentUsing=NullableJsonNodeDeserializer.class)
    public List<Object> _oldOperations;
    
    DeleteAllOperationsCommand() {
    }
    
    DeleteAllOperationsCommand(OasPathItem parent) {
        this._parentPath = Library.createNodePath(parent);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllOperationsCommand] Executing.");
        this._oldOperations = new ArrayList<>();

        OasPathItem parent = (OasPathItem) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        // Save the old operations (if any)
        for (String method : ALL_METHODS) {
            OasOperation oldOp = (OasOperation) NodeCompat.getProperty(parent, method);
            if (!this.isNullOrUndefined(oldOp)) {
                Object oldOpData = JsonCompat.objectNode();
                JsonCompat.setPropertyString(oldOpData, "_method", method);
                JsonCompat.setProperty(oldOpData, "_operation", Library.writeNode(oldOp));
                this._oldOperations.add(oldOpData);
                NodeCompat.setProperty(parent, method, null);
            }
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteAllOperationsCommand] Reverting.");
        if (this._oldOperations == null || this._oldOperations.size() == 0) {
            return;
        }

        OasPathItem parent = (OasPathItem) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        for (Object oldOperationData : this._oldOperations) {
            String method = JsonCompat.getPropertyString(oldOperationData, "_method");
            OasOperation operation = parent.createOperation(method);
            Library.readNode(JsonCompat.getProperty(oldOperationData, "_operation"), operation);
            NodeCompat.setProperty(parent, method, operation);
        }
    }

}
