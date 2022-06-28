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
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;

/**
 * @author eric.wittmann@gmail.com
 */
public class RenameResponseDefinitionCommand_30 extends RenameResponseDefinitionCommand {

    RenameResponseDefinitionCommand_30() {
    }
    
    RenameResponseDefinitionCommand_30(String oldName, String newName) {
        super(oldName, newName);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.RenameResponseDefinitionCommand#_nameToReference(java.lang.String)
     */
    @Override
    protected String _nameToReference(String name) {
        return "#/components/responses/" + name;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.RenameResponseDefinitionCommand#_renameResponseDefinition(io.apicurio.datamodels.openapi.models.OasDocument, java.lang.String, java.lang.String)
     */
    @Override
    protected boolean _renameResponseDefinition(OasDocument document, String fromName, String toName) {
        Oas30Document doc30 = (Oas30Document) document;
        if (this.isNullOrUndefined(doc30.components) || this.isNullOrUndefined(doc30.components.responses)) {
            return false;
        }
        if (ModelUtils.isDefined(doc30.components.getResponseDefinition(toName))) {
            return false;
        }
        doc30.components.renameResponseDefinition(fromName, toName, responseDef -> responseDef.rename(toName));
        return true;
    }

}
