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

import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20SchemaDefinition;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.core.models.Document;

/**
 * @author c.desc2@gmail.com
 */
public class RenameSchemaDefinitionCommand_Aai20 extends RenameSchemaDefinitionCommand {

    RenameSchemaDefinitionCommand_Aai20() {
    }
    
    RenameSchemaDefinitionCommand_Aai20(String oldName, String newName) {
        super(oldName, newName);
    }
    
    /**
     * @see RenameSchemaDefinitionCommand#_nameToReference(String)
     */
    @Override
    protected String _nameToReference(String name) {
        return "#/components/schemas/" + name;
    }
    
    /**
     * @see RenameSchemaDefinitionCommand#_renameSchemaDefinition(Document, String, String)
     */
    @Override
    protected boolean _renameSchemaDefinition(Document document, String fromName, String toName) {
        Aai20Document aai20Document = (Aai20Document) document;
        if (this.isNullOrUndefined(aai20Document.components) || this.isNullOrUndefined(aai20Document.components.schemas)) {
            return false;
        }
        if (ModelUtils.isDefined(aai20Document.components.getSchemaDefinition(toName))) {
            return false;
        }
        Aai20SchemaDefinition schemaDef = (Aai20SchemaDefinition) aai20Document.components.removeSchemaDefinition(fromName);
        schemaDef.rename(toName);
        aai20Document.components.addSchemaDefinition(toName, schemaDef);
        return true;
    }

}
