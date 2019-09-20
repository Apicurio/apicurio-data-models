package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiOperationTrait extends AaiOperationBase implements IAaiTrait {

    /**
     * Constructor.
     */
    public AaiOperationTrait(String opType) {
        super(opType);
    }

    /**
     * Constructor.
     * @param parent
     * @param opType
     */
    public AaiOperationTrait(Node parent, String opType) {
        super(parent, opType);
    }

    /**
     * Constructor.
     * @param parent
     */
    public AaiOperationTrait(Node parent) {
        super(parent);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.models.IAaiTrait#getTraitType()
     */
    @Override
    public AaiTraitType getTraitType() {
        return AaiTraitType.operation;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.Operation#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IAai20Visitor v = (IAai20Visitor) visitor;
        v.visitOperationTrait(this);
    }

}
