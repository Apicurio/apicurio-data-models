package io.apicurio.datamodels.openapi.visitors.dereference;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.util.IReferenceResolver;
import io.apicurio.datamodels.core.util.ReferenceResolverChain;
import io.apicurio.datamodels.core.util.VisitorUtil;
import io.apicurio.datamodels.core.visitors.TraverserDirection;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/**
 * TODO
 *
 */
public class ReferenceLocalization {


    private final Document source;
    private final IReferenceResolver resolver;
    private final boolean strict;

    private final Set<String> unresolvable = new HashSet<>();


    public ReferenceLocalization(Document source) {
        this(source, ReferenceResolverChain.getInstance(), false);
    }

    public ReferenceLocalization(Document source, IReferenceResolver resolver, boolean strict) {
        this.source = source;
        this.resolver = resolver;
        this.strict = strict;
    }

    public Document normalize() {
        Document clone = Library.cloneDocument(source);

        ReferenceLocalizationStrategy ds = null;

        switch (clone.getDocumentType()) {
            case asyncapi2:
                ds = new Aai20ReferenceLocalizationStrategy();
                break;
            case openapi2:
                ds = new Oas20ReferenceLocalizationStrategy();
                break;
            case openapi3:
                ds = new Oas30ReferenceLocalizationStrategy();
                break;
            default:
                throw new RuntimeException("Unknown document type: " + clone.getDocumentType());
        }

        Queue<Context> processQueue = new LinkedList<>();




        Map<String, String> resolvedToLocalMap = new HashMap<>();

        processQueue.add(new Context(null, clone));

        while(!processQueue.isEmpty()) {
            Context item = processQueue.remove();

            // Local components originalRef -> full node
            Map<String, Node> localComponents = ds.getExistingLocalComponents(clone);

            // originalRef -> collect all reference objects
            ReferenceCollectionVisitor rcv = new ReferenceCollectionVisitor();
            VisitorUtil.visitTree(item.node, rcv, TraverserDirection.down);
            Map<String, IReferenceNode> referencedNodes = rcv.getReferencedNodes();


            for (Entry<String, IReferenceNode> e : referencedNodes.entrySet()) {
                // skip if local
                if (!localComponents.containsKey(e.getKey())) {

                    Reference ref = new Reference(e.getKey());

                    Node resolved = resolver.resolveRef(ref.getRef(), (Node) e.getValue());
                    if(resolved == null && ref.isRelative() && item.originalRef != null) {
                        ref = ref.withAbsoluteFrom(new Reference(item.originalRef));
                        resolved = resolver.resolveRef(ref.getRef(), (Node) e.getValue());
                    }

                    // resolve
                    // break recursion?
                    if(resolvedToLocalMap.containsKey(ref.getRef())) {
                        e.getValue().setReference(resolvedToLocalMap.get(ref.getRef()));
                        continue;
                    }

                    // if null keep the reference in an 'unresolvable' set to decide later
                    if (resolved == null) {
                        unresolvable.add(e.getKey());
                    } else {
                        ReferenceLocalizationStrategy.Pair localRef = null;
                        // try to attach, resolve possible name conflict
                        // TODO any other strategy?
                        //String[] parts = e.getKey().split("/"); // TODO check
                        String name = ref.getName(); //parts[parts.length - 1];
                        for (int i = 0; i < 50; i++) { // TODO good limit?
                            if (i > 0)
                                name += i;
                            try {
                                localRef = ds.attachAsDefinition(clone, name, resolved);
                            } catch (IllegalArgumentException ex) {
                                continue;
                            }
                            if(localRef == null) {
                                unresolvable.add(e.getKey());
                            } else {
                                // success!
                                // rename the original reference
                                e.getValue().setReference(localRef.ref);
                                //((IReferenceNode)localRef.node).setReference(localRef.ref);
                                // we're not done yet, possible nested refs
                                //done = false;
                                processQueue.add(new Context(ref.getRef(), localRef.node));
                                resolvedToLocalMap.put(ref.getRef(), localRef.ref);
                            }
                            break;
                        }
                    }
                }
            }
        }
        // we're done, if strict, return null?
        if(unresolvable.size() != 0 && strict)
            return null;

        return clone;
    }

    public IReferenceResolver getResolver() {
        return resolver;
    }

    public boolean isStrict() {
        return strict;
    }

    public Set<String> getUnresolvableReferences() {
        return Collections.unmodifiableSet(unresolvable);
    }


    private class Context {

        //private String parentRef;
        private String originalRef;
        private Node node;

//        public Context(String parentRef, String originalRef, Node node) {
//            this.parentRef = parentRef;
//            this.originalRef = originalRef;
//            this.node = node;
//        }

        public Context(String originalRef, Node node) {
            this.originalRef = originalRef;
            this.node = node;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Context context = (Context) o;
            return /*Objects.equals(parentRef, context.parentRef)*/ true &&
                    Objects.equals(originalRef, context.originalRef) &&
                    Objects.equals(node, context.node);
        }

        @Override
        public int hashCode() {
            return Objects.hash(/*parentRef,*/ originalRef, node);
        }
    }
}
