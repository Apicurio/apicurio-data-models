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

import java.util.List;

import io.apicurio.datamodels.asyncapi.models.AaiChannelBindings;
import io.apicurio.datamodels.asyncapi.models.AaiChannelBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiComponents;
import io.apicurio.datamodels.asyncapi.models.AaiCorrelationId;
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.models.AaiExternalDocumentation;
import io.apicurio.datamodels.asyncapi.models.AaiHeaderItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBase;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBindings;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiOAuthFlows;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBase;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBindings;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiParameter;
import io.apicurio.datamodels.asyncapi.models.AaiSecurityRequirement;
import io.apicurio.datamodels.asyncapi.models.AaiSecurityScheme;
import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindings;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiServerVariable;
import io.apicurio.datamodels.asyncapi.models.AaiTag;
import io.apicurio.datamodels.asyncapi.models.IAaiNodeFactory;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.io.DataModelReader;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.models.common.OAuthFlows;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;

/**
 * A data model reader for the AsyncAPI data model.
 *
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiDataModelReader extends DataModelReader {

    private final IAaiNodeFactory nodeFactory;

    public AaiDataModelReader(IAaiNodeFactory nodeFactory) {
        this.nodeFactory = nodeFactory;
    }

    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readDocument(java.lang.Object, io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void readDocument(Object json, Document node) {
        AaiDocument doc = (AaiDocument) node;

        doc.asyncapi = JsonCompat.consumePropertyString(json, Constants.PROP_ASYNCAPI);
        doc.id = JsonCompat.consumePropertyString(json, Constants.PROP_ID);
        doc.defaultContentType = JsonCompat.consumePropertyString(json, Constants.PROP_DEFAULT_CONTENT_TYPE);

        // channels
        Object channels = JsonCompat.consumeProperty(json, Constants.PROP_CHANNELS);
        if (channels != null) {
            JsonCompat.keys(channels).forEach(key -> {
                Object json_ = JsonCompat.consumeProperty(channels, key);
                AaiChannelItem value = nodeFactory.createChannelItem(node, key);
                this.readChannelItem(json_, value);
                doc.addChannelItem(value);
            });
        }

        // servers
        Object serversJson = JsonCompat.consumeProperty(json, Constants.PROP_SERVERS);
        if (serversJson != null) {
            JsonCompat.keys(serversJson).forEach(key -> {
                Object serverJson = JsonCompat.consumeProperty(serversJson, key);
                AaiServer serverModel = nodeFactory.createServer(node, key);
                this.readServer(serverJson, serverModel);
                doc.addServer(key, serverModel);
            });
        }

        // components
        Object json_ = JsonCompat.consumeProperty(json, Constants.PROP_COMPONENTS);
        if (json_ != null) {
            AaiComponents components = nodeFactory.createComponents(node);
            this.readComponents(json_, components);
            doc.components = components;
        }

        super.readDocument(json, doc);
    }


    public void readChannelItem(Object json, AaiChannelItem node) {
        node.$ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
        node.description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);

        // subscribe
        Object json_ = JsonCompat.consumeProperty(json, Constants.PROP_SUBSCRIBE);
        if (json_ != null) {
            AaiOperation operation = nodeFactory.createOperation(node, Constants.PROP_SUBSCRIBE);
            this.readOperation(json_, operation);
            node.subscribe = operation;
        }

        // publish
        json_ = JsonCompat.consumeProperty(json, Constants.PROP_PUBLISH);
        if (json_ != null) {
            AaiOperation operation = nodeFactory.createOperation(node, Constants.PROP_PUBLISH);
            this.readOperation(json_, operation);
            node.publish = operation;
        }

        // parameters
        final Object jsonP_ = JsonCompat.consumeProperty(json, Constants.PROP_PARAMETERS);
        if (jsonP_ != null && JsonCompat.isObject(jsonP_)) {
            JsonCompat.keys(jsonP_).forEach(key -> {
                Object param = JsonCompat.consumeProperty(jsonP_, key);
                AaiParameter paramModel = nodeFactory.createParameter(node, key);
                this.readAaiParameter(param, paramModel);
                node.addParameter(key, paramModel);
            });
        }

        // bindings
        json_ = JsonCompat.consumeProperty(json, Constants.PROP_BINDINGS);
        if (json_ != null) {
            AaiChannelBindings channelBindingsModel = nodeFactory.createChannelBindings(node);
            this.readChannelBindings(json_, channelBindingsModel);
            node.bindings = channelBindingsModel;
        }

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readServer(java.lang.Object, io.apicurio.datamodels.core.models.common.Server)
     */
    @Override
    public void readServer(Object json, Server node) {
        AaiServer aaiNode = (AaiServer) node;

        aaiNode.protocol = JsonCompat.consumePropertyString(json, Constants.PROP_PROTOCOL);
        aaiNode.protocolVersion = JsonCompat.consumePropertyString(json, Constants.PROP_PROTOCOL_VERSION);

        List<Object> security = JsonCompat.consumePropertyArray(json, Constants.PROP_SECURITY);
        if (security != null) {
            security.forEach(sec -> {
                AaiSecurityRequirement secModel = nodeFactory.createSecurityRequirement(node);
                this.readSecurityRequirement(sec, secModel);
                aaiNode.addSecurityRequirement(secModel);
            });
        }
        
        Object bindings = JsonCompat.consumeProperty(json, Constants.PROP_BINDINGS);
        if (bindings != null) {
            AaiServerBindings serverBindingsModel = nodeFactory.createServerBindings(node);
            this.readServerBindings(bindings, serverBindingsModel);
            aaiNode.bindings = serverBindingsModel;
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

    @Override
    public void readOperation(Object json, Operation node) {
        AaiOperation aaiNode = (AaiOperation) node;
        // traits
        List<Object> traits = JsonCompat.consumePropertyArray(json, Constants.PROP_TRAITS);
        if (traits != null) {
            traits.forEach(trait -> {
                AaiOperationTrait traitModel = nodeFactory.createOperationTrait(node, null);
                this.readOperationTrait(trait, traitModel);
                aaiNode.addTrait(traitModel);
            });
        }

        // message
        Object json_ = JsonCompat.consumeProperty(json, Constants.PROP_MESSAGE);
        if (json_ != null) {
            AaiMessage value = nodeFactory.createMessage(node, null);
            this.readMessage(json_, value);
            aaiNode.message = value;
        }
        this.readOperationBase(json, aaiNode);
    }

    public void readAaiParameter(Object json, AaiParameter node) {
        String $ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
        String description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
        Object schema = JsonCompat.consumeProperty(json, Constants.PROP_SCHEMA);
        String location = JsonCompat.consumePropertyString(json, Constants.PROP_LOCATION);

        node.$ref = $ref;
        node.description = description;
        node.schema = schema;
        node.location = location;

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    public void readMessage(Object json, AaiMessage node) {
        // payload
        Object jPayload = JsonCompat.consumeProperty(json, Constants.PROP_PAYLOAD);
        if (jPayload != null) {
            node.payload = jPayload;
        }
        // traits
        List<Object> traits = JsonCompat.consumePropertyArray(json, Constants.PROP_TRAITS);
        if (traits != null) {
            traits.forEach(trait -> {
                AaiMessageTrait traitModel = nodeFactory.createMessageTrait(node, null);
                this.readMessageTrait(trait, traitModel);
                node.addTrait(traitModel);
            });
        }

        // Process a "oneOf" message
        List<Object> oneOfJson = JsonCompat.consumePropertyArray(json, Constants.PROP_ONE_OF);
        if (oneOfJson != null) {
            for(Object itemJson: oneOfJson) {
                AaiMessage item = nodeFactory.createMessage(node, null);
                this.readMessage(itemJson, item);
                item._isOneOfMessage = true;
                node.addOneOfMessage(item);
            }
        }

        this.readMessageBase(json, node);
    }

    public void readMessageBase(Object json, AaiMessageBase node) {
        // headers
        Object jsonHeaders = JsonCompat.consumeProperty(json, Constants.PROP_HEADERS);
        if (jsonHeaders != null) {
            AaiHeaderItem headerModel = nodeFactory.createHeaderItem(node);
            this.readHeaderItem(jsonHeaders, headerModel);
            node.headers = headerModel;
        }
        // correlationId
        Object jsonCI = JsonCompat.consumeProperty(json, Constants.PROP_CORRELATION_ID);
        if (jsonCI != null) {
            AaiCorrelationId value = nodeFactory.createCorrelationId(node, null);
            this.readCorrelationId(jsonCI, value);
            node.correlationId = value;
        }
        // String properties
        node.schemaFormat = JsonCompat.consumePropertyString(json, Constants.PROP_SCHEMA_FORMAT);
        node.contentType = JsonCompat.consumePropertyString(json, Constants.PROP_CONTENT_TYPE);
        node.name = JsonCompat.consumePropertyString(json, Constants.PROP_NAME);
        node.title = JsonCompat.consumePropertyString(json, Constants.PROP_TITLE);
        node.summary = JsonCompat.consumePropertyString(json, Constants.PROP_SUMMARY);
        node.description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
        node.$ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
        // tags
        List<Object> jsonTags = JsonCompat.consumePropertyArray(json, Constants.PROP_TAGS);
        if (jsonTags != null) {
            jsonTags.forEach(j -> {
                AaiTag tag = nodeFactory.createTag(node);
                this.readTag(j, tag);
                node.addTag(tag);
            });
        }
        // external docs
        Object jsonED = JsonCompat.consumeProperty(json, Constants.PROP_EXTERNAL_DOCS);
        if (jsonED != null) {
            AaiExternalDocumentation value = nodeFactory.createExternalDocumentation(node);
            this.readExternalDocumentation(jsonED, value);
            node.externalDocs = value;
        }

        // bindings
        Object bindings = JsonCompat.consumeProperty(json, Constants.PROP_BINDINGS);
        if (bindings != null) {
            AaiMessageBindings bindingsModel = nodeFactory.createMessageBindings(node);
            this.readMessageBindings(bindings, bindingsModel);
            node.bindings = bindingsModel;
        }

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    public void readCorrelationId(Object json, AaiCorrelationId node) {
        // String properties
        node.$ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
        node.location = JsonCompat.consumePropertyString(json, Constants.PROP_LOCATION);
        node.description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    public void readHeaderItem(Object json, AaiHeaderItem node) {
        String ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
        if (ref != null) {
            node.$ref = ref;
        } else {
            node._schemaRaw = json;
        }
    }

    public void readMessageTrait(Object json, AaiMessageTrait node) {
        this.readMessageBase(json, node);
    }

    public void readOperationBase(Object json, AaiOperationBase node) {
        node.$ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
        // tags
        List<Object> jsonTags = JsonCompat.consumePropertyArray(json, Constants.PROP_TAGS);
        if (jsonTags != null) {
            jsonTags.forEach(j -> {
                AaiTag tag = nodeFactory.createTag(node);
                this.readTag(j, tag);
                node.addTag(tag);
            });
        }
        
        // bindings
        Object bindings = JsonCompat.consumeProperty(json, Constants.PROP_BINDINGS);
        if (bindings != null) {
            AaiOperationBindings operationBindingsModel = nodeFactory.createOperationBindings(node);
            this.readOperationBindings(bindings, operationBindingsModel);
            node.bindings = operationBindingsModel;
        }
        
        super.readOperation(json, node);
    }

    public void readOperationTrait(Object json, AaiOperationTrait node) {
        this.readOperationBase(json, node);
    }

    public void readComponents(Object json, Components node) {
        AaiComponents components = (AaiComponents) node;
        // schemas
        final Object _jsonSchemas = JsonCompat.consumeProperty(json, Constants.PROP_SCHEMAS);
        if (_jsonSchemas != null) {
            JsonCompat.keys(_jsonSchemas).forEach(key -> {
                Object value = JsonCompat.consumeProperty(_jsonSchemas, key);
                components.addSchema(key, value);
            });
        }
        // messages
        final Object _jsonMessages = JsonCompat.consumeProperty(json, Constants.PROP_MESSAGES);
        if (_jsonMessages != null) {
            JsonCompat.keys(_jsonMessages).forEach(key -> {
                Object jsonValue = JsonCompat.consumeProperty(_jsonMessages, key);
                AaiMessage value = nodeFactory.createMessage(node, key);
                this.readMessage(jsonValue, value);
                components.addMessage(key, value);
            });
        }
        // security schemes
        final Object _jsonSecuritySchemes = JsonCompat.consumeProperty(json, Constants.PROP_SECURITY_SCHEMES);
        if (_jsonSecuritySchemes != null) {
            JsonCompat.keys(_jsonSecuritySchemes).forEach(key -> {
                Object jsonValue = JsonCompat.consumeProperty(_jsonSecuritySchemes, key);
                AaiSecurityScheme value = nodeFactory.createSecurityScheme(node, key);
                this.readSecurityScheme(jsonValue, value);
                components.addSecurityScheme(key, value);
            });
        }
        // parameters
        final Object _jsonParams = JsonCompat.consumeProperty(json, Constants.PROP_PARAMETERS);
        if (_jsonParams != null) {
            JsonCompat.keys(_jsonParams).forEach(key -> {
                Object jsonValue = JsonCompat.consumeProperty(_jsonParams, key);
                AaiParameter value = nodeFactory.createParameter(node, key);
                this.readAaiParameter(jsonValue, value);
                components.addParameter(key, value);
            });
        }
        // correlationIds
        final Object _jsonCorrelationIds = JsonCompat.consumeProperty(json, Constants.PROP_CORRELATION_IDS);
        if (_jsonCorrelationIds != null) {
            JsonCompat.keys(_jsonCorrelationIds).forEach(key -> {
                Object jsonValue = JsonCompat.consumeProperty(_jsonCorrelationIds, key);
                AaiCorrelationId value = nodeFactory.createCorrelationId(node, key);
                this.readCorrelationId(jsonValue, value);
                components.addCorrelationId(key, value);
            });
        }
        
        // operation traits
        final Object _jsonOpTraits = JsonCompat.consumeProperty(json, Constants.PROP_OPERATION_TRAITS);
        if (_jsonOpTraits != null) {
            JsonCompat.keys(_jsonOpTraits).forEach(key -> {
                Object jsonValue = JsonCompat.consumeProperty(_jsonOpTraits, key);
                AaiOperationTraitDefinition traitDef = nodeFactory.createOperationTraitDefinition(node, key);
                this.readOperationTrait(jsonValue, traitDef);
                components.addOperationTraitDefinition(key, traitDef);
            });
        }

        // message traits
        final Object _jsonMessageTraits = JsonCompat.consumeProperty(json, Constants.PROP_MESSAGE_TRAITS);
        if (_jsonMessageTraits != null) {
            JsonCompat.keys(_jsonMessageTraits).forEach(key -> {
                Object jsonValue = JsonCompat.consumeProperty(_jsonMessageTraits, key);
                AaiMessageTraitDefinition traitDef = nodeFactory.createMessageTraitDefinition(node, key);
                this.readMessageTrait(jsonValue, traitDef);
                components.addMessageTraitDefinition(key, traitDef);
            });
        }

        // server bindings
        final Object _jsonServerBindings = JsonCompat.consumeProperty(json, Constants.PROP_SERVER_BINDINGS);
        if (_jsonServerBindings != null) {
            JsonCompat.keys(_jsonServerBindings).forEach(key -> {
                Object jsonValue = JsonCompat.consumeProperty(_jsonServerBindings, key);
                AaiServerBindingsDefinition bindingsDef = nodeFactory.createServerBindingsDefinition(node, key);
                this.readServerBindings(jsonValue, bindingsDef);
                components.addServerBindingDefinition(key, bindingsDef);
            });
        }

        // channel bindings
        final Object _jsonChannelBindings = JsonCompat.consumeProperty(json, Constants.PROP_CHANNEL_BINDINGS);
        if (_jsonChannelBindings != null) {
            JsonCompat.keys(_jsonChannelBindings).forEach(key -> {
                Object jsonValue = JsonCompat.consumeProperty(_jsonChannelBindings, key);
                AaiChannelBindingsDefinition bindingsDef = nodeFactory.createChannelBindingsDefinition(node, key);
                this.readChannelBindings(jsonValue, bindingsDef);
                components.addChannelBindingDefinition(key, bindingsDef);
            });
        }

        // operation bindings
        final Object _jsonOpBindings = JsonCompat.consumeProperty(json, Constants.PROP_OPERATION_BINDINGS);
        if (_jsonOpBindings != null) {
            JsonCompat.keys(_jsonOpBindings).forEach(key -> {
                Object jsonValue = JsonCompat.consumeProperty(_jsonOpBindings, key);
                AaiOperationBindingsDefinition bindingsDef = nodeFactory.createOperationBindingsDefinition(node, key);
                this.readOperationBindings(jsonValue, bindingsDef);
                components.addOperationBindingDefinition(key, bindingsDef);
            });
        }

        // message bindings
        final Object _jsonMessageBindings = JsonCompat.consumeProperty(json, Constants.PROP_MESSAGE_BINDINGS);
        if (_jsonMessageBindings != null) {
            JsonCompat.keys(_jsonMessageBindings).forEach(key -> {
                Object jsonValue = JsonCompat.consumeProperty(_jsonMessageBindings, key);
                AaiMessageBindingsDefinition bindingsDef = nodeFactory.createMessageBindingsDefinition(node, key);
                this.readMessageBindings(jsonValue, bindingsDef);
                components.addMessageBindingDefinition(key, bindingsDef);
            });
        }

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    @Override
    public void readSecurityScheme(Object json, SecurityScheme node) {
        AaiSecurityScheme aaiNode = (AaiSecurityScheme) node;
        // $ref
        aaiNode.$ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
        // scheme
        aaiNode.scheme = JsonCompat.consumePropertyString(json, Constants.PROP_SCHEME);
        // flows
        Object jsonFlows = JsonCompat.consumeProperty(json, Constants.PROP_FLOWS);
        if (jsonFlows != null) {
            AaiOAuthFlows value = nodeFactory.createOAuthFlows(node);
            this.readOAuthFlows(jsonFlows, value);
            aaiNode.flows = value;
        }
        // openIdConnectUrl & bearerFormat
        aaiNode.openIdConnectUrl = JsonCompat.consumePropertyString(json, Constants.PROP_OPEN_ID_CONNECT_URL);
        aaiNode.bearerFormat = JsonCompat.consumePropertyString(json, Constants.PROP_BEARER_FORMAT);

        super.readSecurityScheme(json, aaiNode);
    }

    @Override
    public void readOAuthFlows(Object json, OAuthFlows node) {
        super.readOAuthFlows(json, node);
    }
    
    /**
     * Reads a channel bindings model.
     * @param json
     * @param node
     */
    public void readChannelBindings(Object json, AaiChannelBindings node) {
        node.http = JsonCompat.consumeProperty(json, Constants.PROP_HTTP);
        node.ws = JsonCompat.consumeProperty(json, Constants.PROP_WS);
        node.kafka = JsonCompat.consumeProperty(json, Constants.PROP_KAFKA);
        node.amqp = JsonCompat.consumeProperty(json, Constants.PROP_AMQP);
        node.amqp1 = JsonCompat.consumeProperty(json, Constants.PROP_AMQP1);
        node.mqtt = JsonCompat.consumeProperty(json, Constants.PROP_MQTT);
        node.mqtt5 = JsonCompat.consumeProperty(json, Constants.PROP_MQTT5);
        node.nats = JsonCompat.consumeProperty(json, Constants.PROP_NATS);
        node.jms = JsonCompat.consumeProperty(json, Constants.PROP_JMS);
        node.sns = JsonCompat.consumeProperty(json, Constants.PROP_SNS);
        node.sqs = JsonCompat.consumeProperty(json, Constants.PROP_SQS);
        node.stomp = JsonCompat.consumeProperty(json, Constants.PROP_STOMP);
        node.redis = JsonCompat.consumeProperty(json, Constants.PROP_REDIS);
        
        this.readExtraProperties(json, node);
    }
    
    /**
     * Reads a message bindings model.
     * @param json
     * @param node
     */
    public void readMessageBindings(Object json, AaiMessageBindings node) {
        node.http = JsonCompat.consumeProperty(json, Constants.PROP_HTTP);
        node.ws = JsonCompat.consumeProperty(json, Constants.PROP_WS);
        node.kafka = JsonCompat.consumeProperty(json, Constants.PROP_KAFKA);
        node.amqp = JsonCompat.consumeProperty(json, Constants.PROP_AMQP);
        node.amqp1 = JsonCompat.consumeProperty(json, Constants.PROP_AMQP1);
        node.mqtt = JsonCompat.consumeProperty(json, Constants.PROP_MQTT);
        node.mqtt5 = JsonCompat.consumeProperty(json, Constants.PROP_MQTT5);
        node.nats = JsonCompat.consumeProperty(json, Constants.PROP_NATS);
        node.jms = JsonCompat.consumeProperty(json, Constants.PROP_JMS);
        node.sns = JsonCompat.consumeProperty(json, Constants.PROP_SNS);
        node.sqs = JsonCompat.consumeProperty(json, Constants.PROP_SQS);
        node.stomp = JsonCompat.consumeProperty(json, Constants.PROP_STOMP);
        node.redis = JsonCompat.consumeProperty(json, Constants.PROP_REDIS);
        
        this.readExtraProperties(json, node);
    }
    
    /**
     * Reads a operation bindings model.
     * @param json
     * @param node
     */
    public void readOperationBindings(Object json, AaiOperationBindings node) {
        node.http = JsonCompat.consumeProperty(json, Constants.PROP_HTTP);
        node.ws = JsonCompat.consumeProperty(json, Constants.PROP_WS);
        node.kafka = JsonCompat.consumeProperty(json, Constants.PROP_KAFKA);
        node.amqp = JsonCompat.consumeProperty(json, Constants.PROP_AMQP);
        node.amqp1 = JsonCompat.consumeProperty(json, Constants.PROP_AMQP1);
        node.mqtt = JsonCompat.consumeProperty(json, Constants.PROP_MQTT);
        node.mqtt5 = JsonCompat.consumeProperty(json, Constants.PROP_MQTT5);
        node.nats = JsonCompat.consumeProperty(json, Constants.PROP_NATS);
        node.jms = JsonCompat.consumeProperty(json, Constants.PROP_JMS);
        node.sns = JsonCompat.consumeProperty(json, Constants.PROP_SNS);
        node.sqs = JsonCompat.consumeProperty(json, Constants.PROP_SQS);
        node.stomp = JsonCompat.consumeProperty(json, Constants.PROP_STOMP);
        node.redis = JsonCompat.consumeProperty(json, Constants.PROP_REDIS);
        
        this.readExtraProperties(json, node);
    }
    
    /**
     * Reads a server bindings model.
     * @param json
     * @param node
     */
    public void readServerBindings(Object json, AaiServerBindings node) {
        node.http = JsonCompat.consumeProperty(json, Constants.PROP_HTTP);
        node.ws = JsonCompat.consumeProperty(json, Constants.PROP_WS);
        node.kafka = JsonCompat.consumeProperty(json, Constants.PROP_KAFKA);
        node.amqp = JsonCompat.consumeProperty(json, Constants.PROP_AMQP);
        node.amqp1 = JsonCompat.consumeProperty(json, Constants.PROP_AMQP1);
        node.mqtt = JsonCompat.consumeProperty(json, Constants.PROP_MQTT);
        node.mqtt5 = JsonCompat.consumeProperty(json, Constants.PROP_MQTT5);
        node.nats = JsonCompat.consumeProperty(json, Constants.PROP_NATS);
        node.jms = JsonCompat.consumeProperty(json, Constants.PROP_JMS);
        node.sns = JsonCompat.consumeProperty(json, Constants.PROP_SNS);
        node.sqs = JsonCompat.consumeProperty(json, Constants.PROP_SQS);
        node.stomp = JsonCompat.consumeProperty(json, Constants.PROP_STOMP);
        node.redis = JsonCompat.consumeProperty(json, Constants.PROP_REDIS);
        
        this.readExtraProperties(json, node);
    }

}
