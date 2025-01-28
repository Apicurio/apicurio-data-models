package io.apicurio.datamodels.openapi.visitors.dereference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;

public abstract class AbstractReferenceLocalizationStrategy {

    protected String PREFIX = "#/components/";

    /**
     * Warning: This method assumes that the definition and the represented object can be read from the same JSON,
     * i.e. effectively contain the same data.
     */
    @SuppressWarnings("unchecked")
    protected <T extends Node> T wrap(Node source, T target, Document model) {
        target._ownerDocument = model;
        target._parent = target;
        return (T) Library.readNode(Library.writeNode(source), target);
    }

    protected void transform(Map<String, ? extends Node> source, Function<String, String> transformName, Map<String, Node> result) {
        if(result == null)
            throw  new IllegalArgumentException("Result argument is null.");
        if(source != null) {
            for (Map.Entry<String, ?> e : source.entrySet()) {
                result.put(transformName.apply(e.getKey()), (Node) e.getValue());
            }
        }
    }

    protected static <T extends Node> List<T> cloneNodes(List<T> nodes, Supplier<T> nodeSupplier) {
        List<T> clones = new ArrayList<>(nodes.size());
        for (T node : nodes) {
            clones.add(cloneNode(node, nodeSupplier));
        }
        return clones;
    }

    protected static <T extends Node> T cloneNode(T node, Supplier<T> nodeSupplier) {
        Node clone = Library.readNode(Library.writeNode(node), nodeSupplier.get());
        //noinspection unchecked
        return (T) clone;
    }
}
