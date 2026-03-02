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

import java.util.List;

/**
 * A command used to delete a parameter from a path item or operation.
 * @author eric.wittmann@gmail.com
 */
public class DeleteParameterCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _parameterName;
    public String _parameterLocation;

    public ObjectNode _oldParameter;

    public DeleteParameterCommand() {
    }

    public DeleteParameterCommand(OpenApiParametersParent parent, String parameterName,
                                  String parameterLocation) {
        this._parentPath = NodePathUtil.createNodePath((Node) parent);
        this._parameterName = parameterName;
        this._parameterLocation = parameterLocation;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteParameterCommand] Executing.");
        this._oldParameter = null;

        OpenApiParametersParent parent = (OpenApiParametersParent) NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        List<? extends OpenApiParameter> parameters = (List<? extends OpenApiParameter>) parent.getParameters();
        if (this.isNullOrUndefined(parameters)) {
            return;
        }

        for (OpenApiParameter param : parameters) {
            if (this._parameterName.equals(param.getName()) &&
                    this._parameterLocation.equals(param.getIn())) {
                this._oldParameter = Library.writeNode(param);
                parent.removeParameter(param);
                return;
            }
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteParameterCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldParameter)) {
            return;
        }

        OpenApiParametersParent parent = (OpenApiParametersParent) NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        OpenApiParameter newParameter = parent.createParameter();
        Library.readNode(this._oldParameter, newParameter);
        parent.addParameter(newParameter);
    }

}
