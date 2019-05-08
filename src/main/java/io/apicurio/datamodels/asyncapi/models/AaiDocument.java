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

package io.apicurio.datamodels.asyncapi.models;

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.Tag;

/**
 * Models an AsyncAPI root document.
 * @author eric.wittmann@gmail.com
 */
public class AaiDocument extends Document {
	
	public String asyncapi;
	public String id;
    public List<AaiServer> servers;
    // TODO channels
    // TODO components

	/**
     * Constructor.
     */
    public AaiDocument() {
    }

    /**
     * @see io.apicurio.datamodels.core.models.Document#getDocumentType()
     */
    @Override
    public DocumentType getDocumentType() {
        return DocumentType.asyncapi2;
    }

    /**
     * @see io.apicurio.datamodels.core.models.Document#createInfo()
     */
    @Override
    public AaiInfo createInfo() {
        AaiInfo info = new AaiInfo();
        info._parent = this;
        info._ownerDocument = this;
        return info;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Document#createTag()
     */
    @Override
    public Tag createTag() {
        Tag tag = new AaiTag();
        tag._ownerDocument = this.ownerDocument();
        tag._parent = this;
        return tag;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Document#createExternalDocumentation()
     */
    @Override
    public ExternalDocumentation createExternalDocumentation() {
        ExternalDocumentation ed = new AaiExternalDocumentation();
        ed._ownerDocument = this.ownerDocument();
        ed._parent = this;
        return ed;
    }

    /**
     * Creates an OAS 3.0 Server object.
     */
    public AaiServer createServer() {
        AaiServer rval = new AaiServer();
        rval._ownerDocument = this;
        rval._parent = this;
        return rval;
    }

    /**
     * Adds a server.
     * @param url
     * @param description
     */
    public AaiServer addServer(String url, String description) {
        AaiServer server = this.createServer();
        server.url = url;
        server.description = description;
        if (this.servers == null) {
            this.servers = new ArrayList<>();
        }
        this.servers.add(server);
        return server;
    }

}
