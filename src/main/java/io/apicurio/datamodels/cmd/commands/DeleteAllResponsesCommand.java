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
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasResponses;

/**
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllResponsesCommand extends DeleteNodeCommand<OasResponses> {
    
    DeleteAllResponsesCommand() {
    }

    DeleteAllResponsesCommand(String property, OasOperation operation) {
        super(property, operation);
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.DeleteNodeCommand#readNode(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected OasResponses readNode(Document doc, Object node) {
        OasOperation operation = (OasOperation) this._parentPath.resolve(doc);
        OasResponses responses = operation.createResponses();
        Library.readNode(node, responses);
        return responses;
    }

}
