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
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;

/**
 * @author eric.wittmann@gmail.com
 */
public class RenameResponseDefinitionCommand_20 extends RenameResponseDefinitionCommand {

    RenameResponseDefinitionCommand_20() {
    }
    
    RenameResponseDefinitionCommand_20(String oldName, String newName) {
        super(oldName, newName);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.RenameResponseDefinitionCommand#_nameToReference(java.lang.String)
     */
    @Override
    protected String _nameToReference(String name) {
        return "#/responses/" + name;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.RenameResponseDefinitionCommand#_renameResponseDefinition(io.apicurio.datamodels.openapi.models.OasDocument, java.lang.String, java.lang.String)
     */
    @Override
    protected boolean _renameResponseDefinition(OasDocument document, String fromName, String toName) {
        Oas20Document doc20 = (Oas20Document) document;
        if (this.isNullOrUndefined(doc20.responses)) {
            return false;
        }
        if (ModelUtils.isDefined(doc20.responses.getResponse(toName))) {
            return false;
        }
        doc20.responses.renameResponse(fromName, toName, responseDef -> responseDef.rename(toName));
        return true;
    }

}
