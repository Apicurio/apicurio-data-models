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

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.io.DataModelWriter;
import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.models.common.IExample;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.openapi.models.IOasPropertySchema;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.models.OasPaths;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.models.OasResponses;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.models.OasXML;
import io.apicurio.datamodels.openapi.visitors.IOasVisitor;

/**
 * Data model writer for OpenAPI data models (base class).
 * @author eric.wittmann@gmail.com
 */
public class OasDataModelWriter extends DataModelWriter implements IOasVisitor {

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitPaths(io.apicurio.datamodels.openapi.models.OasPaths)
     */
    @Override
    public void visitPaths(OasPaths node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        this.writeExtraProperties(json, node);
        
        JsonCompat.setProperty(parent, Constants.PROP_PATHS, json);
        
        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitPathItem(io.apicurio.datamodels.openapi.models.OasPathItem)
     */
    @Override
    public void visitPathItem(OasPathItem node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        this.writePathItem(json, node);
        this.writeExtraProperties(json, node);
        
        JsonCompat.setProperty(parent, node.getPath(), json);
        
        this.updateIndex(node, json);
    }
    protected void writePathItem(Object json, OasPathItem node) {
        JsonCompat.setPropertyString(json, Constants.PROP_$REF, node.$ref);
        JsonCompat.setPropertyNull(json, Constants.PROP_GET);
        JsonCompat.setPropertyNull(json, Constants.PROP_PUT);
        JsonCompat.setPropertyNull(json, Constants.PROP_POST);
        JsonCompat.setPropertyNull(json, Constants.PROP_DELETE);
        JsonCompat.setPropertyNull(json, Constants.PROP_OPTIONS);
        JsonCompat.setPropertyNull(json, Constants.PROP_HEAD);
        JsonCompat.setPropertyNull(json, Constants.PROP_PATCH);
        JsonCompat.setPropertyNull(json, Constants.PROP_PARAMETERS);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitResponse(io.apicurio.datamodels.openapi.models.OasResponse)
     */
    @Override
    public void visitResponse(OasResponse node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        writeResponse(json, node);
        this.writeExtraProperties(json, node);

        String responseCode = node.getName();
        if (responseCode == null) {
            responseCode = Constants.PROP_DEFAULT;
        }
        JsonCompat.setProperty(parent, responseCode, json);
        
        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitResponses(io.apicurio.datamodels.openapi.models.OasResponses)
     */
    @Override
    public void visitResponses(OasResponses node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyNull(json, Constants.PROP_DEFAULT);
        this.writeExtraProperties(json, node);
        
        JsonCompat.setProperty(parent, Constants.PROP_RESPONSES, json);
        
        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitXML(io.apicurio.datamodels.openapi.models.OasXML)
     */
    @Override
    public void visitXML(OasXML node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_NAME, node.name);
        JsonCompat.setPropertyString(json, Constants.PROP_NAMESPACE, node.namespace);
        JsonCompat.setPropertyString(json, Constants.PROP_PREFIX, node.prefix);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_ATTRIBUTE, node.attribute);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_WRAPPED, node.wrapped);
        this.writeExtraProperties(json, node);

        JsonCompat.setProperty(parent, Constants.PROP_XML, json);
        
        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitAllOfSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitAllOfSchema(OasSchema node) {
        this.doVisitSchema(node, Constants.PROP_ALL_OF, true);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitItemsSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitItemsSchema(OasSchema node) {
        OasSchema parentSchema = (OasSchema) node;
        this.doVisitSchema(node, Constants.PROP_ITEMS, NodeCompat.isList(parentSchema.items));
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitAdditionalPropertiesSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitAdditionalPropertiesSchema(OasSchema node) {
        this.doVisitSchema(node, Constants.PROP_ADDITIONAL_PROPERTIES, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitPropertySchema(io.apicurio.datamodels.openapi.models.IOasPropertySchema)
     */
    @Override
    public void visitPropertySchema(IOasPropertySchema node) {
        OasSchema schema = (OasSchema) node;
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
     * @see io.apicurio.datamodels.core.io.DataModelWriter#writeSchema(java.lang.Object, io.apicurio.datamodels.core.models.common.Schema)
     */
    @Override
    protected void writeSchema(Object json, Schema node) {
        OasSchema schema = (OasSchema) node;
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
        JsonCompat.setPropertyStringArray(json, Constants.PROP_ENUM, schema.enum_);
        JsonCompat.setPropertyString(json, Constants.PROP_TYPE, schema.type);

        JsonCompat.setPropertyNull(json, Constants.PROP_ITEMS);
        JsonCompat.setPropertyNull(json, Constants.PROP_ALL_OF);
        JsonCompat.setPropertyNull(json, Constants.PROP_PROPERTIES);
        if (NodeCompat.isNode(schema.additionalProperties)) {
            JsonCompat.setPropertyNull(json, Constants.PROP_ADDITIONAL_PROPERTIES);
        } else {
            JsonCompat.setPropertyBoolean(json, Constants.PROP_ADDITIONAL_PROPERTIES, (Boolean) schema.additionalProperties);
        }

        JsonCompat.setPropertyBoolean(json, Constants.PROP_READ_ONLY, schema.readOnly);
        JsonCompat.setPropertyNull(json, Constants.PROP_XML);
        JsonCompat.setPropertyNull(json, Constants.PROP_EXTERNAL_DOCS);
        JsonCompat.setProperty(json, Constants.PROP_EXAMPLE, schema.example);
        
        super.writeSchema(json, schema);
    }
    
    /**
     * @see io.apicurio.datamodels.core.io.DataModelWriter#writeOperation(java.lang.Object, io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    protected void writeOperation(Object json, Operation node) {
        OasOperation operation = (OasOperation) node;
        
        JsonCompat.setPropertyStringArray(json, Constants.PROP_TAGS, operation.tags);
        JsonCompat.setPropertyNull(json, Constants.PROP_PARAMETERS);
        JsonCompat.setPropertyNull(json, Constants.PROP_RESPONSES);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_DEPRECATED, operation.deprecated);
        JsonCompat.setPropertyNull(json, Constants.PROP_SECURITY);
        
        super.writeOperation(json, node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.io.DataModelWriter#writeParameter(java.lang.Object, io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    protected void writeParameter(Object json, Parameter node) {
        super.writeParameter(json, node);
        
        OasParameter param = (OasParameter) node;
        JsonCompat.setPropertyString(json, Constants.PROP_IN, param.in);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_REQUIRED, param.required);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_ALLOW_EMPTY_VALUE, param.allowEmptyValue);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitHeader(io.apicurio.datamodels.openapi.models.OasHeader)
     */
    @Override
    public void visitHeader(OasHeader node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        writeHeader(json, node);
        this.writeExtraProperties(json, node);

        addHeaderToParent(parent, json, node);
        
        this.updateIndex(node, json);
    }
    protected void writeHeader(Object json, OasHeader node) {
        JsonCompat.setProperty(json, Constants.PROP_DESCRIPTION, node.description);
    }
    protected void addHeaderToParent(Object parent, Object json, OasHeader node) {
        JsonCompat.setProperty(parent, node.getName(), json);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitResponseDefinition(io.apicurio.datamodels.core.models.common.IDefinition)
     */
    @Override
    public void visitResponseDefinition(IDefinition node) {
        OasResponse response = (OasResponse) node;
        
        Object parent = this.lookupParentJson(response);
        Object json = JsonCompat.objectNode();
        writeResponse(json, response);
        this.writeExtraProperties(json, response);

        addResponseDefinitionToParent(parent, json, node);
        
        this.updateIndex(response, json);
    }
    protected void writeResponse(Object json, OasResponse node) {
        JsonCompat.setPropertyString(json, Constants.PROP_$REF, node.$ref);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
    }
    protected void addResponseDefinitionToParent(Object parent, Object json, IDefinition node) {
        JsonCompat.setProperty(parent, node.getName(), json);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitExample(io.apicurio.datamodels.core.models.common.IExample)
     */
    @Override
    public void visitExample(IExample node) {
        throw new RuntimeException("Must be implemented by subclasses.");
    }

}
