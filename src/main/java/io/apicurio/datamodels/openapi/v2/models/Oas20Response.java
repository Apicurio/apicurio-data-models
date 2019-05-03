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

import io.apicurio.datamodels.openapi.models.OasResponse;

/**
 * Models an OpenAPI 2.0 response.
 * @author eric.wittmann@gmail.com
 */
public class Oas20Response extends OasResponse {

    public Oas20Headers headers;
    public Oas20Schema schema;
    public Oas20Example example;

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
     * Creates an OAS 2.0 headers object.
     */
    public Oas20Headers createHeaders() {
        Oas20Headers rval = new Oas20Headers();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
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
