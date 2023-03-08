package io.apicurio.datamodels.paths;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.visitors.AllNodeVisitor;
import io.apicurio.datamodels.models.visitors.TraversalContext;
import io.apicurio.datamodels.models.visitors.TraversalStepType;
import io.apicurio.datamodels.models.visitors.TraversingVisitor;

public class NodePathVisitor extends AllNodeVisitor implements TraversingVisitor {

    private TraversalContext traversalContext;
    private Node targetNode;
    private NodePath nodePath;

    /**
     * Constructor.
     * @param target
     */
    public NodePathVisitor(Node target) {
        this.targetNode = target;
    }

    @Override
    public void setTraversalContext(TraversalContext context) {
        this.traversalContext = context;
    }

    @Override
    protected void visitNode(Node node) {
        if (node.modelId() == targetNode.modelId()) {
            this.nodePath = createNodePath();
        }
    }

    private NodePath createNodePath() {
        NodePath path = new NodePath();
        this.traversalContext.getAllSteps().forEach(step -> {
            NodePathSegment segment = new NodePathSegment(step.getValue().toString(), step.getType() != TraversalStepType.property);
            path.append(segment);
        });
        return path;
    }

    public NodePath getNodePath() {
        return this.nodePath;
    }

}
