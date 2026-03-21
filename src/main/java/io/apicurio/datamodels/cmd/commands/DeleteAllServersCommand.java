package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.asyncapi.AsyncApiDocument;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServer;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServers;
import io.apicurio.datamodels.models.openapi.OpenApiServer;
import io.apicurio.datamodels.models.openapi.OpenApiServersParent;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;

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
    public List<String> _oldServerNames;

    public DeleteAllServersCommand() {
    }

    public DeleteAllServersCommand(OpenApiServersParent parent) {
        this._parentPath = Library.createNodePath((Node) parent);
    }

    public DeleteAllServersCommand(AsyncApiDocument document) {
        this._parentPath = Library.createNodePath(document);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteAllServersCommand] Executing.");
        this._oldServers = new ArrayList<>();

        if (ModelTypeUtil.isAsyncApiModel(document)) {
            executeForAsyncApi((AsyncApiDocument) document);
        } else {
            executeForOpenApi(document);
        }
    }

    private void executeForOpenApi(Document document) {
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

    private void executeForAsyncApi(AsyncApiDocument doc) {
        AsyncApiServers servers = doc.getServers();
        if (this.isNullOrUndefined(servers)) {
            return;
        }

        this._oldServerNames = new ArrayList<>();

        // Save old servers with their names
        List<String> names = servers.getItemNames();
        if (!this.isNullOrUndefined(names)) {
            for (String name : names) {
                AsyncApiServer server = servers.getItem(name);
                this._oldServerNames.add(name);
                this._oldServers.add(Library.writeNode(server));
            }
        }
        servers.clearItems();
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

        if (ModelTypeUtil.isAsyncApiModel(document)) {
            undoForAsyncApi((AsyncApiDocument) document);
        } else {
            undoForOpenApi(document);
        }
    }

    private void undoForOpenApi(Document document) {
        OpenApiServersParent parent = (OpenApiServersParent) NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        for (ObjectNode oldServer : this._oldServers) {
            OpenApiServer server = (OpenApiServer) parent.createServer();
            Library.readNode(oldServer, server);
            parent.addServer(server);
        }
    }

    private void undoForAsyncApi(AsyncApiDocument doc) {
        AsyncApiServers servers = doc.getServers();
        if (this.isNullOrUndefined(servers)) {
            servers = doc.createServers();
            doc.setServers(servers);
        }

        for (int i = 0; i < this._oldServers.size(); i++) {
            AsyncApiServer server = servers.createServer();
            Library.readNode(this._oldServers.get(i), server);
            servers.addItem(this._oldServerNames.get(i), server);
        }
    }
}
