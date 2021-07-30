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

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Operation;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody;

/**
 * A command used to delete an operation.
 * 
 * @author eric.wittmann@gmail.com
 */
public class DeleteRequestBodyCommand extends DeleteNodeCommand<Oas30RequestBody> {

    DeleteRequestBodyCommand() {
    }

    DeleteRequestBodyCommand(Oas30Operation operation) {
        super(Constants.PROP_REQUEST_BODY, operation);
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.DeleteNodeCommand#readNode(io.apicurio.datamodels.core.models.Document,
     *      java.lang.Object)
     */
    @Override
    protected Oas30RequestBody readNode(Document doc, Object node) {
        Oas30Operation operation = (Oas30Operation) this._parentPath.resolve(doc);
        Oas30RequestBody requestBody = operation.createRequestBody();
        Library.readNode(node, requestBody);
        return requestBody;
    }
}
