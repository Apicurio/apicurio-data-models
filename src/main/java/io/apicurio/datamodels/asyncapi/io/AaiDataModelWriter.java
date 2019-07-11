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

import java.util.Map.Entry;

import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiComponents;
import io.apicurio.datamodels.asyncapi.models.AaiCorrelationId;
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.models.AaiHeaderItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBase;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitExtendedItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitItems;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBase;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitExtendedItem;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitItems;
import io.apicurio.datamodels.asyncapi.models.AaiParameter;
import io.apicurio.datamodels.asyncapi.models.AaiProtocolInfo;
import io.apicurio.datamodels.asyncapi.models.AaiSecurityScheme;
import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.asyncapi.models.AaiServerVariable;
import io.apicurio.datamodels.asyncapi.models.AaiUnknownTrait;
import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.io.DataModelWriter;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlows;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.PasswordOAuthFlow;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;

/**
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiDataModelWriter extends DataModelWriter implements IAaiVisitor {

    /**
     * @see io.apicurio.datamodels.core.io.DataModelWriter#writeDocument(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected void writeDocument(Document node, Object json) {
        AaiDocument aaiNode = (AaiDocument) node;
        JsonCompat.setPropertyString(json, Constants.PROP_ASYNCAPI, aaiNode.asyncapi);
        JsonCompat.setPropertyString(json, Constants.PROP_ID, aaiNode.id);
        JsonCompat.setPropertyString(json, Constants.PROP_DEFAULT_CONTENT_TYPE, aaiNode.defaultContentType);
        JsonCompat.setPropertyNull(json, Constants.PROP_INFO);
        JsonCompat.setPropertyNull(json, Constants.PROP_SERVERS);
        JsonCompat.setPropertyNull(json, Constants.PROP_CHANNELS);
        JsonCompat.setPropertyNull(json, Constants.PROP_COMPONENTS);
        JsonCompat.setPropertyNull(json, Constants.PROP_TAGS);
        JsonCompat.setPropertyNull(json, Constants.PROP_EXTERNAL_DOCS);

        writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    @Override
    public void visitServer(Server node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        writeServer(json, node);
        writeExtraProperties(json, node);
        // PROCESS PARENT
        JsonCompat.appendToArrayProperty(parent, Constants.PROP_SERVERS, json);

        this.writeExtraProperties(json, node);
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

    @Override
    public void visitServerVariable(ServerVariable node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        writeServerVariable(json, node);
        writeExtraProperties(json, node);

        // PROCESS PARENT
        // Set the variable as a property on the parent's "variables" child object
        Object variables = JsonCompat.getProperty(parent, Constants.PROP_VARIABLES);
        if (variables == null) {
            variables = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_VARIABLES, variables);
        }
        JsonCompat.setProperty(variables, node.getName(), json);

        this.writeExtraProperties(json, node);
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


    @Override
    public void visitAuthorizationCodeOAuthFlow(AuthorizationCodeOAuthFlow node) {
        this.doVisitOAuthFlow(node, Constants.PROP_AUTHORIZATION_CODE);
    }

    @Override
    public void visitChannelItem(AaiChannelItem node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();

        JsonCompat.setPropertyString(json, Constants.PROP_$REF, node.$ref);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyNull(json, Constants.PROP_PUBLISH);
        JsonCompat.setPropertyNull(json, Constants.PROP_SUBSCRIBE);
        JsonCompat.setPropertyNull(json, Constants.PROP_PARAMETERS); // list
        JsonCompat.setPropertyNull(json, Constants.PROP_PROTOCOL_INFO); // map
        // PARENT
        Object map = JsonCompat.getProperty(parent, Constants.PROP_CHANNELS);
        if (map == null) {
            map = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_CHANNELS, map);
        }
        JsonCompat.setProperty(map, node.getName(), json);

        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    @Override
    public void visitClientCredentialsOAuthFlow(ClientCredentialsOAuthFlow node) {
        this.doVisitOAuthFlow(node, Constants.PROP_CLIENT_CREDENTIALS);
    }

    @Override
    public void visitComponents(Components node) {
        AaiComponents components = (AaiComponents) node;
        
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        // write the schemas because they are not visited later
        Object schemas = JsonCompat.objectNode();
        if(components.schemas != null) {
            for (Entry<String, Object> e : components.schemas.entrySet()) {
                JsonCompat.setProperty(schemas, e.getKey(), e.getValue());
            }
            JsonCompat.setProperty(json, Constants.PROP_SCHEMAS, schemas); // map
        }
        JsonCompat.setPropertyNull(json, Constants.PROP_MESSAGES); // map
        JsonCompat.setPropertyNull(json, Constants.PROP_SECURITY_SCHEMES); // map
        JsonCompat.setPropertyNull(json, Constants.PROP_PARAMETERS); // map
        JsonCompat.setPropertyNull(json, Constants.PROP_CORRELATION_IDS); // map
        JsonCompat.setPropertyNull(json, Constants.PROP_TRAITS); // map
        // PROCESS PARENT
        JsonCompat.setProperty(parent, Constants.PROP_COMPONENTS, json);

        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    @Override
    public void visitCorrelationId(AaiCorrelationId node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_LOCATION, node.location);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyString(json, Constants.PROP_$REF, node.$ref);
        // PROCESS PARENT
        // determine if property or map
        if (node.getName() != null) {
            // assuming map
            Object map = JsonCompat.getProperty(parent, Constants.PROP_CORRELATION_IDS);
            if (map == null) {
                map = JsonCompat.objectNode();
                JsonCompat.setProperty(parent, Constants.PROP_CORRELATION_IDS, map);
            }
            JsonCompat.setProperty(map, node.getName(), json);
        } else {
            JsonCompat.setProperty(parent, Constants.PROP_CORRELATION_ID, json);
        }

        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    @Override
    public void visitHeaderItem(AaiHeaderItem node) {
        // null properties are deleted later...
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        if (node.$ref != null) {
            JsonCompat.setPropertyString(json, Constants.PROP_$REF, node.$ref);
        } else {
            json = node._schemaRaw;
        }
        // PROCESS PARENT
        // determine if property or map
        if (node.getName() != null) {
            // assuming map
            Object map = JsonCompat.getProperty(parent, Constants.PROP_HEADERS);
            if (map == null) {
                map = JsonCompat.objectNode();
                JsonCompat.setProperty(parent, Constants.PROP_HEADERS, map);
            }
            JsonCompat.setProperty(map, node.getName(), json);
        } else {
            // this case should not be present atm
            throw new IllegalStateException("Could not determine map key for " + node);
        }

        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    protected void doVisitOAuthFlow(OAuthFlow node, String flowName) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_AUTHORIZATION_URL, node.authorizationUrl);
        JsonCompat.setPropertyString(json, Constants.PROP_TOKEN_URL, node.tokenUrl);
        JsonCompat.setPropertyString(json, Constants.PROP_REFRESH_URL, node.refreshUrl);
        JsonCompat.setProperty(json, Constants.PROP_SCOPES, node.scopes);
        // PROCESS PARENT
        JsonCompat.setProperty(parent, flowName, json);

        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    @Override
    public void visitImplicitOAuthFlow(ImplicitOAuthFlow node) {
        this.doVisitOAuthFlow(node, Constants.PROP_IMPLICIT);
    }


    private Object writeMessageBase(AaiMessageBase node) {
        // Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();

        JsonCompat.setPropertyNull(json, Constants.PROP_HEADERS); // map
        JsonCompat.setPropertyNull(json, Constants.PROP_CORRELATION_ID); // prop
        JsonCompat.setPropertyString(json, Constants.PROP_SCHEMA_FORMAT, node.schemaFormat);
        JsonCompat.setPropertyString(json, Constants.PROP_CONTENT_TYPE, node.contentType);
        JsonCompat.setPropertyString(json, Constants.PROP_NAME, node.name);
        JsonCompat.setPropertyString(json, Constants.PROP_TITLE, node.title);
        JsonCompat.setPropertyString(json, Constants.PROP_SUMMARY, node.summary);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyString(json, Constants.PROP_$REF, node.$ref);
        JsonCompat.setPropertyNull(json, Constants.PROP_TAGS); // list
        JsonCompat.setPropertyNull(json, Constants.PROP_EXTERNAL_DOCS); // prop
        JsonCompat.setPropertyNull(json, Constants.PROP_PROTOCOL_INFO); // map

        return json;
    }

    @Override
    public void visitMessage(AaiMessage node) {
        Object parent = this.lookupParentJson(node);
        Object json = this.writeMessageBase(node);

        JsonCompat.setProperty(json, Constants.PROP_PAYLOAD, node.payload);
        JsonCompat.setPropertyNull(json, Constants.PROP_TRAITS); // prop

        // PROCESS PARENT
        // determine if property or map
        if (node.getName() != null) {
            // assuming map
            Object map = JsonCompat.getProperty(parent, Constants.PROP_MESSAGES);
            if (map == null) {
                map = JsonCompat.objectNode();
                JsonCompat.setProperty(parent, Constants.PROP_MESSAGES, map);
            }
            JsonCompat.setProperty(map, node.getName(), json);
        } else {
            // Handle oneOf
            if(node._isOneOfMessage) {
                Object list = JsonCompat.getProperty(parent, Constants.PROP_ONE_OF);
                if (list == null) {
                    list = JsonCompat.arrayNode();
                    JsonCompat.setProperty(parent, Constants.PROP_ONE_OF, list);
                }
                JsonCompat.appendToArray(list, json);
            } else {
                JsonCompat.setProperty(parent, Constants.PROP_MESSAGE, json);
            }
        }

        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    @Override
    public void visitMessageTraitExtendedItem(AaiMessageTraitExtendedItem node) {
        Object parent = this.lookupParentJson(node, JsonCompat.arrayNode());
        Object json = JsonCompat.arrayNode();

        // leave the message trait serialization to the visitor
        JsonCompat.appendToArray(json, JsonCompat.objectNode());
        // serialize extension directly
        if(node._traitExtension != null) {
            Object _traitExtension = JsonCompat.objectNode();
            for (Entry<String, Object> e : node._traitExtension.entrySet()) {
                JsonCompat.setProperty(_traitExtension, e.getKey(), e.getValue());
            }
            JsonCompat.appendToArray(json, _traitExtension);
        }
        // PROCESS PARENT
        // parent json is also an array, (see AaiMessageTraitItems)
        JsonCompat.appendToArray(parent, json);

        this.updateIndex(node, json);
    }

    @Override
    public void visitMessageTraitItems(AaiMessageTraitItems node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.arrayNode();
        // The json of this node is an array, whether it is
        // [Message Trait Object] or [[Message Trait Object, Map]]
        // Visitors of the child nodes will take case of adding the items.
        // PROCESS PARENT
        JsonCompat.setProperty(parent, Constants.PROP_TRAITS, json);

        this.updateIndex(node, json);
    }

    @Override
    public void visitMessageTrait(AaiMessageTrait node) {
        Object json = this.writeMessageBase(node);
        // PROCESS PARENT
        // We need to find out what actually to do with the serialized json...
        if (node.parent() instanceof AaiMessageTraitItems) {
            Object parent = this.lookupParentJson(node, JsonCompat.arrayNode());
            JsonCompat.appendToArray(parent, json);
        } else if (node.parent() instanceof AaiMessageTraitExtendedItem) {
            Object parent = this.lookupParentJson(node, JsonCompat.appendToArray(JsonCompat.arrayNode(), JsonCompat.nullNode()));
            JsonCompat.setToArrayIndex(parent, 0, json);
        } else if (node.parent() instanceof AaiComponents) {
            Object parent = this.lookupParentJson(node);
            
            Object traits = JsonCompat.getProperty(parent, Constants.PROP_TRAITS);
            if (traits == null) {
                traits = JsonCompat.objectNode();
                JsonCompat.setProperty(parent, Constants.PROP_TRAITS, traits);
            }
            
            JsonCompat.setProperty(traits, node.getName(), json);
        } else {
            throw new IllegalStateException("Don't know where to write a trait node.");
        }
        this.updateIndex(node, json);
    }

    @Override
    public void visitOAuthFlows(OAuthFlows node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyNull(json, Constants.PROP_IMPLICIT);
        JsonCompat.setPropertyNull(json, Constants.PROP_PASSWORD);
        JsonCompat.setPropertyNull(json, Constants.PROP_CLIENT_CREDENTIALS);
        JsonCompat.setPropertyNull(json, Constants.PROP_AUTHORIZATION_CODE);
        // PROCESS PARENT
        JsonCompat.setProperty(parent, Constants.PROP_FLOWS, json);

        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    @Override
    public void visitOperationTraitExtendedItem(AaiOperationTraitExtendedItem node) {
        Object parent = this.lookupParentJson(node, JsonCompat.arrayNode());
        Object json = JsonCompat.arrayNode();

        // leave the operation trait serialization to the visitor
        JsonCompat.appendToArray(json, JsonCompat.objectNode());
        // serialize extension directly
        if(node._traitExtension != null) {
            Object _traitExtension = JsonCompat.objectNode();
            for (Entry<String, Object> e : node._traitExtension.entrySet()) {
                JsonCompat.setProperty(_traitExtension, e.getKey(), e.getValue());
            }
            JsonCompat.appendToArray(json, _traitExtension);
        }
        // PROCESS PARENT
        // parent json is also an array, (see AaiOperationTraitItems)
        JsonCompat.appendToArray(parent, json);

        this.updateIndex(node, json);
    }

    @Override
    public void visitOperationTraitItems(AaiOperationTraitItems node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.arrayNode();
        // The json of this node is an array, whether it is
        // [Operation Trait Object] or [[Operation Trait Object, Map]]
        // Visitors of the child nodes will take case of adding the items.
        // PROCESS PARENT
        JsonCompat.setProperty(parent, Constants.PROP_TRAITS, json);

        this.updateIndex(node, json);
    }

    @Override
    protected void writeOperation(Object json, Operation node) {
        AaiOperation aaiNode = (AaiOperation) node;
        super.writeOperation(json, aaiNode);
    }

    protected Object writeOperationBase(AaiOperationBase node) {
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_$REF, node.$ref);
        JsonCompat.setPropertyString(json, Constants.PROP_OPERATION_ID, node.operationId);
        JsonCompat.setPropertyString(json, Constants.PROP_SUMMARY, node.summary);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyNull(json, Constants.PROP_EXTERNAL_DOCS); // prop
        JsonCompat.setPropertyNull(json, Constants.PROP_TAGS); // list
        JsonCompat.setPropertyNull(json, Constants.PROP_PROTOCOL_INFO); // map

        return json;
    }

    @Override
    public void visitOperation(Operation node) {
        AaiOperation aaiNode = (AaiOperation) node;
        Object parent = this.lookupParentJson(node);
        Object json = this.writeOperationBase(aaiNode);

        JsonCompat.setPropertyNull(json, Constants.PROP_TRAITS); // prop
        JsonCompat.setPropertyNull(json, Constants.PROP_MESSAGE); // prop
        // PROCESS PARENT
        JsonCompat.setProperty(parent, node.getType(), json);

        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    @Override
    public void visitOperationTrait(AaiOperationTrait node) {
        Object json = this.writeOperationBase(node);
        // PROCESS PARENT
        // We need to find out what actually to do with the serialized json...
        if (node.parent() instanceof AaiOperationTraitItems) {
            Object parent = this.lookupParentJson(node, JsonCompat.arrayNode());
            JsonCompat.appendToArray(parent, json);
        } else if (node.parent() instanceof AaiOperationTraitExtendedItem) {
            Object parent = this.lookupParentJson(node, JsonCompat.appendToArray(JsonCompat.arrayNode(), JsonCompat.nullNode()));
            JsonCompat.setToArrayIndex(parent, 0, json);
        } else if (node.parent() instanceof AaiComponents) {
            Object parent = this.lookupParentJson(node);

            Object traits = JsonCompat.getProperty(parent, Constants.PROP_TRAITS);
            if (traits == null) {
                traits = JsonCompat.objectNode();
                JsonCompat.setProperty(parent, Constants.PROP_TRAITS, traits);
            }
            
            JsonCompat.setProperty(traits, node.getType(), json);
        } else {
            throw new IllegalStateException("Don't know where to write a trait node.");
        }
        this.updateIndex(node, json);
    }

    @Override
    public void visitPasswordOAuthFlow(PasswordOAuthFlow node) {
        this.doVisitOAuthFlow(node, Constants.PROP_PASSWORD);
    }

    @Override
    public void visitProtocolInfo(AaiProtocolInfo node) {
        Object parent = this.lookupParentJson(node);
        if(node._protocolInfoItems != null) {
            Object json = JsonCompat.objectNode();
            for (Entry<String, Object> e : node._protocolInfoItems.entrySet()) {
                JsonCompat.setProperty(json, e.getKey(), e.getValue());
            }
            // PARENT
            if(node.getName() != null) {
                Object map = JsonCompat.getProperty(parent, Constants.PROP_PROTOCOL_INFO);
                if (map == null) {
                    map = JsonCompat.objectNode();
                    JsonCompat.setProperty(parent, Constants.PROP_PROTOCOL_INFO, map);
                }
                JsonCompat.setProperty(map, node.getName(), json);
            } else {
                throw new IllegalStateException("error in writing protocol info");
            }
            this.updateIndex(node, json);
        }
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitUnknownTrait(io.apicurio.datamodels.asyncapi.models.AaiUnknownTrait)
     */
    @Override
    public void visitUnknownTrait(AaiUnknownTrait node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();

        JsonCompat.setPropertyString(json, Constants.PROP_SUMMARY, node.summary);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyNull(json, Constants.PROP_TAGS); // list
        JsonCompat.setPropertyNull(json, Constants.PROP_EXTERNAL_DOCS); // prop
        JsonCompat.setPropertyNull(json, Constants.PROP_PROTOCOL_INFO); // map
        this.writeExtraProperties(json, node);
        
        Object traits = JsonCompat.getProperty(parent, Constants.PROP_TRAITS);
        if (traits == null) {
            traits = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_TRAITS, traits);
        }
        
        JsonCompat.setProperty(traits, node.getName(), json);

        this.updateIndex(node, json);
    }

    @Override
    public void visitAaiParameter(AaiParameter node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();

        JsonCompat.setPropertyString(json, Constants.PROP_$REF, node.$ref);
        JsonCompat.setPropertyString(json, Constants.PROP_NAME, node.name);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setProperty(json, Constants.PROP_SCHEMA, node.schema);
        // PROCESS PARENT
        if(node.getName() != null) {
            Object map = JsonCompat.getProperty(parent, Constants.PROP_PARAMETERS);
            if (map == null) {
                map = JsonCompat.objectNode();
                JsonCompat.setProperty(parent, Constants.PROP_PARAMETERS, map);
            }
            JsonCompat.setProperty(map, node.getName(), json);
        } else {
            Object list = JsonCompat.getProperty(parent, Constants.PROP_PARAMETERS);
            if (list == null) {
                list = JsonCompat.arrayNode();
                JsonCompat.setProperty(parent, Constants.PROP_PARAMETERS, list);
            }
            JsonCompat.appendToArray(list, json);
        }
        this.updateIndex(node, json);
    }

    @Override
    protected void addSecuritySchemeToParent(Object parent, Object json, SecurityScheme node) {
        if(node.getName() != null) {
            Object map = JsonCompat.getProperty(parent, Constants.PROP_SECURITY_SCHEMES);
            if (map == null) {
                map = JsonCompat.objectNode();
                JsonCompat.setProperty(parent, Constants.PROP_SECURITY_SCHEMES, map);
            }
            JsonCompat.setProperty(map, node.getName(), json);
        } else {
            throw new IllegalStateException("error in writing a security scheme");
        }
    }

    @Override
    protected void writeSecurityScheme(Object json, SecurityScheme node) {
        AaiSecurityScheme aaiNode = (AaiSecurityScheme) node;
        super.writeSecurityScheme(json, node);
        JsonCompat.setPropertyString(json, Constants.PROP_SCHEME, aaiNode.scheme);

        // openIdConnectUrl & bearerFormat
        JsonCompat.setPropertyString(json, Constants.PROP_OPEN_ID_CONNECT_URL, aaiNode.openIdConnectUrl);
        JsonCompat.setPropertyString(json, Constants.PROP_BEARER_FORMAT, aaiNode.bearerFormat);
    }
}
