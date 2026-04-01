package io.apicurio.datamodels.deref;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Referenceable;
import io.apicurio.datamodels.refs.ReferenceContext;

public class SetContextVisitor extends AllReferenceableNodeVisitor {

    private final ReferenceContext context;

    public SetContextVisitor(ReferenceContext context) {
        this.context = context;
    }

    @Override
    protected void visitReferenceableNode(Referenceable refNode) {
        Node node = (Node) refNode;
        node.setNodeAttribute(DereferenceConstants.KEY_REFERENCE_CONTEXT, context);
    }

}
