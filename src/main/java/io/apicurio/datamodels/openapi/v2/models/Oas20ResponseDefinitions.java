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

import io.apicurio.datamodels.core.models.IIndexedNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor;

/**
 * Models an OpenAPI 2.0 responses.
 * @author eric.wittmann@gmail.com
 */
public class Oas20ResponseDefinitions extends Node implements IIndexedNode<Oas20ResponseDefinition> {
    
    public Map<String, Oas20ResponseDefinition> items = new LinkedHashMap<>();
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOas20Visitor viz = (IOas20Visitor) visitor;
        viz.visitResponseDefinitions(this);
    }

    /**
     * Returns a single response by name.
     * @param name
     */
    public Oas20ResponseDefinition getResponse(String name) {
        return this.items.get(name);
    }

    /**
     * Returns an array of all the responses.
     */
    public List<Oas20ResponseDefinition> getResponses() {
        List<Oas20ResponseDefinition> rval = new ArrayList<>();
        rval.addAll(this.items.values());
        return rval;
    }

    /**
     * Adds a response.
     * @param name
     * @param response
     */
    public Oas20ResponseDefinition addResponse(String name, Oas20ResponseDefinition response) {
        this.items.put(name, response);
        return response;
    }

    /**
     * Replaces a response without modifying the order of the responses.
     * @param newResponse
     */
    public Oas20ResponseDefinition replaceResponse(Oas20ResponseDefinition newResponse) {
        // As long as this is backed by a LinkedHashMap, this will preserve the ordering of the entries within
        addResponse(newResponse.getName(), newResponse);
        return newResponse;
    }

    /**
     * Removes a response by name.
     * @param name
     */
    public Oas20ResponseDefinition removeResponse(String name) {
        return this.items.remove(name);
    }

    /**
     * Gets a list of all the response names.
     */
    public List<String> getResponseNames() {
        List<String> rval = new ArrayList<>();
        rval.addAll(this.items.keySet());
        return rval;
    }

    /**
     * Creates an OAS 2.0 Response object.
     * @param name
     */
    public Oas20ResponseDefinition createResponse(String name) {
        Oas20ResponseDefinition rval = new Oas20ResponseDefinition(name);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItem(java.lang.String)
     */
    @Override
    public Oas20ResponseDefinition getItem(String name) {
        return this.getResponse(name);
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItems()
     */
    @Override
    public List<Oas20ResponseDefinition> getItems() {
        return this.getResponses();
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItemNames()
     */
    @Override
    public List<String> getItemNames() {
        return this.getResponseNames();
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#addItem(java.lang.String, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    public void addItem(String name, Oas20ResponseDefinition item) {
        this.addResponse(name, item);
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#deleteItem(java.lang.String)
     */
    @Override
    public Oas20ResponseDefinition deleteItem(String name) {
        return this.removeResponse(name);
    }

}
