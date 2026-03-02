package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.openapi.OpenApiServer;
import io.apicurio.datamodels.models.openapi.OpenApiServersParent;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Server;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Server;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.util.RegexUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A command used to add a new server to any node that supports servers.
 * @author eric.wittmann@gmail.com
 */
public class AddServerCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _serverUrl;
    public String _serverDescription;

    public boolean _serverCreated;

    public AddServerCommand() {
    }

    public AddServerCommand(OpenApiServersParent parent, String serverUrl, String serverDescription) {
        this._parentPath = NodePathUtil.createNodePath((Node) parent);
        this._serverUrl = serverUrl;
        this._serverDescription = serverDescription;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[AddServerCommand] Executing.");
        this._serverCreated = false;

        OpenApiServersParent parent = (OpenApiServersParent) NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        // Check if server already exists
        List<? extends OpenApiServer> existingServers = (List<? extends OpenApiServer>) parent.getServers();
        if (!this.isNullOrUndefined(existingServers)) {
            for (OpenApiServer server : existingServers) {
                if (this._serverUrl.equals(server.getUrl())) {
                    return;
                }
            }
        }

        // Create new server
        OpenApiServer newServer = parent.createServer();
        newServer.setUrl(this._serverUrl);
        if (!this.isNullOrUndefined(this._serverDescription) && !this._serverDescription.isEmpty()) {
            newServer.setDescription(this._serverDescription);
        }

        // Extract and create server variables
        List<String> variableNames = extractVariables(this._serverUrl);
        for (String variableName : variableNames) {
            if (ModelTypeUtil.isOpenApi30Model((Node) parent)) {
                OpenApi30Server server30 = (OpenApi30Server) newServer;
                server30.addVariable(variableName, server30.createServerVariable());
            } else if (ModelTypeUtil.isOpenApi31Model((Node) parent)) {
                OpenApi31Server server31 = (OpenApi31Server) newServer;
                server31.addVariable(variableName, server31.createServerVariable());
            }
        }

        parent.addServer(newServer);
        this._serverCreated = true;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[AddServerCommand] Reverting.");
        if (!this._serverCreated) {
            return;
        }

        OpenApiServersParent parent = (OpenApiServersParent) NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        List<? extends OpenApiServer> servers = (List<? extends OpenApiServer>) parent.getServers();
        if (this.isNullOrUndefined(servers)) {
            return;
        }

        for (OpenApiServer server : servers) {
            if (this._serverUrl.equals(server.getUrl())) {
                parent.removeServer(server);
                return;
            }
        }
    }

    /**
     * Extracts variable names from a server URL template.
     * For example, "http://{domain}.example.com:{port}/api" returns ["domain", "port"].
     */
    private static List<String> extractVariables(String url) {
        List<String> variables = new ArrayList<>();
        List<String[]> matches = RegexUtil.findMatches(url, "\\{([^}]+)\\}");
        for (String[] match : matches) {
            variables.add(match[1]);
        }
        return variables;
    }

}
