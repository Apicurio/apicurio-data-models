package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Document;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Document;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A command used to delete a single property from a schema definition.
 * @author eric.wittmann@gmail.com
 */
public class DeleteSchemaPropertyCommand extends AbstractCommand {

    public String _schemaDefinitionName;
    public String _propertyName;

    public ObjectNode _oldPropertySchema;
    public boolean _oldPropertyRequired;
    public boolean _propertyNotFound;

    public DeleteSchemaPropertyCommand() {
    }

    public DeleteSchemaPropertyCommand(String schemaDefinitionName, String propertyName) {
        this._schemaDefinitionName = schemaDefinitionName;
        this._propertyName = propertyName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteSchemaPropertyCommand] Executing.");

        DeleteSchemaPropertyCommandHelper helper = createHelper(document);
        Schema schema = helper.resolveSchema(document);

        if (this.isNullOrUndefined(schema)) {
            LoggerUtil.info("[DeleteSchemaPropertyCommand] Schema definition '%s' not found.", this._schemaDefinitionName);
            return;
        }

        if (this.isNullOrUndefined(schema.getProperties()) || !schema.getProperties().containsKey(this._propertyName)) {
            this._propertyNotFound = true;
            return;
        }

        this._oldPropertySchema = Library.writeNode(schema.getProperties().get(this._propertyName));
        this._oldPropertyRequired = isRequired(schema, this._propertyName);

        schema.removeProperty(this._propertyName);

        if (this._oldPropertyRequired) {
            removeRequired(schema, this._propertyName);
            if (schema.getRequired() != null && schema.getRequired().isEmpty()) {
                schema.setRequired(null);
            }
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteSchemaPropertyCommand] Reverting.");
        if (this._propertyNotFound) {
            return;
        }

        DeleteSchemaPropertyCommandHelper helper = createHelper(document);
        Schema schema = helper.resolveSchema(document);

        if (this.isNullOrUndefined(schema)) {
            return;
        }

        Schema pschema = schema.createSchema();
        Library.readNode(this._oldPropertySchema, pschema);
        schema.addProperty(this._propertyName, pschema);

        if (this._oldPropertyRequired) {
            addRequired(schema, this._propertyName);
        }
    }

    private boolean isRequired(Schema schema, String pname) {
        return schema.getRequired() != null && schema.getRequired().contains(pname);
    }

    private void removeRequired(Schema schema, String pname) {
        if (schema.getRequired() != null) {
            schema.getRequired().remove(pname);
        }
    }

    private void addRequired(Schema schema, String pname) {
        if (schema.getRequired() == null) {
            schema.setRequired(new ArrayList<>());
        }
        if (!schema.getRequired().contains(pname)) {
            schema.getRequired().add(pname);
        }
    }

    private DeleteSchemaPropertyCommandHelper createHelper(Document document) {
        if (ModelTypeUtil.isOpenApi2Model(document)) {
            return new OpenApi20Helper();
        }
        if (ModelTypeUtil.isOpenApi30Model(document)) {
            return new OpenApi30Helper();
        }
        if (ModelTypeUtil.isOpenApi31Model(document)) {
            return new OpenApi31Helper();
        }
        throw new RuntimeException("Unsupported model type: " + document.root().modelType());
    }

    private interface DeleteSchemaPropertyCommandHelper {
        Schema resolveSchema(Document document);
    }

    private class OpenApi20Helper implements DeleteSchemaPropertyCommandHelper {
        @Override
        public Schema resolveSchema(Document document) {
            OpenApi20Document doc20 = (OpenApi20Document) document;
            if (isNullOrUndefined(doc20.getDefinitions())) {
                return null;
            }
            return doc20.getDefinitions().getItem(_schemaDefinitionName);
        }
    }

    private class OpenApi30Helper implements DeleteSchemaPropertyCommandHelper {
        @Override
        public Schema resolveSchema(Document document) {
            OpenApi30Document doc30 = (OpenApi30Document) document;
            if (isNullOrUndefined(doc30.getComponents())) {
                return null;
            }
            return doc30.getComponents().getSchemas().get(_schemaDefinitionName);
        }
    }

    private class OpenApi31Helper implements DeleteSchemaPropertyCommandHelper {
        @Override
        public Schema resolveSchema(Document document) {
            OpenApi31Document doc31 = (OpenApi31Document) document;
            if (isNullOrUndefined(doc31.getComponents())) {
                return null;
            }
            return doc31.getComponents().getSchemas().get(_schemaDefinitionName);
        }
    }

}
