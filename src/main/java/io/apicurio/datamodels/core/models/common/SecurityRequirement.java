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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * Models a security requirement.
 * @author eric.wittmann@gmail.com
 */
public abstract class SecurityRequirement extends Node {
    
    private Map<String, List<String>> _items = new LinkedHashMap<>();
    
    /**
     * Constructor.
     */
    public SecurityRequirement() {
    }
    
    /**
     * Gets the names of all the security requirements.
     */
    public List<String> getSecurityRequirementNames() {
        List<String> rval = new ArrayList<>();
        rval.addAll(this._items.keySet());
        return rval;
    }

    /**
     * Gets the scopes defined for this security requirement.  This is only valid if the
     * type of security is oauth2.
     */
    public List<String> getScopes(String name) {
        return this._items.get(name);
    }

    /**
     * Adds a security requirement item.
     * @param name
     * @param scopes
     */
    public void addSecurityRequirementItem(String name, List<String> scopes) {
        if (scopes == null) {
            scopes = Collections.emptyList();
        }
        this._items.put(name, scopes);
    }

    /**
     * Removes a single security requirement item (reference to an existing security scheme)
     * by scheme name and returns the array of scopes previously mapped to the scheme name.
     * @param name
     */
    public List<String> removeSecurityRequirementItem(String name) {
        return this._items.remove(name);
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        visitor.visitSecurityRequirement(this);
    }

}
