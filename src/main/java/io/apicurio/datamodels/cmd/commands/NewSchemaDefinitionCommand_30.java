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

import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.factories.OasSchemaFactory;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30SchemaDefinition;

/**
 * @author eric.wittmann@gmail.com
 */
public class NewSchemaDefinitionCommand_30 extends NewSchemaDefinitionCommand {

    public boolean _nullComponents;

    NewSchemaDefinitionCommand_30() {
    }
    
    NewSchemaDefinitionCommand_30(String definitionName, Object example, String description) {
        this._newDefinitionName = definitionName;
        this._newDefinitionExample = example;
        this._newDefinitionDescription = description;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewDefinitionCommand] Executing.");

        Oas30Document doc30 = (Oas30Document) document;
        if (this.isNullOrUndefined(doc30.components)) {
            doc30.components = doc30.createComponents();
            this._nullComponents = true;
        }
        this._nullComponents = false;

        if (this.isNullOrUndefined(doc30.components.getSchemaDefinition(this._newDefinitionName))) {
            Oas30SchemaDefinition definition;
            if (!this.isNullOrUndefined(this._newDefinitionExample)) {
                definition = (Oas30SchemaDefinition) OasSchemaFactory.createSchemaDefinitionFromExample(doc30, 
                        this._newDefinitionName, this._newDefinitionExample);
                definition.example = this._newDefinitionExample;
            } else {
                definition = doc30.components.createSchemaDefinition(this._newDefinitionName);
                definition.type = "object";
            }
            if (ModelUtils.isDefined(this._newDefinitionDescription)) {
                definition.description = this._newDefinitionDescription;
            }
            doc30.components.addSchemaDefinition(this._newDefinitionName, definition);

            this._defExisted = false;
        } else {
            this._defExisted = true;
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewDefinitionCommand] Reverting.");
        Oas30Document doc30 = (Oas30Document) document;

        if (this._nullComponents) {
            doc30.components = null;
        }
        if (this._defExisted) {
            return;
        }

        doc30.components.removeSchemaDefinition(this._newDefinitionName);
    }

}
