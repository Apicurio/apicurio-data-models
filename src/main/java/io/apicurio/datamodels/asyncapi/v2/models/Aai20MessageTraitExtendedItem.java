package io.apicurio.datamodels.asyncapi.v2.models;

import java.util.LinkedHashMap;

import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitExtendedItem;
import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;


/**
 * Represents `[Message Trait Object, Map[string, any]]`
 *
 * @author Jakub Senko<jsenko@redhat.com>
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#messageObject">AsyncAPI 2.0.0 spec</a>
 */
public class Aai20MessageTraitExtendedItem extends AaiMessageTraitExtendedItem {


    public Aai20MessageTraitExtendedItem(Node parent) {
        super(parent);
    }

    @Override
    public void addExtension(String key, Object value) {
        if(_traitExtension == null)
            _traitExtension = new LinkedHashMap<>();
        _traitExtension.put(key, value);
    }

    @Override
    public void accept(IVisitor visitor) {
        IAai20Visitor v = (IAai20Visitor) visitor;
        v.visitMessageTraitExtendedItem(this);
    }
}
