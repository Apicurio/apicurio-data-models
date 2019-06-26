package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitExtendedItem;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitExtendedItem;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitItems;
import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents `[Operation Trait Object] | [[Operation Trait Object, Map]]`
 *
 * @author Jakub Senko<jsenko@redhat.com>
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#messageObject">AsyncAPI 2.0.0 spec</a>
 */
public class Aai20OperationTraitItems extends AaiOperationTraitItems {

    public Aai20OperationTraitItems(Node parent) {
        super(parent);
    }

    @Override
    public void addItem(AaiOperationTrait item) {
        if(_traitItems == null)
            _traitItems = new LinkedList<>();
        _traitItems.add(item);
    }

    @Override
    public void addExtendedItem(AaiOperationTraitExtendedItem item) {
        if(_traitExtendedItems == null)
            _traitExtendedItems = new LinkedList<>();
        _traitExtendedItems.add(item);
    }

    @Override
    public void accept(IVisitor visitor) {
        IAai20Visitor v = (IAai20Visitor) visitor;
        v.visitOperationTraitItems(this);
    }
}
