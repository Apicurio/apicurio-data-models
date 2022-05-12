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
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinition;
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinitions;

/**
 * @author eric.wittmann@gmail.com
 */
public class DeleteResponseDefinitionCommand_20 extends DeleteResponseDefinitionCommand {
    
    public int _oldResponseIndex;

    DeleteResponseDefinitionCommand_20() {
    }
    
    DeleteResponseDefinitionCommand_20(String definitionName) {
        super(definitionName);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.DeleteResponseDefinitionCommand#doDeleteResponseDefinition(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    protected Object doDeleteResponseDefinition(Document document) {
        Oas20Document doc20 = (Oas20Document) document;
        Oas20ResponseDefinitions responses = doc20.responses;
        if (this.isNullOrUndefined(responses) || ModelUtils.isNullOrUndefined(responses.getResponse(this._definitionName))) {
            return null;
        }
        _oldResponseIndex = responses.getResponseNames().indexOf(this._definitionName);
        Oas20ResponseDefinition schemaDef = responses.removeResponse(this._definitionName);
        return Library.writeNode(schemaDef);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.DeleteResponseDefinitionCommand#doRestoreResponseDefinition(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected void doRestoreResponseDefinition(Document document, Object oldDefinition) {
        Oas20Document doc20 = (Oas20Document) document;
        Oas20ResponseDefinitions responses = doc20.responses;
        if (this.isNullOrUndefined(responses)) {
            return;
        }

        Oas20ResponseDefinition definition = doc20.responses.createResponse(this._definitionName);
        Library.readNode(oldDefinition, definition);
        responses.restoreResponseDefinition(this._oldResponseIndex, this._definitionName, definition);
    }

}
