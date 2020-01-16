package io.apicurio.datamodels.openapi.visitors.dereference;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.util.IReferenceResolver;
import io.apicurio.datamodels.core.util.ReferenceResolverChain;
import io.apicurio.datamodels.core.util.VisitorUtil;
import io.apicurio.datamodels.core.visitors.TraverserDirection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Given a {@link Document}, process its reference strings and attempt to dereference them
 * using {@link io.apicurio.datamodels.core.util.IReferenceResolver}. Produce a clone of the original model,
 * with the resolved components/definitions attached.
 *
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Dereferencer {

    private final Document source;
    private final IReferenceResolver resolver;
    private final boolean strict;

    private final Set<String> unresolvable = new HashSet<>();

    /**
     * Setup the dereferencer execution with default config.
     *
     * @param source original {@link Document}
     */
    public Dereferencer(Document source) {
        this(source, ReferenceResolverChain.getInstance(), false);
    }

    /**
     * Setup the dereferencer execution.
     *
     * @param source   original {@link Document}
     * @param resolver provide a custom resolver, otherwise the common {@link io.apicurio.datamodels.core.util.ReferenceResolverChain}
     *                 is used.
     * @param strict   If true, and there are any references that cannot be resolved, raise an exception.
     *                 Defaults to false.
     */
    public Dereferencer(Document source, IReferenceResolver resolver, boolean strict) {
        this.source = source;
        this.resolver = resolver;
        this.strict = strict;
    }

    /**
     * Execute the algorithm.
     *
     * @return dereferenced clone of the source document.
     * @throws java.lang.RuntimeException if strict and some references could not be dereferenced
     */
    public Document dereference() {
        Document clone = Library.cloneDocument(source);

        IReferenceManipulationStrategy strategy = null;
        switch (clone.getDocumentType()) {
            case asyncapi2:
                strategy = new Aai20IReferenceManipulationStrategy();
                break;
            case openapi2:
                strategy = new Oas20IReferenceManipulationStrategy();
                break;
            case openapi3:
                strategy = new Oas30IReferenceManipulationStrategy();
                break;
            default:
                throw new RuntimeException("Unknown document type: " + clone.getDocumentType());
        }

        // Keeps the nodes waiting to be processed (BFS-style)
        Queue<Context> processQueue = new LinkedList<>();
        // start with the whole model
        processQueue.add(new Context(null, clone));

        // Prevents recursive loops
        Map<String, String> resolvedToLocalMap = new HashMap<>();
        for (String ref : strategy.getExistingLocalComponents(clone).keySet()) {
            resolvedToLocalMap.put(ref, ref);
        }

        while (!processQueue.isEmpty()) {
            Context item = processQueue.remove();

            // Local components
            Map<String, Node> localComponents = strategy.getExistingLocalComponents(clone);

            // Collect all reference objects in the processed node
            ReferenceCollectionVisitor rcv = new ReferenceCollectionVisitor();
            VisitorUtil.visitTree(item.node, rcv, TraverserDirection.down);
            List<IReferenceNode> referencedNodes = rcv.getReferencedNodes();

            // For each reference node ...
            for (IReferenceNode node : referencedNodes) {

                // skip if already local, this makes sense if we're initially processing the whole Document
                if (item.parentRef == null && localComponents.containsKey(node.getReference()))
                    continue;

                // Reference to be resolved
                Reference originalRef = new Reference(node.getReference());
                Reference resolvedRef = originalRef;

                // Attempt to resolve
                if(item.parentRef != null && resolvedRef.isRelative()) {
                    resolvedRef = resolvedRef.withAbsoluteFrom(new Reference(item.parentRef));
                }

                // if we've already seen the resolved reference, just point to the existing one
                // to avoid cycles
                if (resolvedToLocalMap.containsKey(resolvedRef.getRef())) {
                    node.setReference(resolvedToLocalMap.get(resolvedRef.getRef()));
                    continue;
                }

                Node resolved = resolver.resolveRef(resolvedRef.getRef(), (Node) node);

                // if null keep the reference in an 'unresolvable' set to decide later
                if (resolved == null) {
                    unresolvable.add(node.getReference());
                } else {
                    IReferenceManipulationStrategy.ReferenceAndNode localRef = null;

                    // try to attach
                    // repeat in case of name conflict
                    String name = originalRef.getName();
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        if (i > 0)
                            name = originalRef.getName() + i;
                        try {
                            localRef = strategy.attachAsDefinition(clone, name, resolved);
                            break;
                        } catch (IllegalArgumentException ex) {
                            // try a different name
                            // TODO maybe avoid exceptions?
                        }
                    }

                    if (localRef == null) {
                        unresolvable.add(node.getReference());
                    } else {
                        // success!
                        // rename the original reference
                        node.setReference(localRef.getRef());
                        // add resolved node to processing queue
                        processQueue.add(new Context(originalRef.getRef(), localRef.getNode()));
                        // remember, to prevent cycles
                        resolvedToLocalMap.put(resolvedRef.getRef(), localRef.getRef());
                    }
                }
            }
        }
        // we're done, if strict, throw
        if (unresolvable.size() != 0 && strict)
            throw new RuntimeException("Could not resolve some references: " + unresolvable);

        return clone;
    }

    public IReferenceResolver getResolver() {
        return resolver;
    }

    public boolean isStrict() {
        return strict;
    }

    /**
     * After {@link #dereference()} is executed and strict is false,
     *
     * @return a possibly non-empty set of unresolved references
     */
    public Set<String> getUnresolvableReferences() {
        return new HashSet<>(unresolvable);
    }

    /**
     * State for each recursive step
     */
    private class Context { // TODO maybe merge with IReferenceManipulationStrategy.ReferenceAndNode}

        private String parentRef;
        private Node node;

        private Context(String parentRef, Node node) {
            this.parentRef = parentRef;
            this.node = node;
        }

        @Override
        public boolean equals(Object o) { // java.util.Objects are not supported by JSweet
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Context context = (Context) o;

            if (parentRef != null ? !parentRef.equals(context.parentRef) : context.parentRef != null)
                return false;
            return node != null ? node.equals(context.node) : context.node == null;
        }

        @Override
        public int hashCode() {
            int result = parentRef != null ? parentRef.hashCode() : 0;
            result = 31 * result + (node != null ? node.hashCode() : 0);
            return result;
        }
    }
}
