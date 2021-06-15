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
import java.util.List;

import io.apicurio.datamodels.core.models.common.IExampleParent;
import io.apicurio.datamodels.openapi.models.IOasHeaderParent;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasResponse;

/**
 * Models an OpenAPI 2.0 response.
 * @author eric.wittmann@gmail.com
 */
public class Oas20Response extends OasResponse implements IExampleParent, IOasHeaderParent {

    public Oas20Headers headers;
    public Oas20Schema schema;
    public Oas20Example examples;

    /**
     * Constructor.
     * @param name
     */
    public Oas20Response(String name) {
        super(name);
    }

    /**
     * Creates an OAS 2.0 schema object.
     */
    public Oas20Schema createSchema() {
        Oas20Schema rval = new Oas20Schema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Creates a http header.
     * @param name
     */
    @Override
    public Oas20Header createHeader(String name) {
        Oas20Header rval = new Oas20Header(name);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Creates an OAS 2.0 headers object.
     */
    public Oas20Headers createHeaders() {
        Oas20Headers rval = new Oas20Headers();
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
        this.headers.addHeader(name, (Oas20Header) header);
    }

    /**
     * Gets a single header by name.
     * @param name
     */
    @Override
    public OasHeader getHeader(String name) {
        return this.headers.getHeader(name);
    }

    /**
     * Removes a single header and returns it.  This may return null or undefined if none found.
     * @param name
     */
    @Override
    public OasHeader removeHeader(String name) {
        return this.headers.removeHeader(name);
    }

    /**
     * Gets a list of all headers.
     * @return
     */
    @Override
    public List<OasHeader> getHeaders() {
        List<OasHeader> rval = new ArrayList<>();
        rval.addAll(this.headers.getHeaders());
        return rval;
    }

    /**
     * Creates an OAS 2.0 schema object.
     */
    public Oas20Example createExample() {
        Oas20Example rval = new Oas20Example();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

}
