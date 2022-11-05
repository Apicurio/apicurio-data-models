package io.apicurio.umg.base.util;

public class ReaderUtil {

    public static final void readExtraProperties(ObjectNode json, Node node) {
        if (json != null) {
            JsonUtil.keys(json).forEach(key -> {
                JsonNode value = JsonUtil.consumeAnyProperty(json, key);
                node.addExtraProperty(key, value);
            });
        }
    }
    
}
