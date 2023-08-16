package io.apicurio.datamodels.deref;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;

public class ReferencedNodeImporter extends CombinedVisitorAdapter {

    private final Document doc;
    private final Node nodeWithUnresolvedRef;
    private final String ref;
    private final boolean _shouldInline;

    private Node importedNode;
    private String pathToImportedNode;

    public ReferencedNodeImporter(Document doc, Node nodeWithUnresolvedRef, String ref, boolean shouldInline) {
        super();
        this.doc = doc;
        this.nodeWithUnresolvedRef = nodeWithUnresolvedRef;
        this.ref = ref;
        this._shouldInline = shouldInline;
    }

    public Node getImportedNode() {
        return this.importedNode;
    }

    public String getPathToImportedNode() {
        return this.pathToImportedNode;
    }

    protected Document getDoc() {
        return doc;
    }

    protected Node getNodeWithUnresolvedRef() {
        return this.nodeWithUnresolvedRef;
    }

    protected String getRef() {
        return ref;
    }

    public boolean shouldInline() {
        return this._shouldInline;
    }

    public String getNameHintFromRef(String defaultHint) {
        int idx = this.ref.lastIndexOf('/');
        if (idx > 0) {
            String nameHint = this.ref.substring(idx + 1);
            return nameHint;
        }
        return defaultHint;
    }

    public void importNode(Node resolvedNode) {
        resolvedNode.accept(this);
    }

    public void setPathToImportedNode(Node importedNode, String pathToImportedNode) {
        this.importedNode = importedNode;
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

    protected String getComponentName(Node nodeWithUnresolvedRef) {
        NodePath nodePath = NodePathUtil.createNodePath(getNodeWithUnresolvedRef());
        return nodePath.getLastSegment().getValue();
    }

}
