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

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.models.SimplifiedPropertyType;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.cmd.util.SimplifiedTypeUtil;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.openapi.models.IOasPropertySchema;
import io.apicurio.datamodels.openapi.models.OasSchema;

/**
 * A command used to create a new schema property.
 * @author eric.wittmann@gmail.com
 */
public class NewSchemaPropertyCommand extends AbstractCommand {

    public String _propertyName;
    public NodePath _schemaPath;
    public String _description;
    public SimplifiedPropertyType _newType;

    public boolean _created;
    public boolean _nullRequired;
    
    NewSchemaPropertyCommand() {
    }
    
    NewSchemaPropertyCommand(Schema schema, String propertyName, String description, SimplifiedPropertyType newType) {
        this._schemaPath = Library.createNodePath(schema);
        this._propertyName = propertyName;
        this._description = description;
        this._newType = newType;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewSchemaPropertyCommand] Executing.");

        this._created = false;

        OasSchema schema = (OasSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            LoggerCompat.info("[NewSchemaPropertyCommand] Schema is null.");
            return;
        }

        if (ModelUtils.isDefined(schema.getProperty(this._propertyName))) {
            LoggerCompat.info("[NewSchemaPropertyCommand] Property already exists.");
            return;
        }

        OasSchema property = schema.createPropertySchema(this._propertyName);
        if (ModelUtils.isDefined(this._description)) {
            property.description = this._description;
        }
        if (ModelUtils.isDefined(this._newType)) {
            this._setPropertyType((IOasPropertySchema) property);
        }
        schema.addProperty(this._propertyName, property);
        LoggerCompat.info("[NewSchemaPropertyCommand] Property [%s] created successfully.", this._propertyName);

        this._created = true;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewSchemaPropertyCommand] Reverting.");
        if (!this._created) {
            return;
        }

        OasSchema schema = (OasSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            return;
        }

        if (this.isNullOrUndefined(schema.getProperty(this._propertyName))) {
            return;
        }

        schema.removeProperty(this._propertyName);

        // if the property was marked as required - need to remove it from the parent's "required" array
        if (ModelUtils.isDefined(this._newType) && this._newType.required == Boolean.TRUE) {
            List<String> required = schema.required;
            required.remove(required.indexOf(this._propertyName));
        }
    }

    /**
     * Sets the property type.
     * @param property
     */
    protected void _setPropertyType(IOasPropertySchema prop) {
        // Update the schema's type
        SimplifiedTypeUtil.setSimplifiedType((OasSchema) prop, this._newType);
        if (ModelUtils.isDefined(this._newType) && this._newType.required == Boolean.TRUE) {
            OasSchema parent = (OasSchema) ((Node) prop).parent();
            List<String> required = parent.required;
            if (this.isNullOrUndefined(required)) {
                required = new ArrayList<>();
                NodeCompat.setProperty(parent, Constants.PROP_REQUIRED, required);
                this._nullRequired = true;
            }
            required.add(prop.getPropertyName());
        }
    }

}
