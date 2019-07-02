package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.Node;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiCorrelationId extends ExtensibleNode {

    public String _name;
    public String $ref;
    public String location;
    public String description;

    public AaiCorrelationId(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    public AaiCorrelationId(Node parent, String name) {
        this(parent);
        this._name = name;
    }

    public String getName() {
        return _name;
    }
}
