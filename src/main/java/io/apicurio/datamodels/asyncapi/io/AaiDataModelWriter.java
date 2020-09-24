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

import io.apicurio.datamodels.asyncapi.models.AaiChannelBindings;
import io.apicurio.datamodels.asyncapi.models.AaiChannelBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiCorrelationId;
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.models.AaiHeaderItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBase;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBindings;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBase;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBindings;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiParameter;
import io.apicurio.datamodels.asyncapi.models.AaiSchema;
import io.apicurio.datamodels.asyncapi.models.AaiSecurityScheme;
import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindings;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiServerVariable;
import io.apicurio.datamodels.asyncapi.models.IAaiPropertySchema;
import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.io.DataModelWriter;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlows;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.PasswordOAuthFlow;
import io.apicurio.datamodels.core.models.common.Schema;
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
        
        // PROCESS PARENT
        Object servers = JsonCompat.getProperty(parent, Constants.PROP_SERVERS);
        if (servers == null) {
            servers = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_SERVERS, servers);
        }
        JsonCompat.setProperty(servers, ((AaiServer) node).getName(), json);

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
        JsonCompat.setPropertyNull(json, Constants.PROP_SECURITY);
        JsonCompat.setPropertyNull(json, Constants.PROP_BINDINGS);
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
        JsonCompat.setPropertyNull(json, Constants.PROP_PARAMETERS); // map
        JsonCompat.setPropertyNull(json, Constants.PROP_BINDINGS); // map
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
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyNull(json, Constants.PROP_SCHEMAS); // map
        JsonCompat.setPropertyNull(json, Constants.PROP_MESSAGES); // map
        JsonCompat.setPropertyNull(json, Constants.PROP_SECURITY_SCHEMES); // map
        JsonCompat.setPropertyNull(json, Constants.PROP_PARAMETERS); // map
        JsonCompat.setPropertyNull(json, Constants.PROP_CORRELATION_IDS); // map
        JsonCompat.setPropertyNull(json, Constants.PROP_OPERATION_TRAITS); // map
        JsonCompat.setPropertyNull(json, Constants.PROP_MESSAGE_TRAITS); // map
        JsonCompat.setPropertyNull(json, Constants.PROP_SERVER_BINDINGS); // map
        JsonCompat.setPropertyNull(json, Constants.PROP_CHANNEL_BINDINGS); // map
        JsonCompat.setPropertyNull(json, Constants.PROP_OPERATION_BINDINGS); // map
        JsonCompat.setPropertyNull(json, Constants.PROP_MESSAGE_BINDINGS); // map

        // PROCESS PARENT
        JsonCompat.setProperty(parent, Constants.PROP_COMPONENTS, json);

        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.core.io.DataModelWriter#addSchemaDefinitionToParent(java.lang.Object, java.lang.Object, io.apicurio.datamodels.core.models.common.IDefinition)
     */
    @Override
    protected void addSchemaDefinitionToParent(Object parent, Object json, IDefinition node) {
       Object schemas = JsonCompat.getProperty(parent, Constants.PROP_SCHEMAS);
       if (schemas == null) {
          schemas = JsonCompat.objectNode();
          JsonCompat.setProperty(parent, Constants.PROP_SCHEMAS, schemas);
       }

       JsonCompat.setProperty(schemas, node.getName(), json);
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
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        if (node.$ref != null) {
            JsonCompat.setPropertyString(json, Constants.PROP_$REF, node.$ref);
        } else {
            json = node._schemaRaw;
        }
        // PROCESS PARENT
        JsonCompat.setProperty(parent, Constants.PROP_HEADERS, json);

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
        JsonCompat.setPropertyNull(json, Constants.PROP_BINDINGS); // map

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
    public void visitMessageTrait(AaiMessageTrait node) {
        Object json = this.writeMessageBase(node);
        // PROCESS PARENT
        Object parent = this.lookupParentJson(node);
        JsonCompat.appendToArrayProperty(parent, Constants.PROP_TRAITS, json);
        
        this.writeExtraProperties(json, node);
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
        JsonCompat.setPropertyNull(json, Constants.PROP_BINDINGS); // map

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
        Object parent = this.lookupParentJson(node);
        JsonCompat.appendToArrayProperty(parent, Constants.PROP_TRAITS, json);

        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    @Override
    public void visitPasswordOAuthFlow(PasswordOAuthFlow node) {
        this.doVisitOAuthFlow(node, Constants.PROP_PASSWORD);
    }

    @Override
    public void visitAaiParameter(AaiParameter node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();

        JsonCompat.setPropertyString(json, Constants.PROP_$REF, node.$ref);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setProperty(json, Constants.PROP_SCHEMA, node.schema);
        JsonCompat.setPropertyString(json, Constants.PROP_LOCATION, node.location);
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
        
        this.writeExtraProperties(json, node);
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
        JsonCompat.setPropertyString(json, Constants.PROP_$REF, aaiNode.$ref);
        JsonCompat.setPropertyString(json, Constants.PROP_SCHEME, aaiNode.scheme);

        // openIdConnectUrl & bearerFormat
        JsonCompat.setPropertyString(json, Constants.PROP_OPEN_ID_CONNECT_URL, aaiNode.openIdConnectUrl);
        JsonCompat.setPropertyString(json, Constants.PROP_BEARER_FORMAT, aaiNode.bearerFormat);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitMessageTraitDefinition(io.apicurio.datamodels.asyncapi.models.AaiMessageTraitDefinition)
     */
    @Override
    public void visitMessageTraitDefinition(AaiMessageTraitDefinition node) {
        Object json = this.writeMessageBase(node);
        // PROCESS PARENT
        Object parent = this.lookupParentJson(node);
        Object map = JsonCompat.getProperty(parent, Constants.PROP_MESSAGE_TRAITS);
        if (map == null) {
            map = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_MESSAGE_TRAITS, map);
        }
        JsonCompat.setProperty(map, node.getName(), json);
        
        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitOperationTraitDefinition(io.apicurio.datamodels.asyncapi.models.AaiOperationTraitDefinition)
     */
    @Override
    public void visitOperationTraitDefinition(AaiOperationTraitDefinition node) {
        Object json = this.writeOperationBase(node);
        // PROCESS PARENT
        Object parent = this.lookupParentJson(node);
        Object map = JsonCompat.getProperty(parent, Constants.PROP_OPERATION_TRAITS);
        if (map == null) {
            map = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_OPERATION_TRAITS, map);
        }
        JsonCompat.setProperty(map, node.getName(), json);
        
        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitServerBindings(io.apicurio.datamodels.asyncapi.models.AaiServerBindings)
     */
    @Override
    public void visitServerBindings(AaiServerBindings node) {
        Object json = this.writeNullBindings();
        JsonCompat.setProperty(json, Constants.PROP_HTTP, node.http);
        JsonCompat.setProperty(json, Constants.PROP_WS, node.ws);
        JsonCompat.setProperty(json, Constants.PROP_KAFKA, node.kafka);
        JsonCompat.setProperty(json, Constants.PROP_AMQP, node.amqp);
        JsonCompat.setProperty(json, Constants.PROP_AMQP1, node.amqp1);
        JsonCompat.setProperty(json, Constants.PROP_MQTT, node.mqtt);
        JsonCompat.setProperty(json, Constants.PROP_MQTT5, node.mqtt5);
        JsonCompat.setProperty(json, Constants.PROP_NATS, node.nats);
        JsonCompat.setProperty(json, Constants.PROP_JMS, node.jms);
        JsonCompat.setProperty(json, Constants.PROP_SNS, node.sns);
        JsonCompat.setProperty(json, Constants.PROP_SQS, node.sqs);
        JsonCompat.setProperty(json, Constants.PROP_STOMP, node.stomp);
        JsonCompat.setProperty(json, Constants.PROP_REDIS, node.redis);
        
        // PROCESS PARENT
        Object parent = this.lookupParentJson(node);
        JsonCompat.setProperty(parent, Constants.PROP_BINDINGS, json);
        
        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitServerBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiServerBindingsDefinition)
     */
    @Override
    public void visitServerBindingsDefinition(AaiServerBindingsDefinition node) {
        Object json = this.writeNullBindings();
        JsonCompat.setProperty(json, Constants.PROP_HTTP, node.http);
        JsonCompat.setProperty(json, Constants.PROP_WS, node.ws);
        JsonCompat.setProperty(json, Constants.PROP_KAFKA, node.kafka);
        JsonCompat.setProperty(json, Constants.PROP_AMQP, node.amqp);
        JsonCompat.setProperty(json, Constants.PROP_AMQP1, node.amqp1);
        JsonCompat.setProperty(json, Constants.PROP_MQTT, node.mqtt);
        JsonCompat.setProperty(json, Constants.PROP_MQTT5, node.mqtt5);
        JsonCompat.setProperty(json, Constants.PROP_NATS, node.nats);
        JsonCompat.setProperty(json, Constants.PROP_JMS, node.jms);
        JsonCompat.setProperty(json, Constants.PROP_SNS, node.sns);
        JsonCompat.setProperty(json, Constants.PROP_SQS, node.sqs);
        JsonCompat.setProperty(json, Constants.PROP_STOMP, node.stomp);
        JsonCompat.setProperty(json, Constants.PROP_REDIS, node.redis);

        // PROCESS PARENT
        Object parent = this.lookupParentJson(node);
        Object map = JsonCompat.getProperty(parent, Constants.PROP_SERVER_BINDINGS);
        if (map == null) {
            map = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_SERVER_BINDINGS, map);
        }
        JsonCompat.setProperty(map, node.getName(), json);
        
        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }
    
    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitOperationBindings(io.apicurio.datamodels.asyncapi.models.AaiOperationBindings)
     */
    @Override
    public void visitOperationBindings(AaiOperationBindings node) {
        Object json = this.writeNullBindings();
        JsonCompat.setProperty(json, Constants.PROP_HTTP, node.http);
        JsonCompat.setProperty(json, Constants.PROP_WS, node.ws);
        JsonCompat.setProperty(json, Constants.PROP_KAFKA, node.kafka);
        JsonCompat.setProperty(json, Constants.PROP_AMQP, node.amqp);
        JsonCompat.setProperty(json, Constants.PROP_AMQP1, node.amqp1);
        JsonCompat.setProperty(json, Constants.PROP_MQTT, node.mqtt);
        JsonCompat.setProperty(json, Constants.PROP_MQTT5, node.mqtt5);
        JsonCompat.setProperty(json, Constants.PROP_NATS, node.nats);
        JsonCompat.setProperty(json, Constants.PROP_JMS, node.jms);
        JsonCompat.setProperty(json, Constants.PROP_SNS, node.sns);
        JsonCompat.setProperty(json, Constants.PROP_SQS, node.sqs);
        JsonCompat.setProperty(json, Constants.PROP_STOMP, node.stomp);
        JsonCompat.setProperty(json, Constants.PROP_REDIS, node.redis);

        // PROCESS PARENT
        Object parent = this.lookupParentJson(node);
        JsonCompat.setProperty(parent, Constants.PROP_BINDINGS, json);
        
        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitOperationBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiOperationBindingsDefinition)
     */
    @Override
    public void visitOperationBindingsDefinition(AaiOperationBindingsDefinition node) {
        Object json = this.writeNullBindings();
        JsonCompat.setProperty(json, Constants.PROP_HTTP, node.http);
        JsonCompat.setProperty(json, Constants.PROP_WS, node.ws);
        JsonCompat.setProperty(json, Constants.PROP_KAFKA, node.kafka);
        JsonCompat.setProperty(json, Constants.PROP_AMQP, node.amqp);
        JsonCompat.setProperty(json, Constants.PROP_AMQP1, node.amqp1);
        JsonCompat.setProperty(json, Constants.PROP_MQTT, node.mqtt);
        JsonCompat.setProperty(json, Constants.PROP_MQTT5, node.mqtt5);
        JsonCompat.setProperty(json, Constants.PROP_NATS, node.nats);
        JsonCompat.setProperty(json, Constants.PROP_JMS, node.jms);
        JsonCompat.setProperty(json, Constants.PROP_SNS, node.sns);
        JsonCompat.setProperty(json, Constants.PROP_SQS, node.sqs);
        JsonCompat.setProperty(json, Constants.PROP_STOMP, node.stomp);
        JsonCompat.setProperty(json, Constants.PROP_REDIS, node.redis);

        // PROCESS PARENT
        Object parent = this.lookupParentJson(node);
        Object map = JsonCompat.getProperty(parent, Constants.PROP_OPERATION_BINDINGS);
        if (map == null) {
            map = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_OPERATION_BINDINGS, map);
        }
        JsonCompat.setProperty(map, node.getName(), json);
        
        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitMessageBindings(io.apicurio.datamodels.asyncapi.models.AaiMessageBindings)
     */
    @Override
    public void visitMessageBindings(AaiMessageBindings node) {
        Object json = this.writeNullBindings();
        JsonCompat.setProperty(json, Constants.PROP_HTTP, node.http);
        JsonCompat.setProperty(json, Constants.PROP_WS, node.ws);
        JsonCompat.setProperty(json, Constants.PROP_KAFKA, node.kafka);
        JsonCompat.setProperty(json, Constants.PROP_AMQP, node.amqp);
        JsonCompat.setProperty(json, Constants.PROP_AMQP1, node.amqp1);
        JsonCompat.setProperty(json, Constants.PROP_MQTT, node.mqtt);
        JsonCompat.setProperty(json, Constants.PROP_MQTT5, node.mqtt5);
        JsonCompat.setProperty(json, Constants.PROP_NATS, node.nats);
        JsonCompat.setProperty(json, Constants.PROP_JMS, node.jms);
        JsonCompat.setProperty(json, Constants.PROP_SNS, node.sns);
        JsonCompat.setProperty(json, Constants.PROP_SQS, node.sqs);
        JsonCompat.setProperty(json, Constants.PROP_STOMP, node.stomp);
        JsonCompat.setProperty(json, Constants.PROP_REDIS, node.redis);

        // PROCESS PARENT
        Object parent = this.lookupParentJson(node);
        JsonCompat.setProperty(parent, Constants.PROP_BINDINGS, json);
        
        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitMessageBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiMessageBindingsDefinition)
     */
    @Override
    public void visitMessageBindingsDefinition(AaiMessageBindingsDefinition node) {
        Object json = this.writeNullBindings();
        JsonCompat.setProperty(json, Constants.PROP_HTTP, node.http);
        JsonCompat.setProperty(json, Constants.PROP_WS, node.ws);
        JsonCompat.setProperty(json, Constants.PROP_KAFKA, node.kafka);
        JsonCompat.setProperty(json, Constants.PROP_AMQP, node.amqp);
        JsonCompat.setProperty(json, Constants.PROP_AMQP1, node.amqp1);
        JsonCompat.setProperty(json, Constants.PROP_MQTT, node.mqtt);
        JsonCompat.setProperty(json, Constants.PROP_MQTT5, node.mqtt5);
        JsonCompat.setProperty(json, Constants.PROP_NATS, node.nats);
        JsonCompat.setProperty(json, Constants.PROP_JMS, node.jms);
        JsonCompat.setProperty(json, Constants.PROP_SNS, node.sns);
        JsonCompat.setProperty(json, Constants.PROP_SQS, node.sqs);
        JsonCompat.setProperty(json, Constants.PROP_STOMP, node.stomp);
        JsonCompat.setProperty(json, Constants.PROP_REDIS, node.redis);

        // PROCESS PARENT
        Object parent = this.lookupParentJson(node);
        Object map = JsonCompat.getProperty(parent, Constants.PROP_MESSAGE_BINDINGS);
        if (map == null) {
            map = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_MESSAGE_BINDINGS, map);
        }
        JsonCompat.setProperty(map, node.getName(), json);
        
        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitChannelBindings(io.apicurio.datamodels.asyncapi.models.AaiChannelBindings)
     */
    @Override
    public void visitChannelBindings(AaiChannelBindings node) {
        Object json = this.writeNullBindings();
        JsonCompat.setProperty(json, Constants.PROP_HTTP, node.http);
        JsonCompat.setProperty(json, Constants.PROP_WS, node.ws);
        JsonCompat.setProperty(json, Constants.PROP_KAFKA, node.kafka);
        JsonCompat.setProperty(json, Constants.PROP_AMQP, node.amqp);
        JsonCompat.setProperty(json, Constants.PROP_AMQP1, node.amqp1);
        JsonCompat.setProperty(json, Constants.PROP_MQTT, node.mqtt);
        JsonCompat.setProperty(json, Constants.PROP_MQTT5, node.mqtt5);
        JsonCompat.setProperty(json, Constants.PROP_NATS, node.nats);
        JsonCompat.setProperty(json, Constants.PROP_JMS, node.jms);
        JsonCompat.setProperty(json, Constants.PROP_SNS, node.sns);
        JsonCompat.setProperty(json, Constants.PROP_SQS, node.sqs);
        JsonCompat.setProperty(json, Constants.PROP_STOMP, node.stomp);
        JsonCompat.setProperty(json, Constants.PROP_REDIS, node.redis);

        // PROCESS PARENT
        Object parent = this.lookupParentJson(node);
        JsonCompat.setProperty(parent, Constants.PROP_BINDINGS, json);
        
        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitChannelBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiChannelBindingsDefinition)
     */
    @Override
    public void visitChannelBindingsDefinition(AaiChannelBindingsDefinition node) {
        Object json = this.writeNullBindings();
        JsonCompat.setProperty(json, Constants.PROP_HTTP, node.http);
        JsonCompat.setProperty(json, Constants.PROP_WS, node.ws);
        JsonCompat.setProperty(json, Constants.PROP_KAFKA, node.kafka);
        JsonCompat.setProperty(json, Constants.PROP_AMQP, node.amqp);
        JsonCompat.setProperty(json, Constants.PROP_AMQP1, node.amqp1);
        JsonCompat.setProperty(json, Constants.PROP_MQTT, node.mqtt);
        JsonCompat.setProperty(json, Constants.PROP_MQTT5, node.mqtt5);
        JsonCompat.setProperty(json, Constants.PROP_NATS, node.nats);
        JsonCompat.setProperty(json, Constants.PROP_JMS, node.jms);
        JsonCompat.setProperty(json, Constants.PROP_SNS, node.sns);
        JsonCompat.setProperty(json, Constants.PROP_SQS, node.sqs);
        JsonCompat.setProperty(json, Constants.PROP_STOMP, node.stomp);
        JsonCompat.setProperty(json, Constants.PROP_REDIS, node.redis);

        // PROCESS PARENT
        Object parent = this.lookupParentJson(node);
        Object map = JsonCompat.getProperty(parent, Constants.PROP_CHANNEL_BINDINGS);
        if (map == null) {
            map = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_CHANNEL_BINDINGS, map);
        }
        JsonCompat.setProperty(map, node.getName(), json);
        
        this.writeExtraProperties(json, node);
        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitAllOfSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitAllOfSchema(AaiSchema node) {
        this.doVisitSchema(node, Constants.PROP_ALL_OF, true);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitOneOfSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitOneOfSchema(AaiSchema node) {
        this.doVisitSchema(node, Constants.PROP_ONE_OF, true);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitAnyOfSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitAnyOfSchema(AaiSchema node) {
        this.doVisitSchema(node, Constants.PROP_ANY_OF, true);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitNotSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitNotSchema(AaiSchema node) {
        this.doVisitSchema(node, Constants.PROP_NOT, false);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitPropertySchema(io.apicurio.datamodels.asyncapi.models.IAaiPropertySchema)
     */
    @Override
    public void visitPropertySchema(IAaiPropertySchema node) {
        AaiSchema schema = (AaiSchema) node;
        Object parent = this.lookupParentJson(schema);
        Object json = JsonCompat.objectNode();
        writeSchema(json, schema);
        writeExtraProperties(json, schema);

        Object properties = JsonCompat.getProperty(parent, Constants.PROP_PROPERTIES);
        if (properties == null) {
            properties = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_PROPERTIES, properties);
        }

        JsonCompat.setProperty(properties, node.getPropertyName(), json);
        this.updateIndex(schema, json);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitItemsSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitItemsSchema(AaiSchema node) {
        AaiSchema parentSchema = (AaiSchema) node;
        this.doVisitSchema(node, Constants.PROP_ITEMS, NodeCompat.isList(parentSchema.items));
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitAdditionalPropertiesSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitAdditionalPropertiesSchema(AaiSchema node) {
        this.doVisitSchema(node, Constants.PROP_ADDITIONAL_PROPERTIES, false);
    }

    /**
     * @see io.apicurio.datamodels.core.io.DataModelWriter#writeSchema(java.lang.Object, io.apicurio.datamodels.core.models.common.Schema)
     */
    @Override
    protected void writeSchema(Object json, Schema node) {
        AaiSchema schema = (AaiSchema) node;
        JsonCompat.setPropertyString(json, Constants.PROP_$REF, schema.$ref);
        JsonCompat.setPropertyString(json, Constants.PROP_FORMAT, schema.format);
        JsonCompat.setPropertyString(json, Constants.PROP_TITLE, schema.title);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, schema.description);
        JsonCompat.setProperty(json, Constants.PROP_DEFAULT, schema.default_);

        JsonCompat.setPropertyNumber(json, Constants.PROP_MULTIPLE_OF, schema.multipleOf);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MAXIMUM, schema.maximum);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_EXCLUSIVE_MAXIMUM, schema.exclusiveMaximum);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MINIMUM, schema.minimum);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_EXCLUSIVE_MINIMUM, schema.exclusiveMinimum);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MAX_LENGTH, schema.maxLength);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MIN_LENGTH, schema.minLength);
        JsonCompat.setPropertyString(json, Constants.PROP_PATTERN, schema.pattern);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MAX_ITEMS, schema.maxItems);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MIN_ITEMS, schema.minItems);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_UNIQUE_ITEMS, schema.uniqueItems);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MAX_PROPERTIES, schema.maxProperties);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MIN_PROPERTIES, schema.minProperties);
        JsonCompat.setPropertyStringArray(json, Constants.PROP_REQUIRED, schema.required);
        if (schema.enum_ != null) {
            for (Object enumValue : schema.enum_) {
                JsonCompat.appendToArrayProperty(json, Constants.PROP_ENUM, enumValue);
            }
        }
        JsonCompat.setPropertyString(json, Constants.PROP_TYPE, schema.type);

        JsonCompat.setPropertyNull(json, Constants.PROP_ITEMS);
        JsonCompat.setPropertyNull(json, Constants.PROP_ALL_OF);
        JsonCompat.setPropertyNull(json, Constants.PROP_ONE_OF);
        JsonCompat.setPropertyNull(json, Constants.PROP_ANY_OF);
        JsonCompat.setPropertyNull(json, Constants.PROP_NOT);
        JsonCompat.setPropertyNull(json, Constants.PROP_PROPERTIES);
        if (NodeCompat.isNode(schema.additionalProperties)) {
            JsonCompat.setPropertyNull(json, Constants.PROP_ADDITIONAL_PROPERTIES);
        } else {
            JsonCompat.setPropertyBoolean(json, Constants.PROP_ADDITIONAL_PROPERTIES, (Boolean) schema.additionalProperties);
        }

        JsonCompat.setPropertyBoolean(json, Constants.PROP_READ_ONLY, schema.readOnly);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_WRITE_ONLY, schema.writeOnly);
        JsonCompat.setPropertyString(json, Constants.PROP_DISCRIMINATOR, schema.discriminator);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_DEPRECATED, schema.deprecated);

        JsonCompat.setPropertyNull(json, Constants.PROP_EXTERNAL_DOCS);
        JsonCompat.setProperty(json, Constants.PROP_EXAMPLE, schema.example);

        super.writeSchema(json, schema);
    }

    /**
     * Writes a Server/Channel/Operation/Message Bindings model to JSON.
     * @param node
     */
    protected Object writeNullBindings() {
        Object json = JsonCompat.objectNode();

        JsonCompat.setPropertyNull(json, Constants.PROP_HTTP);
        JsonCompat.setPropertyNull(json, Constants.PROP_WS);
        JsonCompat.setPropertyNull(json, Constants.PROP_KAFKA);
        JsonCompat.setPropertyNull(json, Constants.PROP_AMQP);
        JsonCompat.setPropertyNull(json, Constants.PROP_AMQP1);
        JsonCompat.setPropertyNull(json, Constants.PROP_MQTT);
        JsonCompat.setPropertyNull(json, Constants.PROP_MQTT5);
        JsonCompat.setPropertyNull(json, Constants.PROP_NATS);
        JsonCompat.setPropertyNull(json, Constants.PROP_JMS);
        JsonCompat.setPropertyNull(json, Constants.PROP_SNS);
        JsonCompat.setPropertyNull(json, Constants.PROP_SQS);
        JsonCompat.setPropertyNull(json, Constants.PROP_STOMP);
        JsonCompat.setPropertyNull(json, Constants.PROP_REDIS);

        return json;
    }
}
