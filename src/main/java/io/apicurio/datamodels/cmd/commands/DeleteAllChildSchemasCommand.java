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
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema;

/**
 * A command used to delete all child schemas from a schema.
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllChildSchemasCommand extends AbstractSchemaInhCommand {

    public NodePath _schemaPath;
    public String _childSchemaType;

    @JsonDeserialize(contentUsing=NullableJsonNodeDeserializer.class)
    public List<Object> _oldSchemas;

    DeleteAllChildSchemasCommand() {
    }
    
    DeleteAllChildSchemasCommand(OasSchema parent, String type) {
        this._schemaPath = Library.createNodePath((Node) parent);
        this._childSchemaType = type;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllChildSchemasCommand] Executing.");
        this._oldSchemas = new ArrayList<>();

        OasSchema schema = (OasSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            return;
        }

        // Pull the schemas out (if allOf, anyOf, or oneOf)
        List<OasSchema> schemas = new ArrayList<>();
        if (NodeCompat.equals(TYPE_ALL_OF, this._childSchemaType)) {
            schemas = schema.allOf;
            addOldSchemas(schemas);
            for (int i = schema.allOf.size() - 1; i >= 0; i--) {
                if (!this.isNullOrUndefined(schema.allOf.get(i).$ref)) {
                    schema.allOf.remove(i);
                }
            }
        }
        if (NodeCompat.equals(TYPE_ANY_OF, this._childSchemaType)) {
            Oas30Schema schema30 = (Oas30Schema) schema;
            schemas = schema30.anyOf;
            addOldSchemas(schemas);
            for (int i = schema30.anyOf.size() - 1; i >= 0; i--) {
                if (!this.isNullOrUndefined(schema30.anyOf.get(i).$ref)) {
                    schema30.anyOf.remove(i);
                }
            }
        }
        if (NodeCompat.equals(TYPE_ONE_OF, this._childSchemaType)) {
            Oas30Schema schema30 = (Oas30Schema) schema;
            schemas = schema30.oneOf;
            addOldSchemas(schemas);
            for (int i = schema30.oneOf.size() - 1; i >= 0; i--) {
                if (!this.isNullOrUndefined(schema30.oneOf.get(i).$ref)) {
                    schema30.oneOf.remove(i);
                }
            }
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteAllChildSchemasCommand] Reverting.");

        if (this.isNullOrUndefined(this._oldSchemas) || this._oldSchemas.size() == 0) {
            return;
        }

        OasSchema schema = (OasSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            return;
        }

        if (NodeCompat.equals(TYPE_ALL_OF, this._childSchemaType)) {
            schema.allOf = new ArrayList<>();
        }
        if (NodeCompat.equals(TYPE_ANY_OF, this._childSchemaType)) {
            Oas30Schema schema30 = (Oas30Schema) schema;
            schema30.anyOf = new ArrayList<>();
        }
        if (NodeCompat.equals(TYPE_ONE_OF, this._childSchemaType)) {
            Oas30Schema schema30 = (Oas30Schema) schema;
            schema30.oneOf = new ArrayList<>();
        }

        this.copySchemaJsTo(_oldSchemas, schema, _childSchemaType);
    }

    private void addOldSchemas(List<OasSchema> schemas) {
        if (this.isNullOrUndefined(schemas)) {
            return;
        }

        // Save the schemas we're about to delete for later undo
        this._oldSchemas = new ArrayList<>(schemas.size());
        schemas.forEach(oldSchema -> {
            this._oldSchemas.add(Library.writeNode(oldSchema));
        });
    }
}
