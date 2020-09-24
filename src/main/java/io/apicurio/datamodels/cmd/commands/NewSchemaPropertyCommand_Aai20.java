/*
 * Copyright 2020 Red Hat
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

import io.apicurio.datamodels.asyncapi.models.AaiSchema;
import io.apicurio.datamodels.cmd.models.SimplifiedPropertyType;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.cmd.util.SimplifiedTypeUtil;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.core.models.common.Schema;

/**
 * A command used to create a new schema property for AsyncAPI Schema
 * @author laurent.broudoux@gmail.com
 */
public class NewSchemaPropertyCommand_Aai20 extends NewSchemaPropertyCommand {

    NewSchemaPropertyCommand_Aai20() {
    }

    NewSchemaPropertyCommand_Aai20(Schema schema, String propertyName, String description, SimplifiedPropertyType newType) {
        super(schema, propertyName, description, newType);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewSchemaPropertyCommand_Aai20] Executing.");

        this._created = false;

        AaiSchema schema = (AaiSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            LoggerCompat.info("[NewSchemaPropertyCommand_Aai20] Schema is null.");
            return;
        }

        if (ModelUtils.isDefined(schema.getProperty(this._propertyName))) {
            LoggerCompat.info("[NewSchemaPropertyCommand_Aai20] Property already exists.");
            return;
        }

        AaiSchema property = schema.createPropertySchema(this._propertyName);
        if (ModelUtils.isDefined(this._description)) {
            property.description = this._description;
        }
        if (ModelUtils.isDefined(this._newType)) {
            this._setPropertyType((IPropertySchema) property);
        }
        schema.addProperty(this._propertyName, property);
        LoggerCompat.info("[NewSchemaPropertyCommand_Aai20] Property [%s] created successfully.", this._propertyName);

        this._created = true;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewSchemaPropertyCommand_Aai20] Reverting.");
        if (!this._created) {
            return;
        }

        AaiSchema schema = (AaiSchema) this._schemaPath.resolve(document);
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
     * @param prop
     */
    protected void _setPropertyType(IPropertySchema prop) {
        // Update the schema's type
        SimplifiedTypeUtil.setSimplifiedType((AaiSchema) prop, this._newType);
        if (ModelUtils.isDefined(this._newType) && this._newType.required == Boolean.TRUE) {
            AaiSchema parent = (AaiSchema) ((Node) prop).parent();
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
