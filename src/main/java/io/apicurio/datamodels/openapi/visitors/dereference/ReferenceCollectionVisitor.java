package io.apicurio.datamodels.openapi.visitors.dereference;

import io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor;
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.INamed;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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

    private Set<IReferenceNode> fullNodes = new HashSet<>();
    private Map<String, IReferenceNode> referencedNodes = new LinkedHashMap<>();

    /**
     * Visit a node.  This is a common method called for every node type.
     *
     * @param node
     */
    @Override
    protected void visitNode(Node node) {
        if (node instanceof IReferenceNode && node instanceof INamed) {
            IReferenceNode refNode = (IReferenceNode) node;
            if(refNode.getReference() != null)
                referencedNodes.put(refNode.getReference(), refNode);
            else
                fullNodes.add(refNode);
        }
    }

    /**
     * @return An unmodifiable map of collected nodes.
     */
    public Map<String, IReferenceNode> getReferencedNodes() {
        return new HashMap<>(referencedNodes);
    }

    public Set<IReferenceNode> getFullNodes() {
        return new HashSet<>(fullNodes);
    }
}
