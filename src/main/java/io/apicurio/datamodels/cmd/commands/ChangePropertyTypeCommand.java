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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.models.SimplifiedPropertyType;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.cmd.util.SimplifiedTypeUtil;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.IOasPropertySchema;
import io.apicurio.datamodels.openapi.models.OasSchema;

/**
 * A command used to modify the type of a property of a schema.
 * @author eric.wittmann@gmail.com
 */
public class ChangePropertyTypeCommand extends AbstractCommand {
    
    public NodePath _propPath;
    public String _propName;
    public SimplifiedPropertyType _newType;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldProperty;
    public boolean _oldRequired;
    public boolean _nullRequired;
    
    ChangePropertyTypeCommand() {
    }

    ChangePropertyTypeCommand(IOasPropertySchema property, SimplifiedPropertyType newType) {
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
        IOasPropertySchema prop = (IOasPropertySchema) this._propPath.resolve(document);
        if (this.isNullOrUndefined(prop)) {
            return;
        }
        
        OasSchema parent = (OasSchema) ((Node) prop).parent();
        List<String> required = parent.required;

        // Save the old info (for later undo operation)
        this._oldProperty = Library.writeNode((Node) prop);
        this._oldRequired = ModelUtils.isDefined(required) && required.size() > 0 && required.indexOf(prop.getPropertyName()) != -1;

        // Update the schema's type
        SimplifiedTypeUtil.setSimplifiedType((OasSchema) prop, this._newType);

        if (!this.isNullOrUndefined(this._newType.required)) {
            // Going from optional to required
            if (this._newType.required && !this._oldRequired) {
                if (this.isNullOrUndefined(required)) {
                    required = new ArrayList<>();
                    parent.required = required;
                    this._nullRequired = true;
                }
                required.add(prop.getPropertyName());
            }
            // Going from required to optional - remove property name from required list.
            if (!this._newType.required && this._oldRequired) {
                required.remove(required.indexOf(prop.getPropertyName()));
            }
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangePropertyTypeCommand] Reverting.");
        IOasPropertySchema prop = (IOasPropertySchema) this._propPath.resolve(document);
        if (this.isNullOrUndefined(prop)) {
            return;
        }

        OasSchema parent = (OasSchema) ((Node) prop).parent();
        List<String> required = parent.required;

        boolean wasRequired = ModelUtils.isDefined(required) && required.size() > 0 && required.indexOf(prop.getPropertyName()) != -1;

        OasSchema oldProp = parent.createPropertySchema(this._propName);
        Library.readNode(this._oldProperty, oldProp);

        // Restore the schema attributes
        OasSchema sprop = (OasSchema) prop;
        sprop.$ref = null;
        sprop.type = null;
        sprop.enum_ = null;
        sprop.format = null;
        sprop.items = null;
        if (ModelUtils.isDefined(oldProp)) {
            sprop.$ref = oldProp.$ref;
            sprop.type = oldProp.type;
            sprop.enum_ = oldProp.enum_;
            sprop.format = oldProp.format;
            sprop.items = oldProp.items;
            if (ModelUtils.isDefined(sprop.items) && !NodeCompat.isList(sprop.items)) {
                Node itemsNode = (Node) sprop.items;
                itemsNode._parent = sprop;
                itemsNode._ownerDocument = sprop.ownerDocument();
            }
        }

        // Restore the "required" flag
        if (!this.isNullOrUndefined(this._newType.required)) {
            if (this._nullRequired) {
                parent.required = null;
            } else {
                // Restoring optional from required
                if (wasRequired && !this._oldRequired) {
                    required.remove(required.indexOf(prop.getPropertyName()));
                }
                // Restoring required from optional
                if (!wasRequired && this._oldRequired) {
                    required.add(prop.getPropertyName());
                }
            }
        }
    }

}
