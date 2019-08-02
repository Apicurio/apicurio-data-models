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
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinition;

/**
 * OAI version 2.0 impl.
 * @author eric.wittmann@gmail.com
 */
public class AddResponseDefinitionCommand_20 extends AddResponseDefinitionCommand {

    public boolean _nullDefinitions;
    
    AddResponseDefinitionCommand_20() {
    }
    
    AddResponseDefinitionCommand_20(String definitionName) {
        super(definitionName);
    }

    AddResponseDefinitionCommand_20(String definitionName, Object from) {
        super(definitionName, from);
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.AddResponseDefinitionCommand#defExists(io.apicurio.datamodels.openapi.models.OasDocument)
     */
    @Override
    protected boolean defExists(OasDocument document) {
        Oas20Document doc20 = (Oas20Document) document;
        if (this.isNullOrUndefined(doc20.responses)) {
            return false;
        }
        return !this.isNullOrUndefined(doc20.responses.getResponse(this._newDefinitionName));
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.AddResponseDefinitionCommand#prepareDocumentForDef(io.apicurio.datamodels.openapi.models.OasDocument)
     */
    @Override
    protected void prepareDocumentForDef(OasDocument document) {
        Oas20Document doc20 = (Oas20Document) document;
        if (this.isNullOrUndefined(doc20.responses)) {
            doc20.responses = doc20.createResponseDefinitions();
            this._nullDefinitions = true;
        }

    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.AddResponseDefinitionCommand#createResponseDefinition(io.apicurio.datamodels.openapi.models.OasDocument)
     */
    @Override
    protected OasResponse createResponseDefinition(OasDocument document) {
        Oas20Document doc20 = (Oas20Document) document;
        Oas20ResponseDefinition definition = doc20.responses.createResponse(this._newDefinitionName);
        Library.readNode(this._newDefinitionObj, definition);
        return definition;
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.AddResponseDefinitionCommand#addDefinition(io.apicurio.datamodels.openapi.models.OasDocument, io.apicurio.datamodels.openapi.models.OasResponse)
     */
    @Override
    protected void addDefinition(OasDocument document, OasResponse definition) {
        Oas20Document doc20 = (Oas20Document) document;
        Oas20ResponseDefinition def20 = (Oas20ResponseDefinition) definition;
        doc20.responses.addResponse(this._newDefinitionName, def20);
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.AddResponseDefinitionCommand#removeDefinition(io.apicurio.datamodels.openapi.models.OasDocument)
     */
    @Override
    protected void removeDefinition(OasDocument document) {
        Oas20Document doc20 = (Oas20Document) document;
        if (this._nullDefinitions) {
            doc20.responses = null;
        } else {
            doc20.responses.removeResponse(this._newDefinitionName);
        }
    }

}
