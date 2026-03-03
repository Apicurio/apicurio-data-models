package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.openapi.OpenApiComponents;
import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Definitions;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Schema;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.util.NodeUtil;

/**
 * A command used to replace a definition schema with a newer version.
 * @author eric.wittmann@gmail.com
 */
public class ReplaceSchemaDefinitionCommand extends AbstractReplaceNodeCommand<Schema> {

    public ReplaceSchemaDefinitionCommand() {
    }

    public ReplaceSchemaDefinitionCommand(Schema old, Schema replacement) {
        super(old, replacement);
    }

    @Override
    protected void replaceNode(Node parent, Schema newNode) {
        String definitionName = this._nodePath.getLastSegment().getValue();
        if (ModelTypeUtil.isOpenApi2Model(parent)) {
            OpenApi20Definitions definitions = (OpenApi20Definitions) parent;
            definitions.addItem(definitionName, (OpenApi20Schema) newNode);
        } else if (ModelTypeUtil.isAsyncApi2Model(parent)) {
            NodeUtil.invokeMethod(parent, "addSchema", definitionName, newNode);
        } else {
            OpenApiComponents components = (OpenApiComponents) parent;
            components.addSchema(definitionName, (OpenApiSchema) newNode);
        }
    }

    @Override
    protected Schema readNode(Node parent, ObjectNode node) {
        if (ModelTypeUtil.isOpenApi2Model(parent)) {
            OpenApi20Definitions definitions = (OpenApi20Definitions) parent;
            OpenApi20Schema definition = definitions.createSchema();
            Library.readNode(node, definition);
            return definition;
        } else if (ModelTypeUtil.isAsyncApi2Model(parent)) {
            Schema schema = (Schema) NodeUtil.invokeMethod(parent, "createSchema");
            Library.readNode(node, schema);
            return schema;
        } else {
            OpenApiComponents components = (OpenApiComponents) parent;
            Schema schema = components.createSchema();
            Library.readNode(node, schema);
            return schema;
        }
    }

}
