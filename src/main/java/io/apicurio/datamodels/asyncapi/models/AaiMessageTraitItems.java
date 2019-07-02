package io.apicurio.datamodels.asyncapi.models;

import java.util.List;

import io.apicurio.datamodels.core.models.Node;

/**
 * Represents `[Message Trait Object] | [[Message Trait Object, Map]]`
 *
 * @author Jakub Senko <jsenko@redhat.com>
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#messageObject">AsyncAPI 2.0.0 spec</a>
 */
public abstract class AaiMessageTraitItems extends Node {

    // is either
    public List<AaiMessageTrait> _traitItems;
    // or
    public List<AaiMessageTraitExtendedItem> _traitExtendedItems;

    public AaiMessageTraitItems(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    public abstract void addItem(AaiMessageTrait item);
    public abstract void addExtendedItem(AaiMessageTraitExtendedItem item);
}
