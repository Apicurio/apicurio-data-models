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

package io.apicurio.datamodels.core.models.common;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author c.desc2@gmail.com
 */
public interface IPropertyParent {
    
    /**
     * Creates a child schema model.
     */
    public Schema createPropertySchema(String propertyName);

    /**
     * Gets a list of all property names.
     */
    public List<String> getPropertyNames();

    /**
     * Gets a list of all the properties.
     */
    public List<Schema> getProperties();

    /**
     * Add a property.
     * @param propertyName
     * @param schema
     */
    public Schema addProperty(String propertyName, Schema schema);

    /**
     * Removes a property by name.
     * @param propertyName
     */
    public Schema removeProperty(String propertyName);
    
    /**
     * Restore a deleted property in its original position
     * @param index
     * @param propertyName
     * @param schema
     */
    public void restoreProperty(int index, String propertyName, Schema schema);
    
    /**
     * Rename a property.
     * @param oldPropertyName
     */
    public void renameProperty(String oldPropertyName, String newPropertyName, Consumer<Schema> propertyConsumer);

    /**
     * Gets a single property.
     * @param propertyName
     */
    public Schema getProperty(String propertyName);

    /**
     * Gets the required properties list.
     */
    public List<String> getRequiredProperties();

    /**
     * Sets the required properties list.
     */
    public void setRequiredProperties(List<String> requiredProperties);

    /**
     * Returns true if the property is required.
     */
    public boolean isPropertyRequired(String propertyName);

    /**
     * Sets the property as required.
     */
    public void setPropertyRequired(String propertyName);

    /**
     * Unsets the property as required.
     */
    public void unsetPropertyRequired(String propertyName);

}
