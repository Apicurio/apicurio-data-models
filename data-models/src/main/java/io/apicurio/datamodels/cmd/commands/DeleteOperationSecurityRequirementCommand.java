package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.SecurityRequirementsParent;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;

import java.util.List;

/**
 * A command used to delete a security requirement from an operation by index.
 * @author eric.wittmann@gmail.com
 */
public class DeleteOperationSecurityRequirementCommand extends AbstractCommand {

    public NodePath _operationPath;
    public int _index;

    public ObjectNode _oldRequirement;

    public DeleteOperationSecurityRequirementCommand() {
    }

    public DeleteOperationSecurityRequirementCommand(SecurityRequirementsParent operation, int index) {
        this._operationPath = NodePathUtil.createNodePath((Node) operation);
        this._index = index;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteOperationSecurityRequirementCommand] Executing.");
        this._oldRequirement = null;

        SecurityRequirementsParent operation = (SecurityRequirementsParent) NodePathUtil.resolveNodePath(
                this._operationPath, document);
        if (this.isNullOrUndefined(operation)) {
            return;
        }

        List<SecurityRequirement> security = (List<SecurityRequirement>) operation.getSecurity();
        if (this.isNullOrUndefined(security) || this._index < 0 || this._index >= security.size()) {
            return;
        }

        SecurityRequirement requirement = security.get(this._index);
        this._oldRequirement = Library.writeNode(requirement);
        operation.removeSecurity(requirement);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteOperationSecurityRequirementCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldRequirement)) {
            return;
        }

        SecurityRequirementsParent operation = (SecurityRequirementsParent) NodePathUtil.resolveNodePath(
                this._operationPath, document);
        if (this.isNullOrUndefined(operation)) {
            return;
        }

        SecurityRequirement requirement = operation.createSecurityRequirement();
        Library.readNode(this._oldRequirement, requirement);
        operation.insertSecurity(requirement, this._index);
    }

}
