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
import java.util.List;

import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * Models an OpenAPI 3.0.x path item.
 * @author eric.wittmann@gmail.com
 */
public class Oas30PathItem extends OasPathItem {
    
    public String summary;
    public String description;
    public Oas30Operation trace;
    public List<Oas30Server> servers;

    /**
     * Constructor.
     * @param path
     */
    public Oas30PathItem(String path) {
        super(path);
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasPathItem#createOperation(java.lang.String)
     */
    @Override
    public OasOperation createOperation(String method) {
        OasOperation rval = new Oas30Operation(method);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasPathItem#createParameter()
     */
    @Override
    public OasParameter createParameter() {
        OasParameter rval = new Oas30Parameter();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Creates an OAS 3.0 Server object.
     */
    public Oas30Server createServer() {
        Oas30Server rval = new Oas30Server();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a server.
     * @param server
     */
    public void addServer(Oas30Server server) {
        if (this.servers == null) {
            this.servers = new ArrayList<>();
        }
        this.servers.add(server);
    }
    
    /**
     * Gets the servers.
     */
    public List<Oas30Server> getServers() {
        List<Oas30Server> rval = new ArrayList<>();
        if (this.servers != null) {
            rval.addAll(this.servers);
        }
        return rval;
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.models.OasPathItem#setOperation(io.apicurio.datamodels.openapi.models.OasOperation)
     */
    @Override
    public void setOperation(OasOperation operation) {
        if (NodeCompat.equals("trace", operation.getMethod())) {
            trace = (Oas30Operation) operation;
        } else {
            super.setOperation(operation);
        }
    }

}
