package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.asyncapi.AsyncApiComponents;
import io.apicurio.datamodels.models.asyncapi.AsyncApiDocument;
import io.apicurio.datamodels.models.asyncapi.AsyncApiSchema;
import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.models.openapi.v2x.OpenApi2xDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.OpenApi2xDocument;
import io.apicurio.datamodels.models.openapi.v2x.OpenApi2xSchema;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Document;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xComponents;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Schema;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Document;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Schema;
import io.apicurio.datamodels.models.openrpc.OpenRpcComponents;
import io.apicurio.datamodels.models.openrpc.OpenRpcDocument;
import io.apicurio.datamodels.models.openrpc.OpenRpcSchema;
import io.apicurio.datamodels.models.union.StringUnionValueImpl;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.util.NodeUtil;

import java.util.Map;

/**
 * A command used to create a new empty schema definition in a document.
 * The schema is created with type "object" by default.
 * @author eric.wittmann@gmail.com
 */
public class CreateSchemaCommand extends AbstractCommand {

    public String _schemaName;

    public boolean _schemaExisted;
    public boolean _nullParent;

    public CreateSchemaCommand() {
    }

    public CreateSchemaCommand(String schemaName) {
        this._schemaName = schemaName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[CreateSchemaCommand] Executing.");
        this._schemaExisted = false;
        this._nullParent = false;

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
        OpenApi2xDefinitions definitions = doc.getDefinitions();
        if (this.isNullOrUndefined(definitions)) {
            definitions = doc.createDefinitions();
            doc.setDefinitions(definitions);
            this._nullParent = true;
        }

        // Check if schema already exists
        if (!this.isNullOrUndefined(definitions.getItem(this._schemaName))) {
            this._schemaExisted = true;
            return;
        }

        OpenApi2xSchema newSchema = (OpenApi2xSchema) definitions.createSchema();
        newSchema.setType("object");
        definitions.addItem(this._schemaName, newSchema);
    }

    private void executeForOpenApi30(OpenApi30Document doc) {
        OpenApi3xComponents components = doc.getComponents();
        if (this.isNullOrUndefined(components)) {
            components = doc.createComponents();
            doc.setComponents(components);
            this._nullParent = true;
        }

        // Check if schema already exists
        Map<String, OpenApiSchema> schemas = (Map<String, OpenApiSchema>) components.getSchemas();
        if (!this.isNullOrUndefined(schemas) && schemas.containsKey(this._schemaName)) {
            this._schemaExisted = true;
            return;
        }

        OpenApi30Schema newSchema = (OpenApi30Schema) components.createSchema();
        newSchema.setType("object");
        components.addSchema(this._schemaName, newSchema);
    }

    private void executeForOpenApi31(OpenApi31Document doc) {
        OpenApi3xComponents components = doc.getComponents();
        if (this.isNullOrUndefined(components)) {
            components = doc.createComponents();
            doc.setComponents(components);
            this._nullParent = true;
        }

        // Check if schema already exists
        Map<String, OpenApiSchema> schemas = (Map<String, OpenApiSchema>) components.getSchemas();
        if (!this.isNullOrUndefined(schemas) && schemas.containsKey(this._schemaName)) {
            this._schemaExisted = true;
            return;
        }

        OpenApi31Schema newSchema = (OpenApi31Schema) components.createSchema();
        newSchema.setType(new StringUnionValueImpl("object"));
        components.addSchema(this._schemaName, newSchema);
    }

    @SuppressWarnings("unchecked")
    private void executeForAsyncApi2(AsyncApiDocument doc) {
        AsyncApiComponents components = doc.getComponents();
        if (this.isNullOrUndefined(components)) {
            components = doc.createComponents();
            doc.setComponents(components);
            this._nullParent = true;
        }

        // Check if schema already exists
        Map<String, ? extends Schema> schemas = (Map<String, ? extends Schema>) NodeUtil.getNodeProperty(components, "schemas");
        if (!this.isNullOrUndefined(schemas) && schemas.containsKey(this._schemaName)) {
            this._schemaExisted = true;
            return;
        }

        AsyncApiSchema newSchema = (AsyncApiSchema) NodeUtil.invokeMethod(components, "createSchema");
        newSchema.setType("object");
        NodeUtil.invokeMethod(components, "addSchema", this._schemaName, newSchema);
    }

    private void executeForOpenRpc(OpenRpcDocument doc) {
        OpenRpcComponents components = doc.getComponents();
        if (this.isNullOrUndefined(components)) {
            components = doc.createComponents();
            doc.setComponents(components);
            this._nullParent = true;
        }

        // Check if schema already exists
        Map<String, OpenRpcSchema> schemas = components.getSchemas();
        if (!this.isNullOrUndefined(schemas) && schemas.containsKey(this._schemaName)) {
            this._schemaExisted = true;
            return;
        }

        OpenRpcSchema newSchema = components.createSchema();
        newSchema.setType("object");
        components.addSchema(this._schemaName, newSchema);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[CreateSchemaCommand] Reverting.");
        if (this._schemaExisted) {
            return;
        }

        if (ModelTypeUtil.isOpenApi2Model(document)) {
            OpenApi2xDocument doc = (OpenApi2xDocument) document;
            if (this._nullParent) {
                doc.setDefinitions(null);
            } else {
                OpenApi2xDefinitions definitions = doc.getDefinitions();
                if (!this.isNullOrUndefined(definitions)) {
                    definitions.removeItem(this._schemaName);
                }
            }
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            OpenApi30Document doc = (OpenApi30Document) document;
            if (this._nullParent) {
                doc.setComponents(null);
            } else {
                OpenApi3xComponents components = doc.getComponents();
                if (!this.isNullOrUndefined(components)) {
                    components.removeSchema(this._schemaName);
                }
            }
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            OpenApi31Document doc = (OpenApi31Document) document;
            if (this._nullParent) {
                doc.setComponents(null);
            } else {
                OpenApi3xComponents components = doc.getComponents();
                if (!this.isNullOrUndefined(components)) {
                    components.removeSchema(this._schemaName);
                }
            }
        } else if (ModelTypeUtil.isAsyncApi2Model(document)) {
            AsyncApiDocument doc = (AsyncApiDocument) document;
            if (this._nullParent) {
                doc.setComponents(null);
            } else {
                AsyncApiComponents components = doc.getComponents();
                if (!this.isNullOrUndefined(components)) {
                    NodeUtil.invokeMethod(components, "removeSchema", this._schemaName);
                }
            }
        } else if (ModelTypeUtil.isOpenRpcModel(document)) {
            OpenRpcDocument doc = (OpenRpcDocument) document;
            if (this._nullParent) {
                doc.setComponents(null);
            } else {
                OpenRpcComponents components = doc.getComponents();
                if (!this.isNullOrUndefined(components)) {
                    components.removeSchema(this._schemaName);
                }
            }
        }
    }

}
