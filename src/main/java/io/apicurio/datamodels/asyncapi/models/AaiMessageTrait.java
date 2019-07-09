package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.Node;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiMessageTrait extends AaiMessageBase implements IAaiTrait {

    /**
     * Constructor.
     */
    public AaiMessageTrait(String name) {
        super(name);
    }

    public AaiMessageTrait(Node parent) {
        super(parent);
    }

    public AaiMessageTrait(Node parent, String name) {
        super(parent, name);
    }
    
    /**
     * @see io.apicurio.datamodels.asyncapi.models.IAaiTrait#isMessageTrait()
     */
    @Override
    public boolean isMessageTrait() {
        return true;
    }
    
    /**
     * @see io.apicurio.datamodels.asyncapi.models.IAaiTrait#isOperationTrait()
     */
    @Override
    public boolean isOperationTrait() {
        return false;
    }
    
    /**
     * @see io.apicurio.datamodels.asyncapi.models.IAaiTrait#isUnknownTrait()
     */
    @Override
    public boolean isUnknownTrait() {
        return false;
    }
}
