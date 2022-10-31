package io.apicurio.umg.base.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.fasterxml.jackson.databind.util.TokenBuffer;


public class JsonUtil {

    private static final JsonNodeFactory factory = JsonNodeFactory.instance;
    private static final ObjectMapper mapper = new ObjectMapper();
    
    public static List<String> keys(ObjectNode json) {
        List<String> rval = new ArrayList<>();
        if (json != null) {
            Iterator<String> fieldNames = json.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                rval.add(fieldName);
            }
        }
        return rval;
    }
    
    /* Get/Consume a JSON (Object) property. */
    public static ObjectNode getJsonProperty(ObjectNode json, String propertyName) {
        if (json.has(propertyName)) {
            JsonNode objectNode = json.get(propertyName);
            if (objectNode.isObject()) {
                return (ObjectNode) objectNode;
            }
        }
        return null;
    }
    public static ObjectNode consumeJsonProperty(ObjectNode json, String propertyName) {
        if (json.has(propertyName)) {
            ObjectNode rval = getJsonProperty(json, propertyName);
            if (rval != null) {
                json.remove(propertyName);
                return rval;
            }
        }
        return null;
    }
    
    /* Get/consume an array of objects property. */
    public static List<JsonNode> getJsonArrayProperty(ObjectNode json, String propertyName) {
        if (!json.has(propertyName)) {
            return null;
        }
        JsonNode node = json.get(propertyName);
        if (node.isArray()) {
            ArrayNode array = (ArrayNode) node;
            List<JsonNode> rval = new ArrayList<>();
            for (JsonNode item : array) {
                rval.add(item);
            }
            return rval;
        } else {
            return null;
        }
    }
    public static List<JsonNode> consumeJsonArrayProperty(ObjectNode json, String propertyName) {
        if (json.has(propertyName)) {
            List<JsonNode> rval = getJsonArrayProperty(json, propertyName);
            json.remove(propertyName);
            return rval;
        }
        return null;
    }
    

    /* Get/consume an array of strings property. */
    public static List<String> getStringArrayProperty(ObjectNode json, String propertyName) {
        if (!json.has(propertyName)) {
            return null;
        }
        JsonNode node = json.get(propertyName);
        if (node.isArray()) {
            ArrayNode array = (ArrayNode) node;
            List<String> rval = new ArrayList<>();
            for (JsonNode item : array) {
                rval.add(item.isNull() ? null : item.asText());
            }
            return rval;
        } else {
            return null;
        }
    }
    public static List<String> consumeStringArrayProperty(ObjectNode json, String propertyName) {
        ObjectNode node = (ObjectNode) json;
        if (node.has(propertyName)) {
            List<String> rval = getStringArrayProperty(json, propertyName);
            node.remove(propertyName);
            return rval;
        }
        return null;
    }
    
    
    /* Get/Consume a string property. */
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
            if (rval != null) {
                json.remove(propertyName);
                return rval;
            }
        }
        return null;
    }


    /* Get/Consume an Integer property. */
    public static Integer getIntegerProperty(ObjectNode json, String propertyName) {
        if (json.has(propertyName)) {
            JsonNode textNode = json.get(propertyName);
            if (textNode.isInt()) {
                return textNode.asInt();
            }
        }
        return null;
    }
    public static Integer consumeIntegerProperty(ObjectNode json, String propertyName) {
        if (json.has(propertyName)) {
            Integer rval = getIntegerProperty(json, propertyName);
            if (rval != null) {
                json.remove(propertyName);
                return rval;
            }
        }
        return null;
    }


    /* Get/Consume a Number property. */
    public static Number getNumberProperty(ObjectNode json, String propertyName) {
        if (json.has(propertyName)) {
            JsonNode node = json.get(propertyName);
            if (node.isInt()) {
                return node.asInt();
            }
            if (node.isLong()) {
                return node.asLong();
            }
            if (node.isFloat() || node.isDouble()) {
                return node.asDouble();
            }
        }
        return null;
    }
    public static Number consumeNumberProperty(ObjectNode json, String propertyName) {
        if (json.has(propertyName)) {
            Number rval = getNumberProperty(json, propertyName);
            if (rval != null) {
                json.remove(propertyName);
                return rval;
            }
        }
        return null;
    }


    /* Get/Consume a Boolean property. */
    public static Boolean getBooleanProperty(ObjectNode json, String propertyName) {
        if (json.has(propertyName)) {
            JsonNode textNode = json.get(propertyName);
            if (textNode.isBoolean()) {
                return textNode.asBoolean();
            }
        }
        return null;
    }
    public static Boolean consumeBooleanProperty(ObjectNode json, String propertyName) {
        if (json.has(propertyName)) {
            Boolean rval = getBooleanProperty(json, propertyName);
            if (rval != null) {
                json.remove(propertyName);
                return rval;
            }
        }
        return null;
    }


    public static String stringify(JsonNode json) {
        try {
            PrettyPrinter pp = new PrettyPrinter();
            return mapper.writer(pp).writeValueAsString(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Object parseJSON(String jsonString) {
        try {
            return mapper.readTree(jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static JsonNode clone(JsonNode json) {
        try {
            TokenBuffer tb = new TokenBuffer(mapper, false);
            mapper.writeTree(tb, json);
            return mapper.readTree(tb.asParser());
        } catch (IOException e) {
            throw new RuntimeException("Error cloning JSON node.", e);
        }
    }

    public static ObjectNode objectNode() {
        return factory.objectNode();
    }

    public static ArrayNode arrayNode() {
        return factory.arrayNode();
    }

    public static NullNode nullNode() {
        return factory.nullNode();
    }

    public static boolean isPropertyDefined(JsonNode json, String propertyName) {
        if (json.isObject()) {
            ObjectNode node = (ObjectNode) json;
            return node.has(propertyName) && !node.get(propertyName).isNull();
        } else {
            return false;
        }
    }
    

    private static class PrettyPrinter extends MinimalPrettyPrinter {
        private static final long serialVersionUID = -4446121026177697380L;

        private int indentLevel = 0;
        
        /**
         * @see com.fasterxml.jackson.core.util.MinimalPrettyPrinter#writeStartObject(com.fasterxml.jackson.core.JsonGenerator)
         */
        @Override
        public void writeStartObject(JsonGenerator g) throws IOException {
            super.writeStartObject(g);
            indentLevel++;
            g.writeRaw("\n");
        }
        
        /**
         * @see com.fasterxml.jackson.core.util.MinimalPrettyPrinter#writeEndObject(com.fasterxml.jackson.core.JsonGenerator, int)
         */
        @Override
        public void writeEndObject(JsonGenerator g, int nrOfEntries) throws IOException {
            indentLevel--;
            g.writeRaw("\n");
            writeIndent(g);
            super.writeEndObject(g, nrOfEntries);
        }
        
        /**
         * @see com.fasterxml.jackson.core.util.MinimalPrettyPrinter#writeStartArray(com.fasterxml.jackson.core.JsonGenerator)
         */
        @Override
        public void writeStartArray(JsonGenerator g) throws IOException {
            super.writeStartArray(g);
            indentLevel++;
        }
        
        /**
         * @see com.fasterxml.jackson.core.util.MinimalPrettyPrinter#writeEndArray(com.fasterxml.jackson.core.JsonGenerator, int)
         */
        @Override
        public void writeEndArray(JsonGenerator g, int nrOfValues) throws IOException {
            g.writeRaw("\n");
            indentLevel--;
            writeIndent(g);
            super.writeEndArray(g, nrOfValues);
        }
        
        /**
         * @see com.fasterxml.jackson.core.util.MinimalPrettyPrinter#beforeObjectEntries(com.fasterxml.jackson.core.JsonGenerator)
         */
        @Override
        public void beforeObjectEntries(JsonGenerator g) throws IOException {
            writeIndent(g);
        }
        
        /**
         * @see com.fasterxml.jackson.core.util.MinimalPrettyPrinter#beforeArrayValues(com.fasterxml.jackson.core.JsonGenerator)
         */
        @Override
        public void beforeArrayValues(JsonGenerator g) throws IOException {
            g.writeRaw("\n");
            writeIndent(g);
        }
        
        /**
         * @see com.fasterxml.jackson.core.util.MinimalPrettyPrinter#writeArrayValueSeparator(com.fasterxml.jackson.core.JsonGenerator)
         */
        @Override
        public void writeArrayValueSeparator(JsonGenerator g) throws IOException {
            super.writeArrayValueSeparator(g);
            g.writeRaw("\n");
            writeIndent(g);
        }
        
        /**
         * @see com.fasterxml.jackson.core.util.MinimalPrettyPrinter#writeObjectEntrySeparator(com.fasterxml.jackson.core.JsonGenerator)
         */
        @Override
        public void writeObjectEntrySeparator(JsonGenerator g) throws IOException {
            super.writeObjectEntrySeparator(g);
            g.writeRaw("\n");
            writeIndent(g);
        }
        
        /**
         * @see com.fasterxml.jackson.core.util.MinimalPrettyPrinter#writeObjectFieldValueSeparator(com.fasterxml.jackson.core.JsonGenerator)
         */
        @Override
        public void writeObjectFieldValueSeparator(JsonGenerator g) throws IOException {
            super.writeObjectFieldValueSeparator(g);
            g.writeRaw(" ");
        }
        
        private void writeIndent(JsonGenerator g) throws IOException {
            for (int idx = 0; idx < this.indentLevel; idx++) {
                g.writeRaw("    ");
            }
        }
    }
    
}
