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
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30SchemaDefinition;

/**
 * OAI version 3.0.x impl.
 * @author eric.wittmann@gmail.com
 */
public class AddSchemaDefinitionCommand_30 extends AddSchemaDefinitionCommand {

    public boolean _nullComponents;
    
    AddSchemaDefinitionCommand_30() {
    }
    
    AddSchemaDefinitionCommand_30(String definitionName) {
        super(definitionName);
    }

    AddSchemaDefinitionCommand_30(String definitionName, Object from) {
        super(definitionName, from);
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.AddSchemaDefinitionCommand#defExists(io.apicurio.datamodels.openapi.models.OasDocument)
     */
    @Override
    protected boolean defExists(OasDocument document) {
        Oas30Document doc30 = (Oas30Document) document;
        if (this.isNullOrUndefined(doc30.components)) {
            return false;
        }
        return !this.isNullOrUndefined(doc30.components.getSchemaDefinition(this._newDefinitionName));
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.AddSchemaDefinitionCommand#prepareDocumentForDef(io.apicurio.datamodels.openapi.models.OasDocument)
     */
    @Override
    protected void prepareDocumentForDef(OasDocument document) {
        Oas30Document doc30 = (Oas30Document) document;
        if (this.isNullOrUndefined(doc30.components)) {
            doc30.components = doc30.createComponents();
            this._nullComponents = true;
        } else {
            this._nullComponents = false;
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.AddSchemaDefinitionCommand#createSchemaDefinition(io.apicurio.datamodels.openapi.models.OasDocument)
     */
    @Override
    protected OasSchema createSchemaDefinition(OasDocument document) {
        Oas30Document doc30 = (Oas30Document) document;
        Oas30SchemaDefinition definition = doc30.components.createSchemaDefinition(this._newDefinitionName);
        Library.readNode(this._newDefinitionObj, definition);
        return definition;
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.AddSchemaDefinitionCommand#addDefinition(io.apicurio.datamodels.openapi.models.OasDocument, io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    protected void addDefinition(OasDocument document, OasSchema definition) {
        Oas30Document doc30 = (Oas30Document) document;
        doc30.components.addSchemaDefinition(this._newDefinitionName, (Oas30SchemaDefinition) definition);
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.AddSchemaDefinitionCommand#removeDefinition(io.apicurio.datamodels.openapi.models.OasDocument)
     */
    @Override
    protected void removeDefinition(OasDocument document) {
        Oas30Document doc30 = (Oas30Document) document;
        if (this._nullComponents) {
            doc30.components = null;
        } else {
            doc30.components.removeSchemaDefinition(this._newDefinitionName);
        }
    }

}
