package io.apicurio.datamodels.asyncapi.v2.models;

import java.util.LinkedList;

import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitExtendedItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitItems;
import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;


/**
 * Represents `[Message Trait Object] | [[Message Trait Object, Map]]`
 *
 * @author Jakub Senko<jsenko@redhat.com>
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#messageObject">AsyncAPI 2.0.0 spec</a>
 */
public class Aai20MessageTraitItems extends AaiMessageTraitItems {


    public Aai20MessageTraitItems(Node parent) {
        super(parent);
    }

    @Override
    public void addItem(AaiMessageTrait item) {
        if(_traitItems == null)
            _traitItems = new LinkedList<>();
        _traitItems.add(item);
    }

    @Override
    public void addExtendedItem(AaiMessageTraitExtendedItem item) {
        if(_traitExtendedItems == null)
            _traitExtendedItems = new LinkedList<>();
        _traitExtendedItems.add(item);
    }

    @Override
    public void accept(IVisitor visitor) {
        IAai20Visitor v = (IAai20Visitor) visitor;
        v.visitMessageTraitItems(this);
    }
}
