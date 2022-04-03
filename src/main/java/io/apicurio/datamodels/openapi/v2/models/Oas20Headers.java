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
import java.util.function.Consumer;

import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.core.models.IIndexedNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class Oas20Headers extends Node implements IIndexedNode<Oas20Header> {
    
    private Map<String, Oas20Header> items = new LinkedHashMap<>();
    
    /**
     * Constructor.
     */
    public Oas20Headers() {
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOas20Visitor viz = (IOas20Visitor) visitor;
        viz.visitHeaders(this);
    }

    /**
     * Creates a header model.
     * @param headerName
     */
    public Oas20Header createHeader(String headerName) {
        Oas20Header rval = new Oas20Header(headerName);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Gets a single header by name.
     * @param headerName
     */
    public Oas20Header getHeader(String headerName) {
        return this.items.get(headerName);
    }

    /**
     * Returns an array of all the headers.
     */
    public List<Oas20Header> getHeaders() {
        List<Oas20Header> rval = new ArrayList<>();
        rval.addAll(this.items.values());
        return rval;
    }

    /**
     * Returns all the header names.
     */
    public List<String> getHeaderNames() {
        List<String> rval = new ArrayList<>();
        rval.addAll(this.items.keySet());
        return rval;
    }

    /**
     * Removes a single header.
     * @param headerName
     */
    public Oas20Header removeHeader(String headerName) {
        return this.items.remove(headerName);
    }
    
    /**
     * Renames a single header.
     * @param from
     * @param to
     * @param headerConsumer
     */
    public void renameHeader(String from, String to, Consumer<OasHeader> headerConsumer) {
        this.items = ModelUtils.renameMapKey(from, to, this.items, headerConsumer::accept);
    }

    /**
     * Adds a header.
     * @param headerName
     * @param header
     */
    public Oas20Header addHeader(String headerName, Oas20Header header) {
        this.items.put(headerName, header);
        return header;
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItem(java.lang.String)
     */
    @Override
    public Oas20Header getItem(String name) {
        return this.getHeader(name);
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItems()
     */
    @Override
    public List<Oas20Header> getItems() {
        return this.getHeaders();
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItemNames()
     */
    @Override
    public List<String> getItemNames() {
        return this.getHeaderNames();
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#addItem(java.lang.String, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    public void addItem(String name, Oas20Header item) {
        this.addHeader(name, item);
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#deleteItem(java.lang.String)
     */
    @Override
    public Oas20Header deleteItem(String name) {
        return this.removeHeader(name);
    }

}
