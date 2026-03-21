package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.asyncapi.AsyncApiComponents;
import io.apicurio.datamodels.models.asyncapi.AsyncApiDocument;
import io.apicurio.datamodels.models.openrpc.OpenRpcComponents;
import io.apicurio.datamodels.models.openrpc.OpenRpcDocument;
import io.apicurio.datamodels.models.openrpc.OpenRpcSchema;
import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Document;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Definitions;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Schema;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Components;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Components;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Document;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.util.NodeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A command used to delete a schema definition from a document.
 * Supports OpenAPI 2.0 (definitions), 3.0, and 3.1 (components/schemas).
 * @author eric.wittmann@gmail.com
 */
public class DeleteSchemaCommand extends AbstractCommand {

    public String _schemaName;

    public ObjectNode _oldSchema;
    public int _schemaIndex;

    public DeleteSchemaCommand() {
    }

    public DeleteSchemaCommand(String schemaName) {
        this._schemaName = schemaName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteSchemaCommand] Executing.");
        this._oldSchema = null;
        this._schemaIndex = -1;

        if (ModelTypeUtil.isOpenApi2Model(document)) {
            executeForOpenApi20((OpenApi20Document) document);
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            executeForOpenApi30((OpenApi30Document) document);
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            executeForOpenApi31((OpenApi31Document) document);
        } else if (ModelTypeUtil.isAsyncApi2Model(document)) {
            executeForAsyncApi2((AsyncApiDocument) document);
        } else if (ModelTypeUtil.isOpenRpcModel(document)) {
            executeForOpenRpc((OpenRpcDocument) document);
        }
    }

    private void executeForOpenApi20(OpenApi20Document doc) {
        OpenApi20Definitions definitions = doc.getDefinitions();
        if (this.isNullOrUndefined(definitions)) {
            return;
        }

        OpenApi20Schema schema = definitions.getItem(this._schemaName);
        if (this.isNullOrUndefined(schema)) {
            return;
        }

        List<String> names = definitions.getItemNames();
        this._schemaIndex = names.indexOf(this._schemaName);
        this._oldSchema = Library.writeNode(schema);
        definitions.removeItem(this._schemaName);
    }

    private void executeForOpenApi30(OpenApi30Document doc) {
        OpenApi30Components components = doc.getComponents();
        if (this.isNullOrUndefined(components)) {
            return;
        }

        Map<String, OpenApiSchema> schemas = (Map<String, OpenApiSchema>) components.getSchemas();
        if (this.isNullOrUndefined(schemas) || !schemas.containsKey(this._schemaName)) {
            return;
        }

        OpenApiSchema schema = schemas.get(this._schemaName);
        this._schemaIndex = indexOf(schemas.keySet(), this._schemaName);
        this._oldSchema = Library.writeNode(schema);
        components.removeSchema(this._schemaName);
    }

    private void executeForOpenApi31(OpenApi31Document doc) {
        OpenApi31Components components = doc.getComponents();
        if (this.isNullOrUndefined(components)) {
            return;
        }

        Map<String, OpenApiSchema> schemas = (Map<String, OpenApiSchema>) components.getSchemas();
        if (this.isNullOrUndefined(schemas) || !schemas.containsKey(this._schemaName)) {
            return;
        }

        OpenApiSchema schema = schemas.get(this._schemaName);
        this._schemaIndex = indexOf(schemas.keySet(), this._schemaName);
        this._oldSchema = Library.writeNode(schema);
        components.removeSchema(this._schemaName);
    }

    @SuppressWarnings("unchecked")
    private void executeForAsyncApi2(AsyncApiDocument doc) {
        AsyncApiComponents components = doc.getComponents();
        if (this.isNullOrUndefined(components)) {
            return;
        }

        Map<String, ? extends Schema> schemas = (Map<String, ? extends Schema>) NodeUtil.getNodeProperty(components, "schemas");
        if (this.isNullOrUndefined(schemas) || !schemas.containsKey(this._schemaName)) {
            return;
        }

        Schema schema = schemas.get(this._schemaName);
        this._schemaIndex = new ArrayList<>(schemas.keySet()).indexOf(this._schemaName);
        this._oldSchema = Library.writeNode(schema);
        NodeUtil.invokeMethod(components, "removeSchema", this._schemaName);
    }

    private void executeForOpenRpc(OpenRpcDocument doc) {
        OpenRpcComponents components = doc.getComponents();
        if (this.isNullOrUndefined(components)) {
            return;
        }

        Map<String, OpenRpcSchema> schemas = components.getSchemas();
        if (this.isNullOrUndefined(schemas) || !schemas.containsKey(this._schemaName)) {
            return;
        }

        OpenRpcSchema schema = schemas.get(this._schemaName);
        this._schemaIndex = new ArrayList<>(schemas.keySet()).indexOf(this._schemaName);
        this._oldSchema = Library.writeNode(schema);
        components.removeSchema(this._schemaName);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteSchemaCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldSchema)) {
            return;
        }

        if (ModelTypeUtil.isOpenApi2Model(document)) {
            undoForOpenApi20((OpenApi20Document) document);
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            undoForOpenApi30((OpenApi30Document) document);
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            undoForOpenApi31((OpenApi31Document) document);
        } else if (ModelTypeUtil.isAsyncApi2Model(document)) {
            undoForAsyncApi2((AsyncApiDocument) document);
        } else if (ModelTypeUtil.isOpenRpcModel(document)) {
            undoForOpenRpc((OpenRpcDocument) document);
        }
    }

    private void undoForOpenApi20(OpenApi20Document doc) {
        OpenApi20Definitions definitions = doc.getDefinitions();
        if (this.isNullOrUndefined(definitions)) {
            definitions = doc.createDefinitions();
            doc.setDefinitions(definitions);
        }

        OpenApi20Schema newSchema = definitions.createSchema();
        Library.readNode(this._oldSchema, newSchema);
        definitions.insertItem(this._schemaName, newSchema, this._schemaIndex);
    }

    private void undoForOpenApi30(OpenApi30Document doc) {
        OpenApi30Components components = doc.getComponents();
        if (this.isNullOrUndefined(components)) {
            components = doc.createComponents();
            doc.setComponents(components);
        }

        OpenApiSchema newSchema = components.createSchema();
        Library.readNode(this._oldSchema, newSchema);
        components.insertSchema(this._schemaName, newSchema, this._schemaIndex);
    }

    private void undoForOpenApi31(OpenApi31Document doc) {
        OpenApi31Components components = doc.getComponents();
        if (this.isNullOrUndefined(components)) {
            components = doc.createComponents();
            doc.setComponents(components);
        }

        OpenApiSchema newSchema = components.createSchema();
        Library.readNode(this._oldSchema, newSchema);
        components.insertSchema(this._schemaName, newSchema, this._schemaIndex);
    }

    private void undoForAsyncApi2(AsyncApiDocument doc) {
        AsyncApiComponents components = doc.getComponents();
        if (this.isNullOrUndefined(components)) {
            components = doc.createComponents();
            doc.setComponents(components);
        }

        Schema newSchema = (Schema) NodeUtil.invokeMethod(components, "createSchema");
        Library.readNode(this._oldSchema, newSchema);
        NodeUtil.invokeMethod(components, "insertSchema", this._schemaName, newSchema, this._schemaIndex);
    }

    private void undoForOpenRpc(OpenRpcDocument doc) {
        OpenRpcComponents components = doc.getComponents();
        if (this.isNullOrUndefined(components)) {
            components = doc.createComponents();
            doc.setComponents(components);
        }

        OpenRpcSchema newSchema = components.createSchema();
        Library.readNode(this._oldSchema, newSchema);
        components.insertSchema(this._schemaName, newSchema, this._schemaIndex);
    }

}
