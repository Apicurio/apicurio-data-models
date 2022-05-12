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

import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.IExample;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor;

/**
 * An OpenAPI 2.0 example.
 * @author eric.wittmann@gmail.com
 */
public class Oas20Example extends Node implements IExample {

    private Map<String, Object> items = new LinkedHashMap<>();

    /**
     * Constructor.
     */
    public Oas20Example() {
    }

    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOas20Visitor viz = (IOas20Visitor) visitor;
        viz.visitExample(this);
    }

    /**
     * Returns an array of all the example content types.
     */
    public List<String> getExampleContentTypes() {
        List<String> rval = new ArrayList<>();
        rval.addAll(items.keySet());
        return rval;
    }

    /**
     * Gets a single example.
     * @param contentType
     */
    public Object getExample(String contentType) {
        return this.items.get(contentType);
    }

    /**
     * Adds an example.
     * @param contentType
     * @param example
     */
    public void addExample(String contentType, Object example) {
        this.items.put(contentType, example);
    }

    /**
     * Removes a single example.
     * @param contentType
     */
    public Object removeExample(String contentType) {
        return this.items.remove(contentType);
    }

    /**
     * Gets the examples.
     */
    public List<Object> getExamples() {
        List<Object> rval = new ArrayList<>();
        rval.addAll(this.items.values());
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#getName()
     */
    @Override
    public String getName() {
        throw new RuntimeException("Operation not supported.");
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#rename(java.lang.String)
     */
    @Override
    public void rename(String newName) {
        throw new RuntimeException("Operation not supported.");
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.IExample#getValue()
     */
    @Override
    public Object getValue() {
        throw new RuntimeException("Operation not supported.");
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.IExample#setValue(java.lang.Object)
     */
    @Override
    public void setValue(Object value) {
        throw new RuntimeException("Operation not supported.");
    }

    /**
     * Restore a removed example in its original position
     * @param oldPosition
     * @param oldKey
     * @param oldValue
     */
    public void restoreExample(int oldPosition, String oldKey, Object oldValue) {
        this.items = ModelUtils.restoreMapEntry(oldPosition, oldKey, oldValue, this.items);
    }

}
