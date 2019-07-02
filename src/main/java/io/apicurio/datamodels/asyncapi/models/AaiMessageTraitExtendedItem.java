package io.apicurio.datamodels.asyncapi.models;

import java.util.Map;

import io.apicurio.datamodels.core.models.Node;

/**
 * Represents `[Message Trait Object, Map[string, any]]`
 *
 * @author Jakub Senko <jsenko@redhat.com>
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#messageObject">AsyncAPI 2.0.0 spec</a>
 */
public abstract class AaiMessageTraitExtendedItem extends Node {


    public AaiMessageTrait _trait;

    /**
     * Represents `Map[string, any]`.
     * Objects of `any` type are deserialized as follows:
     * In TypeScript, this field contains the raw JS object, in case of Java
     * it is a {@link com.fasterxml.jackson.databind.node.ObjectNode}.
     *
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#messageObject">AsyncAPI 2.0.0 spec</a>
     */
    public Map<String, Object> _traitExtension;

    public AaiMessageTraitExtendedItem(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    public abstract void addExtension(String key, Object value);
}
