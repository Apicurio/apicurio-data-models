package io.apicurio.datamodels.deref;

import java.util.List;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.TraverserDirection;
import io.apicurio.datamodels.VisitorUtil;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Referenceable;
import io.apicurio.datamodels.models.visitors.Visitor;
import io.apicurio.datamodels.refs.IReferenceResolver;
import io.apicurio.datamodels.refs.Reference;
import io.apicurio.datamodels.refs.ReferenceContext;
import io.apicurio.datamodels.util.ModelTypeUtil;

public class Dereferencer {

    private IReferenceResolver resolver;
    private boolean strict;

    public Dereferencer(IReferenceResolver resolver, boolean strict) {
        this.resolver = resolver;
        this.strict = strict;
    }

    /**
     * Performs the dereferencing logic on the source document.  Returns a new document that
     * contains no external references (all external references are internalized).
     */
    public Document dereference(final Document source) {
        Document doc = Library.cloneDocument(source);
        ResolvedNodeMap rnm = new ResolvedNodeMap();

        // Mark all internal references as already "resolved"
        InternalRefResolverVisitor irrv = new InternalRefResolverVisitor();
        VisitorUtil.visitTree(doc, irrv, TraverserDirection.down);

        int unresolveableRefCount = 0;
        boolean isDone = false;
        do {
            // Find all nodes in the document with $refs that need to be resolved.
            UnresolvedReferenceNodeVisitor visitor = new UnresolvedReferenceNodeVisitor();
            VisitorUtil.visitTree(doc, visitor, TraverserDirection.down);
            List<Node> nodesWithUnresolvedRefs = visitor.getNodesWithUnresolvedRefs();

            // If none were found, we're done.  Otherwise, resolve all of the unresolved refs!
            if (nodesWithUnresolvedRefs.isEmpty()) {
                isDone = true;
            } else {
                // Resolve all of the external refs.
                for (Node nodeWithUnresolvedRef : nodesWithUnresolvedRefs) {
                    if (!resolveNodeWithRef(doc, rnm, nodeWithUnresolvedRef)) {
                        unresolveableRefCount++;
                    }
                }
            }
        } while (!isDone);

        // Fail if strict mode is enabled and at least one ref was not resolved.
        if (this.strict && unresolveableRefCount > 0) {
            throw new RuntimeException("Could not resolve at least one external reference.");
        }

        return doc;
    }

    /**
     * Resolves external references by loading the external source and pulling out a Node
     * from the resulting model.  That Node is then imported into the original source
     * document in the appropriate place (e.g. within "components" for OpenApi and AsyncApi
     * documents).  Once that is done, the $ref is updated to point to the new, imported
     * component.
     *
     * Returns true if the ref was successfully resolved.
     *
     * @param doc
     * @param rnm
     * @param nodeWithUnresolvedRef
     */
    private boolean resolveNodeWithRef(Document doc, ResolvedNodeMap rnm, Node nodeWithUnresolvedRef) {
        String ref = ((Referenceable) nodeWithUnresolvedRef).get$ref();
        Reference reference = new Reference(ref);

        // What is the context of this reference?  The context is the chain of references that got us
        // to this point.  It could be that A.json has a $ref to B.json which has a $ref to C.json, etc.
        // The context allows us to know that chain of references so that we can canonicalize the value
        // of this $ref into a string that can be resolved by the resolver.
        ReferenceContext refContext = (ReferenceContext) nodeWithUnresolvedRef.getNodeAttribute(DereferenceConstants.KEY_REFERENCE_CONTEXT);
        if (refContext == null) {
            refContext = new ReferenceContext();
        }
        Reference canonicalReference = refContext.canonicalizeRef(reference);
        System.out.println("RES: Canonical ref: " + canonicalReference);

        String newRefValue = null;

        if (rnm.containsKey(canonicalReference)) {
            newRefValue = rnm.get(canonicalReference);
        } else {
            // Resolve the ref to a node.
            Node resolvedNode = resolver.resolveRef(canonicalReference.getRef(), nodeWithUnresolvedRef);

            // Cannot be resolved
            if (resolvedNode == null) {
                nodeWithUnresolvedRef.setNodeAttribute(DereferenceConstants.KEY_RESOLUTION, DereferenceConstants.VALUE_UNRESOLVEABLE);
                return false;
            }

            // Set appropriate context information on any Referenceable nodes in the resolved node.  This
            // will allow us to handle importing nodes that have additional references (either internal or
            // external) to even more nodes.
            ReferenceContext resolvedNodeRefContext = refContext.append(reference);
            Visitor scv = new SetContextVisitor(resolvedNodeRefContext);
            VisitorUtil.visitTree(resolvedNode, scv, TraverserDirection.down);

            // Node was resolved, insert it into the source document (eg. into the #/components section of the source doc)
            // TODO if the nodeWithUnresolvedRef is a definition and "$ref" is the only property, then INLINE the resolvedNode instead of IMPORT
            ReferencedNodeImporter importer = createImporter(doc, ref);
            newRefValue = importer.importNode(resolvedNode);

            // Cache the resolved reference in the resolved node map
            rnm.put(canonicalReference, newRefValue);
        }

        // We have a new value for the $ref
        ((Referenceable) nodeWithUnresolvedRef).set$ref(newRefValue);

        // Resolution complete, make sure to mark it as resolved.
        nodeWithUnresolvedRef.setNodeAttribute(DereferenceConstants.KEY_RESOLUTION, DereferenceConstants.VALUE_RESOLVED);

        return true;
    }

    private ReferencedNodeImporter createImporter(Document doc, String ref) {
        if (ModelTypeUtil.isAsyncApiModel(doc)) {
            return new AsyncApi2NodeImporter(doc, ref);
        } else if (ModelTypeUtil.isOpenApi2Model(doc)) {
            return new OpenApi2NodeImporter(doc, ref);
        } else if (ModelTypeUtil.isOpenApi3Model(doc)) {
            return new OpenApi3NodeImporter(doc, ref);
        }
        throw new RuntimeException("Unsupported model type: " + doc.root().modelType());
    }

}
