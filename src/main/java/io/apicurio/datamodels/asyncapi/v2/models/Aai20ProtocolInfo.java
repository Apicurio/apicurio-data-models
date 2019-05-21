package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiProtocolInfo;
import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;

import java.util.LinkedHashMap;
import java.util.LinkedList;


public class Aai20ProtocolInfo extends AaiProtocolInfo {

    public Aai20ProtocolInfo(String _name) {
        super(_name);
    }

    public Aai20ProtocolInfo(Node parent, String _name) {
        super(parent, _name);
    }

    @Override
    public void addItem(String key, Object value) {
        if(_protocolInfoItems == null)
            _protocolInfoItems = new LinkedHashMap<>();
        _protocolInfoItems.put(key, value);
    }

    @Override
    public void accept(IVisitor visitor) {
        IAai20Visitor v = (IAai20Visitor) visitor;
        v.visitProtocolInfo(this);
    }
}
