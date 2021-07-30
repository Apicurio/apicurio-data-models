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

package io.apicurio.datamodels.openapi.v2.models;

import io.apicurio.datamodels.core.models.common.SecurityScheme;

/**
 * Models an OpenAPI 2.0 security scheme.
 * @author eric.wittmann@gmail.com
 */
public class Oas20SecurityScheme extends SecurityScheme {
    
    public String flow;
    public String authorizationUrl;
    public String tokenUrl;
    public Oas20Scopes scopes;
    
    /**
     * Constructor.
     * @param schemeName
     */
    public Oas20SecurityScheme(String schemeName) {
        super(schemeName);
    }
    
    /**
     * Create the scopes.
     */
    public Oas20Scopes createScopes() {
        Oas20Scopes scopes = new Oas20Scopes();
        scopes._ownerDocument = this.ownerDocument();
        scopes._parent = this;
        return scopes;
    }

}
