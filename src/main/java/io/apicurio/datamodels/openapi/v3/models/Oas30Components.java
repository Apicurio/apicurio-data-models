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

import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor;

/**
 * Models an OpenAPI 3.0.x Components.
 * @author eric.wittmann@gmail.com
 */
public class Oas30Components extends Components {

    public Map<String, Oas30SchemaDefinition> schemas = new LinkedHashMap<>();
    public Map<String, Oas30ResponseDefinition> responses = new LinkedHashMap<>();
    public Map<String, Oas30ParameterDefinition> parameters = new LinkedHashMap<>();
    public Map<String, Oas30ExampleDefinition> examples = new LinkedHashMap<>();
    public Map<String, Oas30RequestBodyDefinition> requestBodies = new LinkedHashMap<>();
    public Map<String, Oas30HeaderDefinition> headers = new LinkedHashMap<>();
    public Map<String, Oas30SecurityScheme> securitySchemes = new LinkedHashMap<>(); // TODO should be a separate definition object?
    public Map<String, Oas30LinkDefinition> links = new LinkedHashMap<>();
    public Map<String, Oas30CallbackDefinition> callbacks = new LinkedHashMap<>();

    /**
     * Constructor.
     */
    public Oas30Components() {
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOas30Visitor viz = (IOas30Visitor) visitor;
        viz.visitComponents(this);
    }

    /**
     * Creates a schema definition.
     * @param name
     */
    public Oas30SchemaDefinition createSchemaDefinition(String name) {
        Oas30SchemaDefinition rval = new Oas30SchemaDefinition(name);
        rval._ownerDocument = this._ownerDocument;
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a schema definition.
     * @param name
     * @param schemaDefinition
     */
    public void addSchemaDefinition(String name, Oas30SchemaDefinition schemaDefinition) {
        this.schemas.put(name, schemaDefinition);
    }

    /**
     * Gets a single schema definition by name.
     * @param name
     */
    public Oas30SchemaDefinition getSchemaDefinition(String name) {
        return this.schemas.get(name);
    }

    /**
     * Removes a single schema definition and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public Oas30SchemaDefinition removeSchemaDefinition(String name) {
        return this.schemas.remove(name);
    }

    /**
     * Gets a list of all schema definitions.
     */
    public List<Oas30SchemaDefinition> getSchemaDefinitions() {
        List<Oas30SchemaDefinition> rval = new ArrayList<>();
        rval.addAll(this.schemas.values());
        return rval;
    }

    /**
     * Creates a response definition.
     * @param name
     */
    public Oas30ResponseDefinition createResponseDefinition(String name) {
        Oas30ResponseDefinition rval = new Oas30ResponseDefinition(name);
        rval._ownerDocument = this._ownerDocument;
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a response definition.
     * @param name
     * @param responseDefinition
     */
    public void addResponseDefinition(String name, Oas30ResponseDefinition responseDefinition) {
        this.responses.put(name, responseDefinition);
    }

    /**
     * Gets a single response definition by name.
     * @param name
     */
    public Oas30ResponseDefinition getResponseDefinition(String name) {
        return this.responses.get(name);
    }

    /**
     * Removes a single response definition and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public Oas30ResponseDefinition removeResponseDefinition(String name) {
        return this.responses.remove(name);
    }

    /**
     * Gets a list of all response definitions.
     */
    public List<Oas30ResponseDefinition> getResponseDefinitions() {
        List<Oas30ResponseDefinition> rval = new ArrayList<>();
        rval.addAll(this.responses.values());
        return rval;
    }

    /**
     * Creates a parameter definition.
     * @param name
     */
    public Oas30ParameterDefinition createParameterDefinition(String name) {
        Oas30ParameterDefinition rval = new Oas30ParameterDefinition(name);
        rval._ownerDocument = this._ownerDocument;
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a parameter definition.
     * @param name
     * @param parameterDefinition
     */
    public void addParameterDefinition(String name, Oas30ParameterDefinition parameterDefinition) {
        this.parameters.put(name, parameterDefinition);
    }

    /**
     * Gets a single parameter definition by name.
     * @param name
     */
    public Oas30ParameterDefinition getParameterDefinition(String name) {
        return this.parameters.get(name);
    }

    /**
     * Removes a single parameter definition and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public Oas30ParameterDefinition removeParameterDefinition(String name) {
        return this.parameters.remove(name);
    }

    /**
     * Gets a list of all parameter definitions.
     */
    public List<Oas30ParameterDefinition> getParameterDefinitions() {
        List<Oas30ParameterDefinition> rval = new ArrayList<>();
        rval.addAll(this.parameters.values());
        return rval;
    }

    /**
     * Creates a example definition.
     * @param name
     */
    public Oas30ExampleDefinition createExampleDefinition(String name) {
        Oas30ExampleDefinition rval = new Oas30ExampleDefinition(name);
        rval._ownerDocument = this._ownerDocument;
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a example definition.
     * @param name
     * @param exampleDefinition
     */
    public void addExampleDefinition(String name, Oas30ExampleDefinition exampleDefinition) {
        this.examples.put(name, exampleDefinition);
    }

    /**
     * Gets a single example definition by name.
     * @param name
     */
    public Oas30ExampleDefinition getExampleDefinition(String name) {
        return this.examples.get(name);
    }

    /**
     * Removes a single example definition and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public Oas30ExampleDefinition removeExampleDefinition(String name) {
        return this.examples.remove(name);
    }

    /**
     * Gets a list of all example definitions.
     */
    public List<Oas30ExampleDefinition> getExampleDefinitions() {
        List<Oas30ExampleDefinition> rval = new ArrayList<>();
        rval.addAll(this.examples.values());
        return rval;
    }

    /**
     * Creates a request body definition.
     * @param name
     */
    public Oas30RequestBodyDefinition createRequestBodyDefinition(String name) {
        Oas30RequestBodyDefinition rval = new Oas30RequestBodyDefinition(name);
        rval._ownerDocument = this._ownerDocument;
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a request body definition.
     * @param name
     * @param requestBodyDefinition
     */
    public void addRequestBodyDefinition(String name, Oas30RequestBodyDefinition requestBodyDefinition) {
        this.requestBodies.put(name, requestBodyDefinition);
    }

    /**
     * Gets a single request body definition by name.
     * @param name
     */
    public Oas30RequestBodyDefinition getRequestBodyDefinition(String name) {
        return this.requestBodies.get(name);
    }

    /**
     * Removes a single request body definition and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public Oas30RequestBodyDefinition removeRequestBodyDefinition(String name) {
        return this.requestBodies.remove(name);
    }

    /**
     * Gets a list of all request body definitions.
     */
    public List<Oas30RequestBodyDefinition> getRequestBodyDefinitions() {
        List<Oas30RequestBodyDefinition> rval = new ArrayList<>();
        rval.addAll(this.requestBodies.values());
        return rval;
    }

    /**
     * Creates a header definition.
     * @param name
     */
    public Oas30HeaderDefinition createHeaderDefinition(String name) {
        Oas30HeaderDefinition rval = new Oas30HeaderDefinition(name);
        rval._ownerDocument = this._ownerDocument;
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a header definition.
     * @param name
     * @param headerDefinition
     */
    public void addHeaderDefinition(String name, Oas30HeaderDefinition headerDefinition) {
        this.headers.put(name, headerDefinition);
    }

    /**
     * Gets a single header definition by name.
     * @param name
     */
    public Oas30HeaderDefinition getHeaderDefinition(String name) {
        return this.headers.get(name);
    }

    /**
     * Removes a single header definition and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public Oas30HeaderDefinition removeHeaderDefinition(String name) {
        return this.headers.remove(name);
    }

    /**
     * Gets a list of all header definitions.
     */
    public List<Oas30HeaderDefinition> getHeaderDefinitions() {
        List<Oas30HeaderDefinition> rval = new ArrayList<>();
        rval.addAll(this.headers.values());
        return rval;
    }

    /**
     * Creates a security scheme definition.
     * @param name
     */
    public Oas30SecurityScheme createSecurityScheme(String name) {
        Oas30SecurityScheme rval = new Oas30SecurityScheme(name);
        rval._ownerDocument = this._ownerDocument;
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a security scheme definition.
     * @param name
     * @param securityScheme
     */
    public void addSecurityScheme(String name, Oas30SecurityScheme securityScheme) {
        this.securitySchemes.put(name, securityScheme);
    }

    /**
     * Gets a single security scheme definition by name.
     * @param name
     */
    public Oas30SecurityScheme getSecurityScheme(String name) {
        return this.securitySchemes.get(name);
    }

    /**
     * Removes a single security scheme definition and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public Oas30SecurityScheme removeSecurityScheme(String name) {
        return this.securitySchemes.remove(name);
    }

    /**
     * Gets a list of all security scheme definitions.
     */
    public List<Oas30SecurityScheme> getSecuritySchemes() {
        List<Oas30SecurityScheme> rval = new ArrayList<>();
        rval.addAll(this.securitySchemes.values());
        return rval;
    }

    /**
     * Creates a link definition.
     * @param name
     */
    public Oas30LinkDefinition createLinkDefinition(String name) {
        Oas30LinkDefinition rval = new Oas30LinkDefinition(name);
        rval._ownerDocument = this._ownerDocument;
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a link definition.
     * @param name
     * @param linkDefinition
     */
    public void addLinkDefinition(String name, Oas30LinkDefinition linkDefinition) {
        this.links.put(name, linkDefinition);
    }

    /**
     * Gets a single link definition by name.
     * @param name
     */
    public Oas30LinkDefinition getLinkDefinition(String name) {
        return this.links.get(name);
    }

    /**
     * Removes a single link definition and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public Oas30LinkDefinition removeLinkDefinition(String name) {
        return this.links.remove(name);
    }

    /**
     * Gets a list of all link definitions.
     */
    public List<Oas30LinkDefinition> getLinkDefinitions() {
        List<Oas30LinkDefinition> rval = new ArrayList<>();
        rval.addAll(this.links.values());
        return rval;
    }

    /**
     * Creates a callback definition.
     * @param name
     */
    public Oas30CallbackDefinition createCallbackDefinition(String name) {
        Oas30CallbackDefinition rval = new Oas30CallbackDefinition(name);
        rval._ownerDocument = this._ownerDocument;
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a callback definition.
     * @param name
     * @param callbackDefinition
     */
    public void addCallbackDefinition(String name, Oas30CallbackDefinition callbackDefinition) {
        this.callbacks.put(name, callbackDefinition);
    }

    /**
     * Gets a single callback definition by name.
     * @param name
     */
    public Oas30CallbackDefinition getCallbackDefinition(String name) {
        return this.callbacks.get(name);
    }

    /**
     * Removes a single callback definition and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public Oas30CallbackDefinition removeCallbackDefinition(String name) {
        return this.callbacks.remove(name);
    }

    /**
     * Gets a list of all callback definitions.
     */
    public List<Oas30CallbackDefinition> getCallbackDefinitions() {
        List<Oas30CallbackDefinition> rval = new ArrayList<>();
        rval.addAll(this.callbacks.values());
        return rval;
    }

}
