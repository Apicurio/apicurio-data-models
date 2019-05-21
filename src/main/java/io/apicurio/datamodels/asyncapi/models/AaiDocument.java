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

import io.apicurio.datamodels.core.models.Document;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Models an AsyncAPI root document.
 *
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiDocument extends Document {

    /**
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#A2SVersionString">AsyncAPI 2.0.0 spec</a>
     */
    public String asyncapi;

    /**
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#A2SIdString">AsyncAPI 2.0.0 spec</a>
     */
    public String id;

    /**
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#channelsObject">AsyncAPI 2.0.0 spec</a>
     */
    public Map<String, AaiChannelItem> channels = new LinkedHashMap<>();

    /**
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#serverObject">AsyncAPI 2.0.0 spec</a>
     */
    public List<AaiServer> servers;

    /**
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#componentsObject">AsyncAPI 2.0.0 spec</a>
     */
    public AaiComponents components;

    public String defaultContentType;

    public abstract AaiServer createServer();

    public abstract AaiServer createServer(String url, String description);

    /**
     * Adds a server and returns it.
     */
    public abstract AaiServer addServer(AaiServer server);

    public abstract List<AaiChannelItem> getChannels();

    public abstract AaiChannelItem createChannelItem(String name);

    public abstract void addChannelItem(AaiChannelItem item);

    public abstract AaiComponents createComponents();
}
