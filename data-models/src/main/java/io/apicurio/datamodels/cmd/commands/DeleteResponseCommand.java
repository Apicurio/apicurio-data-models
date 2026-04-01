package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.models.openapi.OpenApiResponses;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;

/**
 * A command used to delete a response from an operation.
 * @author eric.wittmann@gmail.com
 */
public class DeleteResponseCommand extends AbstractCommand {

    public NodePath _operationPath;
    public String _statusCode;

    public ObjectNode _oldResponse;
    public int _oldIndex;

    public DeleteResponseCommand() {
    }

    public DeleteResponseCommand(OpenApiOperation operation, String statusCode) {
        this._operationPath = NodePathUtil.createNodePath(operation);
        this._statusCode = statusCode;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteResponseCommand] Executing.");
        this._oldResponse = null;

        OpenApiOperation operation = (OpenApiOperation) NodePathUtil.resolveNodePath(this._operationPath, document);
        if (this.isNullOrUndefined(operation)) {
            return;
        }

        OpenApiResponses responses = operation.getResponses();
        if (this.isNullOrUndefined(responses)) {
            return;
        }

        OpenApiResponse response = responses.getItem(this._statusCode);
        if (this.isNullOrUndefined(response)) {
            return;
        }

        // Save the response and its index for undo
        this._oldResponse = Library.writeNode(response);
        this._oldIndex = responses.getItemNames().indexOf(this._statusCode);

        // Remove the response
        responses.removeItem(this._statusCode);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteResponseCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldResponse)) {
            return;
        }

        OpenApiOperation operation = (OpenApiOperation) NodePathUtil.resolveNodePath(this._operationPath, document);
        if (this.isNullOrUndefined(operation)) {
            return;
        }

        OpenApiResponses responses = operation.getResponses();
        if (this.isNullOrUndefined(responses)) {
            return;
        }

        OpenApiResponse newResponse = responses.createResponse();
        Library.readNode(this._oldResponse, newResponse);
        responses.insertItem(this._statusCode, newResponse, this._oldIndex);
    }

}
