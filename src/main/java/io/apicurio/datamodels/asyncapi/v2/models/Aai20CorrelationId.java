package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiCorrelationId;
import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;


public class Aai20CorrelationId extends AaiCorrelationId {


    public Aai20CorrelationId(Node parent) {
        super(parent);
    }

    public Aai20CorrelationId(Node parent, String name) {
        super(parent, name);
    }

    @Override
    public void accept(IVisitor visitor) {
        IAai20Visitor v = (IAai20Visitor) visitor;
        v.visitCorrelationId(this);
    }
}
