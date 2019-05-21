package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.Tag;

import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * Contains shared fields in {@link AaiMessage} and {@link AaiMessageTrait}
 *
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiMessageBase extends ExtensibleNode {

    public String _name; // Map
    public String $ref;

    /**
     * NOT Required.
     * <p>
     * Represents `Map[string, Schema Object | Reference Object]` type.
     */
    public Map<String, AaiHeaderItem> headers;

    /**
     * NOT Required.
     */
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

    public AaiMessageBase(Node parent) {
        requireNonNull(parent);
        this._parent = parent;
        this._ownerDocument = parent.ownerDocument();
    }

    public AaiMessageBase(Node parent, String _name) {
        requireNonNull(parent);
        this._parent = parent;
        this._ownerDocument = parent.ownerDocument();
        this._name = _name;
    }

    public String getName() {
        return _name;
    }

    public abstract List<AaiHeaderItem> getHeadersList();

    public abstract List<AaiProtocolInfo> getProtocolInfoList();

    public abstract void addHeaderItem(AaiHeaderItem item);

    public abstract void addTag(AaiTag tag);

    public abstract void addProtocolInfo(AaiProtocolInfo item);
}
