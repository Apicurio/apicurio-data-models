package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.Node;

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

    public AaiOperationTrait(Node parent, String opType) {
        super(parent, opType);
    }

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
    
}
