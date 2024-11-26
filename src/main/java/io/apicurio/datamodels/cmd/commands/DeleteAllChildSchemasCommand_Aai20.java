package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.models.AaiSchema;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;

import java.util.ArrayList;
import java.util.List;

public class DeleteAllChildSchemasCommand_Aai20 extends AbstractSchemaInhCommand {

    public NodePath _schemaPath;
    public String _childSchemaType;

    @JsonDeserialize(contentUsing= MarshallCompat.NullableJsonNodeDeserializer.class)
    public List<Object> _oldSchemas;

    DeleteAllChildSchemasCommand_Aai20() {
    }

    DeleteAllChildSchemasCommand_Aai20(AaiSchema parent, String type) {
        this._schemaPath = Library.createNodePath(parent);
        this._childSchemaType = type;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllChildSchemasCommand_Aai20] Executing.");
        this._oldSchemas = new ArrayList<>();

        AaiSchema schema = (AaiSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            return;
        }

        // Pull the schemas out (if allOf, anyOf, or oneOf)
        List<AaiSchema> schemas;
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
            schemas = schema.anyOf;
            addOldSchemas(schemas);
            for (int i = schema.anyOf.size() - 1; i >= 0; i--) {
                if (!this.isNullOrUndefined(schema.anyOf.get(i).$ref)) {
                    schema.anyOf.remove(i);
                }
            }
        }
        if (NodeCompat.equals(TYPE_ONE_OF, this._childSchemaType)) {
            schemas = schema.oneOf;
            addOldSchemas(schemas);
            for (int i = schema.oneOf.size() - 1; i >= 0; i--) {
                if (!this.isNullOrUndefined(schema.oneOf.get(i).$ref)) {
                    schema.oneOf.remove(i);
                }
            }
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteAllChildSchemasCommand_Aai20] Reverting.");

        if (this.isNullOrUndefined(this._oldSchemas) || this._oldSchemas.size() == 0) {
            return;
        }

        AaiSchema schema = (AaiSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            return;
        }

        if (NodeCompat.equals(TYPE_ALL_OF, this._childSchemaType)) {
            schema.allOf = new ArrayList<>();
        }
        if (NodeCompat.equals(TYPE_ANY_OF, this._childSchemaType)) {
            schema.anyOf = new ArrayList<>();
        }
        if (NodeCompat.equals(TYPE_ONE_OF, this._childSchemaType)) {
            schema.oneOf = new ArrayList<>();
        }

        this.copySchemaJsTo(_oldSchemas, schema, _childSchemaType);
    }

    private void addOldSchemas(List<AaiSchema> schemas) {
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
