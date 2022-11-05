package io.apicurio.umg.base.util;

import io.apicurio.umg.base.Node;

public class WriterUtil {

    public static final void writeExtraProperties(Node node, ObjectNode json) {
        if (node.hasExtraProperties()) {
            node.getExtraPropertyNames().forEach(name -> {
                JsonNode value = node.getExtraProperty(name);
                JsonUtil.setProperty(json, name, value);
            });
        }
    }
    
}
