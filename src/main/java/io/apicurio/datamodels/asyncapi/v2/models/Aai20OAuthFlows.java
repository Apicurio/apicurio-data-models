package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiOAuthFlows;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow;
import io.apicurio.datamodels.core.models.common.PasswordOAuthFlow;

/**
 * @author Jakub Senko<jsenko@redhat.com>
 */
public class Aai20OAuthFlows extends AaiOAuthFlows {
    
    /**
     * Constructor.
     */
    public Aai20OAuthFlows() {
    }

    public Aai20OAuthFlows(Node parent) {
        super(parent);
    }

    @Override
    public ImplicitOAuthFlow createImplicitOAuthFlow() {
        return new Aai20ImplicitOAuthFlow(this);
    }

    @Override
    public PasswordOAuthFlow createPasswordOAuthFlow() {
        return new Aai20PasswordOAuthFlow(this);
    }

    @Override
    public ClientCredentialsOAuthFlow createClientCredentialsOAuthFlow() {
        return new Aai20ClientCredentialsOAuthFlow(this);
    }

    @Override
    public AuthorizationCodeOAuthFlow createAuthorizationCodeOAuthFlow() {
        return new Aai20AuthorizationCodeOAuthFlow(this);
    }
}
