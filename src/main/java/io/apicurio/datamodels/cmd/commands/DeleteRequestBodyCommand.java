package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Operation;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30RequestBody;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Operation;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31RequestBody;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.NodeUtil;

/**
 * A command used to delete a request body from an operation (OpenAPI 3.0+ only).
 * @author eric.wittmann@gmail.com
 */
public class DeleteRequestBodyCommand extends AbstractCommand {

    public NodePath _operationPath;

    public ObjectNode _oldRequestBody;

    public DeleteRequestBodyCommand() {
    }

    public DeleteRequestBodyCommand(OpenApi30Operation operation) {
        this._operationPath = NodePathUtil.createNodePath(operation);
    }

    public DeleteRequestBodyCommand(OpenApi31Operation operation) {
        this._operationPath = NodePathUtil.createNodePath(operation);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteRequestBodyCommand] Executing.");
        this._oldRequestBody = null;

        Object operation = NodePathUtil.resolveNodePath(this._operationPath, document);
        if (this.isNullOrUndefined(operation)) {
            return;
        }

        Object requestBody = NodeUtil.getProperty(operation, "requestBody");
        if (this.isNullOrUndefined(requestBody)) {
            return;
        }

        // Save the request body for undo
        this._oldRequestBody = Library.writeNode((io.apicurio.datamodels.models.Node) requestBody);

        // Remove the request body
        if (operation instanceof OpenApi30Operation) {
            ((OpenApi30Operation) operation).setRequestBody(null);
        } else if (operation instanceof OpenApi31Operation) {
            ((OpenApi31Operation) operation).setRequestBody(null);
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteRequestBodyCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldRequestBody)) {
            return;
        }

        Object operation = NodePathUtil.resolveNodePath(this._operationPath, document);
        if (this.isNullOrUndefined(operation)) {
            return;
        }

        if (operation instanceof OpenApi30Operation) {
            OpenApi30Operation op30 = (OpenApi30Operation) operation;
            OpenApi30RequestBody requestBody = op30.createRequestBody();
            Library.readNode(this._oldRequestBody, requestBody);
            op30.setRequestBody(requestBody);
        } else if (operation instanceof OpenApi31Operation) {
            OpenApi31Operation op31 = (OpenApi31Operation) operation;
            OpenApi31RequestBody requestBody = op31.createRequestBody();
            Library.readNode(this._oldRequestBody, requestBody);
            op31.setRequestBody(requestBody);
        }
    }

}
