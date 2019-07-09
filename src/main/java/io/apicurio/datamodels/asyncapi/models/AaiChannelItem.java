package io.apicurio.datamodels.asyncapi.models;

import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.INamed;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiChannelItem extends ExtensibleNode implements IReferenceNode, INamed {

    public String _name;
    public String $ref;
    public AaiOperation subscribe;
    public AaiOperation publish;
    public List<AaiParameter> parameters;
    public Map<String, AaiProtocolInfo> protocolInfo;
    public String description;

    /**
     * Constructor.
     */
    public AaiChannelItem(String name) {
        this._name = name;
    }

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
    
    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#getName()
     */
    @Override
    public String getName() {
        return this._name;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#rename(java.lang.String)
     */
    @Override
    public void rename(String newName) {
        this._name = newName;
    }

    public abstract List<AaiProtocolInfo> getProtocolInfoList();
    public abstract void addParameter(AaiParameter param);
    public abstract void addProtocolInfo(String key, AaiProtocolInfo protocolInfo);
}
