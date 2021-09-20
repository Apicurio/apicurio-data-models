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
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.IPropertyParent;
import io.apicurio.datamodels.core.models.common.Schema;

/**
 * A command used to delete all properties from a schema.
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllPropertiesCommand extends AbstractCommand {

    public NodePath _schemaPath;

    @JsonDeserialize(contentUsing=NullableJsonNodeDeserializer.class)
    public List<Object> _oldProperties;

    DeleteAllPropertiesCommand() {
    }
    
    DeleteAllPropertiesCommand(IPropertyParent schema) {
        this._schemaPath = Library.createNodePath((Node) schema);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllPropertiesCommand] Executing.");
        this._oldProperties = new ArrayList<>();

        IPropertyParent schema = (IPropertyParent) this._schemaPath.resolve(document);

        if (this.isNullOrUndefined(schema)) {
            return;
        }

        schema.getPropertyNames().forEach( pname -> {
            Object data = JsonCompat.objectNode();
            JsonCompat.setPropertyString(data, Constants.PROP_NAME, pname);
            JsonCompat.setProperty(data, Constants.PROP_SCHEMA, Library.writeNode(schema.removeProperty(pname)));
            JsonCompat.setPropertyBoolean(data, Constants.PROP_REQUIRED, schema.isPropertyRequired(pname));
            schema.unsetPropertyRequired(pname);
            this._oldProperties.add(data);
        });
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteAllPropertiesCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldProperties) || this._oldProperties.size() == 0) {
            return;
        }

        IPropertyParent schema = (IPropertyParent) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            return;
        }

        this._oldProperties.forEach( oldProp -> {
            String name = JsonCompat.getPropertyString(oldProp, Constants.PROP_NAME);
            Object schemaObj = JsonCompat.getProperty(oldProp, Constants.PROP_SCHEMA);
            Boolean required = JsonCompat.getPropertyBoolean(oldProp, Constants.PROP_REQUIRED);
            
            Schema prop = schema.createPropertySchema(name);
            Library.readNode(schemaObj, prop);
            schema.addProperty(name, prop);
            if (ModelUtils.isDefined(required) && required == Boolean.TRUE) {
                schema.setPropertyRequired(name);
            }
        });        
    }


}
