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
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema;

/**
 * Allows changing the inheritance setting for a schema.  This basically allows changing
 * the usage of "anyOf", "allOf", etc.  The algorithm for this command is as follows:
 * 
 * - When switching from "none" to any inheritance mode, any existing properties of the 
 *   schema will be wrapped in a new schema and added to the new inheritance mode
 * - When switching from any inheritance mode back to "none", any properties in a wrapped
 *   schema will be unwrapped and added as properties
 * - When switching from one inheritance mode to another, the list of schemas will be
 *   copied over with it (e.g. when going from "allOf" to "anyOf")
 * 
 * Challenge: what impact will this have on undo/redo?  Changes made to properties after
 * this command is executed may be lost when this command is undone!
 * 
 * @author eric.wittmann@gmail.com
 */
public class ChangeSchemaInheritanceCommand extends AbstractSchemaInhCommand {
    
    public NodePath _schemaPath;
    public String _newInheritanceType;
    public String _oldInheritanceType;
    @JsonDeserialize(contentUsing=NullableJsonNodeDeserializer.class)
    public List<Object> _oldSchemas;
    
    /**
     * Constructor.
     */
    ChangeSchemaInheritanceCommand() {
    }
    
    /**
     * Constructor.
     * @param schema
     * @param inheritanceType (allOf, anyOf, oneOf, none)
     */
    ChangeSchemaInheritanceCommand(OasSchema schema, String inheritanceType) {
        this._schemaPath = Library.createNodePath(schema);
        this._newInheritanceType = inheritanceType;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangeSchemaInheritanceCommand] Executing: " + this._newInheritanceType);
        
        this._oldInheritanceType = null;
        this._oldSchemas = null;
        
        OasSchema schema = (OasSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            return;
        }
        
        this._oldInheritanceType = getInheritanceType(schema);
        
        switchInheritance(schema, this._oldInheritanceType, this._newInheritanceType, false);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangeSchemaInheritanceCommand] Reverting: " + this._oldInheritanceType);
        
        OasSchema schema = (OasSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            return;
        }
        
        switchInheritance(schema, this._newInheritanceType, this._oldInheritanceType, true);
        
        // If there are "old" schemas, restore them.  This only happens when we went from
        // anyOf, oneOf, or allOf to none.  When that happens any list of schemas that the
        // model might have had are lost.
        if (NodeCompat.equals(TYPE_NONE, this._newInheritanceType) && !this._oldSchemas.isEmpty()) {
            copySchemaJsTo(this._oldSchemas, schema, this._oldInheritanceType);
        }
    }

    /**
     * Switch the inheritance type.
     * @param schema
     * @param fromType
     * @param toType
     * @param isUndo
     */
    private void switchInheritance(OasSchema schema, String fromType, String toType, boolean isUndo) {
        // If the type didn't change, do nothing
        if (NodeCompat.equals(fromType, toType)) {
            return;
        }
        
        // Pull the schemas out (if allOf, anyOf, or oneOf)
        List<OasSchema> schemas = new ArrayList<>();
        if (NodeCompat.equals(TYPE_ALL_OF, fromType)) {
            schemas = schema.allOf;
            schema.allOf = null;
        }
        if (NodeCompat.equals(TYPE_ANY_OF, fromType)) {
            Oas30Schema schema30 = (Oas30Schema) schema;
            schemas = schema30.anyOf;
            schema30.anyOf = null;
        }
        if (NodeCompat.equals(TYPE_ONE_OF, fromType)) {
            Oas30Schema schema30 = (Oas30Schema) schema;
            schemas = schema30.oneOf;
            schema30.oneOf = null;
        }
        
        // When switching to "none" try to copy any properties from the list of schemas
        // and unwrap them into this schema.  Also save the list of schemas so we can 
        // restore them later during Undo
        if (NodeCompat.equals(TYPE_NONE, toType)) {
            if (!isUndo) {
                this._oldSchemas = new ArrayList<>();
            }
            schemas.forEach(s -> {
                if (ModelUtils.isDefined(s.properties)) {
                    moveProperties(s, schema);
                } else if (!isUndo) {
                    this._oldSchemas.add(Library.writeNode(s));
                }
            });
        }
        
        // when switching FROM "none" - wrap any properties this schema has into another
        // schema and add it to the list of schemas
        if (NodeCompat.equals(TYPE_NONE, fromType)) {
            OasSchema wrapperSchema = createSchema(schema, fromType);
            wrapperSchema.type = "object";
            moveProperties(schema, wrapperSchema);
            
            schemas.add(wrapperSchema);
        }
        
        // Copy the schemas
        if (!schemas.isEmpty()) {
            copySchemasTo(schemas, schema, toType);
        }
    }

    /**
     * Copies the given list of schemas to the appropriate property on the model
     * @param schemas
     * @param targetSchema 
     * @param inheritanceType
     */
    private void copySchemasTo(List<OasSchema> schemas, OasSchema targetSchema, String inheritanceType) {
        List<Object> jsSchemas = new ArrayList<>();
        schemas.forEach(schema -> {
            jsSchemas.add(Library.writeNode(schema));
        });
        copySchemaJsTo(jsSchemas, targetSchema, inheritanceType);
    }

    /**
     * Copies the given list of schemas to the appropriate property on the model
     * @param schemas
     * @param targetSchema 
     * @param inheritanceType
     */
    private void copySchemaJsTo(List<Object> schemas, OasSchema targetSchema, String inheritanceType) {
        if (NodeCompat.equals(TYPE_ALL_OF, inheritanceType)) {
            schemas.forEach(ser -> {
                targetSchema.addAllOfSchema((OasSchema) Library.readNode(ser, targetSchema.createAllOfSchema()));
            });
        }
        if (NodeCompat.equals(TYPE_ANY_OF, inheritanceType)) {
            Oas30Schema targetSchema30 = (Oas30Schema) targetSchema;
            schemas.forEach(ser -> {
                targetSchema30.addAnyOfSchema((Oas30AnyOfSchema) Library.readNode(ser, targetSchema30.createAnyOfSchema()));
            });
        }
        if (NodeCompat.equals(TYPE_ONE_OF, inheritanceType)) {
            Oas30Schema targetSchema30 = (Oas30Schema) targetSchema;
            schemas.forEach(ser -> {
                targetSchema30.addOneOfSchema((Oas30OneOfSchema) Library.readNode(ser, targetSchema30.createOneOfSchema()));
            });
        }        
    }

    /**
     * Creates a schema of a particular type (allOf, anyOf, etc).
     * @param parentSchema
     * @param inheritanceType
     */
    private OasSchema createSchema(OasSchema parentSchema, String inheritanceType) {
        if (NodeCompat.equals(TYPE_ALL_OF, inheritanceType)) {
            return parentSchema.createAllOfSchema();
        }
        if (NodeCompat.equals(TYPE_ANY_OF, inheritanceType)) {
            Oas30Schema schema30 = (Oas30Schema) parentSchema;
            return schema30.createAnyOfSchema();
        }
        if (NodeCompat.equals(TYPE_ONE_OF, inheritanceType)) {
            Oas30Schema schema30 = (Oas30Schema) parentSchema;
            return schema30.createOneOfSchema();
        }
        // TODO is it possible to get here?  if so what should we do?
        return parentSchema.createAllOfSchema();
    }

    /**
     * Moves properties from one schema to another.
     * @param from
     * @param to
     */
    private void moveProperties(OasSchema from, OasSchema to) {
        to.properties = from.properties;
        from.properties = null;
        // Update the "parent" setting for all the property schemas
        to.properties.values().forEach(pschema -> {
            pschema._parent = to;
        });
        
        to.required = from.required;
        from.required = null;
    }

    /**
     * Determines the current inheritance type for the given schema.
     * @param schema
     */
    private static String getInheritanceType(OasSchema schema) {
        if (ModelUtils.isDefined(schema.allOf)) {
            return TYPE_ALL_OF;
        }
        if (schema.ownerDocument().getDocumentType() == DocumentType.openapi3) {
            Oas30Schema schema30 = (Oas30Schema) schema;
            if (ModelUtils.isDefined(schema30.anyOf)) {
                return TYPE_ANY_OF;
            }
            if (ModelUtils.isDefined(schema30.oneOf)) {
                return TYPE_ONE_OF;
            }
        }
        return TYPE_NONE;
    }

}
