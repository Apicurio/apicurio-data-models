package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.models.AaiSchema;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;

import java.util.ArrayList;
import java.util.List;

/**
 * Asyncapi equivalent of {@link ChangeSchemaInheritanceCommand}
 */
public class ChangeSchemaInheritanceCommand_Aai20 extends AbstractSchemaInhCommand {
    public NodePath _schemaPath;
    public String _newInheritanceType;
    public String _oldInheritanceType;
    @JsonDeserialize(contentUsing= MarshallCompat.NullableJsonNodeDeserializer.class)
    public List<Object> _oldSchemas;

    /**
     * Constructor.
     */
    ChangeSchemaInheritanceCommand_Aai20() {
    }

    /**
     * Constructor.
     * @param schema
     * @param inheritanceType (allOf, anyOf, oneOf, none)
     */
    ChangeSchemaInheritanceCommand_Aai20(AaiSchema schema, String inheritanceType) {
        this._schemaPath = Library.createNodePath(schema);
        this._newInheritanceType = inheritanceType;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangeSchemaInheritanceCommand_Aai20] Executing: " + this._newInheritanceType);

        this._oldInheritanceType = null;
        this._oldSchemas = null;

        AaiSchema schema = (AaiSchema) this._schemaPath.resolve(document);
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
        LoggerCompat.info("[ChangeSchemaInheritanceCommand_Aai20] Reverting: " + this._oldInheritanceType);

        AaiSchema schema = (AaiSchema) this._schemaPath.resolve(document);
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
    private void switchInheritance(AaiSchema schema, String fromType, String toType, boolean isUndo) {
        // If the type didn't change, do nothing
        if (NodeCompat.equals(fromType, toType)) {
            return;
        }

        // Pull the schemas out (if allOf, anyOf, or oneOf)
        List<AaiSchema> schemas = new ArrayList<>();
        if (NodeCompat.equals(TYPE_ALL_OF, fromType)) {
            schemas = schema.allOf;
            schema.allOf = null;
        }
        if (NodeCompat.equals(TYPE_ANY_OF, fromType)) {
            schemas = schema.anyOf;
            schema.anyOf = null;
        }
        if (NodeCompat.equals(TYPE_ONE_OF, fromType)) {
            schemas = schema.oneOf;
            schema.oneOf = null;
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
            AaiSchema wrapperSchema = (AaiSchema) createSchema(schema, fromType);
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
    private void copySchemasTo(List<AaiSchema> schemas, AaiSchema targetSchema, String inheritanceType) {
        List<Object> jsSchemas = new ArrayList<>();
        schemas.forEach(schema -> {
            jsSchemas.add(Library.writeNode(schema));
        });
        copySchemaJsTo(jsSchemas, targetSchema, inheritanceType);
    }

    /**
     * Moves properties from one schema to another.
     * @param from
     * @param to
     */
    private void moveProperties(AaiSchema from, AaiSchema to) {
        to.properties = from.properties;
        from.properties = null;
        // Update the "parent" setting for all the property schemas
        if (!this.isNullOrUndefined(to.properties)) {
            to.properties.values().forEach(pschema -> {
                pschema._parent = to;
            });
        }

        to.required = from.required;
        from.required = null;
    }

}
