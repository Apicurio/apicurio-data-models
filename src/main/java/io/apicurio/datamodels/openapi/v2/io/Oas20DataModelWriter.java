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
import io.apicurio.datamodels.openapi.io.OasDataModelWriter;
import io.apicurio.datamodels.openapi.models.OasHeader;
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
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Schema;
import io.apicurio.datamodels.openapi.v2.models.Oas20Scopes;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityScheme;
import io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class Oas20DataModelWriter extends OasDataModelWriter implements IOas20Visitor {

    /**
     * @see io.apicurio.datamodels.core.io.DataModelWriter#writeDocument(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected void writeDocument(Document node, Object json) {
        Oas20Document doc = (Oas20Document) node;
        JsonCompat.setPropertyString(json, Constants.PROP_SWAGGER, doc.swagger);
        JsonCompat.setPropertyNull(json, Constants.PROP_INFO);
        JsonCompat.setPropertyString(json, Constants.PROP_HOST, doc.host);
        JsonCompat.setPropertyString(json, Constants.PROP_BASE_PATH, doc.basePath);
        JsonCompat.setPropertyStringArray(json, Constants.PROP_SCHEMES, doc.schemes);
        JsonCompat.setPropertyStringArray(json, Constants.PROP_CONSUMES, doc.consumes);
        JsonCompat.setPropertyStringArray(json, Constants.PROP_PRODUCES, doc.produces);
        JsonCompat.setPropertyNull(json, Constants.PROP_PATHS);
        JsonCompat.setPropertyNull(json, Constants.PROP_DEFINITIONS);
        JsonCompat.setPropertyNull(json, Constants.PROP_PARAMETERS);
        JsonCompat.setPropertyNull(json, Constants.PROP_RESPONSES);
        JsonCompat.setPropertyNull(json, Constants.PROP_SECURITY_DEFINITIONS);
        JsonCompat.setPropertyNull(json, Constants.PROP_SECURITY);
        JsonCompat.setPropertyNull(json, Constants.PROP_TAGS);
        JsonCompat.setPropertyNull(json, Constants.PROP_EXTERNAL_DOCS);
        
        writeExtraProperties(json, node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitParameterDefinition(io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinition)
     */
    @Override
    public void visitParameterDefinition(Oas20ParameterDefinition node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        this.writeParameter(json, node);
        this.writeExtraProperties(json, node);

        JsonCompat.setProperty(parent, node.getName(), json);

        this.updateIndex(node, json);        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitItems(io.apicurio.datamodels.openapi.v2.models.Oas20Items)
     */
    @Override
    public void visitItems(Oas20Items node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_TYPE, node.type);
        JsonCompat.setPropertyString(json, Constants.PROP_FORMAT, node.format);
        JsonCompat.setPropertyNull(json, Constants.PROP_ITEMS);
        JsonCompat.setPropertyString(json, Constants.PROP_COLLECTION_FORMAT, node.collectionFormat);
        JsonCompat.setProperty(json, Constants.PROP_DEFAULT, node.default_);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MAXIMUM, node.maximum);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_EXCLUSIVE_MAXIMUM, node.exclusiveMaximum);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MINIMUM, node.minimum);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_EXCLUSIVE_MINIMUM, node.exclusiveMinimum);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MAX_LENGTH, node.maxLength);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MIN_LENGTH, node.minLength);
        JsonCompat.setPropertyString(json, Constants.PROP_PATTERN, node.pattern);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MAX_ITEMS, node.maxItems);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MIN_ITEMS, node.minItems);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_UNIQUE_ITEMS, node.uniqueItems);
        JsonCompat.setPropertyStringArray(json, Constants.PROP_ENUM, node.enum_);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MULTIPLE_OF, node.multipleOf);
        this.writeExtraProperties(json, node);

        JsonCompat.setProperty(parent, Constants.PROP_ITEMS, json);

        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitSecurityDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20SecurityDefinitions)
     */
    @Override
    public void visitSecurityDefinitions(Oas20SecurityDefinitions node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        this.writeExtraProperties(json, node);

        JsonCompat.setProperty(parent, Constants.PROP_SECURITY_DEFINITIONS, json);

        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.core.io.DataModelWriter#writeSecurityScheme(java.lang.Object, io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    @Override
    protected void writeSecurityScheme(Object json, SecurityScheme node) {
        Oas20SecurityScheme node20 = (Oas20SecurityScheme) node;
        JsonCompat.setPropertyString(json, Constants.PROP_FLOW, node20.flow);
        JsonCompat.setPropertyString(json, Constants.PROP_AUTHORIZATION_URL, node20.authorizationUrl);
        JsonCompat.setPropertyString(json, Constants.PROP_TOKEN_URL, node20.tokenUrl);
        JsonCompat.setPropertyNull(json, Constants.PROP_SCOPES);
        super.writeSecurityScheme(json, node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitScopes(io.apicurio.datamodels.openapi.v2.models.Oas20Scopes)
     */
    @Override
    public void visitScopes(Oas20Scopes node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        for (String scope : node.getScopeNames()) {
            String description = node.getScopeDescription(scope);
            JsonCompat.setPropertyString(json, scope, description);
        }

        JsonCompat.setProperty(parent, Constants.PROP_SCOPES, json);

        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20Definitions)
     */
    @Override
    public void visitDefinitions(Oas20Definitions node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        if (node.getDefinitionNames().size() > 0) {
            JsonCompat.setProperty(parent, Constants.PROP_DEFINITIONS, json);

            this.updateIndex(node, json);
        }
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelWriter#writeSchema(java.lang.Object, io.apicurio.datamodels.core.models.common.Schema)
     */
    @Override
    protected void writeSchema(Object json, Schema node) {
        super.writeSchema(json, node);
        
        Oas20Schema schema = (Oas20Schema) node;
        JsonCompat.setPropertyString(json, Constants.PROP_DISCRIMINATOR, schema.discriminator);
    }

    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelWriter#writeOperation(java.lang.Object, io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    protected void writeOperation(Object json, Operation node) {
        Oas20Operation operation = (Oas20Operation) node;
        
        JsonCompat.setPropertyStringArray(json, Constants.PROP_CONSUMES, operation.consumes);
        JsonCompat.setPropertyStringArray(json, Constants.PROP_PRODUCES, operation.produces);
        JsonCompat.setPropertyStringArray(json, Constants.PROP_SCHEMES, operation.schemes);

        super.writeOperation(json, node);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitParameterDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinitions)
     */
    @Override
    public void visitParameterDefinitions(Oas20ParameterDefinitions node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        if (node.getParameterNames().size() > 0) {
            JsonCompat.setProperty(parent, Constants.PROP_PARAMETERS, json);

            this.updateIndex(node, json);
        }
    }

    /**
     * @see io.apicurio.datamodels.core.io.DataModelWriter#writeParameter(java.lang.Object, io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    protected void writeParameter(Object json, Parameter node) {
        super.writeParameter(json, node);
        
        Oas20Parameter param = (Oas20Parameter) node;
        JsonCompat.setPropertyString(json, Constants.PROP_TYPE, param.type);
        JsonCompat.setPropertyString(json, Constants.PROP_FORMAT, param.format);
        JsonCompat.setPropertyNull(json, Constants.PROP_ITEMS);
        JsonCompat.setPropertyString(json, Constants.PROP_COLLECTION_FORMAT, param.collectionFormat);
        JsonCompat.setProperty(json, Constants.PROP_DEFAULT, param.default_);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MAXIMUM, param.maximum);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_EXCLUSIVE_MAXIMUM, param.exclusiveMaximum);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MINIMUM, param.minimum);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_EXCLUSIVE_MINIMUM, param.exclusiveMinimum);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MAX_LENGTH, param.maxLength);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MIN_LENGTH, param.minLength);
        JsonCompat.setPropertyString(json, Constants.PROP_PATTERN, param.pattern);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MAX_ITEMS, param.maxItems);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MIN_ITEMS, param.minItems);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_UNIQUE_ITEMS, param.uniqueItems);
        JsonCompat.setPropertyStringArray(json, Constants.PROP_ENUM, param.enum_);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MULTIPLE_OF, param.multipleOf);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitExample(io.apicurio.datamodels.openapi.v2.models.Oas20Example)
     */
    @Override
    public void visitExample(Oas20Example node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        
        List<String> contentTypes = node.getExampleContentTypes();
        contentTypes.forEach(ct -> {
            Object example = node.getExample(ct);
            JsonCompat.setProperty(json, ct, example);
        });

        JsonCompat.setProperty(parent, Constants.PROP_EXAMPLES, json);

        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitHeaders(io.apicurio.datamodels.openapi.v2.models.Oas20Headers)
     */
    @Override
    public void visitHeaders(Oas20Headers node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        if (node.getHeaderNames().size() > 0) {
            JsonCompat.setProperty(parent, Constants.PROP_HEADERS, json);

            this.updateIndex(node, json);
        }        
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelWriter#writeHeader(java.lang.Object, io.apicurio.datamodels.openapi.models.OasHeader)
     */
    @Override
    protected void writeHeader(Object json, OasHeader node) {
        super.writeHeader(json, node);
        
        Oas20Header header = (Oas20Header) node;
        JsonCompat.setPropertyString(json, Constants.PROP_TYPE, header.type);
        JsonCompat.setPropertyString(json, Constants.PROP_FORMAT, header.format);
        JsonCompat.setPropertyNull(json, Constants.PROP_ITEMS);
        JsonCompat.setPropertyString(json, Constants.PROP_COLLECTION_FORMAT, header.collectionFormat);
        JsonCompat.setProperty(json, Constants.PROP_DEFAULT, header.default_);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MAXIMUM, header.maximum);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_EXCLUSIVE_MAXIMUM, header.exclusiveMaximum);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MINIMUM, header.minimum);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_EXCLUSIVE_MINIMUM, header.exclusiveMinimum);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MAX_LENGTH, header.maxLength);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MIN_LENGTH, header.minLength);
        JsonCompat.setPropertyString(json, Constants.PROP_PATTERN, header.pattern);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MAX_ITEMS, header.maxItems);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MIN_ITEMS, header.minItems);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_UNIQUE_ITEMS, header.uniqueItems);
        JsonCompat.setPropertyStringArray(json, Constants.PROP_ENUM, header.enum_);
        JsonCompat.setPropertyNumber(json, Constants.PROP_MULTIPLE_OF, header.multipleOf);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitResponseDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinitions)
     */
    @Override
    public void visitResponseDefinitions(Oas20ResponseDefinitions node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        if (node.getResponseNames().size() > 0) {
            JsonCompat.setProperty(parent, Constants.PROP_RESPONSES, json);

            this.updateIndex(node, json);
        }
    }
    
}
