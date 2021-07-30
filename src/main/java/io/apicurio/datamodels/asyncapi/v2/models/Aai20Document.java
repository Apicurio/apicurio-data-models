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

package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;

/**
 * Models an AsyncAPI root document.
 *
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Aai20Document extends AaiDocument {
    
    /**
     * Constructor.
     */
    public Aai20Document() {
        this.asyncapi = Constants.ASYNC_API_20_DEFAULT_VERSION;
    }

    /**
     * @see Document#getDocumentType()
     */
    @Override
    public DocumentType getDocumentType() {
        return DocumentType.asyncapi2;
    }

    /**
     * @see Document#createInfo()
     */
    @Override
    public Aai20Info createInfo() {
        return new Aai20Info(this);
    }

    /**
     * @see Document#createTag()
     */
    @Override
    public Aai20Tag createTag() {
        return new Aai20Tag(this);
    }

    /**
     * @see Document#createExternalDocumentation()
     */
    @Override
    public Aai20ExternalDocumentation createExternalDocumentation() {
        return new Aai20ExternalDocumentation(this);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.models.AaiDocument#createServer(java.lang.String)
     */
    @Override
    public AaiServer createServer(String name) {
        return new Aai20Server(this, name);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.models.AaiDocument#createServer(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public AaiServer createServer(String name, String url, String description) {
        Aai20Server server = new Aai20Server(this, name);
        server.url = url;
        server.description = description;
        return server;
    }

    @Override
    public Aai20ChannelItem createChannelItem(String name) {
        return new Aai20ChannelItem(this, name);
    }

    @Override
    public Aai20Components createComponents() {
        return new Aai20Components(this);
    }
}
