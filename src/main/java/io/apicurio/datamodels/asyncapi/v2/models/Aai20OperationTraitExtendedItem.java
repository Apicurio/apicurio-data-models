package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitExtendedItem;
import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;

import java.util.LinkedHashMap;


/**
 * Represents `[Operation Trait Object, Map[string, any]]`
 *
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#operationObject">AsyncAPI 2.0.0 spec</a>
 */
public class Aai20OperationTraitExtendedItem extends AaiOperationTraitExtendedItem {


    public Aai20OperationTraitExtendedItem(Node parent) {
        super(parent);
    }

    public Aai20OperationTraitExtendedItem(Node parent, String _name) {
        super(parent, _name);
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
        v.visitOperationTraitExtendedItem(this);
    }
}
