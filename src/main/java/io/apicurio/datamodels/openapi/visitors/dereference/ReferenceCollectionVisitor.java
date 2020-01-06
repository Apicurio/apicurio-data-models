package io.apicurio.datamodels.openapi.visitors.dereference;

import io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor;
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.models.Node;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Collect all nodes representing reference objects.
 *
 * Warning: The nodes are not cloned, so modifying them would modify the original model.
 * You MUST clone them yourself before changing their state.
 *
 * Do not reuse visitor instances for multiple traversals.
 *
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class ReferenceCollectionVisitor extends CombinedAllNodeVisitor {

    private Map<String, IReferenceNode> collectedNodes = new LinkedHashMap<>();

    /**
     * Visit a node.  This is a common method called for every node type.
     *
     * @param node
     */
    @Override
    protected void visitNode(Node node) {
        if (node instanceof IReferenceNode) {
            IReferenceNode refNode = (IReferenceNode) node;
            collectedNodes.put(refNode.getReference(), refNode);
        }
    }

    /**
     * @return An unmodifiable map of collected nodes.
     */
    public Map<String, IReferenceNode> getCollectedNodes() {
        return Collections.unmodifiableMap(collectedNodes);
    }
}
