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
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20SchemaDefinition;

/**
 * OAI version 2.0 impl.
 * @author eric.wittmann@gmail.com
 */
public class AddSchemaDefinitionCommand_20 extends AddSchemaDefinitionCommand {

    public boolean _nullDefinitions;
    
    AddSchemaDefinitionCommand_20() {
    }
    
    AddSchemaDefinitionCommand_20(String definitionName) {
        super(definitionName);
    }

    AddSchemaDefinitionCommand_20(String definitionName, Object from) {
        super(definitionName, from);
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.AddSchemaDefinitionCommand#defExists(io.apicurio.datamodels.openapi.models.OasDocument)
     */
    @Override
    protected boolean defExists(OasDocument document) {
        Oas20Document doc20 = (Oas20Document) document;
        if (this.isNullOrUndefined(doc20.definitions)) {
            return false;
        }
        return !this.isNullOrUndefined(doc20.definitions.getDefinition(this._newDefinitionName));
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.AddSchemaDefinitionCommand#prepareDocumentForDef(io.apicurio.datamodels.openapi.models.OasDocument)
     */
    @Override
    protected void prepareDocumentForDef(OasDocument document) {
        Oas20Document doc20 = (Oas20Document) document;
        if (this.isNullOrUndefined(doc20.definitions)) {
            doc20.definitions = doc20.createDefinitions();
            this._nullDefinitions = true;
        }

    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.AddSchemaDefinitionCommand#createSchemaDefinition(io.apicurio.datamodels.openapi.models.OasDocument)
     */
    @Override
    protected OasSchema createSchemaDefinition(OasDocument document) {
        Oas20Document doc20 = (Oas20Document) document;
        Oas20SchemaDefinition definition = doc20.definitions.createSchemaDefinition(this._newDefinitionName);
        Library.readNode(this._newDefinitionObj, definition);
        return definition;
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.AddSchemaDefinitionCommand#addDefinition(io.apicurio.datamodels.openapi.models.OasDocument, io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    protected void addDefinition(OasDocument document, OasSchema definition) {
        Oas20Document doc20 = (Oas20Document) document;
        Oas20SchemaDefinition def20 = (Oas20SchemaDefinition) definition;
        doc20.definitions.addDefinition(this._newDefinitionName, def20);
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.AddSchemaDefinitionCommand#removeDefinition(io.apicurio.datamodels.openapi.models.OasDocument)
     */
    @Override
    protected void removeDefinition(OasDocument document) {
        Oas20Document doc20 = (Oas20Document) document;
        if (this._nullDefinitions) {
            doc20.definitions = null;
        } else {
            doc20.definitions.removeDefinition(this._newDefinitionName);
        }
    }

}
