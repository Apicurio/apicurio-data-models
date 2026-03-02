package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Operation;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30RequestBody;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Operation;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31RequestBody;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.util.NodeUtil;

/**
 * A command used to add a request body to an operation (OpenAPI 3.0+ only).
 * @author eric.wittmann@gmail.com
 */
public class AddRequestBodyCommand extends AbstractCommand {

    public NodePath _operationPath;

    public boolean _created;

    public AddRequestBodyCommand() {
    }

    public AddRequestBodyCommand(OpenApi30Operation operation) {
        this._operationPath = NodePathUtil.createNodePath(operation);
    }

    public AddRequestBodyCommand(OpenApi31Operation operation) {
        this._operationPath = NodePathUtil.createNodePath(operation);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[AddRequestBodyCommand] Executing.");
        this._created = false;

        Object operation = NodePathUtil.resolveNodePath(this._operationPath, document);
        if (this.isNullOrUndefined(operation)) {
            return;
        }

        // Don't create if one already exists
        Object existing = NodeUtil.getProperty(operation, "requestBody");
        if (!this.isNullOrUndefined(existing)) {
            return;
        }

        if (operation instanceof OpenApi30Operation) {
            OpenApi30Operation op30 = (OpenApi30Operation) operation;
            OpenApi30RequestBody requestBody = op30.createRequestBody();
            op30.setRequestBody(requestBody);
            this._created = true;
        } else if (operation instanceof OpenApi31Operation) {
            OpenApi31Operation op31 = (OpenApi31Operation) operation;
            OpenApi31RequestBody requestBody = op31.createRequestBody();
            op31.setRequestBody(requestBody);
            this._created = true;
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[AddRequestBodyCommand] Reverting.");
        if (!this._created) {
            return;
        }

        Object operation = NodePathUtil.resolveNodePath(this._operationPath, document);
        if (this.isNullOrUndefined(operation)) {
            return;
        }

        if (operation instanceof OpenApi30Operation) {
            ((OpenApi30Operation) operation).setRequestBody(null);
        } else if (operation instanceof OpenApi31Operation) {
            ((OpenApi31Operation) operation).setRequestBody(null);
        }
    }

}
