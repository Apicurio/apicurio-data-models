package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiSecurityScheme;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.OAuthFlows;

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

    /**
     * Constructor.
     * @param parent
     */
    public Aai20SecurityScheme(Node parent) {
        super(parent);
    }

    /**
     * Constructor.
     * @param parent
     * @param name
     */
    public Aai20SecurityScheme(Node parent, String name) {
        super(parent, name);
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.common.ModernSecurityScheme#createOAuthFlows()
     */
    @Override
    public OAuthFlows createOAuthFlows() {
        Aai20OAuthFlows rval = new Aai20OAuthFlows();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }
}
