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
 * Models an OpenAPI security definitions.
 * @author eric.wittmann@gmail.com
 */
public class Oas20SecurityDefinitions extends Node implements IIndexedNode<Oas20SecurityScheme> {
    
    private Map<String, Oas20SecurityScheme> items;
    
    /**
     * Constructor.
     */
    public Oas20SecurityDefinitions() {
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOas20Visitor viz = (IOas20Visitor) visitor;
        viz.visitSecurityDefinitions(this);
    }

    /**
     * Gets a list of all the security scheme names.
     */
    public List<String> getSecuritySchemeNames() {
        List<String> rval = new ArrayList<>();
        if (this.items != null) {
            rval.addAll(this.items.keySet());
        }
        return rval;
    }

    /**
     * Returns a single security scheme by name.
     * @param name
     */
    public Oas20SecurityScheme getSecurityScheme(String name) {
        if (this.items != null) {
            return this.items.get(name);
        }
        return null;
    }

    /**
     * Returns an array of all the security schemes.
     */
    public List<Oas20SecurityScheme> getSecuritySchemes() {
        List<Oas20SecurityScheme> rval = new ArrayList<>();
        if (this.items != null) {
            rval.addAll(this.items.values());
        }
        return rval;
    }

    /**
     * Adds a security scheme child node.
     * @param name
     * @param scheme
     */
    public void addSecurityScheme(String name, Oas20SecurityScheme scheme) {
        if (this.items == null) {
            this.items = new LinkedHashMap<>();
        }
        this.items.put(name, scheme);
    }

    /**
     * Removes a single security scheme by name.
     * @param name
     */
    public Oas20SecurityScheme removeSecurityScheme(String name) {
        if (this.items != null) {
            return this.items.remove(name);
        }
        return null;
    }

    /**
     * Creates a child security scheme object and adds it to the list.
     * @param name
     */
    public Oas20SecurityScheme createSecurityScheme(String name) {
        Oas20SecurityScheme rval = new Oas20SecurityScheme(name);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItem(java.lang.String)
     */
    @Override
    public Oas20SecurityScheme getItem(String name) {
        return this.getSecurityScheme(name);
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItems()
     */
    @Override
    public List<Oas20SecurityScheme> getItems() {
        return this.getSecuritySchemes();
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItemNames()
     */
    @Override
    public List<String> getItemNames() {
        return this.getItemNames();
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#addItem(java.lang.String, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    public void addItem(String name, Oas20SecurityScheme item) {
        this.addSecurityScheme(name, item);
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#deleteItem(java.lang.String)
     */
    @Override
    public Oas20SecurityScheme deleteItem(String name) {
        return this.removeSecurityScheme(name);
    }

}
