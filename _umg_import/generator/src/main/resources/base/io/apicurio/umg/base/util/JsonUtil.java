package io.apicurio.umg.base.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil {

    public static String getStringProperty(ObjectNode json, String propertyName) {
        if (json.has(propertyName)) {
            JsonNode textNode = json.get(propertyName);
            if (textNode.isTextual()) {
                return textNode.asText();
            }
        }
        return null;
    }

    public static String consumeStringProperty(ObjectNode json, String propertyName) {
        if (json.has(propertyName)) {
            String rval = getStringProperty(json, propertyName);
            json.remove(propertyName);
            return rval;
        }
        return null;
    }

}
