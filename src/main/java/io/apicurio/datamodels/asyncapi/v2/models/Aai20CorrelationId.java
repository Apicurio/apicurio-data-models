package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiCorrelationId;
import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Aai20CorrelationId extends AaiCorrelationId {

    /**
     * Constructor.
     */
    public Aai20CorrelationId(String name) {
        super(name);
    }

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
