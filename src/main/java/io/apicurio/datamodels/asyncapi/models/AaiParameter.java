package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.INamed;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * TODO Does not currently extend {@link Parameter} because the AAI schema is an {@link Object} while the base class is a {@link Schema} - but OAS will likely move in the direction of AAI, so should converge at some point
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiParameter extends ExtensibleNode implements IReferenceNode, INamed {

    public String _name;

    public String $ref;
    public String description;
    public Object schema;
    public String location;
    
    /**
     * Constructor.
     */
    public AaiParameter(String name) {
        this._name = name;
    }

    /**
     * Constructor.
     * @param parent
     */
    public AaiParameter(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    /**
     * Constructor.
     * @param parent
     * @param name
     */
    public AaiParameter(Node parent, String name) {
        this(parent);
        this._name = name;
    }

    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        ((IAaiVisitor)visitor).visitAaiParameter(this);
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
