/*
 * Copyright 2019 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.datamodels.compat;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonProcessingException;
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

/**
 * Compatibility class used to perform actions on JSON data.  That data is handled natively in 
 * the TS/JS version of this class.  But when in Java we use Jackson.  There is a JsonCompat.ts file
 * that replaces this one *after* Java->TS transpilation.
 * @author eric.wittmann@gmail.com
 */
public class JsonCompat {

    private static final JsonNodeFactory factory = JsonNodeFactory.instance;
    private static final ObjectMapper mapper = new ObjectMapper();
    
    /*
     * Util methods
     */
    
    public static String stringify(Object json) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (JsonProcessingException e) {
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
    
    public static JsonNode clone(Object json) {
        try {
            JsonNode obj = (JsonNode) json;
            TokenBuffer tb = new TokenBuffer(mapper, false);
            mapper.writeTree(tb, obj);
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
    
    public static boolean isPropertyDefined(Object json, String propertyName) {
        ObjectNode node = (ObjectNode) json;
        return node.has(propertyName) && !node.get(propertyName).isNull();
    }
    
    public static Object removeNullProperties(Object json) {
        JsonNode node = (JsonNode) json;
        if (node.isArray()) {
            ArrayNode array = (ArrayNode) node;
            array.forEach(item -> {
                JsonCompat.removeNullProperties(item);
            });
        } else if (node.isObject()) {
            ObjectNode object = (ObjectNode) node;
            JsonCompat.keys(object).forEach(propertyName -> {
                if (!JsonCompat.isPropertyDefined(json, propertyName)) {
                    JsonCompat.consumeProperty(json, propertyName);
                } else {
                    JsonCompat.removeNullProperties(JsonCompat.getProperty(json, propertyName));
                }
            });
        }
        return json;
    }

    public static boolean isBoolean(Object json) {
        if (json == null) {
            return false;
        }
        JsonNode node = (JsonNode) json;
        return node.isBoolean();
    }

    public static boolean isNumber(Object json) {
        if (json == null) {
            return false;
        }
        JsonNode node = (JsonNode) json;
        return node.isNumber();
    }

    public static boolean isObject(Object json) {
        if (json == null) {
            return false;
        }
        JsonNode node = (JsonNode) json;
        return node.isObject();
    }

    public static boolean isString(Object json) {
        if (json == null) {
            return false;
        }
        if (json instanceof String) {
            return true;
        }
        JsonNode node = (JsonNode) json;
        if (node.isTextual()) {
            return true;
        }
        return false;
    }
    
    public static String toString(Object json) {
        if (json instanceof String) {
            return (String) json;
        }
        JsonNode node = (JsonNode) json;
        if (node.isTextual()) {
            return node.asText();
        }
        return null;
    }

    public static Boolean toBoolean(Object json) {
        if (json == null) {
            return null;
        }
        JsonNode node = (JsonNode) json;
        if (node.isBoolean()) {
            return ((BooleanNode) json).asBoolean();
        }
        return null;
    }

    public static Number toNumber(Object json) {
        if (json == null) {
            return null;
        }
        JsonNode node = (JsonNode) json;
        if (node.isNumber()) {
            return ((NumericNode) json).numberValue();
        }
        return null;
    }

    public static boolean isArray(Object json) {
        if (json == null) {
            return false;
        }
        JsonNode node = (JsonNode) json;
        return node.isArray();
    }
    
    public static List<Object> toList(Object json) {
        List<Object> rval = new ArrayList<>();
        if (json != null) {
            ArrayNode array = (ArrayNode) json;
            for (int idx = 0; idx < array.size(); idx++) {
                rval.add(array.get(idx));
            }
        }
        return rval;
    }

    /*
     * Getters
     */
    
    public static List<String> keys(Object json) {
        List<String> rval = new ArrayList<>();
        if (json != null) {
            ObjectNode node = (ObjectNode) json;
            Iterator<String> fieldNames = node.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                rval.add(fieldName);
            }
        }
        return rval;
    }
    
    public static Object getProperty(Object json, String propertyName) {
        JsonNode node = (JsonNode) json;
        JsonNode pnode = node.get(propertyName);
        if (pnode == null) {
            return null;
        }
        if (pnode.isNull()) {
            return null;
        }
        return pnode;
    }
    public static Object consumeProperty(Object json, String propertyName) {
        ObjectNode node = (ObjectNode) json;
        JsonNode pnode = node.remove(propertyName);
        if (pnode == null) {
            return null;
        }
        if (pnode.isNull()) {
            return null;
        }
        return pnode;
    }

    public static Object getPropertyObject(Object json, String propertyName) {
        return JsonCompat.parseObject(JsonCompat.getProperty(json, propertyName));
    }
    public static Object consumePropertyObject(Object json, String propertyName) {
        return JsonCompat.parseObject(JsonCompat.consumeProperty(json, propertyName));
    }

    public static List<Object> getPropertyArray(Object json, String propertyName) {
        if (!JsonCompat.isPropertyDefined(json, propertyName)) {
            return null;
        }
        Object value = JsonCompat.getProperty(json, propertyName);
        JsonNode node = (JsonNode) value;
        if (node.isArray()) {
            ArrayNode array = (ArrayNode) node;
            List<Object> rval = new ArrayList<>();
            for (JsonNode item : array) {
                rval.add(item);
            }
            return rval;
        } else {
            return null;
        }
    }
    public static List<Object> consumePropertyArray(Object json, String propertyName) {
        ObjectNode node = (ObjectNode) json;
        if (node.has(propertyName)) {
            List<Object> rval = JsonCompat.getPropertyArray(json, propertyName);
            node.remove(propertyName);
            return rval;
        }
        return null;
    }

    public static String getPropertyString(Object json, String propertyName) {
        JsonNode propertyNode = (JsonNode) JsonCompat.getProperty(json, propertyName);
        if (propertyNode == null) {
            return null;
        } else {
            return propertyNode.asText();
        }
    }
    public static String consumePropertyString(Object json, String propertyName) {
        JsonNode propertyNode = (JsonNode) JsonCompat.consumeProperty(json, propertyName);
        if (propertyNode == null) {
            return null;
        } else {
            return propertyNode.asText();
        }
    }

    public static Boolean getPropertyBoolean(Object json, String propertyName) {
        JsonNode propertyNode = (JsonNode) JsonCompat.getProperty(json, propertyName);
        if (propertyNode == null) {
            return null;
        } else {
            return propertyNode.asBoolean();
        }
    }
    public static Boolean consumePropertyBoolean(Object json, String propertyName) {
        JsonNode propertyNode = (JsonNode) JsonCompat.consumeProperty(json, propertyName);
        if (propertyNode == null) {
            return null;
        } else {
            return propertyNode.asBoolean();
        }
    }

    public static Number getPropertyNumber(Object json, String propertyName) {
        JsonNode propertyNode = (JsonNode) JsonCompat.getProperty(json, propertyName);
        if (propertyNode == null) {
            return null;
        } else {
            if (propertyNode.isInt()) {
                return propertyNode.asInt();
            }
            if (propertyNode.isLong()) {
                return propertyNode.asLong();
            }
            if (propertyNode.isDouble() || propertyNode.isFloat()) {
                return propertyNode.asDouble();
            }
            return null;
        }
    }
    public static Number consumePropertyNumber(Object json, String propertyName) {
        Number rval = JsonCompat.getPropertyNumber(json, propertyName);
        if (rval != null) {
            JsonCompat.consumeProperty(json, propertyName);
        }
        return rval;
    }
    
    public static List<String> getPropertyStringArray(Object json, String propertyName) {
        if (!JsonCompat.isPropertyDefined(json, propertyName)) {
            return null;
        }
        Object value = JsonCompat.getProperty(json, propertyName);
        JsonNode node = (JsonNode) value;
        if (node.isArray()) {
            ArrayNode array = (ArrayNode) node;
            List<String> rval = new ArrayList<>();
            for (JsonNode item : array) {
                if (item.isNull()) {
                    rval.add(null);
                } else {
                    rval.add(item.asText());
                }
            }
            return rval;
        } else {
            return null;
        }
    }
    public static List<String> consumePropertyStringArray(Object json, String propertyName) {
        ObjectNode node = (ObjectNode) json;
        if (node.has(propertyName)) {
            List<String> rval = JsonCompat.getPropertyStringArray(json, propertyName);
            node.remove(propertyName);
            return rval;
        }
        return null;
    }

    
    /*
     * Setters
     */
    
    public static void setPropertyNull(Object json, String propertyName) {
        ObjectNode node = (ObjectNode) json;
        node.set(propertyName, factory.nullNode());
    }
    
    public static void setProperty(Object json, String propertyName, Object propertyValue) {
        ObjectNode node = (ObjectNode) json;
        if (propertyValue instanceof JsonNode) {
            JsonNode value = (JsonNode) propertyValue;
            node.set(propertyName, value);
        } else if (propertyValue == null) {
            node.set(propertyName, factory.nullNode());
        } else {
            writeObject(node, propertyName, propertyValue);
        }
    }
    
    public static void setPropertyString(Object json, String propertyName, String propertyValue) {
        ObjectNode node = (ObjectNode) json;
        if (propertyValue != null) {
            TextNode textNode = factory.textNode(propertyValue);
            node.set(propertyName, textNode);
        } else {
            node.set(propertyName, factory.nullNode());
        }
    }

    public static void setPropertyBoolean(Object json, String propertyName, Boolean propertyValue) {
        ObjectNode node = (ObjectNode) json;
        if (propertyValue != null) {
            BooleanNode booleanNode = factory.booleanNode(propertyValue);
            node.set(propertyName, booleanNode);
        } else {
            node.set(propertyName, factory.nullNode());
        }
    }

    public static void setPropertyNumber(Object json, String propertyName, Number propertyValue) {
        ObjectNode node = (ObjectNode) json;
        if (propertyValue != null) {
            ValueNode numericNode = null;
            if (propertyValue instanceof Integer) {
                numericNode = factory.numberNode((Integer) propertyValue);
            }
            if (propertyValue instanceof Long) {
                numericNode = factory.numberNode((Long) propertyValue);
            }
            if (propertyValue instanceof Double) {
                numericNode = factory.numberNode((Double) propertyValue);
            }
            node.set(propertyName, numericNode);
        } else {
            node.set(propertyName, factory.nullNode());
        }
    }

    public static void setPropertyStringArray(Object json, String propertyName, List<String> propertyValue) {
        ObjectNode node = (ObjectNode) json;
        if (propertyValue != null) {
            ArrayNode arrayNode = JsonCompat.arrayNode();
            propertyValue.forEach(value -> {
                arrayNode.add(value);
            });
            node.set(propertyName, arrayNode);
        } else {
            node.set(propertyName, factory.nullNode());
        }
    }
    
    public static void appendToArrayProperty(Object json, String arrayPropertyName, Object propertyValue) {
        ObjectNode node = (ObjectNode) json;
        ArrayNode array;
        if (JsonCompat.isPropertyDefined(json, arrayPropertyName)) {
            array = (ArrayNode) node.get(arrayPropertyName);
        } else {
            array = JsonCompat.arrayNode();
            node.set(arrayPropertyName, array);
        }
        if (propertyValue instanceof JsonNode) {
            array.add((JsonNode) propertyValue);
        } else {
            array.add(String.valueOf(propertyValue));
        }
    }

    public static Object appendToArray(Object jsonArray, Object propertyValue) {
        ArrayNode array = (ArrayNode) jsonArray;
        if (propertyValue instanceof JsonNode) {
            array.add((JsonNode) propertyValue);
        } else {
            array.add(String.valueOf(propertyValue));
        }
        return array;
    }

    public static void setToArrayIndex(Object jsonArray, int index, Object propertyValue) {
        ArrayNode array = (ArrayNode) jsonArray;
        if (propertyValue instanceof JsonNode) {
            array.set(index, (JsonNode) propertyValue);
        } else {
            array.set(index, factory.textNode(String.valueOf(propertyValue)));
        }
    }

    public static <T> List<T> mapToList(Map<?, T> items) {
        List<T> rval = new ArrayList<>();
        if(items != null) {
            for (Map.Entry<?, T> e : items.entrySet()) {
                rval.add(e.getValue());
            }
        }
        return rval;
    }
    
    /*
     * Internal only (not required by the TS layer)
     */

    /**
     * Parses the given property value, which could be any type of thing, into an object
     * suitable for the environment.  When in java, this means that the input will be a 
     * Jackson node and should be converted to a native type, or a String, or a List, or
     * a Map (when the input is a JS Object).
     * @param node
     */
    private static Object parseObject(Object json) {
        JsonNode node = (JsonNode) json;
        if (node == null) {
            return null;
        }
        if (node.isNull()) {
            return null;
        }
        if (node.isBigDecimal()) {
            return new BigDecimal(node.asText());
        }
        if (node.isBigInteger()) {
            return new BigInteger(node.asText());
        }
        if (node.isBoolean()) {
            return node.asBoolean();
        }
        if (node.isDouble()) {
            return node.asDouble();
        }
        if (node.isFloat()) {
            return node.asDouble();
        }
        if (node.isInt()) {
            return node.asInt();
        }
        if (node.isLong()) {
            return node.asLong();
        }
        if (node.isTextual()) {
            return node.asText();
        }
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode) node;
            List<Object> items = new ArrayList<>();
            for (JsonNode itemNode : arrayNode) {
                items.add(parseObject(itemNode));
            }
            return items;
        }
        if (node.isObject()) {
            Map<String, Object> items = new LinkedHashMap<>();
            for (Iterator<Entry<String, JsonNode>> fields = node.fields(); fields.hasNext(); ) {
                Entry<String, JsonNode> field = fields.next();
                String fieldName = field.getKey();
                Object fieldValue = parseObject(field.getValue());
                items.put(fieldName, fieldValue);
            }
            return items;
        }
        // Do we need a warning if we get here?
        return json;
    }

    @SuppressWarnings("unchecked")
    private static void writeObject(ObjectNode node, String key, Object value) {
        if (value == null) {
            return;
        }
        if (value instanceof String) {
            node.put(key, (String) value);
        } else if (value instanceof JsonNode) {
            node.set(key, (JsonNode) value);
        } else if (value instanceof BigDecimal) {
            node.put(key, (BigDecimal) value);
        } else if (value instanceof BigInteger) {
            node.put(key, new BigDecimal((BigInteger) value));
        } else if (value instanceof Boolean) {
            node.put(key, (Boolean) value);
        } else if (value instanceof Double) {
            node.put(key, (Double) value);
        } else if (value instanceof Float) {
            node.put(key, (Float) value);
        } else if (value instanceof Integer) {
            node.put(key, (Integer) value);
        } else if (value instanceof Long) {
            node.put(key, (Long) value);
        } else if (value instanceof List) {
            ArrayNode array = node.putArray(key);
            List<Object> values = (List<Object>) value;
            for (Object valueItem : values) {
                addObject(array, valueItem);
            }
        } else if (value instanceof Map) {
            ObjectNode objNode = node.putObject(key);
            Map<String, Object> values = (Map<String, Object>) value;
            for (Entry<String, Object> entry : values.entrySet()) {
                String propertyName = entry.getKey();
                writeObject(objNode, propertyName, entry.getValue());
            }
        } else {
            node.put(key, (String) null);
        }
    }

    /**
     * Adds an object to an array.
     * @param node
     * @param value
     */
    @SuppressWarnings("unchecked")
    private static void addObject(ArrayNode node, Object value) {
        if (value instanceof String) {
            node.add((String) value);
        } else if (value instanceof JsonNode) {
            node.add((JsonNode) value);
        } else if (value instanceof BigDecimal) {
            node.add((BigDecimal) value);
        } else if (value instanceof BigInteger) {
            node.add(new BigDecimal((BigInteger) value));
        } else if (value instanceof Boolean) {
            node.add((Boolean) value);
        } else if (value instanceof Double) {
            node.add((Double) value);
        } else if (value instanceof Float) {
            node.add((Float) value);
        } else if (value instanceof Integer) {
            node.add((Integer) value);
        } else if (value instanceof Long) {
            node.add((Long) value);
        } else if (value instanceof List) {
            ArrayNode array = node.addArray();
            List<Object> values = (List<Object>) value;
            for (Object valueItem : values) {
                addObject(array, valueItem);
            }
        } else if (value instanceof Map) {
            ObjectNode objNode = node.addObject();
            Map<String, Object> values = (Map<String, Object>) value;
            for (Entry<String, Object> entry : values.entrySet()) {
                String propertyName = entry.getKey();
                writeObject(objNode, propertyName, entry.getValue());
            }
        } else {
            node.add((String) null);
        }
    }
}
