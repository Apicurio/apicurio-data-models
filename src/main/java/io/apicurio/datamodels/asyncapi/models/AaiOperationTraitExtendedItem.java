package io.apicurio.datamodels.asyncapi.models;

import java.util.Map;

import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.INamed;

/**
 * Represents `[Operation Trait Object, [string, any]]`
 *
 * @author Jakub Senko <jsenko@redhat.com>
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#operationObject">AsyncAPI 2.0.0 spec</a>
 */
public abstract class AaiOperationTraitExtendedItem extends Node implements INamed {

    public String _name;
    public AaiOperationTrait _operationTrait;

    /**
     * Represents `Map[string, any]`.
     * Objects of `any` type are deserialized as follows:
     * In TypeScript, this field contains the raw JS object, in case of Java
     * it is a {@link com.fasterxml.jackson.databind.node.ObjectNode}.
     *
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#operationObject">AsyncAPI 2.0.0 spec</a>
     */
    public Map<String, Object> _traitExtension;
    
    /**
     * Constructor.
     */
    public AaiOperationTraitExtendedItem() {
    }

    public AaiOperationTraitExtendedItem(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    public AaiOperationTraitExtendedItem(Node parent, String name) {
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

    public abstract void addExtension(String key, Object value);
}
