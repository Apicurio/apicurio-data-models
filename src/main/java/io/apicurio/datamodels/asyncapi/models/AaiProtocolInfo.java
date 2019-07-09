package io.apicurio.datamodels.asyncapi.models;

import java.util.Map;

import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.INamed;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiProtocolInfo extends Node implements INamed {

    public String _name;

    /**
     * Represents `Map[string, any]`.
     * Objects of `any` type are deserialized as follows:
     * In TypeScript, this field contains the raw JS object, in case of Java
     * it is a {@link com.fasterxml.jackson.databind.node.ObjectNode}.
     *
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#protocolInfoObject">AsyncAPI 2.0.0 spec</a>
     */
    public Map<String, Object> _protocolInfoItems;
    
    /**
     * Constructor.
     */
    public AaiProtocolInfo(String name) {
        this._name = name;
    }

    public AaiProtocolInfo(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    public AaiProtocolInfo(Node parent, String name) {
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

    public abstract void addItem(String key, Object value);
}
