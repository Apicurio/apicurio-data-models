package io.apicurio.datamodels.models.visitors;

import io.apicurio.datamodels.models.Node;

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
