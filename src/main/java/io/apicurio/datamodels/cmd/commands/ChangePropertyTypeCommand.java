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
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.models.SimplifiedPropertyType;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.cmd.util.SimplifiedTypeUtil;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.IPropertyParent;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.core.models.common.Schema;

import java.util.ArrayList;
import java.util.List;

/**
 * A command used to modify the type of a property of a schema.
 * @author eric.wittmann@gmail.com
 */
public abstract class ChangePropertyTypeCommand extends AbstractCommand {

    public NodePath _propPath;
    public String _propName;
    public SimplifiedPropertyType _newType;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldProperty;
    public boolean _oldRequired;
    public boolean _nullRequired;
    public Integer _oldRequiredIndex; // nullable for backwards compatibility
    
    ChangePropertyTypeCommand() {
    }

    ChangePropertyTypeCommand(IPropertySchema property, SimplifiedPropertyType newType) {
        this._propPath = Library.createNodePath((Node) property);
        this._propName = property.getPropertyName();
        this._newType = newType;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangePropertyTypeCommand] Executing: " + this._newType);
        IPropertySchema prop = (IPropertySchema) this._propPath.resolve(document);
        if (this.isNullOrUndefined(prop)) {
            return;
        }

        IPropertyParent parent = (IPropertyParent) ((Node) prop).parent();
        List<String> required = parent.getRequiredProperties();

        // Save the old info (for later undo operation)
        this._oldProperty = Library.writeNode((Node) prop);
        final boolean hasRequired = ModelUtils.isDefined(required) && required.size() > 0;
        if (hasRequired) {
            final int indexOf = required.indexOf(prop.getPropertyName());
            this._oldRequired = indexOf != -1;
            this._oldRequiredIndex = indexOf;
        }

        // Update the schema's type
        SimplifiedTypeUtil.setSimplifiedType((Schema) prop, this._newType);

        if (!this.isNullOrUndefined(this._newType.required)) {
            // Going from optional to required
            if (Boolean.TRUE.equals(this._newType.required) && !this._oldRequired) {
                if (this.isNullOrUndefined(required)) {
                    required = new ArrayList<>();
                    parent.setRequiredProperties(required);
                    this._nullRequired = true;
                }
                required.add(prop.getPropertyName());
            }
            // Going from required to optional - remove property name from required list.
            if (Boolean.FALSE.equals(this._newType.required) && this._oldRequired) {
                required.remove(this._oldRequiredIndex.intValue());
                if (required.isEmpty()) {
                    parent.setRequiredProperties(null);
                }
            }
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangePropertyTypeCommand] Reverting.");
        IPropertySchema prop = (IPropertySchema) this._propPath.resolve(document);
        if (this.isNullOrUndefined(prop)) {
            return;
        }

        IPropertyParent parent = (IPropertyParent) ((Node) prop).parent();
        List<String> required = parent.getRequiredProperties();

        boolean currentlyRequired = ModelUtils.isDefined(required) && required.size() > 0 && required.indexOf(prop.getPropertyName()) != -1;

        Schema oldProp = parent.createPropertySchema(this._propName);
        Library.readNode(this._oldProperty, oldProp);

        // Restore the schema attributes
        restoreSchemaInternalProperties((Schema) prop, oldProp);
        // Restore the "required" flag
        if (!this.isNullOrUndefined(this._newType.required)) {
            if (this._nullRequired) {
                parent.setRequiredProperties(null);
            } else {
                // Restoring optional from required
                if (currentlyRequired && !this._oldRequired) {
                    required.remove(required.indexOf(prop.getPropertyName()));
                }
                // Restoring required from optional
                if (!currentlyRequired && this._oldRequired) {
                    if (this.isNullOrUndefined(required) ) {
                        required = new ArrayList<>();
                        parent.setRequiredProperties(required);
                    }
                    ModelUtils.restoreListEntry(this._oldRequiredIndex, prop.getPropertyName(), required);
                }
            }
        }
    }

    /**
     * Restores document type dependent fields in the schema
     */
    protected abstract void restoreSchemaInternalProperties(Schema toSchema, Schema fromSchema);

}
