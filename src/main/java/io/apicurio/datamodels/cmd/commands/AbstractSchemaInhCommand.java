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

import java.util.List;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema;

/**
 * @author eric.wittmann@gmail.com
 */
public abstract class AbstractSchemaInhCommand extends AbstractCommand {

    public static final String TYPE_NONE = "none";
    public static final String TYPE_ALL_OF = "allOf";
    public static final String TYPE_ONE_OF = "oneOf";
    public static final String TYPE_ANY_OF = "anyOf";

    /**
     * Determines the current inheritance type for the given schema.
     * @param schema
     */
    static String getInheritanceType(OasSchema schema) {
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

    /**
     * Creates a schema of a particular type (allOf, anyOf, etc).
     * @param parentSchema
     * @param inheritanceType
     */
    protected OasSchema createSchema(OasSchema parentSchema, String inheritanceType) {
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
     * Copies the given list of schemas to the appropriate property on the model
     * @param schemas
     * @param targetSchema 
     * @param inheritanceType
     */
    protected void copySchemaJsTo(List<Object> schemas, OasSchema targetSchema, String inheritanceType) {
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

}
