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
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30ResponseDefinition;

/**
 * @author eric.wittmann@gmail.com
 */
public class NewResponseDefinitionCommand_30 extends NewResponseDefinitionCommand {

    public boolean _nullComponents;

    NewResponseDefinitionCommand_30() {
    }

    NewResponseDefinitionCommand_30(String definitionName, String description) {
        this._newDefinitionName = definitionName;
        this._newDefinitionDescription = description;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewResponseDefinitionCommand] Executing.");

        Oas30Document doc30 = (Oas30Document) document;
        if (this.isNullOrUndefined(doc30.components)) {
            doc30.components = doc30.createComponents();
            this._nullComponents = true;
        }
        this._nullComponents = false;

        if (this.isNullOrUndefined(doc30.components.getResponseDefinition(this._newDefinitionName))) {
            Oas30ResponseDefinition definition = doc30.components.createResponseDefinition(this._newDefinitionName);
            if (ModelUtils.isDefined(this._newDefinitionDescription)) {
                definition.description = this._newDefinitionDescription;
            }
            doc30.components.addResponseDefinition(this._newDefinitionName, definition);

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
        LoggerCompat.info("[NewResponseDefinitionCommand] Reverting.");
        Oas30Document doc30 = (Oas30Document) document;

        if (this._nullComponents) {
            doc30.components = null;
        }
        if (this._defExisted) {
            return;
        }

        doc30.components.removeResponseDefinition(this._newDefinitionName);
    }

}
