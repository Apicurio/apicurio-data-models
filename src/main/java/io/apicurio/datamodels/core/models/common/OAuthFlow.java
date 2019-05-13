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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.core.models.ExtensibleNode;

/**
 * Models an OAuth Flow.
 * @author eric.wittmann@gmail.com
 */
public abstract class OAuthFlow extends ExtensibleNode {

    public String authorizationUrl;
    public String tokenUrl;
    public String refreshUrl;
    public Map<String, String> scopes;

    /**
     * Constructor.
     */
    public OAuthFlow() {
    }

    /**
     * Adds a scope.
     * @param scope
     * @param description
     */
    public void addScope(String scope, String description) {
        if (this.scopes == null) {
            this.scopes = new LinkedHashMap<>();
        }
        this.scopes.put(scope, description);
    }

    /**
     * Removes a scope.
     * @param scope
     */
    public void removeScope(String scope) {
        if (this.scopes != null) {
            this.scopes.remove(scope);
        }
    }

    /**
     * Gets the list of scopes.
     */
    public List<String> getScopes() {
        List<String> rval = new ArrayList<>();
        if (this.scopes != null) {
            rval.addAll(this.scopes.keySet());
        }
        return rval;
    }

}
