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
import io.apicurio.datamodels.core.models.IIndexedNode;
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.models.common.INamed;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class Oas30Callback extends ExtensibleNode
        implements IIndexedNode<Oas30CallbackPathItem>, INamed, IReferenceNode {

    private Map<String, Oas30CallbackPathItem> items = new LinkedHashMap<>();
    private String _name;
    
    public String $ref;
    
    /**
     * Constructor.
     * @param name
     */
    public Oas30Callback(String name) {
        this._name = name;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOas30Visitor viz = (IOas30Visitor) visitor;
        viz.visitCallback(this);
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
     * Returns a single path item by name.
     * @param name
     */
    public Oas30CallbackPathItem getPathItem(String name) {
        return this.items.get(name);
    }

    /**
     * Returns an array of all the path items.
     */
    public List<Oas30CallbackPathItem> getPathItems() {
        List<Oas30CallbackPathItem> rval = new ArrayList<>();
        rval.addAll(this.items.values());
        return rval;
    }

    /**
     * Adds a path item.
     * @param name
     * @param pathItem
     */
    public Oas30CallbackPathItem addPathItem(String name, Oas30CallbackPathItem pathItem) {
        this.items.put(name, pathItem);
        return pathItem;
    }

    /**
     * Gets a list of all the path names.
     */
    public List<String> getPathItemNames() {
        List<String> rval = new ArrayList<>();
        rval.addAll(this.items.keySet());
        return rval;
    }

    /**
     * Removes a single path item child model by name.
     * @param path
     */
    public Oas30CallbackPathItem removePathItem(String path) {
        return this.items.remove(path);
    }

    /**
     * Creates an OAS path item object.
     * @param path
     */
    public Oas30CallbackPathItem createPathItem(String path) {
        Oas30CallbackPathItem rval = new Oas30CallbackPathItem(path);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItem(java.lang.String)
     */
    @Override
    public Oas30CallbackPathItem getItem(String name) {
        return this.getPathItem(name);
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItems()
     */
    @Override
    public List<Oas30CallbackPathItem> getItems() {
        return this.getPathItems();
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItemNames()
     */
    @Override
    public List<String> getItemNames() {
        return this.getPathItemNames();
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#addItem(java.lang.String, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    public void addItem(String name, Oas30CallbackPathItem item) {
        this.addPathItem(name, item);
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#deleteItem(java.lang.String)
     */
    @Override
    public Oas30CallbackPathItem deleteItem(String name) {
        return this.removePathItem(name);
    }

}
