package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.util.NodeUtil;

/**
 * A command used to replace an operation with a newer version.
 * @author eric.wittmann@gmail.com
 */
public class ReplaceOperationCommand extends AbstractReplaceNodeCommand<OpenApiOperation> {
    
    public ReplaceOperationCommand() {
    }

    public ReplaceOperationCommand(OpenApiOperation old, OpenApiOperation replacement) {
        super(old, replacement);
    }

    @Override
    protected void replaceNode(Node parent, OpenApiOperation newNode) {
        OpenApiPathItem pathItem = (OpenApiPathItem) parent;
        String method = this._nodePath.getLastSegment().getValue();
        NodeUtil.setProperty(pathItem, method, newNode);
    }

    @Override
    protected OpenApiOperation readNode(Node parent, ObjectNode node) {
        OpenApiPathItem pathItem = (OpenApiPathItem) parent;
        OpenApiOperation operation = pathItem.createOperation();
        Library.readNode(node, operation);
        return operation;
    }

}
