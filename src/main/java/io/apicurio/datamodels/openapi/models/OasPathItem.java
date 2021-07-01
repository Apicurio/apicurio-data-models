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
import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.visitors.IOasVisitor;

/**
 * Models an OpenAPI path item.
 * @author eric.wittmann@gmail.com
 */
public abstract class OasPathItem extends ExtensibleNode implements IOasParameterParent, IReferenceNode {

    private String _path;
    public String $ref;
    public OasOperation get;
    public OasOperation put;
    public OasOperation post;
    public OasOperation delete;
    public OasOperation options;
    public OasOperation head;
    public OasOperation patch;
    public List<OasParameter> parameters;
    
    /**
     * Constructor.
     * @param path
     */
    public OasPathItem(String path) {
        this._path = path;
    }
    
    /**
     * Rename the path item (change its path value).
     * @param newPath
     */
    public void rename(String newPath) {
        this._path = newPath;
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
     * Gets the path string.
     */
    public String getPath() {
        return this._path;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOasVisitor oasVisitor = (IOasVisitor) visitor;
        oasVisitor.visitPathItem(this);
    }

    /**
     * Creates an OAS operation object.
     * @param method
     */
    public abstract OasOperation createOperation(String method);

    /**
     * Creates a child parameter.
     */
    public abstract OasParameter createParameter();

    /**
     * Adds a parameter.
     * @param param
     */
    public OasParameter addParameter(OasParameter param) {
        if (this.parameters == null) {
            this.parameters = new ArrayList<>();
        }
        this.parameters.add(param);
        return param;
    }

    /**
     * Returns a single, unique parameter identified by "in" and "name" (which are the two
     * properties that uniquely identify a parameter).  Returns null if no parameter is found.
     * @param in
     * @param name
     */
    public OasParameter getParameter(String in, String name) {
        if (this.parameters == null) {
            return null;
        }
        for (OasParameter param : this.parameters) {
            if (NodeCompat.equals(in, param.in) && NodeCompat.equals(name, param.name)) {
                return param;
            }
        }
        return null;
    }

    /**
     * Sets the given operation on this path item.
     * @param operation
     */
    public void setOperation(OasOperation operation) {
        if (NodeCompat.equals("get", operation.getMethod())) {
            get = operation;
        } else if (NodeCompat.equals("put", operation.getMethod())) {
            put = operation;
        } else if (NodeCompat.equals("post", operation.getMethod())) {
            post = operation;
        } else if (NodeCompat.equals("delete", operation.getMethod())) {
            delete = operation;
        } else if (NodeCompat.equals("head", operation.getMethod())) {
            head = operation;
        } else if (NodeCompat.equals("patch", operation.getMethod())) {
            patch = operation;
        } else if (NodeCompat.equals("options", operation.getMethod())) {
            options = operation;
        }
    }

}
