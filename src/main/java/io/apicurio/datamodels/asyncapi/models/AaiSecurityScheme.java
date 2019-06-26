package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.SecurityScheme;

import static java.util.Objects.requireNonNull;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiSecurityScheme extends SecurityScheme {

    /**
     * Required.
     */
    public String scheme;

    /**
     * Required.
     */
    public AaiOAuthFlows flows;

    /**
     * Required.
     */
    public String openIdConnectUrl;

    /**
     * NOT Required.
     */
    public String bearerFormat;

    public AaiSecurityScheme(Node parent) {
        this(parent, null);
    }

    public AaiSecurityScheme(Node parent, String name) {
        super(name);
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }
}
