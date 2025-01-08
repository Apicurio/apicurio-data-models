package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.OpenApiParametersParent;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.NodeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A command used to delete all parameters from:  PathItem, Operation
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllParametersCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _paramType;

    public List<ObjectNode> _oldParams;

    public DeleteAllParametersCommand() {
    }

    public DeleteAllParametersCommand(OpenApiParametersParent parent, String type) {
        this._parentPath = Library.createNodePath((Node) parent);
        this._paramType = type;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteAllParameters] Executing.");
        this._oldParams = new ArrayList<>();

        OpenApiParametersParent parent = (OpenApiParametersParent) NodePathUtil.resolveNodePath(this._parentPath, document);
        List<OpenApiParameter> parameters = parent.getParameters();
        if (this.isNullOrUndefined(parent) || this.isNullOrUndefined(parameters) || parameters.isEmpty()) {
            return;
        }

        // Save the params we're about to delete for later undo
        List<OpenApiParameter> paramsToRemove = new ArrayList<>();
        for (OpenApiParameter param : parameters) {
            if (NodeUtil.equals(param.getIn(), this._paramType)) {
                this._oldParams.add(Library.writeNode(param));
                paramsToRemove.add(param);
            }
        }

        if (this._oldParams.isEmpty()) {
            return;
        }

        paramsToRemove.forEach(paramToRemove -> {
            parent.removeParameter(paramToRemove);
        });
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteAllParameters] Reverting.");

        if (this.isNullOrUndefined(this._oldParams) || this._oldParams.size() == 0) {
            return;
        }

        OpenApiParametersParent parent = (OpenApiParametersParent) NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        this._oldParams.forEach(paramObj -> {
            OpenApiParameter parameter = parent.createParameter();
            Library.readNode(paramObj, parameter);
            parent.addParameter(parameter);
        });        
    }

}
