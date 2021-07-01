package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.core.models.Node;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Aai20MessageTrait extends AaiMessageTrait {

    /**
     * Constructor.
     */
    public Aai20MessageTrait(String name) {
        super(name);
    }

    public Aai20MessageTrait(Node parent) {
        super(parent);
    }

    public Aai20MessageTrait(Node parent, String name) {
        super(parent, name);
    }

}
