package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.Node;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiOperationTrait extends AaiOperationBase {


    public AaiOperationTrait(Node parent, String opType) {
        super(parent, opType);
    }

    public AaiOperationTrait(Node parent) {
        super(parent);
    }
}
