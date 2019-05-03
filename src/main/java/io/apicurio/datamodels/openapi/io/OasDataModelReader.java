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
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.models.OasSecurityRequirement;
import io.apicurio.datamodels.openapi.models.OasXML;

/**
 * A data model reader for the OpenAPI data model.
 * @author eric.wittmann@gmail.com
 */
public abstract class OasDataModelReader<T extends OasDocument> extends DataModelReader<T> {
    
    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readDocument(java.lang.Object, io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void readDocument(Object json, T node) {
        super.readDocument(json, node);
        
        Object paths = JsonCompat.consumeProperty(json, Constants.PROP_PATHS);
        List<Object> security = JsonCompat.consumePropertyArray(json, Constants.PROP_SECURITY);
        
        if (paths != null) {
            // TODO read paths
        }
        
        if (security != null) {
            security.forEach(sec -> {
                OasSecurityRequirement secModel = node.createSecurityRequirement();
                this.readSecurityRequirement(sec, secModel);
                node.addSecurityRequirement(secModel);
            });
        }
    }

    /**
     * Reads a schema.
     * @param json
     * @param node
     */
    public void readSchema(Object json, OasSchema node) {
        String $ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
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
        
        node.$ref = $ref;
        node.format = format;
        node.title = title;
        node.description = description;
        node.default_ = default_;
        node.multipleOf = multipleOf;
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
        node.maxProperties = maxProperties;
        node.minProperties = minProperties;
        node.required = required;
        node.enum_ = enum_;
        node.type = type;
        node.readOnly = readOnly;
        node.example = example;
        
        if (items != null) {
            if (JsonCompat.isArray(items)) {
                List<OasSchema> schemaModels = new ArrayList<>();
                List<Object> itemList = JsonCompat.toList(items);
                for (Object item : itemList) {
                    OasSchema itemsSchemaModel = node.createItemsSchema();
                    this.readSchema(item, itemsSchemaModel);
                    schemaModels.add(itemsSchemaModel);
                }
                node.items = schemaModels;
            } else {
                node.items = node.createItemsSchema();
                this.readSchema(items, (OasSchema) node.items);
            }
        }
        
        if (allOf != null) {
            List<OasSchema> schemaModels = new ArrayList<>();
            for (Object allOfSchema : allOf) {
                OasSchema allOfSchemaModel = node.createAllOfSchema();
                this.readSchema(allOfSchema, allOfSchemaModel);
                schemaModels.add(allOfSchemaModel);
            }
            node.allOf = schemaModels;
        }
        
        if (properties != null) {
            List<String> propertyNames = JsonCompat.keys(properties);
            for (String propertyName : propertyNames) {
                Object propertySchema = JsonCompat.consumeProperty(properties, propertyName);
                OasSchema propertySchemaModel = node.createPropertySchema(propertyName);
                this.readSchema(propertySchema, propertySchemaModel);
                node.addProperty(propertyName, propertySchemaModel);
            }
        }
        
        if (additionalProperties != null) {
            if (JsonCompat.isBoolean(additionalProperties)) {
                node.additionalProperties = JsonCompat.toBoolean(additionalProperties);
            } else {
                OasSchema additionalPropertiesModel = node.createAdditionalPropertiesSchema();
                this.readSchema(additionalProperties, additionalPropertiesModel);
                node.additionalProperties = additionalPropertiesModel;
            }
        }
        
        if (xml != null) {
            node.xml = node.createXML();
            this.readXML(xml, node.xml);
        }
        
        if (externalDocs != null) {
            node.externalDocs = node.createExternalDocumentation();
            this.readExternalDocumentation(externalDocs, node.externalDocs);
        }

        super.readSchema(json, node);
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
