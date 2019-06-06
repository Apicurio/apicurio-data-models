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
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.IServerParent;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.Tag;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasPaths;
import io.apicurio.datamodels.openapi.models.OasSecurityRequirement;

/**
 * Models the root document of the OpenAPI 2.0 (aka Swagger) data model.
 * @author eric.wittmann@gmail.com
 */
public class Oas30Document extends OasDocument implements IServerParent {

    public String openapi;
    public List<Server> servers;
    public Oas30Components components;
    
    /**
     * Constructor.
     */
    public Oas30Document() {
        this.openapi = Constants.OPEN_API_30_DEFAULT_VERSION;
    }

    /**
     * @see io.apicurio.datamodels.core.models.Document#getDocumentType()
     */
    @Override
    public final DocumentType getDocumentType() {
        return DocumentType.openapi3;
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasDocument#createPaths()
     */
    @Override
    public OasPaths createPaths() {
        OasPaths rval = new Oas30Paths();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.models.OasDocument#createSecurityRequirement()
     */
    @Override
    public OasSecurityRequirement createSecurityRequirement() {
        OasSecurityRequirement requirement = new Oas30SecurityRequirement();
        requirement._ownerDocument = this;
        requirement._parent = this;
        return requirement;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Document#createInfo()
     */
    @Override
    public Info createInfo() {
        Info info = new Oas30Info();
        info._ownerDocument = this;
        info._parent = this;
        return info;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Document#createTag()
     */
    @Override
    public Tag createTag() {
        Tag tag = new Oas30Tag();
        tag._ownerDocument = this;
        tag._parent = this;
        return tag;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Document#createExternalDocumentation()
     */
    @Override
    public ExternalDocumentation createExternalDocumentation() {
        ExternalDocumentation ed = new Oas30ExternalDocumentation();
        ed._ownerDocument = this;
        ed._parent = this;
        return ed;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.IServerParent#createServer()
     */
    @Override
    public Server createServer() {
        Oas30Server rval = new Oas30Server();
        rval._ownerDocument = this;
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a server.
     * @param url
     * @param description
     */
    public Server addServer(String url, String description) {
        Server server = this.createServer();
        server.url = url;
        server.description = description;
        if (this.servers == null) {
            this.servers = new ArrayList<>();
        }
        this.servers.add(server);
        return server;
    }

    /**
     * Creates an OAS 3.0 Components object.
     */
    public Oas30Components createComponents() {
        Oas30Components rval = new Oas30Components();
        rval._ownerDocument = this;
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
        this.servers.add(server);
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
