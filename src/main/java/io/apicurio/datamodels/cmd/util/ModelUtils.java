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

package io.apicurio.datamodels.cmd.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.compat.RegexCompat;
import io.apicurio.datamodels.core.models.Extension;

/**
 * @author eric.wittmann@gmail.com
 */
public class ModelUtils {

    /**
     * Returns true if the given object is null or undefined.  Obviously Java does not have the concept
     * of "undefined" but this must also support the case where this code is running in Javascript.
     * @param object
     */
    public static boolean isNullOrUndefined(Object object) {
        return NodeCompat.isNullOrUndefined(object);
    }

    /**
     * The opposite of isNullOrUndefined.
     * @param object
     */
    public static boolean isDefined(Object object) {
        return NodeCompat.isDefined(object);
    }

    /**
     * Detects the appropriate path parameter names from a path.  For example, if the
     * string "/resources/{fooId}/subresources/{barId}" is passed in, the following
     * string array will be returned:  [ "fooId", "barId" ]
     * @param path
     */
    public static List<String> detectPathParamNames(String path) {
        List<String> paramNames = new ArrayList<>();
        List<String[]> matches = RegexCompat.findMatches(path, "\\{([^\\}]+)\\}");
        for (String[] match : matches) {
            String name = match[1];
            paramNames.add(name.trim());
        }
        return paramNames;
    }

    public static Object marshalExtension(Extension extension) {
        Object wrapper = JsonCompat.objectNode();
        JsonCompat.setProperty(wrapper, extension.name, extension.value);
        return wrapper;
    }

    public static void unmarshalExtension(Object oldExtension, Extension extension) {
        String name = JsonCompat.keys(oldExtension).get(0);
        Object value = JsonCompat.getPropertyObject(oldExtension, name);
        extension.name = name;
        extension.value = value;
    }

    /**
     * If the provided map is a {@link LinkedHashMap} then this method will
     * rename the specified key and maintain insertion order by creating a new
     * {@link LinkedHashMap} with the updated key. If the map is not a
     * {@link LinkedHashMap} then the update will simply update the provided map with
     * the new entry, which will not maintain ordering.
     * 
     * @param <V> Value type
     * @param <T> Bounding type for V
     * @param oldKey Old key instance
     * @param newKey New key instance
     * @param map Old Map
     * @param valueConsumer An optional function to apply to the value of the map entry
     * @return A {@link Map} with the update applied, which may be the provided map or a new instance.
     */
    public static <V extends T, T> Map<String, V> renameMapKey(String oldKey, String newKey, Map<String, V> map, Consumer<T> valueConsumer) {
        // If the old key isn't present, or the new key is present, then return without modification.
        if (!NodeCompat.isDefined(map) || !map.containsKey(oldKey) || map.containsKey(newKey)) {
            return map;
        }
        if (!(map instanceof LinkedHashMap)){
            final V value = map.remove(oldKey);
            if (valueConsumer != null) {
                valueConsumer.accept(value);
            }
            map.put(newKey, value);
            return map;
        }
        
        final LinkedHashMap<String, V> newMap = new LinkedHashMap<>();
        // In order to maintain ordering of the elements when replacing a key in a LinkedHashMap,
        // create a new instance and populate it in insertion order
        for (Entry<String, V> entry : map.entrySet()) {
            // Remap the key
            String key = entry.getKey();
            V value = entry.getValue();
            if (key.equals(oldKey)) {
                key = newKey;
                if (valueConsumer != null) {
                    valueConsumer.accept(value);
                }
            }
            newMap.put(key, value);
        }
        return newMap;
    }
    
    /**
     * If the provided map is a {@link LinkedHashMap} then this method will
     * restore the specified key at the specified position order by creating a new
     * {@link LinkedHashMap} with the updated key. If the map is not a
     * {@link LinkedHashMap} then the update will simply update the provided map with
     * the new entry, which will not maintain ordering.
     * 
     * @param <V> Value type
     * @param key Old key instance
     * @param newKey New key instance
     * @param map Old Map
     * @param valueConsumer An optional function to apply to the value of the map entry
     * @return A {@link Map} with the update applied, which may be the provided map or a new instance.
     */
    public static <V> Map<String, V> restoreMapEntry(int position, String key, V value, Map<String, V> map) {
        // If the new key is present, then return without modification.
        if (!NodeCompat.isDefined(map) || map.containsKey(key)) {
            return map;
        }
        if (!(map instanceof LinkedHashMap) || position >= map.size()){
            map.put(key, value);
            return map;
        }
        

        final LinkedHashMap<String, V> newMap = new LinkedHashMap<>();
        // In order to maintain ordering of the elements when replacing a key in a LinkedHashMap,
        // create a new instance and populate it in insertion order
        int index = 0; 
        for (Entry<String, V> entry : map.entrySet()) {
            if (index++ == position) {
                newMap.put(key, value);
            }
            newMap.put(entry.getKey(), entry.getValue());
        }
        return newMap;
    }
}
