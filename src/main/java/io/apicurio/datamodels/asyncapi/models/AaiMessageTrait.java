package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.Node;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiMessageTrait extends AaiMessageBase {


    public AaiMessageTrait(Node parent) {
        super(parent);
    }

    public AaiMessageTrait(Node parent, String _name) {
        super(parent, _name);
    }
}
