package io.apicurio.umg.base.visitors;

import java.util.Collection;
import java.util.Map;

import io.apicurio.umg.base.Node;
import io.apicurio.umg.base.Visitable;

/**
 * Base class for all traversers.
 */
public abstract class AbstractTraverser implements Traverser, Visitor {

    protected final Visitor visitor;
    
    /**
     * C'tor.
     * @param visitor
     */
    public AbstractTraverser(Visitor visitor) {
        this.visitor = visitor;
    }

    /**
     * Traverse the items of the given array.
     * @param items
     */
    protected void traverseCollection(Collection<? extends Visitable> items) {
        if (items != null) {
            items.forEach(node -> {
                this.traverseIfNotNull(node);
            });
        }
    }
    
    /**
     * Traverse the items of the given map.
     * @param items
     */
    protected void traverseMap(Map<String, ? extends Visitable> items) {
        if (items != null) {
            items.keySet().forEach(key -> {
                this.traverseIfNotNull(items.get(key));
            });
        }
    }

    /**
     * Traverse into the given node, unless it's null.
     * @param node
     */
    protected void traverseIfNotNull(Visitable node) {
        if (node != null) {
            node.accept(this);
        }
    }

    /**
     * Called to traverse the data model starting at the given node and traversing
     * down until this node and all child nodes have been visited.
     * @param node
     */
    public void traverse(Node node) {
        node.accept(this);
    }

}
