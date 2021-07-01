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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.models.common.INamed;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor;

/**
 * Models an OpenAPI 3.0.x Link.
 * @author eric.wittmann@gmail.com
 */
public class Oas30Link extends ExtensibleNode implements INamed, IReferenceNode {

    private String _name;

    public String $ref;
    public String operationRef;
    public String operationId;
    public Map<String, Oas30LinkParameterExpression> parameters = new LinkedHashMap<>();
    public Oas30LinkRequestBodyExpression requestBody;
    public String description;
    public Oas30LinkServer server;
    
    /**
     * Constructor.
     * @param name
     */
    public Oas30Link(String name) {
        this._name = name;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOas30Visitor viz = (IOas30Visitor) visitor;
        viz.visitLink(this);
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#getName()
     */
    @Override
    public String getName() {
        return this._name;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#rename(java.lang.String)
     */
    @Override
    public void rename(String newName) {
        this._name = newName;
    }

    @Override
    public String getReference() {
        return $ref;
    }

    @Override
    public void setReference(String reference) {
        $ref = reference;
    }

    /**
     * Creates a link parameter expression.
     * @param name
     * @param value
     */
    public Oas30LinkParameterExpression createLinkParameterExpression(String name, String value) {
        Oas30LinkParameterExpression rval = new Oas30LinkParameterExpression(name, value);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a link parameter expression.
     * @param name
     * @param expression
     */
    public void addLinkParameterExpression(String name, Oas30LinkParameterExpression expression) {
        this.parameters.put(name, expression);
    }

    /**
     * Adds a link parameter expression.
     * @param name
     * @param expression
     */
    public Oas30LinkParameterExpression addLinkParameter(String name, String expression) {
        Oas30LinkParameterExpression model = this.createLinkParameterExpression(name, expression);
        this.addLinkParameterExpression(name, model);
        return model;
    }

    /**
     * Gets a single link parameter expression by name.
     * @param name
     */
    public Oas30LinkParameterExpression getLinkParameterExpression(String name) {
        return this.parameters.get(name);
    }

    /**
     * Removes a single link parameter expression and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public Oas30LinkParameterExpression removeLinkParameterExpression(String name) {
        return this.parameters.remove(name);
    }

    /**
     * Gets a list of all link parameter expressions.
     */
    public List<Oas30LinkParameterExpression> getLinkParameterExpressions() {
        List<Oas30LinkParameterExpression> rval = new ArrayList<>();
        rval.addAll(this.parameters.values());
        return rval;
    }

    /**
     * Creates a link request body expression.
     * @param value
     */
    public Oas30LinkRequestBodyExpression createLinkRequestBodyExpression(String value) {
        Oas30LinkRequestBodyExpression rval = new Oas30LinkRequestBodyExpression(value);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Creates an OAS 3.0 Server object.
     */
    public Oas30LinkServer createServer() {
        Oas30LinkServer rval = new Oas30LinkServer();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

}
