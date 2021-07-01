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
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.OasOperation;

/**
 * A command used to create a new request body (parameter of an operation).
 * @author eric.wittmann@gmail.com
 */
public abstract class NewRequestBodyCommand extends AbstractCommand {

    public NodePath _operationPath;
    
    public boolean _created;

    NewRequestBodyCommand() {
    }
    
    NewRequestBodyCommand(OasOperation operation) {
        this._operationPath = Library.createNodePath(operation);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewRequestBodyCommand] Executing.");

        this._created = false;

        OasOperation operation = (OasOperation) this._operationPath.resolve(document);
        if (this.isNullOrUndefined(operation)) {
            return;
        }
        if (this.hasRequestBody(operation)) {
            return;
        }

        this.doCreateRequestBody(operation);
        this._created = true;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewRequestBodyCommand] Reverting.");
        if (!this._created) {
            return;
        }

        OasOperation operation = (OasOperation) this._operationPath.resolve(document);
        if (this.isNullOrUndefined(operation)) {
            return;
        }

        this.doRemoveRequestBody(operation);
    }

    /**
     * Returns true if the given operation already has a body parameter.
     * @param operation
     */
    protected abstract boolean hasRequestBody(OasOperation operation);

    /**
     * Creates an empty request body for the given operation.
     * @param operation
     */
    protected abstract void doCreateRequestBody(OasOperation operation);

    /**
     * Removes the request body.
     * @param operation
     */
    protected abstract void doRemoveRequestBody(OasOperation operation);

}
