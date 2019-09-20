package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.core.models.Node;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Aai20ChannelItem extends AaiChannelItem {

    /**
     * Constructor.
     */
    public Aai20ChannelItem(String name) {
        super(name);
    }

    public Aai20ChannelItem(Node parent) {
        super(parent);
    }

    public Aai20ChannelItem(Node parent, String name) {
        super(parent, name);
    }

}
