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

package io.apicurio.datamodels.openapi.v2.io;

import java.util.List;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.openapi.io.OasDataModelReader;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.v2.models.Oas20Definitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20Example;
import io.apicurio.datamodels.openapi.v2.models.Oas20Header;
import io.apicurio.datamodels.openapi.v2.models.Oas20Headers;
import io.apicurio.datamodels.openapi.v2.models.Oas20Items;
import io.apicurio.datamodels.openapi.v2.models.Oas20Operation;
import io.apicurio.datamodels.openapi.v2.models.Oas20Parameter;
import io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinition;
import io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Response;
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinition;
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Schema;
import io.apicurio.datamodels.openapi.v2.models.Oas20SchemaDefinition;
import io.apicurio.datamodels.openapi.v2.models.Oas20Scopes;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityScheme;

/**
 * A data model reader for the OpenAPI 2.0 data model.
 * @author eric.wittmann@gmail.com
 */
public class Oas20DataModelReader extends OasDataModelReader {
    
    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readDocument(java.lang.Object, io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void readDocument(Object json, Document node) {
        Oas20Document doc = (Oas20Document) node;
        
        JsonCompat.consumePropertyString(json, Constants.PROP_SWAGGER);
        String host = JsonCompat.consumePropertyString(json, Constants.PROP_HOST);
        String basePath = JsonCompat.consumePropertyString(json, Constants.PROP_BASE_PATH);
        List<String> schemes = JsonCompat.consumePropertyStringArray(json, Constants.PROP_SCHEMES);
        List<String> consumes = JsonCompat.consumePropertyStringArray(json, Constants.PROP_CONSUMES);
        List<String> produces = JsonCompat.consumePropertyStringArray(json, Constants.PROP_PRODUCES);
        Object definitions = JsonCompat.consumeProperty(json, Constants.PROP_DEFINITIONS);
        Object parameters = JsonCompat.consumeProperty(json, Constants.PROP_PARAMETERS);
        Object responses = JsonCompat.consumeProperty(json, Constants.PROP_RESPONSES);
        Object securityDefinitions = JsonCompat.consumeProperty(json, Constants.PROP_SECURITY_DEFINITIONS);
        
        doc.host = host;
        doc.basePath = basePath;
        doc.schemes = schemes;
        doc.consumes = consumes;
        doc.produces = produces;
        
        if (definitions != null) {
            doc.definitions = doc.createDefinitions();
            this.readDefinitions(definitions, doc.definitions);
        }
        
        if (parameters != null) {
            doc.parameters = doc.createParameterDefinitions();
            this.readParameterDefinitions(parameters, doc.parameters);
        }
        
        if (responses != null) {
            doc.responses = doc.createResponseDefinitions();
            this.readResponseDefinitions(responses, doc.responses);
        }
        
        if (securityDefinitions != null) {
            doc.securityDefinitions = doc.createSecurityDefinitions();
            this.readSecurityDefinitions(securityDefinitions, doc.securityDefinitions);
        }
        
        super.readDocument(json, doc);
    }
    
    /**
     * Reads the definitions.
     * @param json
     * @param node
     */
    public void readDefinitions(Object json, Oas20Definitions node) {
        List<String> names = JsonCompat.keys(json);
        for (String name : names) {
            Object definition = JsonCompat.consumeProperty(json, name);
            Oas20SchemaDefinition definitionModel = node.createSchemaDefinition(name);
            this.readSchema(definition, definitionModel);
            node.addDefinition(name, definitionModel);
        }
    }

    /**
     * Reads the parameter definitions.
     * @param json
     * @param node
     */
    public void readParameterDefinitions(Object json, Oas20ParameterDefinitions node) {
        List<String> names = JsonCompat.keys(json);
        for (String name: names) {
            Object definition = JsonCompat.consumeProperty(json, name);
            Oas20ParameterDefinition definitionModel = node.createParameter(name);
            this.readParameterDefinition(definition, definitionModel);
            node.addParameter(name, definitionModel);
        }
    }

    /**
     * Reads the response definitions.
     * @param json
     * @param node
     */
    public void readResponseDefinitions(Object json, Oas20ResponseDefinitions node) {
        List<String> names = JsonCompat.keys(json);
        for (String name: names) {
            Object definition = JsonCompat.consumeProperty(json, name);
            Oas20ResponseDefinition definitionModel = node.createResponse(name);
            this.readResponseDefinition(definition, definitionModel);
            node.addResponse(name, definitionModel);
        }
    }

    /**
     * Reads a single parameter definition.
     * @param json
     * @param node
     */
    public void readParameterDefinition(Object json, Oas20ParameterDefinition node) {
        this.readParameter(json, node);
    }

    /**
     * Reads a single response definition.
     * @param json
     * @param node
     */
    public void readResponseDefinition(Object json, Oas20ResponseDefinition node) {
        this.readResponse(json, node);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelReader#readResponse(java.lang.Object, io.apicurio.datamodels.openapi.models.OasResponse)
     */
    @Override
    public void readResponse(Object json, OasResponse node) {
        Oas20Response response = (Oas20Response) node;
        Object headers = JsonCompat.consumeProperty(json, Constants.PROP_HEADERS);
        Object schema = JsonCompat.consumeProperty(json, Constants.PROP_SCHEMA);
        Object examples = JsonCompat.consumeProperty(json, Constants.PROP_EXAMPLES);
        
        if (headers != null) {
            response.headers = response.createHeaders();
            this.readHeaders(headers, response.headers);
        }
        
        if (schema != null) {
            response.schema = response.createSchema();
            this.readSchema(schema, response.schema);
        }
        
        if (examples != null) {
            response.examples = response.createExample();
            this.readExample(examples, response.examples);
        }
        
        super.readResponse(json, node);
    }

    /**
     * Reads the headers.
     * @param json
     * @param node
     */
    public void readHeaders(Object json, Oas20Headers node) {
        List<String> names = JsonCompat.keys(json);
        for (String name: names) {
            Object definition = JsonCompat.consumeProperty(json, name);
            Oas20Header definitionModel = node.createHeader(name);
            this.readHeader(definition, definitionModel);
            node.addHeader(name, definitionModel);
        }
    }
    
    /**
     * Reads a single header.
     * @param json
     * @param node
     */
    public void readHeader(Object json, OasHeader node) {
        Oas20Header header = (Oas20Header) node;
        
        String type = JsonCompat.consumePropertyString(json, Constants.PROP_TYPE);
        String format = JsonCompat.consumePropertyString(json, Constants.PROP_FORMAT);
        Object items = JsonCompat.consumeProperty(json, Constants.PROP_ITEMS);
        String collectionFormat = JsonCompat.consumePropertyString(json, Constants.PROP_COLLECTION_FORMAT);
        Object default_ = JsonCompat.consumePropertyObject(json, Constants.PROP_DEFAULT);
        Number maximum = JsonCompat.consumePropertyNumber(json, Constants.PROP_MAXIMUM);
        Boolean exclusiveMaximum = JsonCompat.consumePropertyBoolean(json, Constants.PROP_EXCLUSIVE_MAXIMUM);
        Number minimum = JsonCompat.consumePropertyNumber(json, Constants.PROP_MINIMUM);
        Boolean exclusiveMinimum = JsonCompat.consumePropertyBoolean(json, Constants.PROP_EXCLUSIVE_MINIMUM);
        Number maxLength = JsonCompat.consumePropertyNumber(json, Constants.PROP_MAX_LENGTH);
        Number minLength = JsonCompat.consumePropertyNumber(json, Constants.PROP_MIN_LENGTH);
        String pattern = JsonCompat.consumePropertyString(json, Constants.PROP_PATTERN);
        Number maxItems = JsonCompat.consumePropertyNumber(json, Constants.PROP_MAX_ITEMS);
        Number minItems = JsonCompat.consumePropertyNumber(json, Constants.PROP_MIN_ITEMS);
        Boolean uniqueItems = JsonCompat.consumePropertyBoolean(json, Constants.PROP_UNIQUE_ITEMS);
        List<String> enum_ = JsonCompat.consumePropertyStringArray(json, Constants.PROP_ENUM);
        Number multipleOf = JsonCompat.consumePropertyNumber(json, Constants.PROP_MULTIPLE_OF);
        
        header.type = type;
        header.format = format;
        header.collectionFormat = collectionFormat;
        header.default_ = default_;
        header.maximum = maximum;
        header.exclusiveMaximum = exclusiveMaximum;
        header.minimum = minimum;
        header.exclusiveMinimum = exclusiveMinimum;
        header.maxLength = maxLength;
        header.minLength = minLength;
        header.pattern = pattern;
        header.maxItems = maxItems;
        header.minItems = minItems;
        header.uniqueItems = uniqueItems;
        header.enum_ = enum_;
        header.multipleOf = multipleOf;

        if (items != null) {
            header.items = header.createItems();
            this.readItems(items, header.items);
        }

        super.readHeader(json, header);
    }

    /**
     * Reads an example.
     * @param json
     * @param node
     */
    public void readExample(Object json, Oas20Example node) {
        List<String> contentTypes = JsonCompat.keys(json);
        contentTypes.forEach(ct -> {
            Object example = JsonCompat.consumePropertyObject(json, ct);
            node.addExample(ct, example);
        });
    }

    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelReader#readParameter(java.lang.Object, io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    public void readParameter(Object json, Parameter node) {
        Oas20Parameter param = (Oas20Parameter) node;
        String type = JsonCompat.consumePropertyString(json, Constants.PROP_TYPE);
        String format = JsonCompat.consumePropertyString(json, Constants.PROP_FORMAT);
        Object items = JsonCompat.consumeProperty(json, Constants.PROP_ITEMS);
        String collectionFormat = JsonCompat.consumePropertyString(json, Constants.PROP_COLLECTION_FORMAT);
        Object default_ = JsonCompat.consumePropertyObject(json, Constants.PROP_DEFAULT);
        Number maximum = JsonCompat.consumePropertyNumber(json, Constants.PROP_MAXIMUM);
        Boolean exclusiveMaximum = JsonCompat.consumePropertyBoolean(json, Constants.PROP_EXCLUSIVE_MAXIMUM);
        Number minimum = JsonCompat.consumePropertyNumber(json, Constants.PROP_MINIMUM);
        Boolean exclusiveMinimum = JsonCompat.consumePropertyBoolean(json, Constants.PROP_EXCLUSIVE_MINIMUM);
        Number maxLength = JsonCompat.consumePropertyNumber(json, Constants.PROP_MAX_LENGTH);
        Number minLength = JsonCompat.consumePropertyNumber(json, Constants.PROP_MIN_LENGTH);
        String pattern = JsonCompat.consumePropertyString(json, Constants.PROP_PATTERN);
        Number maxItems = JsonCompat.consumePropertyNumber(json, Constants.PROP_MAX_ITEMS);
        Number minItems = JsonCompat.consumePropertyNumber(json, Constants.PROP_MIN_ITEMS);
        Boolean uniqueItems = JsonCompat.consumePropertyBoolean(json, Constants.PROP_UNIQUE_ITEMS);
        List<String> enum_ = JsonCompat.consumePropertyStringArray(json, Constants.PROP_ENUM);
        Number multipleOf = JsonCompat.consumePropertyNumber(json, Constants.PROP_MULTIPLE_OF);
        
        param.type = type;
        param.format = format;
        param.collectionFormat = collectionFormat;
        param.default_ = default_;
        param.maximum = maximum;
        param.exclusiveMaximum = exclusiveMaximum;
        param.minimum = minimum;
        param.exclusiveMinimum = exclusiveMinimum;
        param.maxLength = maxLength;
        param.minLength = minLength;
        param.pattern = pattern;
        param.maxItems = maxItems;
        param.minItems = minItems;
        param.uniqueItems = uniqueItems;
        param.enum_ = enum_;
        param.multipleOf = multipleOf;

        if (items != null) {
            param.items = param.createItems();
            this.readItems(items, param.items);
        }

        super.readParameter(json, node);
    }

    /**
     * Reads an Items.
     * @param json
     * @param node
     */
    public void readItems(Object json, Oas20Items node) {
        String type = JsonCompat.consumePropertyString(json, Constants.PROP_TYPE);
        String format = JsonCompat.consumePropertyString(json, Constants.PROP_FORMAT);
        Object items = JsonCompat.consumeProperty(json, Constants.PROP_ITEMS);
        String collectionFormat = JsonCompat.consumePropertyString(json, Constants.PROP_COLLECTION_FORMAT);
        Object default_ = JsonCompat.consumePropertyObject(json, Constants.PROP_DEFAULT);
        Number maximum = JsonCompat.consumePropertyNumber(json, Constants.PROP_MAXIMUM);
        Boolean exclusiveMaximum = JsonCompat.consumePropertyBoolean(json, Constants.PROP_EXCLUSIVE_MAXIMUM);
        Number minimum = JsonCompat.consumePropertyNumber(json, Constants.PROP_MINIMUM);
        Boolean exclusiveMinimum = JsonCompat.consumePropertyBoolean(json, Constants.PROP_EXCLUSIVE_MINIMUM);
        Number maxLength = JsonCompat.consumePropertyNumber(json, Constants.PROP_MAX_LENGTH);
        Number minLength = JsonCompat.consumePropertyNumber(json, Constants.PROP_MIN_LENGTH);
        String pattern = JsonCompat.consumePropertyString(json, Constants.PROP_PATTERN);
        Number maxItems = JsonCompat.consumePropertyNumber(json, Constants.PROP_MAX_ITEMS);
        Number minItems = JsonCompat.consumePropertyNumber(json, Constants.PROP_MIN_ITEMS);
        Boolean uniqueItems = JsonCompat.consumePropertyBoolean(json, Constants.PROP_UNIQUE_ITEMS);
        List<String> enum_ = JsonCompat.consumePropertyStringArray(json, Constants.PROP_ENUM);
        Number multipleOf = JsonCompat.consumePropertyNumber(json, Constants.PROP_MULTIPLE_OF);
        
        node.type = type;
        node.format = format;
        node.collectionFormat = collectionFormat;
        node.default_ = default_;
        node.maximum = maximum;
        node.exclusiveMaximum = exclusiveMaximum;
        node.minimum = minimum;
        node.exclusiveMinimum = exclusiveMinimum;
        node.maxLength = maxLength;
        node.minLength = minLength;
        node.pattern = pattern;
        node.maxItems = maxItems;
        node.minItems = minItems;
        node.uniqueItems = uniqueItems;
        node.enum_ = enum_;
        node.multipleOf = multipleOf;

        if (items != null) {
            node.items = node.createItems();
            this.readItems(items, node.items);
        }
        
        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelReader#readSchema(java.lang.Object, io.apicurio.datamodels.core.models.common.Schema)
     */
    @Override
    public void readSchema(Object json, Schema node) {
        Oas20Schema schema = (Oas20Schema) node;
        
        String discriminator = JsonCompat.consumePropertyString(json, Constants.PROP_DISCRIMINATOR);
        
        schema.discriminator = discriminator;
        
        super.readSchema(json, node);
    }

    /**
     * Reads the security definitions.
     * @param json
     * @param node
     */
    public void readSecurityDefinitions(Object json, Oas20SecurityDefinitions node) {
        List<String> names = JsonCompat.keys(json);
        for (String name : names) {
            Object schemeJson = JsonCompat.consumeProperty(json, name);
            Oas20SecurityScheme schemeModel = node.createSecurityScheme(name);
            this.readSecurityScheme(schemeJson, schemeModel);
            node.addSecurityScheme(name, schemeModel);
        }
    }

    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readSecurityScheme(java.lang.Object, io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    public void readSecurityScheme(Object json, SecurityScheme node) {
        Oas20SecurityScheme scheme = (Oas20SecurityScheme) node;
        
        String flow = JsonCompat.consumePropertyString(json, Constants.PROP_FLOW);
        String authorizationUrl = JsonCompat.consumePropertyString(json, Constants.PROP_AUTHORIZATION_URL);
        String tokenUrl = JsonCompat.consumePropertyString(json, Constants.PROP_TOKEN_URL);
        Object scopes = JsonCompat.consumeProperty(json, Constants.PROP_SCOPES);

        scheme.flow = flow;
        scheme.authorizationUrl = authorizationUrl;
        scheme.tokenUrl = tokenUrl;
        
        if (scopes != null) {
            Oas20Scopes scopesModel = scheme.createScopes();
            this.readScopes(scopes, scopesModel);
            scheme.scopes = scopesModel;
        }
        
        super.readSecurityScheme(json, scheme);
    }

    /**
     * Reads the scopes.
     * @param json
     * @param node
     */
    public void readScopes(Object json, Oas20Scopes node) {
        List<String> scopeNames = JsonCompat.keys(json);
        for (String scopeName : scopeNames) {
            if (scopeName.indexOf("x-") == 0) { continue; }
            String scopeDescription = JsonCompat.consumePropertyString(json, scopeName);
            node.addScope(scopeName, scopeDescription);
        }
        this.readExtensions(json, node);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelReader#readOperation(java.lang.Object, io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    public void readOperation(Object json, Operation node) {
        Oas20Operation operation = (Oas20Operation) node;
        
        List<String> consumes = JsonCompat.consumePropertyStringArray(json, Constants.PROP_CONSUMES);
        List<String> produces = JsonCompat.consumePropertyStringArray(json, Constants.PROP_PRODUCES);
        List<String> schemes = JsonCompat.consumePropertyStringArray(json, Constants.PROP_SCHEMES);
        
        operation.consumes = consumes;
        operation.produces = produces;
        operation.schemes = schemes;
        
        super.readOperation(json, node);
    }
}
