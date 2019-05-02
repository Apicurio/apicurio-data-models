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

package io.apicurio.datamodels.openapi.models;

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.common.Operation;

/**
 * Models an OpenAPI operation.
 * @author eric.wittmann@gmail.com
 */
public abstract class OasOperation extends Operation {

    public List<String> tags;
    public List<OasParameter> parameters;
    public OasResponses responses;
    public Boolean deprecated;
    public List<OasSecurityRequirement> security;
    
    /**
     * Constructor.
     * @param method
     */
    public OasOperation(String method) {
        super(method);
    }
    
    /**
     * Gets the operation's method.
     */
    public String getMethod() {
        return this.getType();
    }

    /**
     * Creates a child parameter model.
     */
    public abstract OasParameter createParameter();

    /**
     * Returns a list of parameters with a particular value of "in" (e.g. path, formData, body, etc...).
     * @param in
     */
    public List<OasParameter> getParameters(String in) {
        List<OasParameter> rval = new ArrayList<>();
        if (this.parameters != null) {
            for (OasParameter parameter : this.parameters) {
                if (NodeCompat.equals(parameter.in, in)) {
                    rval.add(parameter);
                }
            }
        }
        return rval;
    }

    /**
     * Returns a single, unique parameter identified by "in" and "name" (which are the two
     * properties that uniquely identify a parameter).  Returns null if no parameter is found.
     * @param in
     * @param name
     * @return {OasParameterBase}
     */
    public OasParameter getParameter(String in, String name) {
        OasParameter rval = null;
        if (this.parameters != null) {
            for (OasParameter parameter : this.parameters) {
                if (NodeCompat.equals(parameter.in, in) && NodeCompat.equals(parameter.name, name)) {
                    rval = parameter;
                }
            }
        }
        return rval;
    }

    /**
     * Adds a parameter.
     * @param parameter
     */
    public OasParameter addParameter(OasParameter parameter) {
        if (this.parameters == null) {
            this.parameters = new ArrayList<>();
        }
        this.parameters.add(parameter);
        return parameter;
    }

    /**
     * Creates a child responses model.
     */
    public abstract OasResponses createResponses();

    /**
     * Creates an OAS Security Requirement object.
     */
    public abstract OasSecurityRequirement createSecurityRequirement();

    /**
     * Adds a security requirement child.
     * @param securityRequirement
     */
    public OasSecurityRequirement addSecurityRequirement(OasSecurityRequirement securityRequirement) {
        if (this.security == null) {
            this.security = new ArrayList<>();
        }
        this.security.add(securityRequirement);
        return securityRequirement;
    }

}
