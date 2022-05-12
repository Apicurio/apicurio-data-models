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
import io.apicurio.datamodels.asyncapi.models.AaiSchema;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20NodeFactory;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.core.models.Document;

/**
 * @author c.desc2@gmail.com
 */
public class DeleteSchemaDefinitionCommand_Aai20 extends DeleteSchemaDefinitionCommand {

    public int _oldDefinitionIndex;

    DeleteSchemaDefinitionCommand_Aai20() {
    }
    
    DeleteSchemaDefinitionCommand_Aai20(String definitionName) {
        super(definitionName);
    }
    
    /**
     * @see DeleteSchemaDefinitionCommand#doDeleteSchemaDefinition(Document)
     */
    @Override
    protected Object doDeleteSchemaDefinition(Document document) {
        Aai20Document aai20Document = (Aai20Document) document;
        if (ModelUtils.isDefined(aai20Document.components)) {
            this._oldDefinitionIndex = aai20Document.components.getSchemaDefinitionNames()
                    .indexOf(this._definitionName);
            AaiSchema oldDef = aai20Document.components.removeSchemaDefinition(this._definitionName);
            return Library.writeNode(oldDef);
        }
        return null;
    }
    
    /**
     * @see DeleteSchemaDefinitionCommand#doRestoreSchemaDefinition(Document, Object)
     */
    @Override
    protected void doRestoreSchemaDefinition(Document document, Object oldDefinition) {
        Aai20Document aai20Document = (Aai20Document) document;
        if (ModelUtils.isDefined(aai20Document.components)) {
            AaiSchema schemaDef = new Aai20NodeFactory().createSchemaDefinition(aai20Document.components, this._definitionName);
            Library.readNode(oldDefinition, schemaDef);
            aai20Document.components.restoreSchemaDefinition(this._oldDefinitionIndex, this._definitionName, schemaDef);
        }
    }
}
