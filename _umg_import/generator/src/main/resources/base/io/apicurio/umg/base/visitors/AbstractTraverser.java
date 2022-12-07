package io.apicurio.umg.base.visitors;

import java.util.Collection;
import java.util.Map;

import io.apicurio.umg.base.MappedNode;
import io.apicurio.umg.base.Node;

/**
 * Base class for all traversers.
 */
public abstract class AbstractTraverser implements Traverser, Visitor {

    protected final Visitor visitor;
    protected final TraversalContextImpl traversalContext = new TraversalContextImpl();

    /**
     * C'tor.
     *
     * @param visitor
     */
    public AbstractTraverser(Visitor visitor) {
        this.visitor = visitor;
        if (visitor instanceof TraversingVisitor) {
            ((TraversingVisitor) visitor).setTraversalContext(this.traversalContext);
        }
    }

    /**
     * Traverse the given node. Guaranteed to not be null here.
     *
     * @param node
     */
    protected void doTraverseNode(Node node) {
        node.accept(this);
    }

    /**
     * Traverse into the given node, unless it's null.
     *
     * @param propertyName
     * @param node
     */
    protected void traverse(String propertyName, Node node) {
        if (node != null) {
            traversalContext.pushProperty(propertyName);
            doTraverseNode(node);
            traversalContext.pop();
        }
    }

    /**
     * Traverse the items of the given array.
     *
     * @param propertyName
     * @param items
     */
    protected void traverseList(String propertyName, Collection<? extends Node> items) {
        if (items != null) {
            int index = 0;
            traversalContext.pushProperty(propertyName);
            for (Node node : items) {
                if (node != null) {
                    traversalContext.pushIndex(index);
                    doTraverseNode(node);
                    traversalContext.pop();
                }
                index++;
            }
            traversalContext.pop();
        }
    }

    /**
     * Traverse the items of the given map.
     *
     * @param propertyName
     * @param items
     */
    protected void traverseMap(String propertyName, Map<String, ? extends Node> items) {
        if (items != null) {
            traversalContext.pushProperty(propertyName);
            items.keySet().forEach(key -> {
                Node value = items.get(key);
                if (value != null) {
                    this.traversalContext.pushIndex(key);
                    this.doTraverseNode(value);
                    this.traversalContext.pop();
                }
            });
            this.traversalContext.pop();
        }
    }

    /**
     * Traverse the items of the given mapped node.
     *
     * @param items
     */
    protected void traverseMappedNode(MappedNode<? extends Node> mappedNode) {
        if (mappedNode != null) {
            mappedNode.getItemNames().forEach(name -> {
                Node value = mappedNode.getItem(name);
                if (value != null) {
                    this.traversalContext.pushIndex(name);
                    this.doTraverseNode(value);
                    this.traversalContext.pop();
                }
            });
        }
    }

    /**
     * Called to traverse the data model starting at the given node and traversing
     * down until this node and all child nodes have been visited.
     *
     * @param node
     */
    @Override
    public void traverse(Node node) {
        node.accept(this);
    }

}
