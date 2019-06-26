package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiParameter;
import io.apicurio.datamodels.asyncapi.models.AaiProtocolInfo;
import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;


public class Aai20ChannelItem extends AaiChannelItem {


    public Aai20ChannelItem(Node parent) {
        super(parent);
    }

    public Aai20ChannelItem(Node parent, String name) {
        super(parent, name);
    }

    @Override
    public void accept(IVisitor visitor) {
        IAai20Visitor v = (IAai20Visitor) visitor;
        v.visitChannelItem(this);
    }

    @Override
    public List<AaiProtocolInfo> getProtocolInfoList() {
        return JsonCompat.mapToList(protocolInfo);
    }

    @Override
    public void addParameter(AaiParameter param) {
        if(parameters == null)
            parameters = new LinkedList<>();
        parameters.add(param);
    }

    @Override
    public void addProtocolInfo(String key, AaiProtocolInfo pi) {
        if(protocolInfo == null)
            protocolInfo = new LinkedHashMap<>();
        protocolInfo.put(key, pi);
    }
}
