package io.apicurio.umg.pipe.java;

import static java.util.Map.entry;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.apicurio.umg.models.concept.PropertyModel;
import io.apicurio.umg.models.concept.PropertyType;
import io.apicurio.umg.models.java.JavaFieldModel;

public class Util {

    public static Map<String, Class<?>> PRIMITIVE_TYPE_MAP = Map.ofEntries(
            entry("string", String.class),
            entry("boolean", Boolean.class),
            entry("number", Number.class),
            entry("integer", Integer.class),
            entry("object", ObjectNode.class),
            entry("any", JsonNode.class)
            );

    public static Map<String, String> JAVA_KEYWORD_MAP = Map.ofEntries(
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

    public static Class<?> primitiveTypeToClass(PropertyType type) {
        if (!type.isPrimitiveType()) {
            throw new UnsupportedOperationException("Property type not primitive: " + type);
        }
        Class<?> rval = PRIMITIVE_TYPE_MAP.get(type.getSimpleType());
        if (rval == null) {
            throw new UnsupportedOperationException("Primitive-to-class mapping not found for: " + type.getSimpleType());
        }
        return rval;
    }
}
