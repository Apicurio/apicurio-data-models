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
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;

/**
 * @author eric.wittmann@gmail.com
 */
public class RenameSchemaDefinitionCommand_20 extends RenameSchemaDefinitionCommand {

    RenameSchemaDefinitionCommand_20() {
    }
    
    RenameSchemaDefinitionCommand_20(String oldName, String newName) {
        super(oldName, newName);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.RenameSchemaDefinitionCommand#_nameToReference(java.lang.String)
     */
    @Override
    protected String _nameToReference(String name) {
        return "#/definitions/" + name;
    }
    
    /**
     * @see RenameSchemaDefinitionCommand#_renameSchemaDefinition(Document, String, String)
     */
    @Override
    protected boolean _renameSchemaDefinition(Document document, String fromName, String toName) {
        Oas20Document doc20 = (Oas20Document) document;
        if (this.isNullOrUndefined(doc20.definitions)) {
            return false;
        }
        if (ModelUtils.isDefined(doc20.definitions.getDefinition(toName))) {
            return false;
        }
        doc20.definitions.renameDefinition(fromName, toName, schemaDef -> schemaDef.rename(toName));
        return true;
    }

}
