package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.Node;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Represents `[Operation Trait Object] | [[Operation Trait Object, Map]]`
 *
 * @author Jakub Senko <jsenko@redhat.com>
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#messageObject">AsyncAPI 2.0.0 spec</a>
 */
public abstract class AaiOperationTraitItems extends Node {

    // is either
    public List<AaiOperationTrait> _traitItems;
    // or
    public List<AaiOperationTraitExtendedItem> _traitExtendedItems;

    public AaiOperationTraitItems(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    public abstract void addExtendedItem(AaiOperationTraitExtendedItem item);
    public abstract void addItem(AaiOperationTrait item);
}
