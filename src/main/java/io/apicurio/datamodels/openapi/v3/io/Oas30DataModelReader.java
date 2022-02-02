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
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.openapi.io.OasDataModelReader;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.v3.models.Oas30Callback;
import io.apicurio.datamodels.openapi.v3.models.Oas30CallbackDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30CallbackPathItem;
import io.apicurio.datamodels.openapi.v3.models.Oas30Components;
import io.apicurio.datamodels.openapi.v3.models.Oas30Discriminator;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Encoding;
import io.apicurio.datamodels.openapi.v3.models.Oas30Example;
import io.apicurio.datamodels.openapi.v3.models.Oas30ExampleDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Header;
import io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Link;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;
import io.apicurio.datamodels.openapi.v3.models.Oas30Operation;
import io.apicurio.datamodels.openapi.v3.models.Oas30Parameter;
import io.apicurio.datamodels.openapi.v3.models.Oas30ParameterDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30PathItem;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Response;
import io.apicurio.datamodels.openapi.v3.models.Oas30ResponseDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30SchemaDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30SecurityScheme;
import io.apicurio.datamodels.openapi.v3.models.Oas30Server;

/**
 * A data model reader for the OpenAPI 2.0 data model.
 * @author eric.wittmann@gmail.com
 */
public class Oas30DataModelReader extends OasDataModelReader {

    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readDocument(java.lang.Object, io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void readDocument(Object json, Document node) {
        Oas30Document doc = (Oas30Document) node;

        String openapi = JsonCompat.consumePropertyString(json, Constants.PROP_OPENAPI);
        List<Object> servers = JsonCompat.consumePropertyArray(json, Constants.PROP_SERVERS);
        Object components = JsonCompat.consumeProperty(json, Constants.PROP_COMPONENTS);

        doc.openapi = openapi;

        if (servers != null) {
            List<Server> serverModels = new ArrayList<>();
            servers.forEach(server -> {
                Server serverModel = doc.createServer();
                this.readServer(server, serverModel);
                serverModels.add(serverModel);
            });
            doc.servers = serverModels;
        }

        if (components != null) {
            doc.components = doc.createComponents();
            this.readComponents(components, doc.components);
        }

        super.readDocument(json, doc);
    }

    /**
     * Reads a Components model.
     * @param json
     * @param node
     */
    public void readComponents(Object json, Oas30Components node) {
        Object schemas = JsonCompat.consumeProperty(json, Constants.PROP_SCHEMAS);
        Object responses = JsonCompat.consumeProperty(json, Constants.PROP_RESPONSES);
        Object parameters = JsonCompat.consumeProperty(json, Constants.PROP_PARAMETERS);
        Object examples = JsonCompat.consumeProperty(json, Constants.PROP_EXAMPLES);
        Object requestBodies = JsonCompat.consumeProperty(json, Constants.PROP_REQUEST_BODIES);
        Object headers = JsonCompat.consumeProperty(json, Constants.PROP_HEADERS);
        Object securitySchemes = JsonCompat.consumeProperty(json, Constants.PROP_SECURITY_SCHEMES);
        Object links = JsonCompat.consumeProperty(json, Constants.PROP_LINKS);
        Object callbacks = JsonCompat.consumeProperty(json, Constants.PROP_CALLBACKS);

        if (schemas != null) {
            JsonCompat.keys(schemas).forEach(name -> {
                Object schema = JsonCompat.consumeProperty(schemas, name);
                Oas30SchemaDefinition schemaModel = node.createSchemaDefinition(name);
                this.readSchema(schema, schemaModel);
                node.addSchemaDefinition(name, schemaModel);
            });
        }

        if (responses != null) {
            JsonCompat.keys(responses).forEach(name -> {
                Object response = JsonCompat.consumeProperty(responses, name);
                Oas30ResponseDefinition responseModel = node.createResponseDefinition(name);
                this.readResponse(response, responseModel);
                node.addResponseDefinition(name, responseModel);
            });
        }

        if (parameters != null) {
            JsonCompat.keys(parameters).forEach(name -> {
                Object parameter = JsonCompat.consumeProperty(parameters, name);
                Oas30ParameterDefinition parameterModel = node.createParameterDefinition(name);
                this.readParameter(parameter, parameterModel);
                node.addParameterDefinition(name, parameterModel);
            });
        }

        if (examples != null) {
            JsonCompat.keys(examples).forEach(name -> {
                Object example = JsonCompat.consumeProperty(examples, name);
                Oas30ExampleDefinition exampleModel = node.createExampleDefinition(name);
                this.readExample(example, exampleModel);
                node.addExampleDefinition(name, exampleModel);
            });
        }

        if (requestBodies != null) {
            JsonCompat.keys(requestBodies).forEach(name -> {
                Object requestBody = JsonCompat.consumeProperty(requestBodies, name);
                Oas30RequestBodyDefinition requestBodyModel = node.createRequestBodyDefinition(name);
                this.readRequestBody(requestBody, requestBodyModel);
                node.addRequestBodyDefinition(name, requestBodyModel);
            });
        }

        if (headers != null) {
            JsonCompat.keys(headers).forEach(name -> {
                Object header = JsonCompat.consumeProperty(headers, name);
                Oas30HeaderDefinition headerModel = node.createHeaderDefinition(name);
                this.readHeader(header, headerModel);
                node.addHeaderDefinition(name, headerModel);
            });
        }

        if (securitySchemes != null) {
            JsonCompat.keys(securitySchemes).forEach(name -> {
                Object securityScheme = JsonCompat.consumeProperty(securitySchemes, name);
                Oas30SecurityScheme securitySchemeModel = node.createSecurityScheme(name);
                this.readSecurityScheme(securityScheme, securitySchemeModel);
                node.addSecurityScheme(name, securitySchemeModel);
            });
        }

        if (links != null) {
            JsonCompat.keys(links).forEach(name -> {
                Object link = JsonCompat.consumeProperty(links, name);
                Oas30LinkDefinition linkModel = node.createLinkDefinition(name);
                this.readLink(link, linkModel);
                node.addLinkDefinition(name, linkModel);
            });
        }

        if (callbacks != null) {
            JsonCompat.keys(callbacks).forEach(name -> {
                Object callback = JsonCompat.consumeProperty(callbacks, name);
                Oas30CallbackDefinition callbackModel = node.createCallbackDefinition(name);
                this.readCallback(callback, callbackModel);
                node.addCallbackDefinition(name, callbackModel);
            });
        }
    }

    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readSecurityScheme(java.lang.Object, io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    @Override
    public void readSecurityScheme(Object json, SecurityScheme node) {
        Oas30SecurityScheme oasNode = (Oas30SecurityScheme) node;

        String $ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
        String scheme = JsonCompat.consumePropertyString(json, Constants.PROP_SCHEME);
        String bearerFormat = JsonCompat.consumePropertyString(json, Constants.PROP_BEARER_FORMAT);
        Object flows = JsonCompat.consumeProperty(json, Constants.PROP_FLOWS);
        String openIdConnectUrl = JsonCompat.consumePropertyString(json, Constants.PROP_OPEN_ID_CONNECT_URL);

        oasNode.$ref = $ref;
        oasNode.scheme = scheme;
        oasNode.bearerFormat = bearerFormat;
        oasNode.openIdConnectUrl = openIdConnectUrl;

        if (flows != null) {
            oasNode.flows = oasNode.createOAuthFlows();
            this.readOAuthFlows(flows, oasNode.flows);
        }

        super.readSecurityScheme(json, oasNode);
    }

    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelReader#readPathItem(java.lang.Object, io.apicurio.datamodels.openapi.models.OasPathItem)
     */
    @Override
    public void readPathItem(Object json, OasPathItem node) {
        Oas30PathItem pi = (Oas30PathItem) node;

        String summary = JsonCompat.consumePropertyString(json, Constants.PROP_SUMMARY);
        String description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
        Object trace = JsonCompat.consumeProperty(json, Constants.PROP_TRACE);
        List<Object> servers = JsonCompat.consumePropertyArray(json, Constants.PROP_SERVERS);

        pi.summary = summary;
        pi.description = description;

        if (trace != null) {
            pi.trace = (Oas30Operation) pi.createOperation(Constants.PROP_TRACE);
            this.readOperation(trace, pi.trace);
        }

        if (servers != null) {
            servers.forEach(server -> {
                Oas30Server serverModel = (Oas30Server) pi.createServer();
                this.readServer(server, serverModel);
                pi.addServer(serverModel);
            });
        }

        super.readPathItem(json, node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelReader#readHeader(java.lang.Object, io.apicurio.datamodels.openapi.models.OasHeader)
     */
    @Override
    public void readHeader(Object json, OasHeader node) {
        Oas30Header header = (Oas30Header) node;

        String $ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
        Boolean required = JsonCompat.consumePropertyBoolean(json, Constants.PROP_REQUIRED);
        Object schema = JsonCompat.consumeProperty(json, Constants.PROP_SCHEMA);
        Boolean allowEmptyValue = JsonCompat.consumePropertyBoolean(json, Constants.PROP_ALLOW_EMPTY_VALUE);
        Boolean deprecated = JsonCompat.consumePropertyBoolean(json, Constants.PROP_DEPRECATED);
        String style = JsonCompat.consumePropertyString(json, Constants.PROP_STYLE);
        Boolean explode = JsonCompat.consumePropertyBoolean(json, Constants.PROP_EXPLODE);
        Boolean allowReserved = JsonCompat.consumePropertyBoolean(json, Constants.PROP_ALLOW_RESERVED);
        Object example = JsonCompat.consumeProperty(json, Constants.PROP_EXAMPLE);
        Object examples = JsonCompat.consumeProperty(json, Constants.PROP_EXAMPLES);
        Object content = JsonCompat.consumeProperty(json, Constants.PROP_CONTENT);

        header.$ref = $ref;
        header.required = required;

        header.allowEmptyValue = allowEmptyValue;
        header.deprecated = deprecated;
        header.style = style;
        header.explode = explode;
        header.allowReserved = allowReserved;

        header.example = example;

        if (schema != null) {
            header.schema = header.createSchema();
            this.readSchema(schema, header.schema);
        }

        if (examples != null) {
            JsonCompat.keys(examples).forEach(name -> {
                Object exx = JsonCompat.consumeProperty(examples, name);
                Oas30Example exampleModel = header.createExample(name);
                this.readExample(exx, exampleModel);
                header.addExample(exampleModel);
            });
        }

        if (content != null) {
            JsonCompat.keys(content).forEach(name -> {
                Object mediaType = JsonCompat.consumeProperty(content, name);
                Oas30MediaType mediaTypeModel = header.createMediaType(name);
                this.readMediaType(mediaType, mediaTypeModel);
                header.addMediaType(name, mediaTypeModel);
            });
        }

        super.readHeader(json, node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelReader#readParameter(java.lang.Object, io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    public void readParameter(Object json, Parameter node) {
        Oas30Parameter param = (Oas30Parameter) node;

        Boolean deprecated = JsonCompat.consumePropertyBoolean(json, Constants.PROP_DEPRECATED);
        String style = JsonCompat.consumePropertyString(json, Constants.PROP_STYLE);
        Boolean explode = JsonCompat.consumePropertyBoolean(json, Constants.PROP_EXPLODE);
        Boolean allowReserved = JsonCompat.consumePropertyBoolean(json, Constants.PROP_ALLOW_RESERVED);
        Object example = JsonCompat.consumeProperty(json, Constants.PROP_EXAMPLE);
        Object examples = JsonCompat.consumeProperty(json, Constants.PROP_EXAMPLES);
        Object content = JsonCompat.consumeProperty(json, Constants.PROP_CONTENT);

        param.deprecated = deprecated;
        param.style = style;
        param.explode = explode;
        param.allowReserved = allowReserved;
        param.example = example;

        if (examples != null) {
            JsonCompat.keys(examples).forEach(name -> {
                Object exx = JsonCompat.consumeProperty(examples, name);
                Oas30Example exampleModel = param.createExample(name);
                this.readExample(exx, exampleModel);
                param.addExample(exampleModel);
            });
        }

        if (content != null) {
            JsonCompat.keys(content).forEach(name -> {
                Object mediaType = JsonCompat.consumeProperty(content, name);
                Oas30MediaType mediaTypeModel = param.createMediaType(name);
                this.readMediaType(mediaType, mediaTypeModel);
                param.addMediaType(name, mediaTypeModel);
            });
        }

        super.readParameter(json, node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelReader#readOperation(java.lang.Object, io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    public void readOperation(Object json, Operation node) {
        Oas30Operation operation = (Oas30Operation) node;

        Object requestBody = JsonCompat.consumeProperty(json, Constants.PROP_REQUEST_BODY);
        Object callbacks = JsonCompat.consumeProperty(json, Constants.PROP_CALLBACKS);
        List<Object> servers = JsonCompat.consumePropertyArray(json, Constants.PROP_SERVERS);
//        String path = JsonCompat.consumePropertyString(json, Con)

        if (requestBody != null) {
            operation.requestBody = operation.createRequestBody();
            this.readRequestBody(requestBody, operation.requestBody);
        }

        if (callbacks != null) {
            JsonCompat.keys(callbacks).forEach(name -> {
                Object callback = JsonCompat.consumeProperty(callbacks, name);
                Oas30Callback callbackModel = operation.createCallback(name);
                this.readCallback(callback, callbackModel);
                operation.addCallback(name, callbackModel);
            });
        }

        if (servers != null) {
            servers.forEach(server -> {
                Oas30Server serverModel = (Oas30Server) operation.createServer();
                this.readServer(server, serverModel);
                operation.addServer(serverModel);
            });
        }

        super.readOperation(json, node);
    }

    /**
     * Reads an OAS 3.0 Callback object from the given JS data.
     * @param json
     * @param node
     */
    public void readCallback(Object json, Oas30Callback node) {
        JsonCompat.keys(json).forEach(name -> {
            if (NodeCompat.equals(name, Constants.PROP_$REF)) {
                node.$ref = JsonCompat.consumePropertyString(json, name);
            } else {
                Object pathItem = JsonCompat.consumeProperty(json, name);
                Oas30CallbackPathItem pathItemModel = node.createPathItem(name);
                this.readPathItem(pathItem, pathItemModel);
                node.addPathItem(name, pathItemModel);
            }
        });
        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * Reads an OAS 3.0 Request Body object from the given JS data.
     * @param json
     * @param node
     */
    public void readRequestBody(Object json, Oas30RequestBody node) {
        String $ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
        String description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
        Object content = JsonCompat.consumeProperty(json, Constants.PROP_CONTENT);
        Boolean required = JsonCompat.consumePropertyBoolean(json, Constants.PROP_REQUIRED);

        node.$ref = $ref;
        node.description = description;
        node.required = required;

        if (content != null) {
            JsonCompat.keys(content).forEach(name -> {
                Object mediaType = JsonCompat.consumeProperty(content, name);
                Oas30MediaType mediaTypeModel = node.createMediaType(name);
                this.readMediaType(mediaType, mediaTypeModel);
                node.addMediaType(name, mediaTypeModel);
            });
        }

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * Reads an OAS 3.0 Media Type from the given js data.
     * @param json
     * @param node
     */
    public void readMediaType(Object json, Oas30MediaType node) {
        Object schema = JsonCompat.consumeProperty(json, Constants.PROP_SCHEMA);
        Object example = JsonCompat.consumePropertyObject(json, Constants.PROP_EXAMPLE);
        Object examples = JsonCompat.consumeProperty(json, Constants.PROP_EXAMPLES);
        Object encodings = JsonCompat.consumeProperty(json, Constants.PROP_ENCODING);

        node.example = example;

        if (schema != null) {
            node.schema = node.createSchema();
            this.readSchema(schema, node.schema);
        }

        if (examples != null) {
            JsonCompat.keys(examples).forEach(name -> {
                Object exx = JsonCompat.consumeProperty(examples, name);
                Oas30Example exampleModel = (Oas30Example) node.createExample(name);
                this.readExample(exx, exampleModel);
                node.addExample(exampleModel);
            });
        }

        if (encodings != null) {
            JsonCompat.keys(encodings).forEach(name -> {
                Object encoding = JsonCompat.consumeProperty(encodings, name);
                Oas30Encoding encodingModel = node.createEncoding(name);
                this.readEncoding(encoding, encodingModel);
                node.addEncoding(name, encodingModel);
            });
        }

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * Reads an OAS 3.0 Example from the given js data.
     * @param json
     * @param node
     */
    public void readExample(Object json, Oas30Example node) {
        String $ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
        String summary = JsonCompat.consumePropertyString(json, Constants.PROP_SUMMARY);
        String description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
        Object value = JsonCompat.consumePropertyObject(json, Constants.PROP_VALUE);
        String externalValue = JsonCompat.consumePropertyString(json, Constants.PROP_EXTERNAL_VALUE);

        node.$ref = $ref;
        node.summary = summary;
        node.description = description;
        node.value = value;
        node.externalValue = externalValue;

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * Reads an OAS 3.0 Encoding from the given js data.
     * @param json
     * @param node
     */
    public void readEncoding(Object json, Oas30Encoding node) {
        String contentType = JsonCompat.consumePropertyString(json, Constants.PROP_CONTENT_TYPE);
        Object headers = JsonCompat.consumeProperty(json, Constants.PROP_HEADERS);
        String style = JsonCompat.consumePropertyString(json, Constants.PROP_STYLE);
        Boolean explode = JsonCompat.consumePropertyBoolean(json, Constants.PROP_EXPLODE);
        Boolean allowReserved = JsonCompat.consumePropertyBoolean(json, Constants.PROP_ALLOW_RESERVED);

        node.contentType = contentType;
        node.style = style;
        node.explode = explode;
        node.allowReserved = allowReserved;

        if (headers != null) {
            JsonCompat.keys(headers).forEach(name -> {
                Object header = JsonCompat.consumeProperty(headers, name);
                Oas30Header headerModel = node.createHeader(name);
                this.readHeader(header, headerModel);
                node.addHeader(name, headerModel);
            });
        }

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelReader#readResponse(java.lang.Object, io.apicurio.datamodels.openapi.models.OasResponse)
     */
    @Override
    public void readResponse(Object json, OasResponse node) {
        Oas30Response response = (Oas30Response) node;
        Object headers = JsonCompat.consumeProperty(json, Constants.PROP_HEADERS);
        Object content = JsonCompat.consumeProperty(json, Constants.PROP_CONTENT);
        Object links = JsonCompat.consumeProperty(json, Constants.PROP_LINKS);

        if (headers != null) {
            JsonCompat.keys(headers).forEach(name -> {
                Object header = JsonCompat.consumeProperty(headers, name);
                Oas30Header headerModel = response.createHeader(name);
                this.readHeader(header, headerModel);
                response.addHeader(name, headerModel);
            });
        }

        if (content != null) {
            JsonCompat.keys(content).forEach(name -> {
                Object mediaType = JsonCompat.consumeProperty(content, name);
                Oas30MediaType mediaTypeModel = response.createMediaType(name);
                this.readMediaType(mediaType, mediaTypeModel);
                response.addMediaType(name, mediaTypeModel);
            });
        }

        if (links != null) {
            JsonCompat.keys(links).forEach(name -> {
                Object link = JsonCompat.consumeProperty(links, name);
                Oas30Link linkModel = response.createLink(name);
                this.readLink(link, linkModel);
                response.addLink(name, linkModel);
            });
        }

        super.readResponse(json, node);
    }

    /**
     * Reads an OAS 3.0 Link object from the given js data.
     * @param json
     * @param node
     */
    public void readLink(Object json, Oas30Link node) {
        String $ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
        String operationRef = JsonCompat.consumePropertyString(json, Constants.PROP_OPERATION_REF);
        String operationId = JsonCompat.consumePropertyString(json, Constants.PROP_OPERATION_ID);
        Object parameters = JsonCompat.consumeProperty(json, Constants.PROP_PARAMETERS);
        String requestBody = JsonCompat.consumePropertyString(json, Constants.PROP_REQUEST_BODY);
        String description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
        Object server = JsonCompat.consumeProperty(json, Constants.PROP_SERVER);

        node.$ref = $ref;
        node.operationRef = operationRef;
        node.operationId = operationId;
        node.description = description;

        if (parameters != null) {
            JsonCompat.keys(parameters).forEach(name -> {
                String expression = JsonCompat.consumePropertyString(parameters, name);
                node.addLinkParameter(name, expression);
            });
        }

        if (requestBody != null) {
            node.requestBody = node.createLinkRequestBodyExpression(requestBody);
        }

        if (server != null) {
            node.server = node.createServer();
            this.readServer(server, node.server);
        }

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelReader#readSchema(java.lang.Object, io.apicurio.datamodels.core.models.common.Schema)
     */
    @Override
    public void readSchema(Object json, Schema node) {
        Oas30Schema schema = (Oas30Schema) node;

        List<Object> oneOf = JsonCompat.consumePropertyArray(json, Constants.PROP_ONE_OF);
        List<Object> anyOf = JsonCompat.consumePropertyArray(json, Constants.PROP_ANY_OF);
        Object not = JsonCompat.consumeProperty(json, Constants.PROP_NOT);
        Object discriminator = JsonCompat.consumeProperty(json, Constants.PROP_DISCRIMINATOR);
        Boolean nullable = JsonCompat.consumePropertyBoolean(json, Constants.PROP_NULLABLE);
        Boolean writeOnly = JsonCompat.consumePropertyBoolean(json, Constants.PROP_WRITE_ONLY);
        Boolean deprecated = JsonCompat.consumePropertyBoolean(json, Constants.PROP_DEPRECATED);

        schema.nullable = nullable;
        schema.writeOnly = writeOnly;
        schema.deprecated = deprecated;

        if (oneOf != null) {
            oneOf.forEach(oneOfSchema -> {
                Oas30OneOfSchema oneOfSchemaModel = schema.createOneOfSchema();
                this.readSchema(oneOfSchema, oneOfSchemaModel);
                schema.addOneOfSchema(oneOfSchemaModel);
            });
        }

        if (anyOf != null) {
            anyOf.forEach(anyOfSchema -> {
                Oas30AnyOfSchema anyOfSchemaModel = schema.createAnyOfSchema();
                this.readSchema(anyOfSchema, anyOfSchemaModel);
                schema.addAnyOfSchema(anyOfSchemaModel);
            });
        }

        if (not != null) {
            schema.not = schema.createNotSchema();
            this.readSchema(not, schema.not);
        }

        if (discriminator != null) {
            schema.discriminator = schema.createDiscriminator();
            this.readDiscriminator(discriminator, schema.discriminator);
        }

        super.readSchema(json, node);
    }

    /**
     * Reads an OAS 3.0 Discriminator object from the given JS data.
     * @param json
     * @param node
     */
    public void readDiscriminator(Object json, Oas30Discriminator node) {
        String propertyName = JsonCompat.consumePropertyString(json, Constants.PROP_PROPERTY_NAME);
        Object mapping = JsonCompat.consumeProperty(json, Constants.PROP_MAPPING);

        node.propertyName = propertyName;

        if (mapping != null) {
            JsonCompat.keys(mapping).forEach(key -> {
                String value = JsonCompat.consumePropertyString(mapping, key);
                node.addMapping(key, value);
            });
        }

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }
}
