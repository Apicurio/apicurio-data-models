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
import io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor;

/**
 * Models an OpenAPI 2.0 Definitions.
 * @author eric.wittmann@gmail.com
 */
public class Oas20Definitions extends Node implements IIndexedNode<Oas20SchemaDefinition> {
    
    public Map<String, Oas20SchemaDefinition> items = new LinkedHashMap<>();
    
    /**
     * Constructor.
     */
    public Oas20Definitions() {
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOas20Visitor viz = (IOas20Visitor) visitor;
        viz.visitDefinitions(this);
    }

    /**
     * Returns a single definition schema by name.
     * @param name
     */
    public Oas20SchemaDefinition getDefinition(String name) {
        return this.items.get(name);
    }

    /**
     * Returns an array of all the definitions.
     */
    public List<Oas20SchemaDefinition> getDefinitions() {
        List<Oas20SchemaDefinition> rval = new ArrayList<>();
        rval.addAll(this.items.values());
        return rval;
    }

    /**
     * Adds a definition.
     * @param name
     * @param schema
     */
    public Oas20SchemaDefinition addDefinition(String name, Oas20SchemaDefinition schema) {
        this.items.put(name, schema);
        return schema;
    }
    
    /**
     * Replaces a definition without modifying the order of the definitions.
     * @param newSchema
     */
    public Oas20SchemaDefinition replaceDefinition(Oas20SchemaDefinition newSchema) {
        // As long as this is backed by a LinkedHashMap, this will preserve the ordering of the entries within
        addDefinition(newSchema.getName(), newSchema);
        return newSchema;
    }

    /**
     * Rename a defintion without modifying the order of the definitions.
     * @param fromName
     * @param toName
     * @param valueConsumer
     */
    public void renameDefinition(String fromName, String toName, Consumer<Oas20SchemaDefinition> valueConsumer) {
        this.items = ModelUtils.renameMapKey(fromName, toName, this.items, valueConsumer);
    }
    
    /**
     * Restore a deleted schema definition in its original place.
     * @param index
     * @param name
     * @param schemaDef
     */
    public void restoreSchemaDefinition(Integer index, String name, Oas20SchemaDefinition schemaDef) {
        this.items = ModelUtils.restoreMapEntry(index, name, schemaDef, this.items);
    }

    /**
     * Removes a definition by name.
     * @param name
     */
    public Oas20SchemaDefinition removeDefinition(String name) {
        return this.items.remove(name);
    }

    /**
     * Gets a list of all the definition names.
     */
    public List<String> getDefinitionNames() {
        List<String> rval = new ArrayList<>();
        rval.addAll(this.items.keySet());
        return rval;
    }

    /**
     * Creates an OAS 2.0 Schema object.
     * @param name
     */
    public Oas20SchemaDefinition createSchemaDefinition(String name) {
        Oas20SchemaDefinition rval = new Oas20SchemaDefinition(name);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItem(java.lang.String)
     */
    @Override
    public Oas20SchemaDefinition getItem(String name) {
        return this.getDefinition(name);
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItems()
     */
    @Override
    public List<Oas20SchemaDefinition> getItems() {
        return this.getDefinitions();
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#getItemNames()
     */
    @Override
    public List<String> getItemNames() {
        return this.getDefinitionNames();
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#addItem(java.lang.String, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    public void addItem(String name, Oas20SchemaDefinition item) {
        this.addDefinition(name, item);
    }

    /**
     * @see io.apicurio.datamodels.core.models.IIndexedNode#deleteItem(java.lang.String)
     */
    @Override
    public Oas20SchemaDefinition deleteItem(String name) {
        return this.removeDefinition(name);
    }

}
