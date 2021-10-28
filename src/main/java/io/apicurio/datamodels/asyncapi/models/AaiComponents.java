/*
 * Copyright 2020 Red Hat
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
package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.asyncapi.v2.models.Aai20SchemaDefinition;
import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.visitors.IVisitor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Models an AsyncAPI Components.
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiComponents extends Components {

    public Map<String, AaiSchema> schemas = new LinkedHashMap<>();
    public Map<String, AaiMessage> messages = new LinkedHashMap<>();
    public Map<String, AaiSecurityScheme> securitySchemes = new LinkedHashMap<>();
    public Map<String, AaiParameter> parameters = new LinkedHashMap<>();
    public Map<String, AaiCorrelationId> correlationIds = new LinkedHashMap<>();
    public Map<String, AaiOperationTraitDefinition> operationTraits = new LinkedHashMap<>();
    public Map<String, AaiMessageTraitDefinition> messageTraits = new LinkedHashMap<>();
    public Map<String, AaiServerBindingsDefinition> serverBindings = new LinkedHashMap<>();
    public Map<String, AaiChannelBindingsDefinition> channelBindings = new LinkedHashMap<>();
    public Map<String, AaiOperationBindingsDefinition> operationBindings = new LinkedHashMap<>();
    public Map<String, AaiMessageBindingsDefinition> messageBindings = new LinkedHashMap<>();

    /**
     * Constructor.
     */
    public AaiComponents() {
    }

    /**
     * Constructor.
     * @param parent
     */
    public AaiComponents(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    @Override
    public void accept(IVisitor visitor) {
        IAaiVisitor v = (IAaiVisitor) visitor;
        v.visitComponents(this);
    }

    public List<AaiMessage> getMessagesList() {
        return JsonCompat.mapToList(messages);
    }

    public List<AaiSecurityScheme> getSecuritySchemesList() {
        return JsonCompat.mapToList(securitySchemes);
    }

    public List<AaiParameter> getParametersList() {
        return JsonCompat.mapToList(parameters);
    }

    public List<AaiCorrelationId> getCorrelationIdsList() {
        return JsonCompat.mapToList(correlationIds);
    }

    public List<AaiOperationTraitDefinition> getOperationTraitDefinitionsList() {
        return JsonCompat.mapToList(operationTraits);
    }

    public List<AaiMessageTraitDefinition> getMessageTraitDefinitionsList() {
        return JsonCompat.mapToList(messageTraits);
    }
    
    public List<AaiServerBindingsDefinition> getServerBindingsDefinitionList() {
        return JsonCompat.mapToList(serverBindings);
    }

    public List<AaiChannelBindingsDefinition> getChannelBindingsDefinitionList() {
        return JsonCompat.mapToList(channelBindings);
    }

    public List<AaiOperationBindingsDefinition> getOperationBindingsDefinitionList() {
        return JsonCompat.mapToList(operationBindings);
    }

    public List<AaiMessageBindingsDefinition> getMessageBindingsDefinitionList() {
        return JsonCompat.mapToList(messageBindings);
    }
    

    public void addSchemaDefinition(String key, AaiSchema value) {
        if(schemas == null)
            schemas = new LinkedHashMap<>();
        schemas.put(key, value);
    }

    /**
     * Gets a single schema definition by name.
     * @param name
     */
    public AaiSchema getSchemaDefinition(String name) {
        if (this.schemas != null) {
            return this.schemas.get(name);
        }
        return null;
    }

    /**
     * Removes a single schema definition and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public AaiSchema removeSchemaDefinition(String name) {
        return this.schemas.remove(name);
    }

    /**
     * Gets a list of all schema definitions.
     */
    public List<AaiSchema> getSchemaDefinitions() {
        List<AaiSchema> rval = new ArrayList<>();
        if (this.schemas != null) {
            rval.addAll(this.schemas.values());
        }
        return rval;
    }

    public void addMessage(String key, AaiMessage value) {
        if(messages == null)
            messages = new LinkedHashMap<>();
        messages.put(key, value);
    }

    public AaiMessage getMessage(String name) {
        if (messages != null) {
            return messages.get(name);
        }
        return null;
    }

    public AaiMessage removeMessage(String name) {
        if (messages != null) {
            return messages.remove(name);
        }
        return null;
    }

    public void addSecurityScheme(String key, AaiSecurityScheme value) {
        if(securitySchemes == null)
            securitySchemes = new LinkedHashMap<>();
        securitySchemes.put(key, value);
    }

    public AaiSecurityScheme getSecurityScheme(String name) {
        if(securitySchemes != null)
            return securitySchemes.get(name);
        return null;
    }

    public AaiSecurityScheme removeSecurityScheme(String name) {
        if(securitySchemes != null)
            return securitySchemes.remove(name);
        return null;
    }

    public void addParameter(String key, AaiParameter value) {
        if(parameters == null)
            parameters = new LinkedHashMap<>();
        parameters.put(key, value);
    }

    public void addCorrelationId(String key, AaiCorrelationId value) {
        if(correlationIds == null)
            correlationIds = new LinkedHashMap<>();
        correlationIds.put(key, value);
    }

    public void addMessageTraitDefinition(String key, AaiMessageTraitDefinition value) {
        if(messageTraits == null)
            messageTraits = new LinkedHashMap<>();
        messageTraits.put(key, value);        
    }

    public AaiMessageTraitDefinition getMessageTraitDefinition(String name) {
        if (messageTraits != null) {
            return messageTraits.get(name);
        }
        return null;
    }

    public AaiMessageTraitDefinition removeMessageTraitDefinition(String name) {
        if (messageTraits != null) {
            return messageTraits.remove(name);
        }
        return null;
    }
    
    public void addOperationTraitDefinition(String key, AaiOperationTraitDefinition value) {
        if(operationTraits == null)
            operationTraits = new LinkedHashMap<>();
        operationTraits.put(key, value);        
    }

    public AaiOperationTraitDefinition getOperationTraitDefinition(String name) {
        if (operationTraits != null) {
            return operationTraits.get(name);
        }
        return null;
    }

    public AaiOperationTraitDefinition removeOperationTraitDefinition(String name) {
        if (operationTraits != null) {
            return operationTraits.remove(name);
        }
        return null;
    }
    
    public void addServerBindingDefinition(String key, AaiServerBindingsDefinition value) {
        if(serverBindings == null)
            serverBindings = new LinkedHashMap<>();
        serverBindings.put(key, value);        
    }
    
    public void addChannelBindingDefinition(String key, AaiChannelBindingsDefinition value) {
        if(channelBindings == null)
            channelBindings = new LinkedHashMap<>();
        channelBindings.put(key, value);        
    }
    
    public void addOperationBindingDefinition(String key, AaiOperationBindingsDefinition value) {
        if(operationBindings == null)
            operationBindings = new LinkedHashMap<>();
        operationBindings.put(key, value);        
    }
    
    public void addMessageBindingDefinition(String key, AaiMessageBindingsDefinition value) {
        if(messageBindings == null)
            messageBindings = new LinkedHashMap<>();
        messageBindings.put(key, value);        
    }

    /**
     * Creates a schema definition.
     * @param name
     */
    public Aai20SchemaDefinition createSchemaDefinition(String name) {
        Aai20SchemaDefinition rval = new Aai20SchemaDefinition(name);
        rval._ownerDocument = this._ownerDocument;
        rval._parent = this;
        return rval;
    }
}
