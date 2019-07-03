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
     * Represents `Map[string, Schema Object | Reference Object]` type.
     */
    public Map<String, AaiHeaderItem> headers;

    public AaiCorrelationId correlationId;
    public String schemaFormat;
    public String contentType;
    public String name;
    public String title;
    public String summary;
    public String description;
    public List<Tag> tags;
    public ExternalDocumentation externalDocs;
    public Map<String, AaiProtocolInfo> protocolInfo;

    /**
     * Represents `Map[string, any]` type.
     * <p>
     * Objects of `any` type are deserialized as follows:
     * In TypeScript, this field contains the raw JS object, in case of Java
     * it is a {@link com.fasterxml.jackson.databind.node.ObjectNode}.
     *
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0-rc1/#schemaObject">AsyncAPI 2.0.0 spec</a>
     */
    public Map<String, Object> examples;
    
    /**
     * Constructor.
     */
    public AaiMessageBase(String name) {
        this._name = name;
    }

    public AaiMessageBase(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

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
    
    public abstract List<AaiHeaderItem> getHeadersList();
    public abstract List<AaiProtocolInfo> getProtocolInfoList();
    public abstract void addHeaderItem(AaiHeaderItem item);
    public abstract void addTag(AaiTag tag);
    public abstract void addProtocolInfo(AaiProtocolInfo item);
}
