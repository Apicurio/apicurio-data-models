package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.openapi.OpenApiServer;
import io.apicurio.datamodels.models.openapi.OpenApiServersParent;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;

import java.util.List;

/**
 * A command used to delete a server from any node that supports servers.
 * @author eric.wittmann@gmail.com
 */
public class DeleteServerCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _serverUrl;

    public ObjectNode _oldServer;
    public int _serverIndex;

    public DeleteServerCommand() {
    }

    public DeleteServerCommand(OpenApiServersParent parent, String serverUrl) {
        this._parentPath = NodePathUtil.createNodePath((Node) parent);
        this._serverUrl = serverUrl;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteServerCommand] Executing.");
        this._oldServer = null;
        this._serverIndex = -1;

        OpenApiServersParent parent = (OpenApiServersParent) NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        List<? extends OpenApiServer> servers = (List<? extends OpenApiServer>) parent.getServers();
        if (this.isNullOrUndefined(servers)) {
            return;
        }

        int idx = 0;
        for (OpenApiServer server : servers) {
            if (this._serverUrl.equals(server.getUrl())) {
                this._oldServer = Library.writeNode(server);
                this._serverIndex = idx;
                parent.removeServer(server);
                return;
            }
            idx++;
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteServerCommand] Reverting.");
        if (this._serverIndex < 0 || this.isNullOrUndefined(this._oldServer)) {
            return;
        }

        OpenApiServersParent parent = (OpenApiServersParent) NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        OpenApiServer newServer = parent.createServer();
        Library.readNode(this._oldServer, newServer);
        parent.insertServer(newServer, this._serverIndex);
    }

}
