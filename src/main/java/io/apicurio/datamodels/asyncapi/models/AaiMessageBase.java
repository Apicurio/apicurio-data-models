package io.apicurio.datamodels.asyncapi.models;

import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.INamed;
import io.apicurio.datamodels.core.models.common.Tag;

/**
 * Contains shared fields in {@link AaiMessage} and {@link AaiMessageTrait}
 *
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiMessageBase extends ExtensibleNode implements INamed {

    public String _name; // Map
    public String $ref;

    /**
     * Represents `Schema Object | Reference Object` type.
     */
    public AaiHeaderItem headers;

    public AaiCorrelationId correlationId;
    public String schemaFormat;
    public String contentType;
    public String name;
    public String title;
    public String summary;
    public String description;
    public List<Tag> tags;
    public ExternalDocumentation externalDocs;
    public AaiMessageBindings bindings;

    /**
     * Represents `Map[string, any]` type.
     * <p>
     * Objects of `any` type are deserialized as follows:
     * In TypeScript, this field contains the raw JS object, in case of Java
     * it is a {@link com.fasterxml.jackson.databind.node.ObjectNode}.
     *
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#schemaObject">AsyncAPI 2.0.0 spec</a>
     */
    public Map<String, Object> examples;
    
    /**
     * Constructor.
     */
    public AaiMessageBase(String name) {
        this._name = name;
    }

    /**
     * Constructor.
     * @param parent
     */
    public AaiMessageBase(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    /**
     * Constructor.
     * @param parent
     * @param name
     */
    public AaiMessageBase(Node parent, String name) {
        this(parent);
        this._name = name;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#getName()
     */
    @Override
    public String getName() {
        return _name;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#rename(java.lang.String)
     */
    @Override
    public void rename(String newName) {
        this._name = newName;
    }
    
    public abstract void addTag(AaiTag tag);
}
