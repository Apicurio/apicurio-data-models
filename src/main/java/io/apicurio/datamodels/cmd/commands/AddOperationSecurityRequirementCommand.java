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
 * A command used to add a security requirement to an operation.
 * @author eric.wittmann@gmail.com
 */
public class AddOperationSecurityRequirementCommand extends AbstractCommand {

    public NodePath _operationPath;
    public ObjectNode _requirement;

    public boolean _added;

    public AddOperationSecurityRequirementCommand() {
    }

    public AddOperationSecurityRequirementCommand(SecurityRequirementsParent operation,
                                                  SecurityRequirement requirement) {
        this._operationPath = NodePathUtil.createNodePath((Node) operation);
        this._requirement = Library.writeNode(requirement);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[AddOperationSecurityRequirementCommand] Executing.");
        this._added = false;

        SecurityRequirementsParent operation = (SecurityRequirementsParent) NodePathUtil.resolveNodePath(
                this._operationPath, document);
        if (this.isNullOrUndefined(operation)) {
            return;
        }

        SecurityRequirement requirement = operation.createSecurityRequirement();
        Library.readNode(this._requirement, requirement);
        operation.addSecurity(requirement);
        this._added = true;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[AddOperationSecurityRequirementCommand] Reverting.");
        if (!this._added) {
            return;
        }

        SecurityRequirementsParent operation = (SecurityRequirementsParent) NodePathUtil.resolveNodePath(
                this._operationPath, document);
        if (this.isNullOrUndefined(operation)) {
            return;
        }

        List<SecurityRequirement> security = (List<SecurityRequirement>) operation.getSecurity();
        if (this.isNullOrUndefined(security) || security.isEmpty()) {
            return;
        }

        // Remove the last added requirement
        SecurityRequirement last = security.get(security.size() - 1);
        operation.removeSecurity(last);
    }

}
