package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.models.openapi.OpenApiServer;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Operation;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30PathItem;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Server;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Document;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Operation;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31PathItem;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Server;
import io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter;
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

    public DeleteAllServersCommand() {
    }

    public DeleteAllServersCommand(Node parent) {
        this._parentPath = Library.createNodePath(parent);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteAllServersCommand] Executing.");
        this._oldServers = new ArrayList<>();

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        // Save the old servers (if any)
        List<? extends Server> servers = getServers(parent);
        if (!this.isNullOrUndefined(servers)) {
            servers.forEach( server -> {
                this._oldServers.add(Library.writeNode(server));
            });
        }
        clearServers(parent);
    }

    private List<? extends Server> getServers(Node parent) {
        GetServersVisitor viz = new GetServersVisitor();
        parent.accept(viz);
        return viz._servers;
    }

    private void clearServers(Node parent) {
        ClearServersVisitor viz = new ClearServersVisitor();
        parent.accept(viz);
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

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        for (ObjectNode oldServer : this._oldServers) {
            OpenApiServer server = createServer(parent);
            Library.readNode(oldServer, server);
            addServer(parent, server);
        }
    }

    private OpenApiServer createServer(Node parent) {
        CreateServerVisitor viz = new CreateServerVisitor();
        parent.accept(viz);
        return (OpenApiServer) viz._server;
    }

    private void addServer(Node parent, OpenApiServer server) {
        AddServerVisitor viz = new AddServerVisitor(server);
        parent.accept(viz);
    }

    private abstract class ServersParentVisitor extends CombinedVisitorAdapter {
        @Override
        public void visitDocument(Document node) {
            if (ModelTypeUtil.isOpenApi30Model(node)) {
                visitOpenApi30Document((OpenApi30Document) node);
            } else if (ModelTypeUtil.isOpenApi31Model(node)) {
                visitOpenApi31Document((OpenApi31Document) node);
            }
        }

        @Override
        public void visitPathItem(OpenApiPathItem node) {
            if (ModelTypeUtil.isOpenApi30Model(node)) {
                visitOpenApi30PathItem((OpenApi30PathItem) node);
            } else if (ModelTypeUtil.isOpenApi31Model(node)) {
                visitOpenApi31PathItem((OpenApi31PathItem) node);
            }
        }

        @Override
        public void visitOperation(Operation node) {
            if (ModelTypeUtil.isOpenApi30Model(node)) {
                visitOpenApi30Operation((OpenApi30Operation) node);
            } else if (ModelTypeUtil.isOpenApi31Model(node)) {
                visitOpenApi31Operation((OpenApi31Operation) node);
            }
        }

        abstract void visitOpenApi31Document(OpenApi31Document node);
        abstract void visitOpenApi30Document(OpenApi30Document node);
        abstract void visitOpenApi31PathItem(OpenApi31PathItem node);
        abstract void visitOpenApi30PathItem(OpenApi30PathItem node);
        abstract void visitOpenApi31Operation(OpenApi31Operation node);
        abstract void visitOpenApi30Operation(OpenApi30Operation node);
    }

    private class GetServersVisitor extends ServersParentVisitor {
        List<? extends Server> _servers;

        @Override
        void visitOpenApi31Document(OpenApi31Document node) {
            this._servers = node.getServers();
        }

        @Override
        void visitOpenApi30Document(OpenApi30Document node) {
            this._servers = node.getServers();
        }

        @Override
        void visitOpenApi31PathItem(OpenApi31PathItem node) {
            this._servers = node.getServers();
        }

        @Override
        void visitOpenApi30PathItem(OpenApi30PathItem node) {
            this._servers = node.getServers();
        }

        @Override
        void visitOpenApi31Operation(OpenApi31Operation node) {
            this._servers = node.getServers();
        }

        @Override
        void visitOpenApi30Operation(OpenApi30Operation node) {
            this._servers = node.getServers();
        }
    }

    private class ClearServersVisitor extends ServersParentVisitor {
        @Override
        void visitOpenApi31Document(OpenApi31Document node) {
            node.clearServers();
        }

        @Override
        void visitOpenApi30Document(OpenApi30Document node) {
            node.clearServers();
        }

        @Override
        void visitOpenApi31PathItem(OpenApi31PathItem node) {
            node.clearServers();
        }

        @Override
        void visitOpenApi30PathItem(OpenApi30PathItem node) {
            node.clearServers();
        }

        @Override
        void visitOpenApi31Operation(OpenApi31Operation node) {
            node.clearServers();
        }

        @Override
        void visitOpenApi30Operation(OpenApi30Operation node) {
            node.clearServers();
        }
    }

    private class CreateServerVisitor extends ServersParentVisitor {
        Server _server;

        @Override
        void visitOpenApi31Document(OpenApi31Document node) {
            this._server = node.createServer();
        }

        @Override
        void visitOpenApi30Document(OpenApi30Document node) {
            this._server = node.createServer();
        }

        @Override
        void visitOpenApi31PathItem(OpenApi31PathItem node) {
            this._server = node.createServer();
        }

        @Override
        void visitOpenApi30PathItem(OpenApi30PathItem node) {
            this._server = node.createServer();
        }

        @Override
        void visitOpenApi31Operation(OpenApi31Operation node) {
            this._server = node.createServer();
        }

        @Override
        void visitOpenApi30Operation(OpenApi30Operation node) {
            this._server = node.createServer();
        }
    }

    private class AddServerVisitor extends ServersParentVisitor {
        private final OpenApiServer _server;

        public AddServerVisitor(OpenApiServer server) {
            this._server = server;
        }

        @Override
        void visitOpenApi31Document(OpenApi31Document node) {
            node.addServer((OpenApi31Server) this._server);
        }

        @Override
        void visitOpenApi30Document(OpenApi30Document node) {
            node.addServer((OpenApi30Server) this._server);
        }

        @Override
        void visitOpenApi31PathItem(OpenApi31PathItem node) {
            node.addServer((OpenApi31Server) this._server);
        }

        @Override
        void visitOpenApi30PathItem(OpenApi30PathItem node) {
            node.addServer((OpenApi30Server) this._server);
        }

        @Override
        void visitOpenApi31Operation(OpenApi31Operation node) {
            node.addServer((OpenApi31Server) this._server);
        }

        @Override
        void visitOpenApi30Operation(OpenApi30Operation node) {
            node.addServer((OpenApi30Server) this._server);
        }
    }
}
