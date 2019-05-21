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
        super(null);
        requireNonNull(parent);
        this._parent = parent;
        this._ownerDocument = parent.ownerDocument();
    }

    public AaiSecurityScheme(Node parent, String _name) {
        super(_name);
        requireNonNull(parent);
        this._parent = parent;
        this._ownerDocument = parent.ownerDocument();
    }
}
