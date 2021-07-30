/*
 * Copyright 2019 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.datamodels.openapi.v3.models;

import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlows;
import io.apicurio.datamodels.core.models.common.PasswordOAuthFlow;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor;

/**
 * Models an OpenAPI 3.0.x OAuth Flows.
 * @author eric.wittmann@gmail.com
 */
public class Oas30OAuthFlows extends OAuthFlows {

    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOas30Visitor viz = (IOas30Visitor) visitor;
        viz.visitOAuthFlows(this);
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.OAuthFlows#createImplicitOAuthFlow()
     */
    @Override
    public ImplicitOAuthFlow createImplicitOAuthFlow() {
        Oas30ImplicitOAuthFlow rval = new Oas30ImplicitOAuthFlow();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.OAuthFlows#createPasswordOAuthFlow()
     */
    @Override
    public PasswordOAuthFlow createPasswordOAuthFlow() {
        Oas30PasswordOAuthFlow rval = new Oas30PasswordOAuthFlow();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.OAuthFlows#createClientCredentialsOAuthFlow()
     */
    @Override
    public ClientCredentialsOAuthFlow createClientCredentialsOAuthFlow() {
        Oas30ClientCredentialsOAuthFlow rval = new Oas30ClientCredentialsOAuthFlow();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.OAuthFlows#createAuthorizationCodeOAuthFlow()
     */
    @Override
    public AuthorizationCodeOAuthFlow createAuthorizationCodeOAuthFlow() {
        Oas30AuthorizationCodeOAuthFlow rval = new Oas30AuthorizationCodeOAuthFlow();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

}
