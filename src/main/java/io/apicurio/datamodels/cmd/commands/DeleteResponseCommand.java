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
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.models.OasResponses;

/**
 * A command used to delete a single response from an operation.
 * @author eric.wittmann@gmail.com
 */
public class DeleteResponseCommand extends AbstractCommand {
    // TODO: Ordering on undo needs to be preserved
    
    public String _responseCode;
    public NodePath _responsePath;
    public NodePath _responsesPath;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldResponse;
    
    DeleteResponseCommand() {
    }

    DeleteResponseCommand(OasResponse response) {
        this._responseCode = response.getStatusCode();
        this._responsePath = Library.createNodePath(response);
        this._responsesPath = Library.createNodePath(response.parent());
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteResponseCommand] Executing.");
        this._oldResponse = null;

        OasResponse response = (OasResponse) this._responsePath.resolve(document);
        if (this.isNullOrUndefined(response)) {
            return;
        }

        OasResponses responses = (OasResponses) response.parent();
        if (this.isNullOrUndefined(this._responseCode)) {
            responses.default_ = null;
        } else {
            responses.removeResponse(this._responseCode);
        }

        this._oldResponse = Library.writeNode(response);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteResponseCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldResponse)) {
            return;
        }

        OasResponses responses = (OasResponses) this._responsesPath.resolve(document);
        if (this.isNullOrUndefined(responses)) {
            return;
        }

        OasResponse response = responses.createResponse(this._responseCode);
        Library.readNode(this._oldResponse, response);
        if (this.isNullOrUndefined(this._responseCode)) {
            responses.default_ = response;
        } else {
            responses.addResponse(this._responseCode, response);
        }
    }

}
