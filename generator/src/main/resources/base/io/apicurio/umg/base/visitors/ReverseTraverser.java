package io.apicurio.umg.base.visitors;

import io.apicurio.umg.base.Node;

public class ReverseTraverser extends AllNodeVisitor implements Traverser {

    protected Visitor visitor;

    public ReverseTraverser(Visitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public void traverse(Node node) {
        node.accept(this);
    }

    @Override
    protected void visitNode(Node node) {
        node.accept(this.visitor);

        if (node.parent() != null) {
            this.traverse(node.parent());
        }
    }
    
}
