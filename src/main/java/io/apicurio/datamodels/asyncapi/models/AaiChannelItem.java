package io.apicurio.datamodels.asyncapi.models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.INamed;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiChannelItem extends ExtensibleNode implements IReferenceNode, INamed {

    public String _name;
    public String $ref;
    public String description;
    public AaiOperation subscribe;
    public AaiOperation publish;
    public Map<String, AaiParameter> parameters;
    public AaiChannelBindings bindings;

    /**
     * Constructor.
     */
    public AaiChannelItem(String name) {
        this._name = name;
    }

    public AaiChannelItem(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    public AaiChannelItem(Node parent, String name) {
        this(parent);
        this._name = name;
    }

    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IAai20Visitor v = (IAai20Visitor) visitor;
        v.visitChannelItem(this);
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

    @Override
    public String getReference() {
        return $ref;
    }

    @Override
    public void setReference(String reference) {
        $ref = reference;
    }

    /**
     * Adds a parameter.
     * @param paramName
     * @param param
     */
    public void addParameter(String paramName, AaiParameter param) {
        if (this.parameters == null) {
            this.parameters = new LinkedHashMap<>();
        }
        this.parameters.put(paramName, param);
    }

    public List<AaiParameter> getParameterlist() {
        return JsonCompat.mapToList(parameters);
    }
}
