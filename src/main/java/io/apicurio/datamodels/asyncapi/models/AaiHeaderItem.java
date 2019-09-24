package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * Represents `Schema Object | Reference Object` type.
 *
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiHeaderItem extends ExtensibleNode {

    public String $ref;

    /**
     * We do not support deserialization of `Schema Object` yet.
     * In TypeScript, this field contains the raw JS object, in case of Java
     * it is a {@link com.fasterxml.jackson.databind.node.ObjectNode}.
     *
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#schemaObject">AsyncAPI 2.0.0 spec</a>
     */
    public Object _schemaRaw;
    
    /**
     * Constructor.
     */
    public AaiHeaderItem() {
    }

    /**
     * Constructor.
     * @param parent
     */
    public AaiHeaderItem(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IAaiVisitor v = (IAaiVisitor) visitor;
        v.visitHeaderItem(this);
    }
}
