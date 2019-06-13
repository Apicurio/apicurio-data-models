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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.IIndexedNode;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.visitors.IOasVisitor;

/**
 * Models an OpenAPI responses.
 * @author eric.wittmann@gmail.com
 */
public abstract class OasResponses extends ExtensibleNode implements IIndexedNode<OasResponse> {

    public OasResponse default_;
    private Map<String, OasResponse> _responses;

    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOasVisitor viz = (IOasVisitor) visitor;
        viz.visitResponses(this);
    }

    /**
     * Returns a single response by status code.
     * @param statusCode
     */
    public OasResponse getResponse(String statusCode) {
        if (NodeCompat.equals(statusCode, Constants.PROP_DEFAULT)) {
            return this.default_;
        }
        
        if (this._responses != null) {
            return this._responses.get(statusCode);
        }

        return null;
    }

    /**
     * Returns an array of all the responses.
     */
    public List<OasResponse> getResponses() {
        List<OasResponse> rval = new ArrayList<>();
        if (this._responses != null) {
            rval.addAll(this._responses.values());
        }
        if (this.default_ != null) {
            rval.add(this.default_);
        }
        return rval;
    }

    /**
     * Adds a response.
     * @param statusCode
     * @param response
     */
    public OasResponse addResponse(String statusCode, OasResponse response) {
        if (statusCode == null || NodeCompat.equals(statusCode, Constants.PROP_DEFAULT)) {
            this.default_ = response;
            return response;
        }

        if (this._responses == null) {
            this._responses = new LinkedHashMap<>();
        }
        this._responses.put(statusCode, response);
        return response;
    }

    /**
     * Removes a single response child model.
     * @param statusCode
     */
    public OasResponse removeResponse(String statusCode) {
        if (statusCode == null || NodeCompat.equals(statusCode, Constants.PROP_DEFAULT)) {
            OasResponse rval = this.default_;
            this.default_ = null;
            return rval;
        }
        if (this._responses != null) {
            return this._responses.remove(statusCode);
        }
        return null;
    }

    /**
     * Gets a list of all the response status codes.
     */
    public List<String> getResponseStatusCodes() {
        List<String> rval = new ArrayList<>();
        if (this._responses != null) {
            rval.addAll(this._responses.keySet());
        }
        return rval;
    }

    /**
     * Creates an OAS Response object.
     * @param statusCode
     */
    public abstract OasResponse createResponse(String statusCode);
    
    /**
     * Creates a default OAS response.
     */
    public OasResponse createDefaultResponse() {
        return this.createResponse(null);
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItem(java.lang.String)
     */
    @Override
    public OasResponse getItem(String name) {
        return this.getResponse(name);
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItems()
     */
    @Override
    public List<OasResponse> getItems() {
        return this.getResponses();
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItemNames()
     */
    @Override
    public List<String> getItemNames() {
        return this.getResponseStatusCodes();
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#addItem(java.lang.String, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    public void addItem(String name, OasResponse item) {
        this.addResponse(name, item);
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#deleteItem(java.lang.String)
     */
    @Override
    public OasResponse deleteItem(String name) {
        return this.removeResponse(name);
    }

}
