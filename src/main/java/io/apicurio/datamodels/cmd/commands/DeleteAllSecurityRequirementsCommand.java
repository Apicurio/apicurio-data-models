package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.asyncapi.v20.AsyncApi20SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v20.AsyncApi20Server;
import io.apicurio.datamodels.models.asyncapi.v21.AsyncApi21SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v21.AsyncApi21Server;
import io.apicurio.datamodels.models.asyncapi.v22.AsyncApi22SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v22.AsyncApi22Server;
import io.apicurio.datamodels.models.asyncapi.v23.AsyncApi23SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v23.AsyncApi23Server;
import io.apicurio.datamodels.models.asyncapi.v24.AsyncApi24SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v24.AsyncApi24Server;
import io.apicurio.datamodels.models.asyncapi.v25.AsyncApi25SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v25.AsyncApi25Server;
import io.apicurio.datamodels.models.asyncapi.v26.AsyncApi26SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v26.AsyncApi26Server;
import io.apicurio.datamodels.models.openapi.OpenApiDocument;
import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import io.apicurio.datamodels.models.openapi.OpenApiSecurityRequirement;
import io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.NodeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A command used to delete all security requirements from a document or operation.
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllSecurityRequirementsCommand extends AbstractCommand {

    public NodePath _parentPath;
    public List<ObjectNode> _oldSecurityRequirements;
    
    public DeleteAllSecurityRequirementsCommand() {
    }

    public DeleteAllSecurityRequirementsCommand(Node parent) {
        this._parentPath = Library.createNodePath(parent);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteAllSecurityRequirementsCommand] Executing.");
        this._oldSecurityRequirements = new ArrayList<>();

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        // Save the old security-requirements (if any)
        List<? extends SecurityRequirement> requirements = (List<? extends SecurityRequirement>) NodeUtil.getProperty(parent, "security");
        if (!this.isNullOrUndefined(requirements)) {
            requirements.forEach( req -> {
                this._oldSecurityRequirements.add(Library.writeNode(req));
            });
        }

        clearSecurityRequirements(parent);
    }

    private void clearSecurityRequirements(Node parent) {
        ClearSecurityRequirementsVisitor csrv = new ClearSecurityRequirementsVisitor();
        parent.accept(csrv);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteAllSecurityRequirementsCommand] Reverting.");
        if (NodeUtil.isDefined(this._oldSecurityRequirements) && this._oldSecurityRequirements.isEmpty()) {
            return;
        }

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        List<? extends SecurityRequirement> requirements = (List<? extends SecurityRequirement>) NodeUtil.getProperty(parent, "security");
        if (this.isNullOrUndefined(requirements)) {
            requirements = new ArrayList<>();
            NodeUtil.setProperty(parent, "security", requirements);
        }
        for (ObjectNode oldSecurityRequirement : this._oldSecurityRequirements) {
            SecurityRequirement requirement = createSecurityRequirement(parent);
            Library.readNode(oldSecurityRequirement, requirement);
            addSecurityRequirement(parent, requirement);
        }
    }

    private SecurityRequirement createSecurityRequirement(Node parent) {
        CreateSecurityRequirementVisitor csrv = new CreateSecurityRequirementVisitor();
        parent.accept(csrv);
        return csrv._requirement;
    }

    private void addSecurityRequirement(Node parent, SecurityRequirement requirement) {
        AddSecurityRequirementVisitor asrv = new AddSecurityRequirementVisitor(requirement);
        parent.accept(asrv);
    }

    private class ClearSecurityRequirementsVisitor extends CombinedVisitorAdapter {

        public ClearSecurityRequirementsVisitor() {
        }

        @Override
        public void visitDocument(Document node) {
            ((OpenApiDocument) node).clearSecurity();
        }

        @Override
        public void visitOperation(Operation node) {
            ((OpenApiOperation) node).clearSecurity();
        }

        @Override
        public void visitServer(Server node) {
            switch (node.root().modelType()) {
                case ASYNCAPI20:
                    ((AsyncApi20Server) node).clearSecurity();
                    break;
                case ASYNCAPI21:
                    ((AsyncApi21Server) node).clearSecurity();
                    break;
                case ASYNCAPI22:
                    ((AsyncApi22Server) node).clearSecurity();
                    break;
                case ASYNCAPI23:
                    ((AsyncApi23Server) node).clearSecurity();
                    break;
                case ASYNCAPI24:
                    ((AsyncApi24Server) node).clearSecurity();
                    break;
                case ASYNCAPI25:
                    ((AsyncApi25Server) node).clearSecurity();
                    break;
                case ASYNCAPI26:
                    ((AsyncApi26Server) node).clearSecurity();
                    break;
                default:
                    break;
            }
        }
    }

    private class CreateSecurityRequirementVisitor extends CombinedVisitorAdapter {
        SecurityRequirement _requirement;

        public CreateSecurityRequirementVisitor() {
        }

        @Override
        public void visitDocument(Document node) {
            this._requirement = ((OpenApiDocument) node).createSecurityRequirement();
        }

        @Override
        public void visitOperation(Operation node) {
            this._requirement = ((OpenApiOperation) node).createSecurityRequirement();
        }

        @Override
        public void visitServer(Server node) {
            switch (node.root().modelType()) {
                case ASYNCAPI20:
                    this._requirement = ((AsyncApi20Server) node).createSecurityRequirement();
                    break;
                case ASYNCAPI21:
                    this._requirement = ((AsyncApi21Server) node).createSecurityRequirement();
                    break;
                case ASYNCAPI22:
                    this._requirement = ((AsyncApi22Server) node).createSecurityRequirement();
                    break;
                case ASYNCAPI23:
                    this._requirement = ((AsyncApi23Server) node).createSecurityRequirement();
                    break;
                case ASYNCAPI24:
                    this._requirement = ((AsyncApi24Server) node).createSecurityRequirement();
                    break;
                case ASYNCAPI25:
                    this._requirement = ((AsyncApi25Server) node).createSecurityRequirement();
                    break;
                case ASYNCAPI26:
                    this._requirement = ((AsyncApi26Server) node).createSecurityRequirement();
                    break;
                default:
                    break;
            }
        }
    }

    private class AddSecurityRequirementVisitor extends CombinedVisitorAdapter {
        SecurityRequirement _requirement;

        public AddSecurityRequirementVisitor(SecurityRequirement requirement) {
            this._requirement = requirement;
        }

        @Override
        public void visitDocument(Document node) {
            ((OpenApiDocument) node).addSecurity((OpenApiSecurityRequirement) this._requirement);
        }

        @Override
        public void visitOperation(Operation node) {
            ((OpenApiOperation) node).addSecurity((OpenApiSecurityRequirement) this._requirement);
        }

        @Override
        public void visitServer(Server node) {
            switch (node.root().modelType()) {
                case ASYNCAPI20:
                    ((AsyncApi20Server) node).addSecurity((AsyncApi20SecurityRequirement) this._requirement);
                    break;
                case ASYNCAPI21:
                    ((AsyncApi21Server) node).addSecurity((AsyncApi21SecurityRequirement) this._requirement);
                    break;
                case ASYNCAPI22:
                    ((AsyncApi22Server) node).addSecurity((AsyncApi22SecurityRequirement) this._requirement);
                    break;
                case ASYNCAPI23:
                    ((AsyncApi23Server) node).addSecurity((AsyncApi23SecurityRequirement) this._requirement);
                    break;
                case ASYNCAPI24:
                    ((AsyncApi24Server) node).addSecurity((AsyncApi24SecurityRequirement) this._requirement);
                    break;
                case ASYNCAPI25:
                    ((AsyncApi25Server) node).addSecurity((AsyncApi25SecurityRequirement) this._requirement);
                    break;
                case ASYNCAPI26:
                    ((AsyncApi26Server) node).addSecurity((AsyncApi26SecurityRequirement) this._requirement);
                    break;
                default:
                    break;
            }
        }
    }

}
