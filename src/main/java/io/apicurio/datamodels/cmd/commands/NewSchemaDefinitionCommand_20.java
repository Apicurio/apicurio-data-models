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
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20SchemaDefinition;

/**
 * @author eric.wittmann@gmail.com
 */
public class NewSchemaDefinitionCommand_20 extends NewSchemaDefinitionCommand {

    public boolean _nullDefinitions;
    
    NewSchemaDefinitionCommand_20() {
    }
    
    NewSchemaDefinitionCommand_20(String definitionName, Object example, String description) {
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
        Oas20Document doc20 = (Oas20Document) document;
        if (this.isNullOrUndefined(doc20.definitions)) {
            doc20.definitions = doc20.createDefinitions();
            this._nullDefinitions = true;
        }

        if (this.isNullOrUndefined(doc20.definitions.getDefinition(this._newDefinitionName))) {
            Oas20SchemaDefinition definition;
            if (!this.isNullOrUndefined(this._newDefinitionExample)) {
                definition = (Oas20SchemaDefinition) OasSchemaFactory.createSchemaDefinitionFromExample(doc20, 
                        this._newDefinitionName, this._newDefinitionExample);
                definition.example = this._newDefinitionExample;
            } else {
                definition = doc20.definitions.createSchemaDefinition(this._newDefinitionName);
                definition.type = "object";
            }
            if (ModelUtils.isDefined(this._newDefinitionDescription)) {
                definition.description = this._newDefinitionDescription;
            }
            doc20.definitions.addDefinition(this._newDefinitionName, definition);

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
        Oas20Document doc20 = (Oas20Document) document;
        if (this._nullDefinitions) {
            doc20.definitions = null;
        }

        if (this._defExisted) {
            return;
        }
        doc20.definitions.removeDefinition(this._newDefinitionName);
    }

}
