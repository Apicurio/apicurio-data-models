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

package io.apicurio.datamodels.asyncapi.io;

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.models.AaiSecurityRequirement;
import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.asyncapi.models.AaiServerVariable;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.io.DataModelReader;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;

/**
 * A data model reader for the AsyncAPI data model.
 * @author eric.wittmann@gmail.com
 */
public class AaiDataModelReader extends DataModelReader<AaiDocument> {

    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readDocument(java.lang.Object, io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void readDocument(Object json, AaiDocument node) {
        String asyncapi = JsonCompat.consumePropertyString(json, Constants.PROP_ASYNCAPI);
        String id = JsonCompat.consumePropertyString(json, Constants.PROP_ID);
        List<Object> servers = JsonCompat.consumePropertyArray(json, Constants.PROP_SERVERS);

        node.asyncapi = asyncapi;
        node.id = id;
        
        if (servers != null) {
            List<AaiServer> serverModels = new ArrayList<>();
            servers.forEach(server -> {
                AaiServer serverModel = node.createServer();
                this.readServer(server, serverModel);
                serverModels.add(serverModel);
            });
            node.servers = serverModels;
        }

        super.readDocument(json, node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readServer(java.lang.Object, io.apicurio.datamodels.core.models.common.Server)
     */
    @Override
    public void readServer(Object json, Server node) {
        AaiServer aaiNode = (AaiServer) node;
        String protocol = JsonCompat.consumePropertyString(json, Constants.PROP_PROTOCOL);
        String protocolVersion = JsonCompat.consumePropertyString(json, Constants.PROP_PROTOCOL_VERSION);
        String baseChannel = JsonCompat.consumePropertyString(json, Constants.PROP_BASE_CHANNEL);
        List<Object> security = JsonCompat.consumePropertyArray(json, Constants.PROP_SECURITY);

        aaiNode.protocol = protocol;
        aaiNode.protocolVersion = protocolVersion;
        aaiNode.baseChannel = baseChannel;

        if (security != null) {
            security.forEach(sec -> {
                AaiSecurityRequirement secModel = aaiNode.createSecurityRequirement();
                this.readSecurityRequirement(sec, secModel);
                aaiNode.addSecurityRequirement(secModel);
            });
        }

        super.readServer(json, node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readServerVariable(java.lang.Object, io.apicurio.datamodels.core.models.common.ServerVariable)
     */
    @Override
    public void readServerVariable(Object json, ServerVariable node) {
        AaiServerVariable aaiNode = (AaiServerVariable) node;
        List<String> examples = JsonCompat.consumePropertyStringArray(json, Constants.PROP_EXAMPLES);
        
        aaiNode.examples = examples;
        
        super.readServerVariable(json, node);
    }

}
