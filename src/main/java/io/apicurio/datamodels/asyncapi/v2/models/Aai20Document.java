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

import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiComponents;
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Models an AsyncAPI root document.
 *
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Aai20Document extends AaiDocument {

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
        Aai20Info info = new Aai20Info();
        info._parent = this;
        info._ownerDocument = this;
        return info;
    }

    /**
     * @see Document#createTag()
     */
    @Override
    public Aai20Tag createTag() {
        Aai20Tag tag = new Aai20Tag(this);
        return tag;
    }

    /**
     * @see Document#createExternalDocumentation()
     */
    @Override
    public Aai20ExternalDocumentation createExternalDocumentation() {
        Aai20ExternalDocumentation ed = new Aai20ExternalDocumentation(this);
        return ed;
    }

    /**
     * Creates an AAI 2.0 Server object.
     */
    @Override
    public Aai20Server createServer() {
        Aai20Server rval = new Aai20Server();
        rval._ownerDocument = this;
        rval._parent = this;
        return rval;
    }

    /**
     * Creates an AAI 2.0 Server object.
     */
    @Override
    public Aai20Server createServer(String url, String description) {
        Aai20Server rval = createServer();
        rval.url = url;
        rval.description = description;
        return rval;
    }

    @Override
    public AaiServer addServer(AaiServer server) {
        if (this.servers == null) {
            this.servers = new ArrayList<>();
        }
        this.servers.add(server);
        return server;
    }

    @Override
    public List<AaiChannelItem> getChannels() {
        return JsonCompat.mapToList(this.channels);
    }

    @Override
    public AaiChannelItem createChannelItem(String name) {
        return new Aai20ChannelItem(this, name);
    }

    @Override
    public void addChannelItem(AaiChannelItem item) {
        if (channels == null)
            channels = new LinkedHashMap<>();
        channels.put(item.getName(), item);
    }

    @Override
    public AaiComponents createComponents() {
        return new Aai20Components(this);
    }
}
