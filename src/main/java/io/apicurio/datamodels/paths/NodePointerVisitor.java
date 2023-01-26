package io.apicurio.datamodels.paths;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.visitors.AllNodeVisitor;
import io.apicurio.datamodels.models.visitors.TraversalContext;
import io.apicurio.datamodels.models.visitors.TraversingVisitor;

public class NodePointerVisitor extends AllNodeVisitor implements TraversingVisitor {

    private TraversalContext traversalContext;
    private Node targetNode;
    private NodePointer nodePointer;

    /**
     * Constructor.
     * @param target
     */
    public NodePointerVisitor(Node target) {
        this.targetNode = target;
    }

    @Override
    public void setTraversalContext(TraversalContext context) {
        this.traversalContext = context;
    }

    @Override
    protected void visitNode(Node node) {
        if (node.modelId() == targetNode.modelId()) {
            this.nodePointer = createNodePointer();
        }
    }

    private NodePointer createNodePointer() {
        NodePointer pointer = new NodePointer();
        this.traversalContext.getAllSteps().forEach(step -> {
            NodePointerSegment segment = new NodePointerSegment(step.getValue().toString());
            pointer.append(segment);
        });
        return pointer;
    }

    public NodePointer getNodePointer() {
        return this.nodePointer;
    }

}
