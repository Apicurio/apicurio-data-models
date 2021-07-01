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

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema;

/**
 * A command used to add a child schema to the 
 * @author eric.wittmann@gmail.com
 */
public class AddChildSchemaCommand extends AbstractSchemaInhCommand {

    public NodePath _schemaPath;
    public String _newChildSchemaType;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _newChildSchemaObj;
    public boolean _nullChildList;
    public int _childIndex;
    
    AddChildSchemaCommand() {
    }
    
    AddChildSchemaCommand(OasSchema schema, OasSchema childSchema, String childType) {
        this._schemaPath = Library.createNodePath(schema);
        this._newChildSchemaObj = Library.writeNode(childSchema);
        this._newChildSchemaType = childType;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[AddChildSchemaCommand] Executing.");
        this._nullChildList = false;
        this._childIndex = -1;
        
        OasSchema schema = (OasSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            return;
        }

        List<OasSchema> schemas = null;
        OasSchema newSchema = null;
        if (NodeCompat.equals(TYPE_ALL_OF, _newChildSchemaType)) {
            schemas = schema.allOf;
            newSchema = schema.createAllOfSchema();
            Library.readNode(this._newChildSchemaObj, newSchema);
            schema.addAllOfSchema(newSchema);
            this._childIndex = schema.allOf.size() - 1;
        }
        if (NodeCompat.equals(TYPE_ANY_OF, _newChildSchemaType)) {
            Oas30Schema schema30 = (Oas30Schema) schema;
            schemas = schema30.anyOf;
            newSchema = ((Oas30Schema) schema).createAnyOfSchema();
            Library.readNode(this._newChildSchemaObj, newSchema);
            ((Oas30Schema) schema).addAnyOfSchema((Oas30AnyOfSchema) newSchema);
            this._childIndex = ((Oas30Schema) schema).anyOf.size() - 1;
        }
        if (NodeCompat.equals(TYPE_ONE_OF, _newChildSchemaType)) {
            Oas30Schema schema30 = (Oas30Schema) schema;
            schemas = schema30.oneOf;
            newSchema = ((Oas30Schema) schema).createOneOfSchema();
            Library.readNode(this._newChildSchemaObj, newSchema);
            ((Oas30Schema) schema).addOneOfSchema((Oas30OneOfSchema) newSchema);
            this._childIndex = ((Oas30Schema) schema).oneOf.size() - 1;
        }
        
        if (schemas == null) {
            this._nullChildList = true;
        }

    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[AddChildSchemaCommand] Reverting.");

        OasSchema schema = (OasSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema) || this._childIndex == -1) {
            return;
        }
        
        if (NodeCompat.equals(TYPE_ALL_OF, _newChildSchemaType)) {
            if (this._nullChildList) {
                schema.allOf = null;
            } else {
                schema.allOf.remove(this._childIndex);
            }
        }
        if (NodeCompat.equals(TYPE_ANY_OF, _newChildSchemaType)) {
            Oas30Schema schema30 = (Oas30Schema) schema;
            if (this._nullChildList) {
                schema30.anyOf = null;
            } else {
                schema30.anyOf.remove(this._childIndex);
            }
        }
        if (NodeCompat.equals(TYPE_ONE_OF, _newChildSchemaType)) {
            Oas30Schema schema30 = (Oas30Schema) schema;
            if (this._nullChildList) {
                schema30.oneOf = null;
            } else {
                schema30.oneOf.remove(this._childIndex);
            }
        }
        
    }


}
