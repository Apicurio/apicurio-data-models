package io.apicurio.umg.base;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public interface Node extends Visitable {

    public int modelId();
    public Node root();
    public Node parent();
    public Map<String, JsonNode> extraProperties();
    public Object getAttribute(String attributeName);
    public void setAttribute(String attributeName, Object attributeValue);
    public Collection<String> getAttributeNames();
    public void clearAttributes();
    public void addExtraProperty(String key, JsonNode value);
    public JsonNode removeExtraProperty(String name);
    public boolean hasExtraProperties();
    public List<String> getExtraPropertyNames();
    public JsonNode getExtraProperty(String name);
    public boolean isAttached();
    public void attach(Node parent);

}
