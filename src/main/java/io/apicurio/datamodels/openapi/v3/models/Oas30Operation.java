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

import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.IServerParent;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasResponses;
import io.apicurio.datamodels.openapi.models.OasSecurityRequirement;

/**
 * Models an OpenAPI 3.0.x operation.
 * @author eric.wittmann@gmail.com
 */
public class Oas30Operation extends OasOperation implements IServerParent {

    public Oas30RequestBody requestBody;
    public Map<String, Oas30Callback> callbacks = new LinkedHashMap<>();
    public List<Server> servers;
    
    /**
     * Constructor.
     * @param method
     */
    public Oas30Operation(String method) {
        super(method);
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.common.Operation#createExternalDocumentation()
     */
    @Override
    public ExternalDocumentation createExternalDocumentation() {
        ExternalDocumentation externalDocs = new Oas30ExternalDocumentation();
        externalDocs._ownerDocument = this.ownerDocument();
        externalDocs._parent = this;
        return externalDocs;
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.models.OasOperation#createParameter()
     */
    @Override
    public OasParameter createParameter() {
        OasParameter param = new Oas30Parameter();
        param._ownerDocument = this.ownerDocument();
        param._parent = this;
        return param;
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.models.OasOperation#createResponses()
     */
    @Override
    public OasResponses createResponses() {
        OasResponses responses = new Oas30Responses();
        responses._ownerDocument = this.ownerDocument();
        responses._parent = this;
        return responses;
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.models.OasOperation#createSecurityRequirement()
     */
    @Override
    public OasSecurityRequirement createSecurityRequirement() {
        OasSecurityRequirement requirement = new Oas30SecurityRequirement();
        requirement._ownerDocument = this.ownerDocument();
        requirement._parent = this;
        return requirement;
    }

    /**
     * Creates a callback.
     * @param name
     */
    public Oas30Callback createCallback(String name) {
        Oas30Callback rval = new Oas30Callback(name);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a callback.
     * @param name
     * @param callback
     */
    public void addCallback(String name, Oas30Callback callback) {
        this.callbacks.put(name, callback);
    }

    /**
     * Gets a single callback by name.
     * @param name
     */
    public Oas30Callback getCallback(String name) {
        return this.callbacks.get(name);
    }

    /**
     * Removes a single callback and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public Oas30Callback removeCallback(String name) {
        return this.callbacks.remove(name);
    }

    /**
     * Gets a list of all callbacks.
     */
    public List<Oas30Callback> getCallbacks() {
        List<Oas30Callback> rval = new ArrayList<>();
        rval.addAll(this.callbacks.values());
        return rval;
    }

    /**
     * Creates a child RequestBody model.
     */
    public Oas30RequestBody createRequestBody() {
        Oas30RequestBody rval = new Oas30RequestBody();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.IServerParent#createServer()
     */
    @Override
    public Server createServer() {
        Oas30Server rval = new Oas30Server();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.IServerParent#addServer(io.apicurio.datamodels.core.models.common.Server)
     */
    @Override
    public void addServer(Server server) {
        if (this.servers == null) {
            this.servers = new ArrayList<>();
        }
        this.servers.add((Oas30Server) server);
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.IServerParent#getServer(java.lang.String)
     */
    @Override
    public Server getServer(String url) {
        if (this.servers != null) {
            for (Server server : this.servers) {
                if (NodeCompat.equals(server.url, url)) {
                    return server;
                }
            }
        }
        return null;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.IServerParent#getServers()
     */
    @Override
    public List<Server> getServers() {
        return this.servers;
    }

}
