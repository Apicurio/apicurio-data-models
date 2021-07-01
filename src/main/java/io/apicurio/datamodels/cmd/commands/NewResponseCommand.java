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
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasResponse;

/**
 * A command used to create a new response in an operation.
 * @author eric.wittmann@gmail.com
 */
public class NewResponseCommand extends AbstractCommand {

    public NodePath _operationPath;
    public String _statusCode;

    public boolean _created;
    public boolean _nullResponses;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object sourceResponse;
    
    NewResponseCommand() {
    }
    
    NewResponseCommand(OasOperation operation, String statusCode, OasResponse sourceResponse) {
        this._operationPath = Library.createNodePath(operation);
        this._statusCode = statusCode;
        if (ModelUtils.isDefined(sourceResponse)) {
            this.sourceResponse = Library.writeNode(sourceResponse);
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewResponseCommand] Executing.  Status Code=%s", this._statusCode);

        this._created = false;
        this._nullResponses = false;

        OasOperation operation = (OasOperation) this._operationPath.resolve(document);
        if (this.isNullOrUndefined(operation)) {
            return;
        }

        if (this.isNullOrUndefined(operation.responses)) {
            operation.responses = operation.createResponses();
            this._nullResponses = true;
        }

        OasResponse response = (OasResponse) operation.responses.getResponse(this._statusCode);
        if (this.isNullOrUndefined(response)) {
            response = (OasResponse) operation.responses.createResponse(this._statusCode);
            if (ModelUtils.isDefined(this.sourceResponse)) {
                Library.readNode(this.sourceResponse, response);
            }
            operation.responses.addResponse(this._statusCode, response);
            this._created = true;
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewResponseCommand] Reverting.");

        OasOperation operation = (OasOperation) this._operationPath.resolve(document);
        if (this.isNullOrUndefined(operation)) {
            return;
        }

        if (this._nullResponses) {
            operation.responses = null;
            return;
        }

        if (!this._created) {
            return;
        }

        operation.responses.removeResponse(this._statusCode);
    }
}
