package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.Node;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Represents `[Message Trait Object] | [[Message Trait Object, Map]]`
 *
 * @author Jakub Senko <jsenko@redhat.com>
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#messageObject">AsyncAPI 2.0.0 spec</a>
 */
public abstract class AaiMessageTraitItems extends Node {

    //public String _name;

    // is either
    public List<AaiMessageTrait> _traitItems;
    // or
    public List<AaiMessageTraitExtendedItem> _traitExtendedItems;

//    public AaiMessageTraitItems(String _name) {
//        this._name = _name;
//    }
//
//    public String getName() {
//        return _name;
//    }

    public AaiMessageTraitItems(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    public abstract void addItem(AaiMessageTrait item);

    public abstract void addExtendedItem(AaiMessageTraitExtendedItem item);
}
