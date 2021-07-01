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
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinition;

/**
 * @author eric.wittmann@gmail.com
 */
public class NewResponseDefinitionCommand_20 extends NewResponseDefinitionCommand {

    public boolean _nullResponses;
    
    NewResponseDefinitionCommand_20() {
    }
    
    NewResponseDefinitionCommand_20(String definitionName, String description) {
        this._newDefinitionName = definitionName;
        this._newDefinitionDescription = description;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewResponseDefinitionCommand] Executing.");
        Oas20Document doc20 = (Oas20Document) document;
        if (this.isNullOrUndefined(doc20.responses)) {
            doc20.responses = doc20.createResponseDefinitions();
            this._nullResponses = true;
        }

        if (this.isNullOrUndefined(doc20.responses.getResponse(this._newDefinitionName))) {
            Oas20ResponseDefinition responseDef = doc20.responses.createResponse(this._newDefinitionName);
            if (ModelUtils.isDefined(this._newDefinitionDescription)) {
                responseDef.description = this._newDefinitionDescription;
            }
            doc20.responses.addResponse(this._newDefinitionName, responseDef);

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
        Oas20Document doc20 = (Oas20Document) document;
        if (this._nullResponses) {
            doc20.responses = null;
        }

        if (this._defExisted) {
            return;
        }
        doc20.responses.removeResponse(this._newDefinitionName);
    }

}
