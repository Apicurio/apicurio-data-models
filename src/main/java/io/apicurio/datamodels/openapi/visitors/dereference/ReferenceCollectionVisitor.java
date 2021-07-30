package io.apicurio.datamodels.openapi.visitors.dereference;

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor;
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.INamed;

/**
 * Collect all nodes representing reference objects. Those nodes
 * MUST implement {@link IReferenceNode} and {@link INamed}.
 * <p>
 * Warning: The nodes are not cloned, so modifying them would modify the original model.
 * You MUST clone them yourself before changing their state.
 * <p>
 * Do not reuse visitor instances for multiple traversals.
 *
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class ReferenceCollectionVisitor extends CombinedAllNodeVisitor {

    private List<IReferenceNode> fullNodes = new ArrayList<>();
    private List<IReferenceNode> referencedNodes = new ArrayList<>();


    /**
     * Visit a node.  This is a common method called for every node type.
     *
     * @param node
     */
    @Override
    protected void visitNode(Node node) {
        if (node instanceof IReferenceNode) {
            IReferenceNode refNode = (IReferenceNode) node;
            if (refNode.getReference() != null)
                referencedNodes.add(refNode);
            else
                fullNodes.add(refNode);
        }
    }

    /**
     * @return An map of collected nodes that CONTAIN an actual reference string.
     */
    public List<IReferenceNode> getReferencedNodes() {
        return new ArrayList<>(referencedNodes);
    }

    /**
     * @return An map of collected nodes that DO NOT CONTAIN an actual reference string,
     * i.e. full nodes.
     */
    public List<IReferenceNode> getFullNodes() {
        return new ArrayList<>(fullNodes);
    }
}
