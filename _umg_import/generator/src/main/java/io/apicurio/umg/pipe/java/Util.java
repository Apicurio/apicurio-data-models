package io.apicurio.umg.pipe.java;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import io.apicurio.umg.models.concept.PropertyModel;
import io.apicurio.umg.models.java.JavaFieldModel;

public class Util {

    private static Map<String, String> JAVA_KEYWORD_MAP = Map.ofEntries(
            Map.entry("default", "_default"),
            Map.entry("enum", "_enum"),
            Map.entry("const", "_const"),
            Map.entry("if", "_if"),
            Map.entry("else", "_else")
            );

    public static String sanitizeFieldName(String name) {
        return JAVA_KEYWORD_MAP.getOrDefault(name, name);
    }

    public static String fieldGetter(JavaFieldModel fieldModel) {
        boolean isBool = fieldModel.getPrimitiveType() != null && fieldModel.getPrimitiveType().endsWith("Boolean");
        return (isBool ? "is" : "get") + StringUtils.capitalize(fieldModel.getName());
    }

    public static String fieldSetter(JavaFieldModel fieldModel) {
        return "set" + StringUtils.capitalize(fieldModel.getName());
    }

    public static String fieldGetter(PropertyModel propertyModel) {
        boolean isBool = propertyModel.getType().isPrimitiveType() && propertyModel.getType().getSimpleType().equals("boolean");
        return (isBool ? "is" : "get") + StringUtils.capitalize(propertyModel.getName());
    }

    public static String fieldSetter(PropertyModel propertyModel) {
        return "set" + StringUtils.capitalize(propertyModel.getName());
    }
}
