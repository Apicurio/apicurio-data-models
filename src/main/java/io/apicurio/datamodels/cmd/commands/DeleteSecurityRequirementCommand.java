package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.SecurityRequirementsParent;
import io.apicurio.datamodels.util.LoggerUtil;

import java.util.List;

/**
 * A command used to delete a document-level security requirement by index.
 * @author eric.wittmann@gmail.com
 */
public class DeleteSecurityRequirementCommand extends AbstractCommand {

    public int _index;

    public ObjectNode _oldRequirement;

    public DeleteSecurityRequirementCommand() {
    }

    public DeleteSecurityRequirementCommand(int index) {
        this._index = index;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteSecurityRequirementCommand] Executing.");
        this._oldRequirement = null;

        SecurityRequirementsParent secParent = (SecurityRequirementsParent) document;
        List<SecurityRequirement> security = (List<SecurityRequirement>) secParent.getSecurity();
        if (this.isNullOrUndefined(security) || this._index < 0 || this._index >= security.size()) {
            return;
        }

        SecurityRequirement requirement = security.get(this._index);
        this._oldRequirement = Library.writeNode(requirement);
        secParent.removeSecurity(requirement);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteSecurityRequirementCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldRequirement)) {
            return;
        }

        SecurityRequirementsParent secParent = (SecurityRequirementsParent) document;
        SecurityRequirement requirement = secParent.createSecurityRequirement();
        Library.readNode(this._oldRequirement, requirement);
        secParent.insertSecurity(requirement, this._index);
    }

}
