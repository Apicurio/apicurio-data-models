package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import io.apicurio.datamodels.models.openapi.OpenApiResponses;
import io.apicurio.datamodels.paths.NodePathUtil;

/**
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllResponsesCommand extends DeleteNodeCommand<OpenApiResponses> {
    
    public DeleteAllResponsesCommand() {
    }

    public DeleteAllResponsesCommand(OpenApiOperation operation) {
        super("responses", operation);
    }

    @Override
    protected OpenApiResponses readNode(Document doc, ObjectNode node) {
        OpenApiOperation operation = (OpenApiOperation) NodePathUtil.resolveNodePath(this._parentPath, doc);
        OpenApiResponses responses = operation.createResponses();
        Library.readNode(node, responses);
        return responses;
    }

}
