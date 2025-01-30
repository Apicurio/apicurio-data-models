package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.openapi.OpenApiComponents;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Response;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20ResponseDefinitions;
import io.apicurio.datamodels.util.ModelTypeUtil;

/**
 * A command used to replace a response definition with a newer version.
 * @author eric.wittmann@gmail.com
 */
public class ReplaceResponseDefinitionCommand extends AbstractReplaceNodeCommand<OpenApiResponse> {

    public ReplaceResponseDefinitionCommand() {
    }

    public ReplaceResponseDefinitionCommand(OpenApiResponse old, OpenApiResponse replacement) {
        super(old, replacement);
    }

    @Override
    protected void replaceNode(Node parent, OpenApiResponse newNode) {
        String definitionName = this._nodePath.getLastSegment().getValue();
        if (ModelTypeUtil.isOpenApi2Model(parent)) {
            OpenApi20ResponseDefinitions responseDefinitions = (OpenApi20ResponseDefinitions) parent;
            responseDefinitions.addItem(definitionName, (OpenApi20Response) newNode);
        } else {
            OpenApiComponents components = (OpenApiComponents) parent;
            components.addResponse(definitionName, newNode);
        }
    }

    @Override
    protected OpenApiResponse readNode(Node parent, ObjectNode node) {
        if (ModelTypeUtil.isOpenApi2Model(parent)) {
            OpenApi20ResponseDefinitions responseDefinitions = (OpenApi20ResponseDefinitions) parent;
            OpenApi20Response response = responseDefinitions.createResponse();
            Library.readNode(node, response);
            return response;
        } else {
            OpenApiComponents components = (OpenApiComponents) parent;
            OpenApiResponse response = components.createResponse();
            Library.readNode(node, response);
            return response;
        }
    }

}
