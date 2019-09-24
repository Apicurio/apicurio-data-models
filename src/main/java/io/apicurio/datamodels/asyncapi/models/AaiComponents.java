package io.apicurio.datamodels.asyncapi.models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiComponents extends Components {

    /**
     * Represents `Map[string, any]`.
     * Objects of `any` type are deserialized as follows:
     * In TypeScript, this field contains the raw JS object, in case of Java
     * it is a {@link com.fasterxml.jackson.databind.node.ObjectNode}.
     *
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#protocolInfoObject">AsyncAPI 2.0.0 spec</a>
     */
    public Map<String, Object> schemas;

    public Map<String, AaiMessage> messages;
    public Map<String, AaiSecurityScheme> securitySchemes;
    public Map<String, AaiParameter> parameters;
    public Map<String, AaiCorrelationId> correlationIds;
    public Map<String, AaiOperationTraitDefinition> operationTraits;
    public Map<String, AaiMessageTraitDefinition> messageTraits;
    public Map<String, AaiServerBindingsDefinition> serverBindings;
    public Map<String, AaiChannelBindingsDefinition> channelBindings;
    public Map<String, AaiOperationBindingsDefinition> operationBindings;
    public Map<String, AaiMessageBindingsDefinition> messageBindings;

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
    

    public void addSchema(String key, Object value) {
        if(schemas == null)
            schemas = new LinkedHashMap<>();
        schemas.put(key, value);
    }

    public void addMessage(String key, AaiMessage value) {
        if(messages == null)
            messages = new LinkedHashMap<>();
        messages.put(key, value);
    }

    public void addSecurityScheme(String key, AaiSecurityScheme value) {
        if(securitySchemes == null)
            securitySchemes = new LinkedHashMap<>();
        securitySchemes.put(key, value);
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
    
    public void addOperationTraitDefinition(String key, AaiOperationTraitDefinition value) {
        if(operationTraits == null)
            operationTraits = new LinkedHashMap<>();
        operationTraits.put(key, value);        
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
    
}
