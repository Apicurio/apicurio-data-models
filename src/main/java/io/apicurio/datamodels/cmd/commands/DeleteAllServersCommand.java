package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.openapi.OpenApiServer;
import io.apicurio.datamodels.models.openapi.OpenApiServersParent;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A command used to delete all servers from a document.
 * 
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllServersCommand extends AbstractCommand {

    public NodePath _parentPath;
    public List<ObjectNode> _oldServers;

    public DeleteAllServersCommand() {
    }

    public DeleteAllServersCommand(OpenApiServersParent parent) {
        this._parentPath = Library.createNodePath((Node) parent);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteAllServersCommand] Executing.");
        this._oldServers = new ArrayList<>();

        OpenApiServersParent parent = (OpenApiServersParent) NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        // Save the old servers (if any)
        List<? extends Server> servers = parent.getServers();
        if (!this.isNullOrUndefined(servers)) {
            servers.forEach( server -> {
                this._oldServers.add(Library.writeNode(server));
            });
        }
        parent.clearServers();
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteAllServersCommand] Reverting.");
        if (this._oldServers.isEmpty()) {
            return;
        }

        OpenApiServersParent parent = (OpenApiServersParent) NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        for (ObjectNode oldServer : this._oldServers) {
            OpenApiServer server = parent.createServer();
            Library.readNode(oldServer, server);
            parent.addServer(server);
        }
    }
}
