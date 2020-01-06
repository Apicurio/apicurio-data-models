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
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
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

        boolean done = false;
        while(!done) {

            Map<String, Node> localComponents = ds.getExistingLocalComponents(clone);

            // collect all
            ReferenceCollectionVisitor rcv = new ReferenceCollectionVisitor();
            VisitorUtil.visitTree(clone, rcv, TraverserDirection.down);
            Map<String, IReferenceNode> collectedNodes = rcv.getCollectedNodes();

            for (Entry<String, IReferenceNode> e : collectedNodes.entrySet()) {
                // skip if local
                if (!localComponents.containsKey(e.getKey())) {

                    // resolve
                    Node resolved = resolver.resolveRef(e.getKey(), (Node) e.getValue());
                    // if null keep the reference in an 'unresolvable' set to decide later
                    if (resolved == null) {
                        unresolvable.add(e.getKey());
                    } else {
                        String ref = null;
                        // try to attach, resolve possible name conflict
                        // TODO any other strategy?
                        String[] parts = e.getKey().split("/"); // TODO check
                        String name = parts[parts.length - 1];
                        for (int i = 0; i < 50; i++) { // TODO good limit?
                            if (i > 0)
                                name += i;
                            try {
                                ref = ds.attachAsDefinition(clone, name, resolved);
                            } catch (IllegalArgumentException ex) {
                                continue;
                            }
                            if(ref == null) {
                                unresolvable.add(e.getKey());
                            } else {
                                // success!
                                // rename the original reference
                                e.getValue().setReference(ref);
                                // we're not done yet, possible nested refs
                                done = true;
                            }
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
}
