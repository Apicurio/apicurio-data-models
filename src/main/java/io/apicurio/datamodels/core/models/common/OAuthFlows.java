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

package io.apicurio.datamodels.core.models.common;

import io.apicurio.datamodels.core.models.ExtensibleNode;

/**
 * Models an OAuth Flows entity.
 * @author eric.wittmann@gmail.com
 */
public abstract class OAuthFlows extends ExtensibleNode {
    
    public ImplicitOAuthFlow implicit;
    public PasswordOAuthFlow password;
    public ClientCredentialsOAuthFlow clientCredentials;
    public AuthorizationCodeOAuthFlow authorizationCode;
    
    /**
     * Constructor.
     */
    public OAuthFlows() {
    }

    /**
     * Creates an OAuth Flow object.
     */
    public abstract ImplicitOAuthFlow createImplicitOAuthFlow();

    /**
     * Creates an OAuth Flow object.
     */
    public abstract PasswordOAuthFlow createPasswordOAuthFlow();

    /**
     * Creates an OAuth Flow object.
     */
    public abstract ClientCredentialsOAuthFlow createClientCredentialsOAuthFlow();

    /**
     * Creates an OAuth Flow object.
     */
    public abstract AuthorizationCodeOAuthFlow createAuthorizationCodeOAuthFlow();

}
