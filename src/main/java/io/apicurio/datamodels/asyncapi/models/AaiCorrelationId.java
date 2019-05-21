package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.Node;

import static java.util.Objects.requireNonNull;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiCorrelationId extends ExtensibleNode {

    public String _name;

    public String $ref;
    /**
     * Required.
     */
    public String location;

    /**
     * NOT Required.
     */
    public String description;

    public AaiCorrelationId(Node parent) {
        requireNonNull(parent);
        this._parent = parent;
        this._ownerDocument = parent.ownerDocument();
    }

    public AaiCorrelationId(Node parent, String _name) {
        requireNonNull(parent);
        this._parent = parent;
        this._ownerDocument = parent.ownerDocument();
        this._name = _name;
    }

    public String getName() {
        return _name;
    }
}
