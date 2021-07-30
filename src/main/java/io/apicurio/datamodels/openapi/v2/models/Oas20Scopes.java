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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor;

/**
 * Models an OpenAPI 2.0 scopes.
 * @author eric.wittmann@gmail.com
 */
public class Oas20Scopes extends ExtensibleNode {
    
    private Map<String, String> items = new LinkedHashMap<>();
    
    /**
     * Constructor.
     */
    public Oas20Scopes() {
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOas20Visitor viz = (IOas20Visitor) visitor;
        viz.visitScopes(this);
    }

    /**
     * Returns all the scopes.
     */
    public List<String> getScopeNames() {
        List<String> rval = new ArrayList<>();
        rval.addAll(this.items.keySet());
        return rval;
    }

    /**
     * Gets a scope description.
     * @param scope
     */
    public String getScopeDescription(String scope) {
        return this.items.get(scope);
    }

    /**
     * Adds a scope to the map.
     * @param scope
     * @param description
     */
    public void addScope(String scope, String description) {
        this.items.put(scope, description);
    }

    /**
     * Removes a scope.
     * @param scope
     */
    public String removeScope(String scope) {
        return this.items.remove(scope);
    }

}
