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
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.models.common.IExample;
import io.apicurio.datamodels.core.models.common.IExampleParent;
import io.apicurio.datamodels.core.models.common.IExamplesParent;
import io.apicurio.datamodels.openapi.models.OasHeader;

/**
 * Models an OpenAPI 3.0.x header.
 * @author eric.wittmann@gmail.com
 */
public class Oas30Header extends OasHeader implements IReferenceNode, IExampleParent, IExamplesParent {

    public String $ref;
    public Boolean required;
    public Boolean deprecated;
    public Boolean allowEmptyValue;
    public String style;
    public Boolean explode;
    public Boolean allowReserved;
    public Oas30Schema schema;
    public Object example;
    public Map<String, IExample> examples = new LinkedHashMap<>();
    public Map<String, Oas30MediaType> content = new LinkedHashMap<>();

    /**
     * Constructor.
     */
    public Oas30Header(String name) {
        super(name);
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
     * Creates a child items model.
     */
    public Oas30Schema createSchema() {
        Oas30Schema rval = new Oas30Schema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Creates a child Example model.
     */
    @Override
    public Oas30Example createExample(String name) {
        Oas30Example rval = new Oas30Example(name);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Adds the Example to the map of examples.
     * @param example
     */
    @Override
    public void addExample(IExample example) {
        this.examples.put(example.getName(), example);
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.IExampleParent#setExample(java.lang.Object)
     */
    @Override
    public void setExample(Object example) {
        this.example = example;
    }

    /**
     * Removes an Example and returns it.
     * @param name
     */
    @Override
    public IExample removeExample(String name) {
        return this.examples.remove(name);
    }
    
    /**
     * see {@link io.apicurio.datamodels.core.models.common.IExamplesParent#restoreExample(Integer, String, IExample)}
     */
    @Override
    public void restoreExample(Integer index, String name, IExample example) {
        this.examples = ModelUtils.restoreMapEntry(index, name, example, this.examples);
    }

    /**
     * Gets a single example by name.
     * @param name
     */
    @Override
    public IExample getExample(String name) {
        return this.examples.get(name);
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.IExampleParent#getExample()
     */
    @Override
    public Object getExample() {
        return this.example;
    }

    /**
     * Gets all examples.
     */
    @Override
    public List<IExample> getExamples() {
        List<IExample> rval = new ArrayList<>();
        rval.addAll(this.examples.values());
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.IExamplesParent#clearExamples()
     */
    @Override
    public void clearExamples() {
        this.examples = new LinkedHashMap<>();
    }

    /**
     * Creates a media type.
     * @param name
     */
    public Oas30MediaType createMediaType(String name) {
        Oas30MediaType rval = new Oas30MediaType(name);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a media type.
     * @param name
     * @param mediaType
     */
    public void addMediaType(String name, Oas30MediaType mediaType) {
        this.content.put(name, mediaType);
    }

    /**
     * Gets a single media type by name.
     * @param name
     */
    public Oas30MediaType getMediaType(String name) {
        return this.content.get(name);
    }

    /**
     * Removes a single media type and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public Oas30MediaType removeMediaType(String name) {
        return this.content.remove(name);
    }

    /**
     * Gets a list of all media types.
     */
    public List<Oas30MediaType> getMediaTypes() {
        List<Oas30MediaType> rval = new ArrayList<>();
        rval.addAll(this.content.values());
        return rval;
    }

}
