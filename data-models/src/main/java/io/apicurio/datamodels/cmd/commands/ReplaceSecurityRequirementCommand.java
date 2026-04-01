package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.SecurityRequirementsParent;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;

import java.util.List;

/**
 * A command used to replace a definition schema with a newer version.
 * @author eric.wittmann@gmail.com
 */
public class ReplaceSecurityRequirementCommand extends AbstractCommand {

    public NodePath _parentPath;
    public ObjectNode _oldRequirement;
    public ObjectNode _newRequirement;

    public boolean _replaced;

    public ReplaceSecurityRequirementCommand() {
    }
    
    public ReplaceSecurityRequirementCommand(SecurityRequirement old, SecurityRequirement replacement) {
        this._parentPath = Library.createNodePath(old.parent());
        this._oldRequirement = Library.writeNode(old);
        this._newRequirement = Library.writeNode(replacement);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[ReplaceSecurityRequirementCommand] Executing.");
        this._replaced = false;

        SecurityRequirementsParent parent = (SecurityRequirementsParent) NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        SecurityRequirement oldRequirement = parent.createSecurityRequirement();
        Library.readNode(this._oldRequirement, oldRequirement);

        int oldIdx = this.indexOfRequirement(parent.getSecurity(), oldRequirement);
        if (oldIdx == -1) {
            return;
        }

        SecurityRequirement newRequirement = parent.createSecurityRequirement();
        Library.readNode(this._newRequirement, newRequirement);
        parent.getSecurity().set(oldIdx, newRequirement);

        this._replaced = true;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[ReplaceSecurityRequirementCommand] Reverting.");
        if (!this._replaced) {
            return;
        }

        SecurityRequirementsParent parent = (SecurityRequirementsParent) NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        SecurityRequirement replacementRequirement = parent.createSecurityRequirement();
        Library.readNode(this._newRequirement, replacementRequirement);

        int idx = this.indexOfRequirement(parent.getSecurity(), replacementRequirement);
        if (idx == -1) {
            return;
        }

        SecurityRequirement originalRequirement = parent.createSecurityRequirement();
        Library.readNode(this._oldRequirement, originalRequirement);

        parent.getSecurity().set(idx, originalRequirement);
    }

    protected int indexOfRequirement(List<SecurityRequirement> requirements, SecurityRequirement requirement) {
        int idx = 0;
        for (SecurityRequirement r : requirements) {
            if (this.isEqual(r, requirement)) {
                return idx;
            }
            idx++;
        }
        return -1;
    }

    protected boolean isEqual(SecurityRequirement req1, SecurityRequirement req2) {
        List<String> names1 = req1.getItemNames();
        List<String> names2 = req2.getItemNames();
        if (names1.size() != names2.size()) {
            return false;
        }
        boolean rval = true;
        for (String name1 : names1) {
            if (names2.indexOf(name1) == -1 || !areScopesEqual(req1.getItem(name1), req2.getItem(name1))) {
                rval = false;
            }
        }
        return rval;
    }

    protected boolean areScopesEqual(List<String> scopes1, List<String> scopes2) {
        if(scopes1.size() != scopes2.size()) {
            return false;
        }
        boolean rval = true;
        for (String scope1 : scopes1) {
            if (scopes2.indexOf(scope1) == -1) {
                rval = false;
            }
        }
        return rval;
    }

}
