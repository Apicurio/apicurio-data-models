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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

/**
 * @author eric.wittmann@gmail.com
 */
public class JsonCompat {

    private static final JsonNodeFactory factory = JsonNodeFactory.instance;
    
    /*
     * Util methods
     */

    public static ObjectNode objectNode() {
        return factory.objectNode();
    }

    public static ArrayNode arrayNode() {
        return factory.arrayNode();
    }

    /**
     * Parses the given property value, which could be any type of thing, into an object
     * suitable for the environment.  When in java, this means that the input will be a 
     * Jackson node and should be converted to a native type, or a String, or a List, or
     * a Map (when the input is a JS Object).
     * @param node
     */
    public static Object parseObject(Object json) {
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
    
    public static Object property(Object json, String propertyName) {
        JsonNode node = (JsonNode) json;
        return node.get(propertyName);
    }
    
    public static String propertyString(Object json, String propertyName) {
        JsonNode propertyNode = (JsonNode) JsonCompat.property(json, propertyName);
        if (propertyNode == null) {
            return null;
        } else {
            return propertyNode.asText();
        }
    }
    
    
    /*
     * Setters
     */
    
    public static void setProperty(Object json, String propertyName, Object propertyValue) {
        ObjectNode node = (ObjectNode) json;
        if (propertyValue instanceof JsonNode) {
            JsonNode value = (JsonNode) propertyValue;
            node.set(propertyName, value);
        } else {
            _writeObject(node, propertyName, propertyValue);
        }
    }
    
    public static void setPropertyString(Object json, String propertyName, String propertyValue) {
        if (propertyValue != null) {
            ObjectNode node = (ObjectNode) json;
            TextNode textNode = factory.textNode(propertyValue);
            node.set(propertyName, textNode);
        }
    }

    
    /*
     * Internal only (not required by the TS layer)
     */

    @SuppressWarnings("unchecked")
    private static void _writeObject(ObjectNode node, String key, Object value) {
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
                _addObject(array, valueItem);
            }
        } else if (value instanceof Map) {
            ObjectNode objNode = node.putObject(key);
            Map<String, Object> values = (Map<String, Object>) value;
            for (Entry<String, Object> entry : values.entrySet()) {
                String propertyName = entry.getKey();
                _writeObject(objNode, propertyName, entry.getValue());
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
    private static void _addObject(ArrayNode node, Object value) {
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
                _addObject(array, valueItem);
            }
        } else if (value instanceof Map) {
            ObjectNode objNode = node.addObject();
            Map<String, Object> values = (Map<String, Object>) value;
            for (Entry<String, Object> entry : values.entrySet()) {
                String propertyName = entry.getKey();
                _writeObject(objNode, propertyName, entry.getValue());
            }
        } else {
            node.add((String) null);
        }
    }
}
