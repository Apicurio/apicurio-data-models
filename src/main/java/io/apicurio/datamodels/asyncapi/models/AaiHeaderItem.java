package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.INamed;

/**
 * Represents `Schema Object | Reference Object` type.
 *
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiHeaderItem extends ExtensibleNode implements INamed {

    public String _name;

    public String $ref;

    /**
     * We do not support deserialization of `Schema Object` yet.
     * In TypeScript, this field contains the raw JS object, in case of Java
     * it is a {@link com.fasterxml.jackson.databind.node.ObjectNode}.
     *
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#schemaObject">AsyncAPI 2.0.0 spec</a>
     */
    public Object _schemaRaw;
    
    /**
     * Constructor.
     */
    public AaiHeaderItem(String name) {
        this._name = name;
    }

    public AaiHeaderItem(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    public AaiHeaderItem(Node parent, String name) {
        this(parent);
        this._name = name;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#getName()
     */
    @Override
    public String getName() {
        return this._name;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#rename(java.lang.String)
     */
    @Override
    public void rename(String newName) {
        this._name = newName;
    }

}
