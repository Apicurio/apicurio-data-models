package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.models.AaiSchema;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;

import java.util.List;

/**
 * Asyncapi equivalent of {@link AddChildSchemaCommand}
 */
public class AddChildSchemaCommand_Aai20 extends AbstractSchemaInhCommand {
    public NodePath _schemaPath;
    public String _newChildSchemaType;
    @JsonDeserialize(using= MarshallCompat.NullableJsonNodeDeserializer.class)
    public Object _newChildSchemaObj;
    public boolean _nullChildList;
    public int _childIndex;

    AddChildSchemaCommand_Aai20() {
    }

    AddChildSchemaCommand_Aai20(AaiSchema schema, AaiSchema childSchema, String childType) {
        this._schemaPath = Library.createNodePath(schema);
        this._newChildSchemaObj = Library.writeNode(childSchema);
        this._newChildSchemaType = childType;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[AddChildSchemaCommand_Aai20] Executing.");
        this._nullChildList = false;
        this._childIndex = -1;

        AaiSchema schema = (AaiSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            return;
        }

        List<AaiSchema> schemas = null;
        AaiSchema newSchema = null;
        if (NodeCompat.equals(TYPE_ALL_OF, _newChildSchemaType)) {
            schemas = schema.allOf;
            newSchema = schema.createAllOfSchema();
            Library.readNode(this._newChildSchemaObj, newSchema);
            schema.addAllOfSchema(newSchema);
            this._childIndex = schema.allOf.size() - 1;
        }
        if (NodeCompat.equals(TYPE_ANY_OF, _newChildSchemaType)) {
            schemas = schema.anyOf;
            newSchema = schema.createAnyOfSchema();
            Library.readNode(this._newChildSchemaObj, newSchema);
            schema.addAnyOfSchema(newSchema);
            this._childIndex = schema.anyOf.size() - 1;
        }
        if (NodeCompat.equals(TYPE_ONE_OF, _newChildSchemaType)) {
            schemas = schema.oneOf;
            newSchema = schema.createOneOfSchema();
            Library.readNode(this._newChildSchemaObj, newSchema);
            schema.addOneOfSchema(newSchema);
            this._childIndex = schema.oneOf.size() - 1;
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
        LoggerCompat.info("[AddChildSchemaCommand_Aai20] Reverting.");

        AaiSchema schema = (AaiSchema) this._schemaPath.resolve(document);
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
            if (this._nullChildList) {
                schema.anyOf = null;
            } else {
                schema.anyOf.remove(this._childIndex);
            }
        }
        if (NodeCompat.equals(TYPE_ONE_OF, _newChildSchemaType)) {
            if (this._nullChildList) {
                schema.oneOf = null;
            } else {
                schema.oneOf.remove(this._childIndex);
            }
        }
    }
}
