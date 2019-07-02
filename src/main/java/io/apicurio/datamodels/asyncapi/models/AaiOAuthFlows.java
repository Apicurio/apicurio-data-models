package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.OAuthFlows;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiOAuthFlows extends OAuthFlows {


    public AaiOAuthFlows(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }
}
