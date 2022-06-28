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
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30SchemaDefinition;

/**
 * @author eric.wittmann@gmail.com
 */
public class DeleteSchemaDefinitionCommand_30 extends DeleteSchemaDefinitionCommand {

    public Integer _oldDefinitionIndex; // nullable for backwards compatibility

    DeleteSchemaDefinitionCommand_30() {
    }
    
    DeleteSchemaDefinitionCommand_30(String definitionName) {
        super(definitionName);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.DeleteSchemaDefinitionCommand#doDeleteSchemaDefinition(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    protected Object doDeleteSchemaDefinition(Document document) {
        Oas30Document doc30 = (Oas30Document) document;
        if (ModelUtils.isDefined(doc30.components)) {
            this._oldDefinitionIndex = doc30.components.getSchemaDefinitionNames().indexOf(this._definitionName);
            Oas30SchemaDefinition oldDef = doc30.components.removeSchemaDefinition(this._definitionName);
            return Library.writeNode(oldDef);
        }
        return null;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.DeleteSchemaDefinitionCommand#doRestoreSchemaDefinition(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected void doRestoreSchemaDefinition(Document document, Object oldDefinition) {
        Oas30Document doc30 = (Oas30Document) document;
        if (ModelUtils.isDefined(doc30.components)) {
            Oas30SchemaDefinition schemaDef = doc30.components.createSchemaDefinition(this._definitionName);
            Library.readNode(oldDefinition, schemaDef);
            doc30.components.restoreSchemaDefinition(this._oldDefinitionIndex, this._definitionName, schemaDef);
        }
    }
}
