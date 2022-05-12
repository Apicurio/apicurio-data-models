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

import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.IIndexedNode;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.visitors.IOasVisitor;

/**
 * Modles the OpenAPI paths.
 * @author eric.wittmann@gmail.com
 */
public abstract class OasPaths extends ExtensibleNode implements IIndexedNode<OasPathItem> {

    private Map<String, OasPathItem> _pathItems = new LinkedHashMap<>();
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOasVisitor oasVisitor = (IOasVisitor) visitor;
        oasVisitor.visitPaths(this);
    }

    /**
     * Returns a single path item by name.
     * @param name
     */
    public OasPathItem getPathItem(String name) {
        return this._pathItems.get(name);
    }

    /**
     * Returns an array of all the path items.
     */
    public List<OasPathItem> getPathItems() {
        List<OasPathItem> rval = new ArrayList<>();
        rval.addAll(this._pathItems.values());
        return rval;
    }

    /**
     * Adds a path item.
     * @param name
     * @param pathItem
     */
    public OasPathItem addPathItem(String name, OasPathItem pathItem) {
        this._pathItems.put(name, pathItem);
        return pathItem;
    }
    
    /**
     * Restores a path item at its original position
     * @param index
     * @param name
     * @param pathItem
     * @return
     */
    public void restorePathItem(int index, String name, OasPathItem pathItem) {
        this._pathItems = ModelUtils.restoreMapEntry(index, name, pathItem, this._pathItems);
    }

    /**
     * Replaces a path item without modifying the order of the path items.
     * @param name
     * @param pathItem
     */
    public OasPathItem replacePathItem(String name, OasPathItem pathItem) {
        // As long as this is backed by a LinkedHashMap, this will preserve the ordering of the entries within
        addPathItem(name, pathItem);
        return pathItem;
    }

    /**
     * Gets a list of all the path names.
     */
    public List<String> getPathItemNames() {
        List<String> names = new ArrayList<>();
        names.addAll(this._pathItems.keySet());
        return names;
    }

    /**
     * Removes a single path item child model by name.
     * @param path
     */
    public OasPathItem removePathItem(String path) {
        return this._pathItems.remove(path);
    }
    
    /**
     * Rename a single path item child model, without changing the order of the items.
     * @param fromName
     * @param toName
     * @return
     */
    public OasPathItem renamePathItem(String fromName, String toName) {
        this._pathItems = ModelUtils.renameMapKey(fromName, toName, this._pathItems, path -> path.rename(toName));
        return this._pathItems.get(toName);
    }

    /**
     * Creates an OAS path item object.
     * @param path
     */
    public abstract OasPathItem createPathItem(String path);
    
    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#addItem(java.lang.String, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    public void addItem(String name, OasPathItem item) {
        this.addPathItem(name, item);
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#deleteItem(java.lang.String)
     */
    @Override
    public OasPathItem deleteItem(String name) {
        return this.removePathItem(name);
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItem(java.lang.String)
     */
    @Override
    public OasPathItem getItem(String name) {
        return this.getPathItem(name);
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItemNames()
     */
    @Override
    public List<String> getItemNames() {
        return this.getPathItemNames();
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItems()
     */
    @Override
    public List<OasPathItem> getItems() {
        return this.getPathItems();
    }

}
