package io.apicurio.datamodels.asyncapi.models;

import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.models.Node;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiChannelItem extends ExtensibleNode implements IReferenceNode {

    public String _name;
    public String $ref;
    public AaiOperation subscribe;
    public AaiOperation publish;
    public List<AaiParameter> parameters;
    public Map<String, AaiProtocolInfo> protocolInfo;
    public String description;


    public AaiChannelItem(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    public AaiChannelItem(Node parent, String name) {
        this(parent);
        this._name = name;
    }

    public String getName() {
        return _name;
    }

    public abstract List<AaiProtocolInfo> getProtocolInfoList();
    public abstract void addParameter(AaiParameter param);
    public abstract void addProtocolInfo(String key, AaiProtocolInfo protocolInfo);
}
