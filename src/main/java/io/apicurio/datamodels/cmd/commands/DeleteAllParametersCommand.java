package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter;
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

    public DeleteAllParametersCommand(OpenApiPathItem parent, String type) {
        this._parentPath = Library.createNodePath(parent);
        this._paramType = type;
    }

    public DeleteAllParametersCommand(OpenApiOperation parent, String type) {
        this._parentPath = Library.createNodePath(parent);
        this._paramType = type;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteAllParameters] Executing.");
        this._oldParams = new ArrayList<>();

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        List<OpenApiParameter> parameters = getParameters(parent);
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
            parameters.remove(paramToRemove);
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

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        this._oldParams.forEach(paramObj -> {
            OpenApiParameter parameter = createParameter(parent);
            Library.readNode(paramObj, parameter);
            addParameter(parent, parameter);
        });        
    }

    private void addParameter(Node parent, OpenApiParameter parameter) {
        AddParameterVisitor gpv = new AddParameterVisitor(parameter);
        parent.accept(gpv);
    }

    private OpenApiParameter createParameter(Node parent) {
        CreateParameterVisitor gpv = new CreateParameterVisitor();
        parent.accept(gpv);
        return gpv._param;
    }

    private List<OpenApiParameter> getParameters(Node parent) {
        GetParametersVisitor gpv = new GetParametersVisitor();
        parent.accept(gpv);
        return gpv._params;
    }

    private class GetParametersVisitor extends CombinedVisitorAdapter {
        List<OpenApiParameter> _params;

        @Override
        public void visitPathItem(OpenApiPathItem node) {
            this._params = node.getParameters();
        }

        @Override
        public void visitOperation(Operation node) {
            this._params = ((OpenApiOperation) node).getParameters();
        }
    }

    private class CreateParameterVisitor extends CombinedVisitorAdapter {
        OpenApiParameter _param;

        @Override
        public void visitPathItem(OpenApiPathItem node) {
            this._param = node.createParameter();
        }

        @Override
        public void visitOperation(Operation node) {
            this._param = ((OpenApiOperation) node).createParameter();
        }
    }

    private class AddParameterVisitor extends CombinedVisitorAdapter {
        OpenApiParameter _param;

        public AddParameterVisitor(OpenApiParameter param) {
            this._param = param;
        }

        @Override
        public void visitPathItem(OpenApiPathItem node) {
            node.addParameter(_param);
        }

        @Override
        public void visitOperation(Operation node) {
            ((OpenApiOperation) node).addParameter(_param);
        }
    }

}
