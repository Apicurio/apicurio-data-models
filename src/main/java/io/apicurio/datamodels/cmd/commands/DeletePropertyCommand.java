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

import java.util.ArrayList;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.IOasPropertySchema;
import io.apicurio.datamodels.openapi.models.OasSchema;

/**
 * A command used to delete a single property from a schema.
 * @author eric.wittmann@gmail.com
 */
public class DeletePropertyCommand extends AbstractCommand {

    public String _propertyName;
    public NodePath _propertyPath;
    public NodePath _schemaPath;

    public Object _oldProperty;
    public boolean _oldRequired;
    
    DeletePropertyCommand() {
    }
    
    DeletePropertyCommand(IOasPropertySchema property) {
        this._propertyName = property.getPropertyName();
        this._propertyPath = Library.createNodePath((Node) property);
        this._schemaPath = Library.createNodePath(((Node) property).parent());
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeletePropertyCommand] Executing.");
        this._oldProperty = null;

        IOasPropertySchema property = (IOasPropertySchema) this._propertyPath.resolve(document);
        if (this.isNullOrUndefined(property)) {
            return;
        }

        OasSchema schema = (OasSchema) ((Node) property).parent();
        this._oldProperty = Library.writeNode(schema.removeProperty(this._propertyName));
        this._oldRequired = ModelUtils.isDefined(schema.required) && schema.required.indexOf(this._propertyName) != -1;
        if (this._oldRequired) {
            schema.required.remove(schema.required.indexOf(this._propertyName));
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeletePropertyCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldProperty)) {
            return;
        }

        OasSchema schema = (OasSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            return;
        }

        OasSchema propSchema = schema.createPropertySchema(this._propertyName);
        Library.readNode(this._oldProperty, propSchema);
        schema.addProperty(this._propertyName, propSchema);
        if (this._oldRequired) {
            if (this.isNullOrUndefined(schema.required)) {
                schema.required = new ArrayList<>();
            }
            schema.required.add(this._propertyName);
        }
    }

}
