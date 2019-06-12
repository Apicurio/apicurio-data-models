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
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20Definitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20SchemaDefinition;

/**
 * @author eric.wittmann@gmail.com
 */
public class DeleteSchemaDefinitionCommand_20 extends DeleteSchemaDefinitionCommand {
    
    DeleteSchemaDefinitionCommand_20() {
    }
    
    DeleteSchemaDefinitionCommand_20(String definitionName) {
        super(definitionName);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.DeleteSchemaDefinitionCommand#doDeleteSchemaDefinition(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    protected Object doDeleteSchemaDefinition(Document document) {
        Oas20Document doc20 = (Oas20Document) document;
        Oas20Definitions definitions = doc20.definitions;
        if (this.isNullOrUndefined(definitions)) {
            return null;
        }
        Oas20SchemaDefinition schemaDef = definitions.removeDefinition(this._definitionName);
        return Library.writeNode(schemaDef);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.DeleteSchemaDefinitionCommand#doRestoreSchemaDefinition(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected void doRestoreSchemaDefinition(Document document, Object oldDefinition) {
        Oas20Document doc20 = (Oas20Document) document;
        Oas20Definitions definitions = doc20.definitions;
        if (this.isNullOrUndefined(definitions)) {
            return;
        }

        Oas20SchemaDefinition definition = doc20.definitions.createSchemaDefinition(this._definitionName);
        Library.readNode(oldDefinition, definition);
        definitions.addDefinition(this._definitionName, definition);
    }

}
