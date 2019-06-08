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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;

/**
 * A command used to delete a schema definition.
 * @author eric.wittmann@gmail.com
 */
public abstract class DeleteSchemaDefinitionCommand extends AbstractCommand {

    public String _definitionName;

    @JsonDeserialize(using=JsonNodeDeserializer.class)
    public Object _oldDefinition;
    
    DeleteSchemaDefinitionCommand() {
    }
    
    DeleteSchemaDefinitionCommand(String definitionName) {
        this._definitionName = definitionName;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteDefinitionSchemaCommand] Executing.");
        this._oldDefinition = this.doDeleteSchemaDefinition(document);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteDefinitionSchemaCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldDefinition)) {
            return;
        }

        this.doRestoreSchemaDefinition(document, this._oldDefinition);
    }

    /**
     * Deletes the schema definition.
     */
    protected abstract Object doDeleteSchemaDefinition(Document document);

    /**
     * Restores the schema definition previously deleted.
     */
    protected abstract void doRestoreSchemaDefinition(Document document, Object oldDefinition);
}
