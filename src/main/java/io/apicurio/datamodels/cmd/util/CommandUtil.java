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

import io.apicurio.datamodels.util.NodeUtil;
import io.apicurio.datamodels.util.RegexUtil;

/**
 * @author eric.wittmann@gmail.com
 */
public class CommandUtil {

    /**
     * Returns true if the given object is null or undefined.  Obviously Java does not have the concept
     * of "undefined" but this must also support the case where this code is running in Javascript.
     * @param object
     */
    public static boolean isNullOrUndefined(Object object) {
        return NodeUtil.isNullOrUndefined(object);
    }

    /**
     * The opposite of isNullOrUndefined.
     * @param object
     */
    public static boolean isDefined(Object object) {
        return NodeUtil.isDefined(object);
    }

    /**
     * Detects the appropriate path parameter names from a path.  For example, if the
     * string "/resources/{fooId}/subresources/{barId}" is passed in, the following
     * string array will be returned:  [ "fooId", "barId" ]
     * @param path
     */
    public static List<String> detectPathParamNames(String path) {
        List<String> paramNames = new ArrayList<>();
        List<String[]> matches = RegexUtil.findMatches(path, "\\{([^\\}]+)\\}");
        for (String[] match : matches) {
            String name = match[1];
            paramNames.add(name.trim());
        }
        return paramNames;
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
        if (!NodeUtil.isDefined(map) || !map.containsKey(oldKey) || map.containsKey(newKey)) {
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
     * @param position The position, may be <code>null</code>.
     * @param key The map entry key
     * @param value The map entry value
     * @param map The map to update, may be <code>null</code>.
     * @return A {@link Map} with the update applied, which may be the provided map or a new instance.
     */
    public static <V> Map<String, V> restoreMapEntry(Integer position, String key, V value, Map<String, V> map) {
        // Create a new map if not defined.
        if (!NodeUtil.isDefined(map)) {
            final LinkedHashMap<String, V> newMap = new LinkedHashMap<>();
            newMap.put(key, value);
            return newMap;
        }
        // If the new key is present, then return without modification.
        if (map.containsKey(key)) {
            return map;
        }
        // If the map isn't an LHM then ordering can't be maintained anyway
        // If the position is null then we're trying to undo a command that was persisted prior to this functionality being added
        // If the position is >= the map size it has to go at the end anyway
        if (!(map instanceof LinkedHashMap) || !NodeUtil.isDefined(position) || position >= map.size()){
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

    /**
     * Restore a list entry at a certain position. If the provided list is
     * <code>null</code> a new list will be returned.
     *
     * @param <V> Value type
     * @param position The position, may be <code>null</code>.
     * @param value The value
     * @param list The list
     * @return A list with the value, which may be the original list or a new instance.
     */
    public static <V> List<V> restoreListEntry(Integer position, V value, List<V> list) {
        if (!NodeUtil.isDefined(list)) {
            final ArrayList<V> newList = new ArrayList<V>();
            newList.add(value);
            return newList;
        }
        if (!NodeUtil.isDefined(position) || position >= list.size()) {
            list.add(value);
        } else if (position < 0) {
            list.add(0, value);
        } else {
            list.add(position, value);
        }
        return list;
    }
}
