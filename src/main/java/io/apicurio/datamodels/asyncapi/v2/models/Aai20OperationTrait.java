package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiTag;
import io.apicurio.datamodels.core.models.Node;

/**
 * @author Jakub Senko<jsenko@redhat.com>
 */
public class Aai20OperationTrait extends AaiOperationTrait {
    
    /**
     * Constructor.
     */
    public Aai20OperationTrait(String opType) {
        super(opType);
    }

    /**
     * Constructor.
     * @param parent
     * @param opType
     */
    public Aai20OperationTrait(Node parent, String opType) {
        super(parent, opType);
    }

    /**
     * Constructor.
     * @param parent
     */
    public Aai20OperationTrait(Node parent) {
        super(parent);
    }
    
    /**
     * @see io.apicurio.datamodels.asyncapi.models.AaiOperationBase#createTag()
     */
    @Override
    public AaiTag createTag() {
        return new Aai20Tag(this);
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.Operation#createExternalDocumentation()
     */
    @Override
    public Aai20ExternalDocumentation createExternalDocumentation() {
        return new Aai20ExternalDocumentation(this);
    }

}
