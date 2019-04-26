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

package io.apicurio.datamodels.openapi.v3.io;

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.openapi.io.OasDataModelReader;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Server;

/**
 * A data model reader for the OpenAPI 2.0 data model.
 * @author eric.wittmann@gmail.com
 */
public class Oas30DataModelReader extends OasDataModelReader<Oas30Document> {
    
    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readDocument(java.lang.Object, io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void readDocument(Object json, Oas30Document node) {
        super.readDocument(json, node);

        String openapi = JsonCompat.consumePropertyString(json, Constants.PROP_OPENAPI);
        List<Object> servers = JsonCompat.consumePropertyArray(json, Constants.PROP_SERVERS);

        // TODO implement this
        
        node.openapi = openapi;

        if (servers != null) {
            List<Oas30Server> serverModels = new ArrayList<>();
            servers.forEach(server -> {
                Oas30Server serverModel = node.createServer();
                this.readServer(server, serverModel);
                serverModels.add(serverModel);
            });
            node.servers = serverModels;
        }

        this.readExtraProperties(json, node);
    }

}
