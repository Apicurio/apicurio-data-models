package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.core.models.Node;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Aai20Message extends AaiMessage {

    /**
     * Constructor.
     */
    public Aai20Message(String name) {
        super(name);
    }

    public Aai20Message(Node parent) {
        super(parent);
    }

    public Aai20Message(Node parent, String name) {
        super(parent, name);
    }

}
