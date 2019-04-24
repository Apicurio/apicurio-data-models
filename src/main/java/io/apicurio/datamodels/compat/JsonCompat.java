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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
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
    
    /*
     * Setters
     */
    
    public static void setProperty(Object json, String propertyName, Object propertyValue) {
        ObjectNode node = (ObjectNode) json;
        if (propertyValue instanceof JsonNode) {
            JsonNode value = (JsonNode) propertyValue;
            node.set(propertyName, value);
        } else {
            writeObject(node, propertyName, propertyValue);
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
