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

import java.util.Arrays;
import java.util.List;

import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiComponents;
import io.apicurio.datamodels.asyncapi.models.AaiCorrelationId;
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.models.AaiExternalDocumentation;
import io.apicurio.datamodels.asyncapi.models.AaiHeaderItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBase;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitExtendedItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitItems;
import io.apicurio.datamodels.asyncapi.models.AaiOAuthFlows;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBase;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitExtendedItem;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitItems;
import io.apicurio.datamodels.asyncapi.models.AaiParameter;
import io.apicurio.datamodels.asyncapi.models.AaiProtocolInfo;
import io.apicurio.datamodels.asyncapi.models.AaiSecurityRequirement;
import io.apicurio.datamodels.asyncapi.models.AaiSecurityScheme;
import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.asyncapi.models.AaiServerVariable;
import io.apicurio.datamodels.asyncapi.models.AaiTag;
import io.apicurio.datamodels.asyncapi.models.AaiTraitType;
import io.apicurio.datamodels.asyncapi.models.AaiUnknownTrait;
import io.apicurio.datamodels.asyncapi.models.IAaiNodeFactory;
import io.apicurio.datamodels.asyncapi.models.IAaiTrait;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.io.DataModelReader;
import io.apicurio.datamodels.core.models.Document;
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
        List<Object> servers = JsonCompat.consumePropertyArray(json, Constants.PROP_SERVERS);
        if (servers != null) {
            servers.forEach(server -> {
                AaiServer serverModel = nodeFactory.createServer(node);
                this.readServer(server, serverModel);
                doc.addServer(serverModel);
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
        List<Object> parameters = JsonCompat.consumePropertyArray(json, Constants.PROP_PARAMETERS);
        if (parameters != null) {
            parameters.forEach(server -> {
                AaiParameter serverModel = nodeFactory.createParameter(node, null);
                this.readAaiParameter(server, serverModel);
                node.addParameter(serverModel);
            });
        }

        // protocol info
        Object pi = JsonCompat.consumeProperty(json, Constants.PROP_PROTOCOL_INFO);
        if (pi != null) {
            JsonCompat.keys(pi).forEach(key -> {
                Object j = JsonCompat.consumeProperty(pi, key);
                AaiProtocolInfo value = nodeFactory.createProtocolInfo(node, key);
                this.readProtocolInfo(j, value);
                node.addProtocolInfo(key, value);
            });
        }

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }


    public void readProtocolInfo(Object json, AaiProtocolInfo node) {
        JsonCompat.keys(json).forEach(key -> {
            Object value = JsonCompat.consumeProperty(json, key);
            node.addItem(key, value);
        });

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
        aaiNode.baseChannel = JsonCompat.consumePropertyString(json, Constants.PROP_BASE_CHANNEL);

        List<Object> security = JsonCompat.consumePropertyArray(json, Constants.PROP_SECURITY);
        if (security != null) {
            security.forEach(sec -> {
                AaiSecurityRequirement secModel = nodeFactory.createSecurityRequirement(node);
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

    @Override
    public void readOperation(Object json, Operation node) {
        AaiOperation aaiNode = (AaiOperation) node;
        // traits
        List<Object> traits = JsonCompat.consumePropertyArray(json, Constants.PROP_TRAITS);
        if (traits != null) {
            AaiOperationTraitItems items = nodeFactory.createOperationTraitItems(node);
            this.readOperationTraitItems(traits, items);
            aaiNode.traits = items;
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

    public void readMessage(Object json, AaiMessage node) {
        // payload
        Object jPayload = JsonCompat.consumeProperty(json, Constants.PROP_PAYLOAD);
        if (jPayload != null) {
            node.payload = jPayload;
        }
        // traits
        List<Object> traits = JsonCompat.consumePropertyArray(json, Constants.PROP_TRAITS);
        if (traits != null) {
            AaiMessageTraitItems items = nodeFactory.createMessageTraitItems(node);
            this.readMessageTraitItems(traits, items);
            node.traits = items;
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

    public void readMessageTraitItems(List<Object> json, AaiMessageTraitItems node) {
        if (json != null) {
            json.forEach(e -> {
                if (JsonCompat.isArray(e)) { // extended
                    AaiMessageTraitExtendedItem value = nodeFactory.createMessageTraitExtendedItem(node);
                    this.readMessageTraitExtendedItem(JsonCompat.toList(e), value);
                    node.addExtendedItem(value);
                } else {
                    AaiMessageTrait value = nodeFactory.createMessageTrait(node, null);
                    this.readMessageTrait(e, value);
                    node.addItem(value);
                }
            });
        }
    }

    public void readMessageBase(Object json, AaiMessageBase node) {
        // headers
        Object jsonHeaders = JsonCompat.consumeProperty(json, Constants.PROP_HEADERS);
        if (jsonHeaders != null) {
            JsonCompat.keys(jsonHeaders).forEach(key -> {
                Object j = JsonCompat.consumeProperty(jsonHeaders, key);
                AaiHeaderItem value = nodeFactory.createHeaderItem(node, key);
                this.readHeaderItem(j, value);
                node.addHeaderItem(value);
            });
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
        // protocol info
        Object pi = JsonCompat.consumeProperty(json, Constants.PROP_PROTOCOL_INFO);
        if (pi != null) {
            JsonCompat.keys(pi).forEach(key -> {
                Object j = JsonCompat.consumeProperty(pi, key);
                AaiProtocolInfo value = nodeFactory.createProtocolInfo(node, key);
                this.readProtocolInfo(j, value);
                node.addProtocolInfo(value);
            });
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

    public void readMessageTraitExtendedItem(List<Object> json, AaiMessageTraitExtendedItem node) {
        Object jsonTrait = json.get(0);
        if (jsonTrait != null) {
            AaiMessageTrait value = nodeFactory.createMessageTrait(node, null);
            this.readMessageTrait(jsonTrait, value);
            node._trait = value;
        }
        Object jsonExt = json.get(1);
        if (jsonExt != null) {
            JsonCompat.keys(jsonExt).forEach(key -> {
                Object value = JsonCompat.consumeProperty(jsonExt, key);
                node.addExtension(key, value);
            });
        }
    }

    public void readOperationTraitItems(List<Object> json, AaiOperationTraitItems node) {
        if (json != null) {
            json.forEach(e -> {
                if (JsonCompat.isArray(e)) { // extended
                    AaiOperationTraitExtendedItem value = nodeFactory.createOperationTraitExtendedItem(node, null);
                    this.readOperationTraitExtendedItem(JsonCompat.toList(e), value);
                    node.addExtendedItem(value);
                } else {
                    AaiOperationTrait value = nodeFactory.createOperationTrait(node, null);
                    this.readOperationTrait(e, value);
                    node.addItem(value);
                }
            });
        }
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
        // protocol info
        Object pi = JsonCompat.consumeProperty(json, Constants.PROP_PROTOCOL_INFO);
        if (pi != null) {
            JsonCompat.keys(pi).forEach(key -> {
                Object j = JsonCompat.consumeProperty(pi, key);
                AaiProtocolInfo value = nodeFactory.createProtocolInfo(node, key);
                this.readProtocolInfo(j, value);
                node.addProtocolInfo(value);
            });
        }
        super.readOperation(json, node);
    }
    
    public void readUnknownTrait(Object json, AaiUnknownTrait node) {
        String summary = JsonCompat.consumePropertyString(json, Constants.PROP_SUMMARY);
        String description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
        Object externalDocs = JsonCompat.consumeProperty(json, Constants.PROP_EXTERNAL_DOCS);
        
        node.summary = summary;
        node.description = description;

        if (externalDocs != null) {
            node.externalDocs = node.createExternalDocumentation();
            this.readExternalDocumentation(externalDocs, node.externalDocs);
        }
        
        // tags
        List<Object> jsonTags = JsonCompat.consumePropertyArray(json, Constants.PROP_TAGS);
        if (jsonTags != null) {
            jsonTags.forEach(j -> {
                AaiTag tag = nodeFactory.createTag(node);
                this.readTag(j, tag);
                node.addTag(tag);
            });
        }
        // protocol info
        Object pi = JsonCompat.consumeProperty(json, Constants.PROP_PROTOCOL_INFO);
        if (pi != null) {
            JsonCompat.keys(pi).forEach(key -> {
                Object j = JsonCompat.consumeProperty(pi, key);
                AaiProtocolInfo value = nodeFactory.createProtocolInfo(node, key);
                this.readProtocolInfo(j, value);
                node.addProtocolInfo(value);
            });
        }

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    public void readOperationTrait(Object json, AaiOperationTrait node) {
        this.readOperationBase(json, node);
    }

    public void readOperationTraitExtendedItem(List<Object> json, AaiOperationTraitExtendedItem node) {
        Object jsonTrait = json.get(0);
        if (jsonTrait != null) {
            AaiOperationTrait value = nodeFactory.createOperationTrait(node, null);
            this.readOperationTrait(jsonTrait, value);
            node._operationTrait = value;
        }
        Object jsonExt = json.get(1);
        if (jsonExt != null) {
            JsonCompat.keys(jsonExt).forEach(key -> {
                Object value = JsonCompat.consumeProperty(jsonExt, key);
                node.addExtension(key, value);
            });
        }
    }

    public void readComponents(Object json, AaiComponents node) {
        // schemas
        Object jsonSch = JsonCompat.consumeProperty(json, Constants.PROP_SCHEMAS);
        if (jsonSch != null) {
            JsonCompat.keys(jsonSch).forEach(key -> {
                Object value = JsonCompat.consumeProperty(jsonSch, key);
                node.addSchema(key, value);
            });
        }
        // messages
        Object jsonM = JsonCompat.consumeProperty(json, Constants.PROP_MESSAGES);
        if (jsonM != null) {
            JsonCompat.keys(jsonM).forEach(key -> {
                Object jsonValue = JsonCompat.consumeProperty(jsonM, key);
                AaiMessage value = nodeFactory.createMessage(node, key);
                this.readMessage(jsonValue, value);
                node.addMessage(key, value);
            });
        }
        // security schemes
        Object jsonSS = JsonCompat.consumeProperty(json, Constants.PROP_SECURITY_SCHEMES);
        if (jsonSS != null) {
            JsonCompat.keys(jsonSS).forEach(key -> {
                Object jsonValue = JsonCompat.consumeProperty(jsonSS, key);
                AaiSecurityScheme value = nodeFactory.createSecurityScheme(node, key);
                this.readSecurityScheme(jsonValue, value);
                node.addSecurityScheme(key, value);
            });
        }
        // parameters
        Object jsonParameters = JsonCompat.consumeProperty(json, Constants.PROP_PARAMETERS);
        if (jsonParameters != null) {
            JsonCompat.keys(jsonParameters).forEach(key -> {
                Object jsonValue = JsonCompat.consumeProperty(jsonParameters, key);
                AaiParameter value = nodeFactory.createParameter(node, key);
                this.readAaiParameter(jsonValue, value);
                node.addParameter(key, value);
            });
        }
        // correlationIds
        Object jsonCI = JsonCompat.consumeProperty(json, Constants.PROP_CORRELATION_IDS);
        if (jsonCI != null) {
            JsonCompat.keys(jsonCI).forEach(key -> {
                Object jsonValue = JsonCompat.consumeProperty(jsonCI, key);
                AaiCorrelationId value = nodeFactory.createCorrelationId(node, key);
                this.readCorrelationId(jsonValue, value);
                node.addCorrelationId(key, value);
            });
        }
        // traits
        Object jsonT = JsonCompat.consumeProperty(json, Constants.PROP_TRAITS);
        if (jsonT != null) {
            JsonCompat.keys(jsonT).forEach(key -> {
                Object jsonValue = JsonCompat.consumeProperty(jsonT, key);
                IAaiTrait value = createTrait(node, key, jsonValue);
                AaiTraitType tt = value.getTraitType();
                if (tt == AaiTraitType.message) {
                    this.readMessageTrait(jsonValue, (AaiMessageTrait) value);
                } else if (tt == AaiTraitType.operation) {
                    this.readOperationTrait(jsonValue, (AaiOperationTrait) value);
                } else {
                    this.readUnknownTrait(jsonValue, (AaiUnknownTrait) value);
                }
                node.addTrait(key, value);
            });
        }

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }


    /**
     * Detect if the json represents message or operation trait
     * Return null if we could not determine which one it is
     */
    private AaiTraitType getTraitType(Object json) {
        List<String> messageOnlyProperties = Arrays.asList(
                "headers", "correlationId", "schemaFormat",
                "contentType", "name", "title",
                "examples"
        );
        List<String> operationOnlyProperties = Arrays.asList(
                "operationId"
        );
        for (String p : messageOnlyProperties) {
            if (JsonCompat.isPropertyDefined(json, p)) {
                return AaiTraitType.message;
            }
        }
        for (String p : operationOnlyProperties) {
            if (JsonCompat.isPropertyDefined(json, p)) {
                return AaiTraitType.operation;
            }
        }
        return AaiTraitType.unknown;
    }

    private IAaiTrait createTrait(AaiComponents parent, String key, Object jsonValue) {
        AaiTraitType traitType = getTraitType(jsonValue);
        if (traitType == AaiTraitType.message) {
            return nodeFactory.createMessageTrait(parent, key);
        } else if (traitType == AaiTraitType.operation) {
            return nodeFactory.createOperationTrait(parent, key);
        } else {
            LoggerCompat.warn("Could not determine if this JSON represents a message " +
                    "or an operation trait: %s", jsonValue);
            return nodeFactory.createUnknownTrait(parent, key);
        }
    }

    @Override
    public void readSecurityScheme(Object json, SecurityScheme node) {
        AaiSecurityScheme aaiNode = (AaiSecurityScheme) node;
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
}
