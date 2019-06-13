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

import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.asyncapi.models.AaiServerVariable;
import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.io.DataModelWriter;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;

/**
 * @author eric.wittmann@gmail.com
 */
public class AaiDataModelWriter extends DataModelWriter implements IAaiVisitor {

    /**
     * @see io.apicurio.datamodels.core.io.DataModelWriter#writeDocument(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected void writeDocument(Document node, Object json) {
        AaiDocument doc = (AaiDocument) node;
        JsonCompat.setPropertyString(json, Constants.PROP_ASYNCAPI, doc.asyncapi);
        JsonCompat.setPropertyString(json, Constants.PROP_ID, doc.id);
        JsonCompat.setPropertyNull(json, Constants.PROP_INFO);
        JsonCompat.setPropertyNull(json, Constants.PROP_SERVERS);
        JsonCompat.setPropertyNull(json, Constants.PROP_CHANNELS);
        JsonCompat.setPropertyNull(json, Constants.PROP_COMPONENTS);
        JsonCompat.setPropertyNull(json, Constants.PROP_TAGS);
        JsonCompat.setPropertyNull(json, Constants.PROP_EXTERNAL_DOCS);
        
        writeExtraProperties(json, node);
    }
    
    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitServer(io.apicurio.datamodels.core.models.common.Server)
     */
    @Override
    public void visitServer(Server node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        writeServer(json, node);
        writeExtraProperties(json, node);

        JsonCompat.appendToArrayProperty(parent, Constants.PROP_SERVERS, json);
        
        this.updateIndex(node, json);
    }
    protected void writeServer(Object json, Server node) {
        JsonCompat.setPropertyString(json, Constants.PROP_URL, node.url);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyNull(json, Constants.PROP_VARIABLES);
        
        AaiServer server = (AaiServer) node;
        JsonCompat.setPropertyString(json, Constants.PROP_PROTOCOL, server.protocol);
        JsonCompat.setPropertyString(json, Constants.PROP_PROTOCOL_VERSION, server.protocolVersion);
        JsonCompat.setPropertyString(json, Constants.PROP_BASE_CHANNEL, server.baseChannel);
        JsonCompat.setPropertyNull(json, Constants.PROP_SECURITY);
    }
    
    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitServerVariable(io.apicurio.datamodels.core.models.common.ServerVariable)
     */
    @Override
    public void visitServerVariable(ServerVariable node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        writeServerVariable(json, node);
        writeExtraProperties(json, node);
        
        // Set the variable as a property on the parent's "variables" child object
        Object variables = JsonCompat.getProperty(parent, Constants.PROP_VARIABLES);
        if (variables == null) {
            variables = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_VARIABLES, variables);
        }
        JsonCompat.setProperty(variables, node.getName(), json);

        this.updateIndex(node, json);
    }
    protected void writeServerVariable(Object json, ServerVariable node) {
        JsonCompat.setPropertyStringArray(json, Constants.PROP_ENUM, node.enum_);
        JsonCompat.setPropertyString(json, Constants.PROP_DEFAULT, node.default_);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyNull(json, Constants.PROP_VARIABLES);

        AaiServerVariable serverVar = (AaiServerVariable) node;
        JsonCompat.setPropertyStringArray(json, Constants.PROP_EXAMPLES, serverVar.examples);
    }

}
