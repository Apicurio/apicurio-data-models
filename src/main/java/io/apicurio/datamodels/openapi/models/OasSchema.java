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

package io.apicurio.datamodels.openapi.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.IExternalDocumentationParent;
import io.apicurio.datamodels.core.models.common.IPropertyParent;
import io.apicurio.datamodels.core.models.common.Schema;

/**
 * Models an OpenAPI schema.
 * @author eric.wittmann@gmail.com
 */
public abstract class OasSchema extends Schema implements IExternalDocumentationParent, IPropertyParent {

    public String format;
    public String title;
    public String description;
    public Object default_;
    public Number multipleOf;
    public Number maximum;
    public Boolean exclusiveMaximum;
    public Number minimum;
    public Boolean exclusiveMinimum;
    public Number maxLength;
    public Number minLength;
    public String pattern;
    public Number maxItems;
    public Number minItems;
    public Boolean uniqueItems;
    public Number maxProperties;
    public Number minProperties;
    public List<String> required;
    public List<String> enum_;
    public String type;

    public Object items; // May be an OasSchema or List<OasSchema>
    public List<OasSchema> allOf;
    public Map<String, OasSchema> properties;
    public Object additionalProperties; // May be a Boolean or OasSchema

    public Boolean readOnly;
    public OasXML xml;
    public ExternalDocumentation externalDocs;
    public Object example;

    /**
     * Creates a child external documentation model.
     */
    public abstract ExternalDocumentation createExternalDocumentation();
    
    /**
     * @see io.apicurio.datamodels.core.models.common.IExternalDocumentationParent#setExternalDocumentation(io.apicurio.datamodels.core.models.common.ExternalDocumentation)
     */
    @Override
    public void setExternalDocumentation(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    /**
     * Creates a child XML model.
     */
    public abstract OasXML createXML();

    /**
     * Creates a child schema model.
     */
    public abstract OasSchema createAllOfSchema();

    /**
     * Creates a child schema model.
     */
    public abstract OasSchema createItemsSchema();

    /**
     * Creates a child schema model.
     */
    public abstract OasSchema createAdditionalPropertiesSchema();

    /**
     * @see IPropertyParent#createPropertySchema(String) 
     */
    @Override
    public abstract Schema createPropertySchema(String propertyName);

    /**
     * @see IPropertyParent#getPropertyNames() 
     */
    @Override
    public List<String> getPropertyNames() {
        List<String> rval = new ArrayList<>();
        if (ModelUtils.isDefined(this.properties)) {
            rval.addAll(this.properties.keySet());
        }
        return rval;
    }

    /**
     * @see IPropertyParent#getProperties() 
     */
    @Override
    public List<Schema> getProperties() {
        List<Schema> rval = new ArrayList<>();
        if (ModelUtils.isDefined(this.properties)) {
            rval.addAll(this.properties.values());
        }
        return rval;
    }

    /**
     * @see IPropertyParent#addProperty(String, Schema) 
     */
    @Override
    public Schema addProperty(String propertyName, Schema schema) {
        if (ModelUtils.isNullOrUndefined(this.properties)) {
            this.properties = new LinkedHashMap<>();
        }
        this.properties.put(propertyName, (OasSchema) schema);
        return schema;
    }

    /**
     * @see IPropertyParent#removeProperty(String) 
     */
    @Override
    public Schema removeProperty(String propertyName) {
        if (ModelUtils.isDefined(this.properties)) {
            return this.properties.remove(propertyName);
        }
        return null;
    }
    
    @Override
    public void renameProperty(String oldPropertyName, String newPropertyName, Consumer<Schema> propertyConsumer) {
        this.properties = ModelUtils.renameMapKey(oldPropertyName, newPropertyName, this.properties, propertyConsumer);
    }
    
    @Override
    public void restoreProperty(Integer index, String propertyName, Schema schema) {
        this.properties = ModelUtils.restoreMapEntry(index, propertyName, (OasSchema) schema, this.properties);
    }

    /**
     * @see IPropertyParent#getProperty(String) 
     */
    @Override
    public Schema getProperty(String propertyName) {
        if (ModelUtils.isDefined(this.properties)) {
            return this.properties.get(propertyName);
        }
        return null;
    }

    /**
     * @see IPropertyParent#getRequiredProperties() 
     */
    @Override
    public List<String> getRequiredProperties() {
        return this.required;
    }

    /**
     * @see IPropertyParent#setRequiredProperties(List)
     */
    @Override
    public void setRequiredProperties(List<String> requiredProperties) {
        this.required = requiredProperties;
    }
    
    /**
     * @see IPropertyParent#isPropertyRequired(String) 
     */
    @Override
    public boolean isPropertyRequired(String propertyName) {
        if (ModelUtils.isDefined(this.required)) {
            return this.required.contains(propertyName);
        }
        return false;
    }

    /**
     * @see IPropertyParent#setPropertyRequired(String)
     */
    @Override
    public void setPropertyRequired(String propertyName) {
        if (ModelUtils.isNullOrUndefined(this.required)) {
            this.required = new ArrayList<>();
        }
        if (!this.required.contains(propertyName)) {
            this.required.add(propertyName);
        }
    }

    /**
     * @see IPropertyParent#unsetPropertyRequired(String)
     */
    @Override
    public void unsetPropertyRequired(String propertyName) {
        if (ModelUtils.isDefined(this.required)) {
            this.required.remove(propertyName);
            if (this.required.isEmpty()) {
                this.required = null;
            }
        }
    }

    /**
     * Returns true if there is a single items schema.
     */
    public boolean hasItemsSchema() {
        return NodeCompat.isNode(this.items);
    }

    /**
     * Returns true if there is a list of items schemas.  Even if there is only one schema in the list.
     */
    public boolean hasItemsSchemas() {
        return NodeCompat.isList(this.items);
    }
    
    /**
     * Returns true if there is an additional properties schema.
     */
    public boolean hasAdditionalPropertiesSchema() {
        return NodeCompat.isNode(this.additionalProperties);
    }
    
    /**
     * Returns true if there is an "additionalProperties" property that is a boolean value and not a schema.
     */
    public boolean hasAdditionalPropertiesBoolean() {
        return this.additionalProperties != null && !NodeCompat.isNode(this.additionalProperties);
    }

    /**
     * Adds an AllOf schema.
     * @param schema
     */
    public void addAllOfSchema(OasSchema schema) {
        if (this.allOf == null) {
            this.allOf = new ArrayList<>();
        }
        this.allOf.add(schema);
    }
    
    /**
     * Removes a allOf schema.
     * @param schema
     */
    public void removeAllOfSchema(OasSchema schema) {
        if (this.allOf != null) {
            this.allOf.remove(schema);
        }
    }
    
}