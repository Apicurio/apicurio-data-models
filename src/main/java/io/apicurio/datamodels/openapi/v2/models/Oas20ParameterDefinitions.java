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
 * Models an OpenAPI 2.0 Parameter Definitions.
 * @author eric.wittmann@gmail.com
 */
public class Oas20ParameterDefinitions extends Node implements IIndexedNode<Oas20ParameterDefinition> {

    private Map<String, Oas20ParameterDefinition> items = new LinkedHashMap<>();
    
    /**
     * Constructor.
     */
    public Oas20ParameterDefinitions() {
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOas20Visitor viz = (IOas20Visitor) visitor;
        viz.visitParameterDefinitions(this);
    }

    /**
     * Returns a single parameter by name.
     * @param name
     */
    public Oas20ParameterDefinition getParameter(String name) {
        return this.items.get(name);
    }

    /**
     * Returns an array of all the parameters.
     */
    public List<Oas20ParameterDefinition> getParameters() {
        List<Oas20ParameterDefinition> rval = new ArrayList<>();
        rval.addAll(this.items.values());
        return rval;
    }

    /**
     * Adds a parameter.
     * @param name
     * @param parameter
     */
    public Oas20ParameterDefinition addParameter(String name, Oas20ParameterDefinition parameter) {
        this.items.put(name, parameter);
        return parameter;
    }

    /**
     * Removes a parameter by name.
     * @param name
     */
    public Oas20ParameterDefinition removeParameter(String name) {
        return this.items.remove(name);
    }

    /**
     * Gets a list of all the parameter names.
     */
    public List<String> getParameterNames() {
        List<String> rval = new ArrayList<>();
        rval.addAll(this.items.keySet());
        return rval;
    }

    /**
     * Creates an OAS 2.0 Parameter object.
     * @param name
     */
    public Oas20ParameterDefinition createParameter(String name) {
        Oas20ParameterDefinition rval = new Oas20ParameterDefinition(name);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItem(java.lang.String)
     */
    @Override
    public Oas20ParameterDefinition getItem(String name) {
        return this.getParameter(name);
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItems()
     */
    @Override
    public List<Oas20ParameterDefinition> getItems() {
        return this.getParameters();
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItemNames()
     */
    @Override
    public List<String> getItemNames() {
        return this.getParameterNames();
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#addItem(java.lang.String, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    public void addItem(String name, Oas20ParameterDefinition item) {
        this.addParameter(name, item);
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#deleteItem(java.lang.String)
     */
    @Override
    public Oas20ParameterDefinition deleteItem(String name) {
        return this.removeParameter(name);
    }

}
