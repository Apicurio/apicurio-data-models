package io.apicurio.datamodels.deref;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Referenceable;

/**
 * Simple visitor used to mark internal references as "resolved".
 * @author eric.wittmann@gmail.com
 */
public class InternalRefResolverVisitor extends AllReferenceableNodeVisitor {

    @Override
    protected void visitReferenceableNode(Referenceable refNode) {
        String ref = refNode.get$ref();
        // Internal refs are marked as resolved
        if (ref.startsWith("#/")) {
            Node node = (Node) refNode;
            node.setNodeAttribute(DereferenceConstants.KEY_RESOLUTION, DereferenceConstants.VALUE_RESOLVED);
        }
    }

}
