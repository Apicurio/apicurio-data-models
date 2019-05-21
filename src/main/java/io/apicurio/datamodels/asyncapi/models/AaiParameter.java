package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.visitors.IVisitor;

import static java.util.Objects.requireNonNull;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiParameter extends ExtensibleNode implements IReferenceNode {

    public String _name;

    public String $ref;
    public String name;
    public String description;
    public Object schema;

    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        ((IAaiVisitor)visitor).visitAaiParameter(this);
    }

    //public abstract Schema createSchema();

    public AaiParameter(Node parent) {
        requireNonNull(parent);
        this._parent = parent;
        this._ownerDocument = parent.ownerDocument();
    }

    public AaiParameter(Node parent, String _name) {
        requireNonNull(parent);
        this._parent = parent;
        this._ownerDocument = parent.ownerDocument();
        this._name = _name;
    }

    public String getName() {
        return _name;
    }
}
