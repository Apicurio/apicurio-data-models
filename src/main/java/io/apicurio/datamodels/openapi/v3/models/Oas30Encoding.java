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
import io.apicurio.datamodels.core.models.common.INamed;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor;

/**
 * Models an OpenAPI 3.0.x Encoding.
 * @author eric.wittmann@gmail.com
 */
public class Oas30Encoding extends ExtensibleNode implements INamed {

    private String _name;
    public String contentType;
    public Map<String, Oas30Header> headers = new LinkedHashMap<>();
    public String style;
    public Boolean explode;
    public Boolean allowReserved;
    
    /**
     * Constructor.
     * @param name
     */
    public Oas30Encoding(String name) {
        this._name = name;
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
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOas30Visitor viz = (IOas30Visitor) visitor;
        viz.visitEncoding(this);
    }

    /**
     * Creates a header.
     * @param name
     */
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
    public void addHeader(String name, Oas30Header header) {
        this.headers.put(name, header);
    }

    /**
     * Gets a single header by name.
     * @param name
     */
    public Oas30Header getHeader(String name) {
        return this.headers.get(name);
    }
    
    /**
     * Gets the header names.
     */
    public List<String> getHeaderNames() {
        List<String> rval = new ArrayList<>();
        rval.addAll(this.headers.keySet());
        return rval;
    }

    /**
     * Removes a single header and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public Oas30Header removeHeader(String name) {
        return this.headers.remove(name);
    }

    /**
     * Gets a list of all headers.
     */
    public List<Oas30Header> getHeaders() {
        List<Oas30Header> rval = new ArrayList<>();
        rval.addAll(this.headers.values());
        return rval;
    }

}
