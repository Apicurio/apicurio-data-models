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
import io.apicurio.datamodels.asyncapi.models.AaiSchema;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.common.Schema;
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
    static String getInheritanceType(Schema schema) {
        if (schema instanceof OasSchema) {
            if (ModelUtils.isDefined(((OasSchema) schema).allOf)) {
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
        } else if (schema instanceof AaiSchema) {
            if (ModelUtils.isDefined(((AaiSchema) schema).allOf)) {
                return TYPE_ALL_OF;
            }
            if (ModelUtils.isDefined(((AaiSchema) schema).anyOf)) {
                return TYPE_ANY_OF;
            }
            if (ModelUtils.isDefined(((AaiSchema) schema).oneOf)) {
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
    protected Schema createSchema(Schema parentSchema, String inheritanceType) {
        if (NodeCompat.equals(TYPE_ALL_OF, inheritanceType)) {
            return createAllOfSchema(parentSchema);
        }
        if (NodeCompat.equals(TYPE_ANY_OF, inheritanceType)) {
            return createAnyOfSchema(parentSchema);
        }
        if (NodeCompat.equals(TYPE_ONE_OF, inheritanceType)) {
            return createOneOfSchema(parentSchema);
        }
        // TODO is it possible to get here?  if so what should we do?
        return createAllOfSchema(parentSchema);
    }

    /**
     * Copies the given list of schemas to the appropriate property on the model
     * @param schemas
     * @param targetSchema 
     * @param inheritanceType
     */
    protected void copySchemaJsTo(List<Object> schemas, Schema targetSchema, String inheritanceType) {
        if (NodeCompat.equals(TYPE_ALL_OF, inheritanceType)) {
            schemas.forEach(ser -> {
                addAllOfSchema(targetSchema, (Schema) Library.readNode(ser, createAllOfSchema(targetSchema)));
            });
        }
        if (NodeCompat.equals(TYPE_ANY_OF, inheritanceType)) {
            schemas.forEach(ser -> {
                addAnyOfSchema(targetSchema, (Schema) Library.readNode(ser, createAnyOfSchema(targetSchema)));
            });
        }
        if (NodeCompat.equals(TYPE_ONE_OF, inheritanceType)) {
            schemas.forEach(ser -> {
                addOneOfSchema(targetSchema, (Schema) Library.readNode(ser, createOneOfSchema(targetSchema)));
            });
        }        
    }
    
    private static void addAllOfSchema(Schema to, Schema from) {
        if (to instanceof OasSchema) {
            ((OasSchema) to).addAllOfSchema((OasSchema) from);
        } else if (to instanceof AaiSchema) {
            ((AaiSchema) to).addAllOfSchema((AaiSchema) from);
        }
    }
    
    private static Schema createAllOfSchema(Schema schema) {
        if (schema instanceof OasSchema) {
            return ((OasSchema) schema).createAllOfSchema();
        } else if (schema instanceof AaiSchema) {
            return ((AaiSchema) schema).createAllOfSchema();
        }
        return null;
    }
    
    private static void addAnyOfSchema(Schema to, Schema from) {
        if (to instanceof Oas30Schema) {
            ((Oas30Schema) to).addAnyOfSchema((Oas30AnyOfSchema) from);
        } else if (to instanceof AaiSchema) {
            ((AaiSchema) to).addAnyOfSchema((AaiSchema) from);
        }
    }
    
    private static Schema createAnyOfSchema(Schema schema) {
        if (schema instanceof Oas30Schema) {
            return ((Oas30Schema) schema).createAnyOfSchema();
        } else if (schema instanceof AaiSchema) {
            return ((AaiSchema) schema).createAnyOfSchema();
        }
        return null;
    }
    
    private static void addOneOfSchema(Schema to, Schema from) {
        if (to instanceof Oas30Schema) {
            ((Oas30Schema) to).addOneOfSchema((Oas30OneOfSchema) from);
        } else if (to instanceof AaiSchema) {
            ((AaiSchema) to).addOneOfSchema((AaiSchema) from);
        }
    }
    
    private static Schema createOneOfSchema(Schema schema) {
        if (schema instanceof Oas30Schema) {
            return ((Oas30Schema) schema).createOneOfSchema();
        } else if (schema instanceof AaiSchema) {
            return ((AaiSchema) schema).createOneOfSchema();
        }
        return null;
    }

}
