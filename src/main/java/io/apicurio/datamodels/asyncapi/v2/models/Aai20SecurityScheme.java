package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiSecurityScheme;
import io.apicurio.datamodels.core.models.Node;

/**
 * @author Jakub Senko<jsenko@redhat.com>
 */
public class Aai20SecurityScheme extends AaiSecurityScheme {
    
    /**
     * Constructor.
     */
    public Aai20SecurityScheme(String name) {
        super(name);
    }

    public Aai20SecurityScheme(Node parent) {
        super(parent);
    }

    public Aai20SecurityScheme(Node parent, String name) {
        super(parent, name);
    }
}
