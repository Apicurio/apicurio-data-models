package io.apicurio.datamodels.deref;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter;

public class ReferencedNodeImporter extends CombinedVisitorAdapter {

    private final Document doc;
    private final String ref;
    private String pathToImportedNode;

    public ReferencedNodeImporter(Document doc, String ref) {
        super();
        this.doc = doc;
        this.ref = ref;
    }

    protected Document getDoc() {
        return doc;
    }

    public String getRef() {
        return ref;
    }

    public String getNameHintFromRef(String defaultHint) {
        int idx = this.ref.lastIndexOf('/');
        if (idx > 0) {
            String nameHint = this.ref.substring(idx + 1);
            return nameHint;
        }
        return defaultHint;
    }

    public String importNode(Node resolvedNode) {
        resolvedNode.accept(this);
        return pathToImportedNode;
    }

    public void setPathToImportedNode(String pathToImportedNode) {
        this.pathToImportedNode = pathToImportedNode;
    }

    protected String generateNodeName(String nameHint, Collection<String> existingNames) {
        if (!existingNames.contains(nameHint)) {
            return nameHint;
        }
        int counter = 1;
        while (existingNames.contains(nameHint + counter)) {
            counter++;
        }
        return nameHint + counter;
    }

    protected Collection<String> getComponentNames(Map<String, ?> components) {
        if (components == null) {
            return new HashSet<>();
        }
        return components.keySet();
    }

}
