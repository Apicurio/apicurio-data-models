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

import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.openapi.models.IOasHeaderParent;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasResponse;

/**
 * Models an OpenAPI 3.0.x response.
 * @author eric.wittmann@gmail.com
 */
public class Oas30Response extends OasResponse implements IOas30MediaTypeParent, IOasHeaderParent {

    // header parameters
    public Map<String, Oas30Header> headers = new LinkedHashMap<>();
    public Map<String, Oas30MediaType> content = new LinkedHashMap<>();
    public Map<String, Oas30Link> links = new LinkedHashMap<>();

    /**
     * Constructor.
     * @param statusCode
     */
    public Oas30Response(String statusCode) {
        super(statusCode);
    }

    /**
     * Gets the status code.
     */
    public String getStatusCode() {
        return this.getName();
    }



    /**
     * @see io.apicurio.datamodels.openapi.v3.models.IOas30MediaTypeParent#createMediaType(java.lang.String)
     */
    @Override
    public Oas30MediaType createMediaType(String name) {
        Oas30MediaType rval = new Oas30MediaType(name);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.models.IOas30MediaTypeParent#addMediaType(java.lang.String, io.apicurio.datamodels.openapi.v3.models.Oas30MediaType)
     */
    @Override
    public void addMediaType(String name, Oas30MediaType mediaType) {
        this.content.put(name, mediaType);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.models.IOas30MediaTypeParent#getMediaType(java.lang.String)
     */
    @Override
    public Oas30MediaType getMediaType(String name) {
        return this.content.get(name);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.models.IOas30MediaTypeParent#removeMediaType(java.lang.String)
     */
    @Override
    public Oas30MediaType removeMediaType(String name) {
        return this.content.remove(name);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.models.IOas30MediaTypeParent#getMediaTypes()
     */
    @Override
    public List<Oas30MediaType> getMediaTypes() {
        List<Oas30MediaType> rval = new ArrayList<>();
        rval.addAll(this.content.values());
        return rval;
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.v3.models.IOas30MediaTypeParent#restoreMediaType(int, String, Oas30MediaType)
     */
    @Override
    public void restoreMediaType(int index, String name, Oas30MediaType mediaType) {
        this.content = ModelUtils.restoreMapEntry(index, name, mediaType, this.content);
    }

    /**
     * Creates a link.
     * @param name
     */
    public Oas30Link createLink(String name) {
        Oas30Link rval = new Oas30Link(name);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a link.
     * @param name
     * @param link
     */
    public void addLink(String name, Oas30Link link) {
        this.links.put(name, link);
    }

    /**
     * Gets a single link by name.
     * @param name
     */
    public Oas30Link getLink(String name) {
        return this.links.get(name);
    }

    /**
     * Removes a single link and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public Oas30Link removeLink(String name) {
        return this.links.remove(name);
    }

    /**
     * Gets a list of all links.
     */
    public List<Oas30Link> getLinks() {
        List<Oas30Link> rval = new ArrayList<>();
        rval.addAll(this.links.values());
        return rval;
    }

    /**
     * Creates a http header.
     * @param name
     */
    @Override
    public Oas30Header createHeader(String name) {
        Oas30Header rval = new Oas30Header(name);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a header.
     * @param name
     * @param header
     */
    @Override
    public void addHeader(String name, OasHeader header) {
        this.headers.put(name, (Oas30Header) header);
    }

    /**
     * Gets a single header by name.
     * @param name
     */
    @Override
    public OasHeader getHeader(String name) {
        return this.headers.get(name);
    }

    /**
     * Removes a single header and returns it.  This may return null or undefined if none found.
     * @param name
     */
    @Override
    public OasHeader removeHeader(String name) {
        return this.headers.remove(name);
    }

    /**
     * Gets a list of all headers.
     * @return
     */
    @Override
    public List<OasHeader> getHeaders() {
        List<OasHeader> rval = new ArrayList<>();
        rval.addAll(this.headers.values());
        return rval;
    }
}
