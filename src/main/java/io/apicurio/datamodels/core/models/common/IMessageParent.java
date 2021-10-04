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

/**
 * @author vvilerio
 */
public interface IMessageParent {
    
    /**
     * Creates a child schema model.
     */
    public Schema createOneOfMessage(String oneOfMessageName);

    /**
     * Gets a list of all message names.
     */
    public List<String> getMessageNames();

    /**
     * Gets a list of all the properties.
     */
    public List<Schema> getMessages();

    /**
     * Add a message.
     * @param messageName
     * @param schema
     */
    public Schema addMessage(String messageName, Schema schema);

    /**
     * Removes a message by name.
     * @param messageName
     */
    public Schema removeMessage(String messageName);

    /**
     * Gets a single message.
     * @param messageName
     */
    public Schema getMessage(String messageName);

    /**
     * Gets the required properties list.
     */
    public List<String> getRequiredProperties();

    /**
     * Sets the required properties list.
     */
    public void setRequiredProperties(List<String> requiredProperties);

    /**
     * Returns true if the message is required.
     */
    public boolean isMessageRequired(String messageName);

    /**
     * Sets the message as required.
     */
    public void setMessageRequired(String messageName);

    /**
     * Unsets the message as required.
     */
    public void unsetMessageRequired(String messageName);

}
