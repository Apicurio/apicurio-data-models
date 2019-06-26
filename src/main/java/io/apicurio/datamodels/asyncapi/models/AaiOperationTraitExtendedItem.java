package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.Node;

import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * Represents `[Operation Trait Object, [string, any]]`
 *
 * @author Jakub Senko <jsenko@redhat.com>
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#operationObject">AsyncAPI 2.0.0 spec</a>
 */
public abstract class AaiOperationTraitExtendedItem extends Node {

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

    public String getName() {
        return _name;
    }
    public abstract void addExtension(String key, Object value);
}
