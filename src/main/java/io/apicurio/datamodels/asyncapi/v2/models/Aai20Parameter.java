package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiParameter;
import io.apicurio.datamodels.core.models.Node;

/**
 * @author Jakub Senko<jsenko@redhat.com>
 */
public class Aai20Parameter extends AaiParameter {

    public Aai20Parameter(Node parent) {
        super(parent);
    }

    public Aai20Parameter(Node parent, String name) {
        super(parent, name);
    }
}
