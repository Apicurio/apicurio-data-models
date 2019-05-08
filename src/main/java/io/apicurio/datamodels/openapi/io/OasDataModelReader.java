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

package io.apicurio.datamodels.openapi.io;

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.io.DataModelReader;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.models.OasPaths;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.models.OasResponses;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.models.OasSecurityRequirement;
import io.apicurio.datamodels.openapi.models.OasXML;

/**
 * A data model reader for the OpenAPI data model.
 * @author eric.wittmann@gmail.com
 */
public abstract class OasDataModelReader extends DataModelReader {
    
    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readDocument(java.lang.Object, io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void readDocument(Object json, Document node) {
        OasDocument doc = (OasDocument) node;
        
        Object paths = JsonCompat.consumeProperty(json, Constants.PROP_PATHS);
        List<Object> security = JsonCompat.consumePropertyArray(json, Constants.PROP_SECURITY);
        
        if (paths != null) {
            doc.paths = doc.createPaths();
            this.readPaths(paths, doc.paths);
        }
        
        if (security != null) {
            security.forEach(sec -> {
                OasSecurityRequirement secModel = doc.createSecurityRequirement();
                this.readSecurityRequirement(sec, secModel);
                doc.addSecurityRequirement(secModel);
            });
        }

        super.readDocument(json, doc);
    }

    /**
     * Read the paths.
     * @param json
     * @param node
     */
    public void readPaths(Object json, OasPaths node) {
        List<String> pathNames = JsonCompat.keys(json);
        for (String pathName : pathNames) {
            if (pathName.indexOf("x-") == 0) {
                continue;
            }
            Object pathItem = JsonCompat.consumeProperty(json, pathName);
            OasPathItem pathItemModel = node.createPathItem(pathName);
            this.readPathItem(pathItem, pathItemModel);
            node.addPathItem(pathName, pathItemModel);
        }
        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * Reads a path item.
     * @param json
     * @param node
     */
    public void readPathItem(Object json, OasPathItem node) {
        String $ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
        Object get = JsonCompat.consumeProperty(json, Constants.PROP_GET);
        Object put = JsonCompat.consumeProperty(json, Constants.PROP_PUT);
        Object post = JsonCompat.consumeProperty(json, Constants.PROP_POST);
        Object delete = JsonCompat.consumeProperty(json, Constants.PROP_DELETE);
        Object options = JsonCompat.consumeProperty(json, Constants.PROP_OPTIONS);
        Object head = JsonCompat.consumeProperty(json, Constants.PROP_HEAD);
        Object patch = JsonCompat.consumeProperty(json, Constants.PROP_PATCH);
        List<Object> parameters = JsonCompat.consumePropertyArray(json, Constants.PROP_PARAMETERS);
        
        node.$ref = $ref;
        
        if (get != null) {
            node.get = node.createOperation(Constants.PROP_GET);
            this.readOperation(get, node.get);
        }
        if (put != null) {
            node.put = node.createOperation(Constants.PROP_PUT);
            this.readOperation(put, node.put);
        }
        if (post != null) {
            node.post = node.createOperation(Constants.PROP_POST);
            this.readOperation(post, node.post);
        }
        if (delete != null) {
            node.delete = node.createOperation(Constants.PROP_DELETE);
            this.readOperation(delete, node.delete);
        }
        if (options != null) {
            node.options = node.createOperation(Constants.PROP_OPTIONS);
            this.readOperation(options, node.options);
        }
        if (head != null) {
            node.head = node.createOperation(Constants.PROP_HEAD);
            this.readOperation(head, node.head);
        }
        if (patch != null) {
            node.patch = node.createOperation(Constants.PROP_PATCH);
            this.readOperation(patch, node.patch);
        }
        if (parameters != null) {
            parameters.forEach(parameter -> {
                OasParameter parameterModel = node.createParameter();
                this.readParameter(parameter, parameterModel);
                node.addParameter(parameterModel);
            });
        }
        
        this.readExtensions(json, node);
    }

    /**
     * Reads a single operation.
     * @param json
     * @param node
     */
    public void readOperation(Object json, Operation node) {
        OasOperation operation = (OasOperation) node;

        List<String> tags = JsonCompat.consumePropertyStringArray(json, Constants.PROP_TAGS);
        List<Object> parameters = JsonCompat.consumePropertyArray(json, Constants.PROP_PARAMETERS);
        Object responses = JsonCompat.consumeProperty(json, Constants.PROP_RESPONSES);
        Boolean deprecated = JsonCompat.consumePropertyBoolean(json, Constants.PROP_DEPRECATED);
        List<Object> security = JsonCompat.consumePropertyArray(json, Constants.PROP_SECURITY);
        
        operation.tags = tags;
        operation.deprecated = deprecated;
        
        if (parameters != null) {
            parameters.forEach(parameter -> {
                OasParameter parameterModel = operation.createParameter();
                this.readParameter(parameter, parameterModel);
                operation.addParameter(parameterModel);
            });
        }
        
        if (responses != null) {
            operation.responses = operation.createResponses();
            this.readResponses(responses, operation.responses);
        }
        
        if (security != null) {
            security.forEach(requirement -> {
                OasSecurityRequirement requirementModel = operation.createSecurityRequirement();
                this.readSecurityRequirement(requirement, requirementModel);
                operation.addSecurityRequirement(requirementModel);
            });
        }
        
        super.readOperation(json, node);
    }

    /**
     * Reads the responses.
     * @param json
     * @param node
     */
    public void readResponses(Object json, OasResponses node) {
        Object default_ = JsonCompat.consumeProperty(json, Constants.PROP_DEFAULT);
        
        if (default_ != null) {
            node.default_ = node.createDefaultResponse();
            this.readResponse(default_, node.default_);
        }

        List<String> responseCodes = JsonCompat.keys(json);
        responseCodes.forEach(code -> {
            if (code.indexOf("x-") == 0) {
                // Skip this one!
            } else {
                Object response = JsonCompat.consumeProperty(json, code);
                OasResponse responseModel = node.createResponse(code);
                this.readResponse(response, responseModel);
                node.addResponse(code, responseModel);
            }
        });
        
        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readSchema(java.lang.Object, io.apicurio.datamodels.core.models.common.Schema)
     */
    @Override
    public void readSchema(Object json, Schema node) {
        OasSchema schema = (OasSchema) node;
        
        String format = JsonCompat.consumePropertyString(json, Constants.PROP_FORMAT);
        String title = JsonCompat.consumePropertyString(json, Constants.PROP_TITLE);
        String description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
        Object default_ = JsonCompat.consumePropertyObject(json, Constants.PROP_DEFAULT);
        Number multipleOf = JsonCompat.consumePropertyNumber(json, Constants.PROP_MULTIPLE_OF);
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
        Number maxProperties = JsonCompat.consumePropertyNumber(json, Constants.PROP_MAX_PROPERTIES);
        Number minProperties = JsonCompat.consumePropertyNumber(json, Constants.PROP_MIN_PROPERTIES);
        List<String> required = JsonCompat.consumePropertyStringArray(json, Constants.PROP_REQUIRED);
        List<String> enum_ = JsonCompat.consumePropertyStringArray(json, Constants.PROP_ENUM);
        String type = JsonCompat.consumePropertyString(json, Constants.PROP_TYPE);
        
        Object items = JsonCompat.consumeProperty(json, Constants.PROP_ITEMS);
        List<Object> allOf = JsonCompat.consumePropertyArray(json, Constants.PROP_ALL_OF);
        Object properties = JsonCompat.consumeProperty(json, Constants.PROP_PROPERTIES);
        Object additionalProperties = JsonCompat.consumeProperty(json, Constants.PROP_ADDITIONAL_PROPERTIES);
        
        Boolean readOnly = JsonCompat.consumePropertyBoolean(json, Constants.PROP_READ_ONLY);
        Object xml = JsonCompat.consumeProperty(json, Constants.PROP_XML);
        Object externalDocs = JsonCompat.consumeProperty(json, Constants.PROP_EXTERNAL_DOCS);
        Object example = JsonCompat.consumePropertyObject(json, Constants.PROP_EXAMPLE);
        
        schema.format = format;
        schema.title = title;
        schema.description = description;
        schema.default_ = default_;
        schema.multipleOf = multipleOf;
        schema.maximum = maximum;
        schema.exclusiveMaximum = exclusiveMaximum;
        schema.minimum = minimum;
        schema.exclusiveMinimum = exclusiveMinimum;
        schema.maxLength = maxLength;
        schema.minLength = minLength;
        schema.pattern = pattern;
        schema.maxItems = maxItems;
        schema.minItems = minItems;
        schema.uniqueItems = uniqueItems;
        schema.maxProperties = maxProperties;
        schema.minProperties = minProperties;
        schema.required = required;
        schema.enum_ = enum_;
        schema.type = type;
        schema.readOnly = readOnly;
        schema.example = example;
        
        if (items != null) {
            if (JsonCompat.isArray(items)) {
                List<OasSchema> schemaModels = new ArrayList<>();
                List<Object> itemList = JsonCompat.toList(items);
                for (Object item : itemList) {
                    OasSchema itemsSchemaModel = schema.createItemsSchema();
                    this.readSchema(item, itemsSchemaModel);
                    schemaModels.add(itemsSchemaModel);
                }
                schema.items = schemaModels;
            } else {
                schema.items = schema.createItemsSchema();
                this.readSchema(items, (OasSchema) schema.items);
            }
        }
        
        if (allOf != null) {
            List<OasSchema> schemaModels = new ArrayList<>();
            for (Object allOfSchema : allOf) {
                OasSchema allOfSchemaModel = schema.createAllOfSchema();
                this.readSchema(allOfSchema, allOfSchemaModel);
                schemaModels.add(allOfSchemaModel);
            }
            schema.allOf = schemaModels;
        }
        
        if (properties != null) {
            List<String> propertyNames = JsonCompat.keys(properties);
            for (String propertyName : propertyNames) {
                Object propertySchema = JsonCompat.consumeProperty(properties, propertyName);
                OasSchema propertySchemaModel = schema.createPropertySchema(propertyName);
                this.readSchema(propertySchema, propertySchemaModel);
                schema.addProperty(propertyName, propertySchemaModel);
            }
        }
        
        if (additionalProperties != null) {
            if (JsonCompat.isBoolean(additionalProperties)) {
                schema.additionalProperties = JsonCompat.toBoolean(additionalProperties);
            } else {
                OasSchema additionalPropertiesModel = schema.createAdditionalPropertiesSchema();
                this.readSchema(additionalProperties, additionalPropertiesModel);
                schema.additionalProperties = additionalPropertiesModel;
            }
        }
        
        if (xml != null) {
            schema.xml = schema.createXML();
            this.readXML(xml, schema.xml);
        }
        
        if (externalDocs != null) {
            schema.externalDocs = schema.createExternalDocumentation();
            this.readExternalDocumentation(externalDocs, schema.externalDocs);
        }

        super.readSchema(json, schema);
    }

    /**
     * Reads an XML.
     * @param json
     * @param node
     */
    public void readXML(Object json, OasXML node) {
        String name = JsonCompat.consumePropertyString(json, Constants.PROP_NAME);
        String namespace = JsonCompat.consumePropertyString(json, Constants.PROP_NAMESPACE);
        String prefix = JsonCompat.consumePropertyString(json, Constants.PROP_PREFIX);
        Boolean attribute = JsonCompat.consumePropertyBoolean(json, Constants.PROP_ATTRIBUTE);
        Boolean wrapped = JsonCompat.consumePropertyBoolean(json, Constants.PROP_WRAPPED);
        
        node.name = name;
        node.namespace = namespace;
        node.prefix = prefix;
        node.attribute = attribute;
        node.wrapped = wrapped;

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readParameter(java.lang.Object, io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    public void readParameter(Object json, Parameter node) {
        OasParameter param = (OasParameter) node;
        String in = JsonCompat.consumePropertyString(json, Constants.PROP_IN);
        Boolean required = JsonCompat.consumePropertyBoolean(json, Constants.PROP_REQUIRED);
        Boolean allowEmptyValue = JsonCompat.consumePropertyBoolean(json, Constants.PROP_ALLOW_EMPTY_VALUE);
        
        param.in = in;
        param.required = required;
        param.allowEmptyValue = allowEmptyValue;

        super.readParameter(json, node);
    }

    /**
     * Reads a single response.
     * @param json
     * @param node
     */
    public void readResponse(Object json, OasResponse node) {
        String $ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
        String description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);

        node.$ref = $ref;
        node.description = description;
        
        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * Reads a single header.
     * @param json
     * @param node
     */
    public void readHeader(Object json, OasHeader node) {
        node.description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
    }
}
