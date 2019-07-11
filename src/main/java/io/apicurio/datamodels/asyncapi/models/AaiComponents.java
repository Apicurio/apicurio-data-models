package io.apicurio.datamodels.asyncapi.models;

import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.Components;

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
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#protocolInfoObject">AsyncAPI 2.0.0 spec</a>
     */
    public Map<String, Object> schemas;

    public Map<String, AaiMessage> messages;
    public Map<String, AaiSecurityScheme> securitySchemes;
    public Map<String, AaiParameter> parameters;
    public Map<String, AaiCorrelationId> correlationIds;

    /**
     * Represents `Map[string, Operation Trait Object | Message Trait Object]` type.
     *
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#componentsObject">AsyncAPI 2.0.0 spec</a>
     */
    public Map<String, IAaiTrait> traits;

    public abstract List<AaiMessage> getMessagesList();
    public abstract List<AaiSecurityScheme> getSecuritySchemesList();
    public abstract List<AaiParameter> getParametersList();
    public abstract List<AaiCorrelationId> getCorrelationIdsList();
    public abstract List<IAaiTrait> getTraitsList();
    
    /**
     * Constructor.
     */
    public AaiComponents() {
    }

    public AaiComponents(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    public abstract void addSchema(String key, Object value);
    public abstract void addMessage(String key, AaiMessage value);
    public abstract void addSecurityScheme(String key, AaiSecurityScheme value);
    public abstract void addParameter(String key, AaiParameter value);
    public abstract void addCorrelationId(String key, AaiCorrelationId value);
    public abstract void addTrait(String key, IAaiTrait value);
}
