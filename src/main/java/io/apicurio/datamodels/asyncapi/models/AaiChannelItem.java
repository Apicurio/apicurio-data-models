package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.models.Node;

import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiChannelItem extends ExtensibleNode implements IReferenceNode {

    public String _name;

    /**
     * NOT Required.
     */
    public String $ref;

    /**
     * NOT Required.
     */
    public AaiOperation subscribe;

    /**
     * NOT Required.
     */
    public AaiOperation publish;

    /**
     * NOT Required.
     */
    public List<AaiParameter> parameters;

    /**
     * NOT Required.
     */
    public Map<String, AaiProtocolInfo> protocolInfo;
    public String description;

    public AaiChannelItem(String _name) {
        this._name = _name;
    }

    public AaiChannelItem(Node parent, String _name) {
        requireNonNull(parent);
        requireNonNull(_name);
        this._parent = parent;
        this._ownerDocument = parent.ownerDocument();
        this._name = _name;
    }

    public String getName() {
        return _name;
    }

    public abstract List<AaiProtocolInfo> getProtocolInfoList();

    public abstract void addParameter(AaiParameter param);

    public abstract void addProtocolInfo(String key, AaiProtocolInfo protocolInfo);
}
