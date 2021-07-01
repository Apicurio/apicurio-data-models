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

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.models.SimplifiedType;
import io.apicurio.datamodels.cmd.util.SimplifiedTypeUtil;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.OasSchema;

/**
 * A command used to modify the type of a schema.
 * @author eric.wittmann@gmail.com
 */
public class ChangeSchemaTypeCommand extends AbstractCommand {
    
    public NodePath _schemaPath;
    public SimplifiedType _newType;
    public SimplifiedType _oldType;
    
    ChangeSchemaTypeCommand() {
    }

    ChangeSchemaTypeCommand(OasSchema schema, SimplifiedType newType) {
        this._schemaPath = Library.createNodePath(schema);
        this._newType = newType;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangeSchemaTypeCommand] Executing: " + this._newType);
        this._oldType = null;
        
        OasSchema schema = (OasSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            return;
        }
        
        // Save the old info (for later undo operation)
        this._oldType = SimplifiedType.fromSchema(schema);

        // Update the schema's type
        SimplifiedTypeUtil.setSimplifiedType((OasSchema) schema, this._newType);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangeSchemaTypeCommand] Reverting.");
        if (this._oldType == null) {
            return;
        }
        
        OasSchema schema = (OasSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            return;
        }

        SimplifiedTypeUtil.setSimplifiedType((OasSchema) schema, this._oldType);
    }

}
