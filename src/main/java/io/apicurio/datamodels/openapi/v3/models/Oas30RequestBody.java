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
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor;

/**
 * Models an OpenAPI 3.0.x Request Body.
 * @author eric.wittmann@gmail.com
 */
public class Oas30RequestBody extends ExtensibleNode implements IReferenceNode, IOas30MediaTypeParent {

    public String $ref;
    public String description;
    public Map<String, Oas30MediaType> content = new LinkedHashMap<>();
    public Boolean required;
    
    /**
     * Constructor.
     */
    public Oas30RequestBody() {
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOas30Visitor viz = (IOas30Visitor) visitor;
        viz.visitRequestBody(this);
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

}
