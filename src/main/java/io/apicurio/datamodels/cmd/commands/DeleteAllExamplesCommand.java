package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.openapi.OpenApiExample;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.OpenApiMediaType;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Example;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Header;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Parameter;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Example;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Header;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Parameter;
import io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A command used to delete a single mediaType from an operation.
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllExamplesCommand extends AbstractCommand {

    public NodePath _parentPath;

    public Map<String, ObjectNode> _oldExamples;

    public DeleteAllExamplesCommand() {
    }

    public DeleteAllExamplesCommand(OpenApiMediaType parent) {
        this._parentPath = NodePathUtil.createNodePath(parent);
    }

    public DeleteAllExamplesCommand(OpenApiParameter parent) {
        this._parentPath = NodePathUtil.createNodePath(parent);
    }

    public DeleteAllExamplesCommand(OpenApiHeader parent) {
        this._parentPath = NodePathUtil.createNodePath(parent);
    }

    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteAllExamplesCommand] Executing.");

        if (this.isNullOrUndefined(document)) {
            LoggerUtil.debug("[DeleteAllExamplesCommand] Could not execute the command, invalid argument.");
            return;
        }

        if (this.isNullOrUndefined(this._parentPath)) {
            LoggerUtil.debug("[DeleteAllExamplesCommand] Could not execute the command, problem when unmarshalling.");
            return;
        }

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            LoggerUtil.debug("[DeleteAllExamplesCommand] Parent node not found.");
            return;
        }

        // Get the examples from the parent
        GetExamplesVisitor gev = new GetExamplesVisitor();
        parent.accept(gev);

        // Save the examples to enable undo
        this._oldExamples = new LinkedHashMap<>();
        gev.examples.keySet().forEach(k -> {
            Node n = gev.examples.get(k);
            this._oldExamples.put(k, Library.writeNode(n));
        });

        // Clear (remove all) the examples
        ClearExamplesVisitor cev = new ClearExamplesVisitor();
        parent.accept(cev);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        if (this.isNullOrUndefined(document)) {
            LoggerUtil.debug("[DeleteAllExamplesCommand] Could not revert the command, invalid argument.");
            return;
        }

        if (this.isNullOrUndefined(this._parentPath)) {
            LoggerUtil.debug("[DeleteAllExamplesCommand] Could not revert the command, problem when unmarshalling.");
            return;
        }

        LoggerUtil.info("[DeleteAllExamplesCommand] Reverting.");

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            LoggerUtil.info("[DeleteAllExamplesCommand] No parent node found.");
            return;
        }

        if (this.isNullOrUndefined(this._oldExamples)) {
            LoggerUtil.info("[DeleteAllExamplesCommand] Could not revert. Previous data is not available.");
            return;
        }

        for (String k : this._oldExamples.keySet()) {
            Node example = createExample(parent);
            Library.readNode(this._oldExamples.get(k), (Node) example);
            addExample(parent, k, example);
        }
    }

    private Node createExample(Node parent) {
        CreateExampleVisitor cev = new CreateExampleVisitor();
        parent.accept(cev);
        return cev.example;
    }

    private void addExample(Node parent, String name, Node example) {
        AddExampleVisitor aev = new AddExampleVisitor(name, example);
        parent.accept(aev);
    }

    private static abstract class ExamplesParentVisitor extends CombinedVisitorAdapter {

        protected abstract void visitParameterOpenApi30(OpenApi30Parameter param);
        protected abstract void visitParameterOpenApi31(OpenApi31Parameter param);
        protected abstract void visitHeaderOpenApi30(OpenApi30Header param);
        protected abstract void visitHeaderOpenApi31(OpenApi31Header param);

        @Override
        public void visitParameter(Parameter node) {
            if (ModelTypeUtil.isOpenApi30Model(node)) {
                visitParameterOpenApi30((OpenApi30Parameter) node);
            } else if (ModelTypeUtil.isOpenApi31Model(node)) {
                visitParameterOpenApi31((OpenApi31Parameter) node);
            }
        }

        @Override
        public void visitHeader(OpenApiHeader node) {
            if (ModelTypeUtil.isOpenApi30Model(node)) {
                visitHeaderOpenApi30((OpenApi30Header) node);
            } else if (ModelTypeUtil.isOpenApi31Model(node)) {
                visitHeaderOpenApi31((OpenApi31Header) node);
            }
        }

        @Override
        public abstract void visitMediaType(OpenApiMediaType node);
    }

    private static class GetExamplesVisitor extends ExamplesParentVisitor {
        public Map<String, ? extends Node> examples;

        @Override
        protected void visitParameterOpenApi30(OpenApi30Parameter param) {
            examples = param.getExamples();
        }

        @Override
        protected void visitParameterOpenApi31(OpenApi31Parameter param) {
            examples = param.getExamples();
        }

        @Override
        protected void visitHeaderOpenApi30(OpenApi30Header param) {
            examples = param.getExamples();
        }

        @Override
        protected void visitHeaderOpenApi31(OpenApi31Header param) {
            examples = param.getExamples();
        }

        @Override
        public void visitMediaType(OpenApiMediaType node) {
            examples = node.getExamples();
        }
    }

    private static class ClearExamplesVisitor extends ExamplesParentVisitor {

        @Override
        protected void visitParameterOpenApi30(OpenApi30Parameter param) {
            param.clearExamples();
        }

        @Override
        protected void visitParameterOpenApi31(OpenApi31Parameter param) {
            param.clearExamples();
        }

        @Override
        protected void visitHeaderOpenApi30(OpenApi30Header param) {
            param.clearExamples();
        }

        @Override
        protected void visitHeaderOpenApi31(OpenApi31Header param) {
            param.clearExamples();
        }

        @Override
        public void visitMediaType(OpenApiMediaType node) {
            node.clearExamples();
        }
    }

    private static class CreateExampleVisitor extends ExamplesParentVisitor {

        public Node example;

        @Override
        protected void visitParameterOpenApi30(OpenApi30Parameter param) {
            example = param.createExample();
        }

        @Override
        protected void visitParameterOpenApi31(OpenApi31Parameter param) {
            example = param.createExample();
        }

        @Override
        protected void visitHeaderOpenApi30(OpenApi30Header param) {
            example = param.createExample();
        }

        @Override
        protected void visitHeaderOpenApi31(OpenApi31Header param) {
            example = param.createExample();
        }

        @Override
        public void visitMediaType(OpenApiMediaType node) {
            example = node.createExample();
        }
    }

    private static class AddExampleVisitor extends ExamplesParentVisitor {
        private final String name;
        private final Node example;

        public AddExampleVisitor(String name, Node example) {
            this.name = name;
            this.example = example;
        }

        @Override
        protected void visitParameterOpenApi30(OpenApi30Parameter param) {
            param.addExample(this.name, (OpenApi30Example) this.example);
        }

        @Override
        protected void visitParameterOpenApi31(OpenApi31Parameter param) {
            param.addExample(this.name, (OpenApi31Example) this.example);
        }

        @Override
        protected void visitHeaderOpenApi30(OpenApi30Header param) {
            param.addExample(this.name, (OpenApi30Example) this.example);
        }

        @Override
        protected void visitHeaderOpenApi31(OpenApi31Header param) {
            param.addExample(this.name, (OpenApi31Example) this.example);
        }

        @Override
        public void visitMediaType(OpenApiMediaType node) {
            node.addExample(this.name, (OpenApiExample) this.example);
        }
    }

}
