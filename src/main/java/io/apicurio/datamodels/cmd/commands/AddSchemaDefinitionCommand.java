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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasSchema;

/**
 * A command used to add a new definition in a document.  Source for the new
 * definition must be provided.  This source will be converted to an OAS
 * definition object and then added to the data model.
 * @author eric.wittmann@gmail.com
 */
public abstract class AddSchemaDefinitionCommand extends AbstractCommand {

    public boolean _defExisted;
    public String _newDefinitionName;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _newDefinitionObj;
    
    AddSchemaDefinitionCommand() {
    }
    
    AddSchemaDefinitionCommand(String definitionName) {
        this._newDefinitionName = definitionName;
    }
    
    AddSchemaDefinitionCommand(String definitionName, Object obj) {
        this._newDefinitionName = definitionName;
        this._newDefinitionObj = obj;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[AddSchemaDefinitionCommand] Executing.");
        
        OasDocument doc = (OasDocument) document;

        // Do nothing if the definition already exists.
        if (this.defExists(doc)) {
            LoggerCompat.info("[AddSchemaDefinitionCommand] Definition with name %s already exists.", this._newDefinitionName);
            this._defExisted = true;
            return;
        }

        this.prepareDocumentForDef(doc);

        OasSchema definition = this.createSchemaDefinition(doc);
        this.addDefinition(doc, definition);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[AddSchemaDefinitionCommand] Reverting.");
        if (this._defExisted) {
            return;
        }

        OasDocument doc = (OasDocument) document;

        this.removeDefinition(doc);
    }

    protected abstract boolean defExists(OasDocument document);

    protected abstract void prepareDocumentForDef(OasDocument document);

    protected abstract OasSchema createSchemaDefinition(OasDocument document);

    protected abstract void addDefinition(OasDocument document, OasSchema definition);

    protected abstract void removeDefinition(OasDocument document);
}
