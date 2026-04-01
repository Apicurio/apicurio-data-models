package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.openapi.OpenApiServer;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A command used to delete a variable from a server.
 * @author eric.wittmann@gmail.com
 */
public class DeleteServerVariableCommand extends AbstractCommand {

    public NodePath _serverPath;
    public String _variableName;

    public ObjectNode _oldVariable;
    public int _oldIndex;

    public DeleteServerVariableCommand() {
    }

    public DeleteServerVariableCommand(OpenApiServer server, String variableName) {
        this._serverPath = NodePathUtil.createNodePath((Node) server);
        this._variableName = variableName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteServerVariableCommand] Executing.");
        this._oldVariable = null;

        Server server = (Server) NodePathUtil.resolveNodePath(this._serverPath, document);
        if (this.isNullOrUndefined(server)) {
            return;
        }

        Map<String, ? extends ServerVariable> variables = (Map<String, ? extends ServerVariable>) server.getVariables();
        if (this.isNullOrUndefined(variables) || !variables.containsKey(this._variableName)) {
            return;
        }

        // Save the variable and its index for undo
        ServerVariable variable = variables.get(this._variableName);
        this._oldVariable = Library.writeNode(variable);
        List<String> variableNames = new ArrayList<>(variables.keySet());
        this._oldIndex = variableNames.indexOf(this._variableName);

        // Remove the variable
        server.removeVariable(this._variableName);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteServerVariableCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldVariable)) {
            return;
        }

        Server server = (Server) NodePathUtil.resolveNodePath(this._serverPath, document);
        if (this.isNullOrUndefined(server)) {
            return;
        }

        ServerVariable newVariable = server.createServerVariable();
        Library.readNode(this._oldVariable, newVariable);
        server.insertVariable(this._variableName, newVariable, this._oldIndex);
    }

}
