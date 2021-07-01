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
import io.apicurio.datamodels.core.models.common.ISecurityRequirementParent;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;

/**
 * Models an OpenAPI operation.
 * @author eric.wittmann@gmail.com
 */
public abstract class OasOperation extends Operation implements IOasParameterParent, ISecurityRequirementParent {

    public List<String> tags;
    public List<OasParameter> parameters;
    public OasResponses responses;
    public Boolean deprecated;
    public List<SecurityRequirement> security;
    
    /**
     * Constructor.
     * @param method
     */
    public OasOperation(String method) {
        super(method);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.models.IOasParameterParent#getParameters()
     */
    @Override
    public List<OasParameter> getParameters() {
        return parameters;
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.models.IOasParameterParent#getParametersIn(java.lang.String)
     */
    @Override
    public List<OasParameter> getParametersIn(String in) {
        List<OasParameter> params = new ArrayList<>();
        if (!NodeCompat.isNullOrUndefined(this.parameters)) {
            this.parameters.forEach(param -> {
                if (NodeCompat.equals(param.in, in)) {
                    params.add(param);
                }
            });
        }
        return params;
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
     * Returns a single, unique parameter identified by "in" and "name" (which are the two
     * properties that uniquely identify a parameter).  Returns null if no parameter is found.
     * @param in
     * @param name
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
     * @see io.apicurio.datamodels.core.models.common.ISecurityRequirementParent#createSecurityRequirement()
     */
    @Override
    public abstract OasSecurityRequirement createSecurityRequirement();

    /**
     * @see io.apicurio.datamodels.core.models.common.ISecurityRequirementParent#addSecurityRequirement(io.apicurio.datamodels.core.models.common.SecurityRequirement)
     */
    @Override
    public SecurityRequirement addSecurityRequirement(SecurityRequirement securityRequirement) {
        if (this.security == null) {
            this.security = new ArrayList<>();
        }
        this.security.add(securityRequirement);
        return securityRequirement;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.common.ISecurityRequirementParent#getSecurityRequirements()
     */
    @Override
    public List<SecurityRequirement> getSecurityRequirements() {
        return this.security;
    }

}
