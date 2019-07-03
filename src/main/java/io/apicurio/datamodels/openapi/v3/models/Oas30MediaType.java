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
import io.apicurio.datamodels.core.models.common.IExampleParent;
import io.apicurio.datamodels.core.models.common.INamed;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor;

/**
 * Models an OpenAPI 3.0.x media type.
 * @author eric.wittmann@gmail.com
 */
public class Oas30MediaType extends ExtensibleNode implements INamed, IExampleParent {

    private String _name;

    public Oas30Schema schema;
    public Object example;
    public Map<String, Oas30Example> examples = new LinkedHashMap<>();
    public Map<String, Oas30Encoding> encoding = new LinkedHashMap<>();
    
    /**
     * Constructor.
     * @param name
     */
    public Oas30MediaType(String name) {
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
        viz.visitMediaType(this);
    }

    /**
     * Creates a child schema model.
     */
    public Oas30Schema createSchema() {
        Oas30Schema rval = new Oas30Schema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Creates a encoding.
     * @param name
     */
    public Oas30Encoding createEncoding(String name) {
        Oas30Encoding rval = new Oas30Encoding(name);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a encoding.
     * @param name
     * @param encoding
     */
    public void addEncoding(String name, Oas30Encoding encoding) {
        this.encoding.put(name, encoding);
    }

    /**
     * Gets a single encoding by name.
     * @param name
     */
    public Oas30Encoding getEncoding(String name) {
        return this.encoding.get(name);
    }

    /**
     * Removes a single encoding and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public Oas30Encoding removeEncoding(String name) {
        return this.encoding.remove(name);
    }

    /**
     * Gets a list of all encodings.
     */
    public List<Oas30Encoding> getEncodings() {
        List<Oas30Encoding> rval = new ArrayList<>();
        rval.addAll(this.encoding.values());
        return rval;
    }

    /**
     * Creates a child Example model.
     */
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
    public void addExample(Oas30Example example) {
        this.examples.put(example.getName(), example);
    }

    /**
     * Removes an Example and returns it.
     * @param name
     */
    public Oas30Example removeExample(String name) {
        return this.examples.remove(name);
    }

    /**
     * Gets a single example by name.
     * @param name
     */
    public Oas30Example getExample(String name) {
        return this.examples.get(name);
    }

    /**
     * Gets all examples.
     */
    public List<Oas30Example> getExamples() {
        List<Oas30Example> rval = new ArrayList<>();
        rval.addAll(this.examples.values());
        return rval;
    }

}
