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

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * A command used to delete an operation.
 * @author eric.wittmann@gmail.com
 */
public class DeleteOperationCommand extends DeleteNodeCommand<Operation> {

    DeleteOperationCommand() {
    }
    
    DeleteOperationCommand(String opMethod, OasPathItem pathItem) {
        super(opMethod, pathItem);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.DeleteNodeCommand#readNode(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected Operation readNode(Document doc, Object node) {
        OasPathItem pathItem = (OasPathItem) this._parentPath.resolve(doc);
        Operation operation = pathItem.createOperation(this._property);
        Library.readNode(node, operation);
        return operation;
    }
    
}
