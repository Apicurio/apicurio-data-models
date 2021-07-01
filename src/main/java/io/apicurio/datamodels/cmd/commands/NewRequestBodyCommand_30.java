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

import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.v3.models.Oas30Operation;

/**
 * @author eric.wittmann@gmail.com
 */
public class NewRequestBodyCommand_30 extends NewRequestBodyCommand {
    

    NewRequestBodyCommand_30() {
    }
    
    NewRequestBodyCommand_30(OasOperation operation) {
        super(operation);
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.NewRequestBodyCommand#hasRequestBody(io.apicurio.datamodels.openapi.models.OasOperation)
     */
    @Override
    protected boolean hasRequestBody(OasOperation operation) {
        Oas30Operation op30 = (Oas30Operation) operation;
        return !this.isNullOrUndefined(op30.requestBody);
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.NewRequestBodyCommand#doCreateRequestBody(io.apicurio.datamodels.openapi.models.OasOperation)
     */
    @Override
    protected void doCreateRequestBody(OasOperation operation) {
        Oas30Operation op30 = (Oas30Operation) operation;
        op30.requestBody = op30.createRequestBody();
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.NewRequestBodyCommand#doRemoveRequestBody(io.apicurio.datamodels.openapi.models.OasOperation)
     */
    @Override
    protected void doRemoveRequestBody(OasOperation operation) {
        Oas30Operation op30 = (Oas30Operation) operation;
        op30.requestBody = null;
    }

}
