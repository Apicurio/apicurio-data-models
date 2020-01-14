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
import java.util.Map;
import java.util.Map.Entry;
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

        while (!processQueue.isEmpty()) {
            Context item = processQueue.remove();

            // Local components
            Map<String, Node> localComponents = strategy.getExistingLocalComponents(clone);

            // Collect all reference objects in the processed node
            ReferenceCollectionVisitor rcv = new ReferenceCollectionVisitor();
            VisitorUtil.visitTree(item.node, rcv, TraverserDirection.down);
            Map<String, IReferenceNode> referencedNodes = rcv.getReferencedNodes();

            // For each reference node ...
            for (Entry<String, IReferenceNode> e : referencedNodes.entrySet()) {

                // skip if already local, this makes sense if we're initially processing the whole Document
                if (item.originalRef == null && localComponents.containsKey(e.getKey()))
                    continue;

                // Reference to be resolved
                Reference ref = new Reference(e.getKey());

                // Attempt to resolve
                Node resolved = resolver.resolveRef(ref.getRef(), (Node) e.getValue());
                if (resolved == null && ref.isRelative() && item.originalRef != null) {
                    // otherwise if possible do a relative resolve
                    // TODO maybe do this immediately
                    ref = ref.withAbsoluteFrom(new Reference(item.originalRef));
                    resolved = resolver.resolveRef(ref.getRef(), (Node) e.getValue());
                }

                // if we've already seen the resolved reference, just point to the existing one
                // to avoid cycles
                if (resolvedToLocalMap.containsKey(ref.getRef())) {
                    e.getValue().setReference(resolvedToLocalMap.get(ref.getRef()));
                    continue;
                }

                // if null keep the reference in an 'unresolvable' set to decide later
                if (resolved == null) {
                    unresolvable.add(e.getKey());
                } else {
                    IReferenceManipulationStrategy.ReferenceAndNode localRef = null;

                    // try to attach
                    // repeat in case of name conflict
                    String name = ref.getName();
                    for (int i = 0; i < 50; i++) { // TODO correct limit?
                        if (i > 0)
                            name = ref.getName() + i;
                        try {
                            localRef = strategy.attachAsDefinition(clone, name, resolved);
                            break;
                        } catch (IllegalArgumentException ex) {
                            // try a different name
                            // TODO maybe avoid exceptions?
                        }
                    }

                    if (localRef == null) {
                        unresolvable.add(e.getKey());
                    } else {
                        // success!
                        // rename the original reference
                        e.getValue().setReference(localRef.getRef());
                        // add resolved node to processing queue
                        processQueue.add(new Context(ref.getRef(), localRef.getNode()));
                        // remember, to prevent cycles
                        resolvedToLocalMap.put(ref.getRef(), localRef.getRef());
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

        private String originalRef;
        private Node node;

        private Context(String originalRef, Node node) {
            this.originalRef = originalRef;
            this.node = node;
        }

        @Override
        public boolean equals(Object o) { // java.util.Objects are not supported by JSweet
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Context context = (Context) o;

            if (originalRef != null ? !originalRef.equals(context.originalRef) : context.originalRef != null)
                return false;
            return node != null ? node.equals(context.node) : context.node == null;
        }

        @Override
        public int hashCode() {
            int result = originalRef != null ? originalRef.hashCode() : 0;
            result = 31 * result + (node != null ? node.hashCode() : 0);
            return result;
        }
    }
}
