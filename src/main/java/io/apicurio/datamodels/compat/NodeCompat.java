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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Node;

/**
 * A compatibility layer containing methods used to interact with data model nodes.  Anything that
 * needs to be handled differently (with respect to data model nodes) in Java vs. TS/JS should be done 
 * here.  There is a NodeCompat.ts file that contains the TS/JS specific implementation of this class.
 * @author eric.wittmann@gmail.com
 */
public class NodeCompat {
    
    /**
     * Returns the value for a given node property.  Also supports List and Map as the input "node".
     * @param node
     * @param propertyName
     */
    public static Object getProperty(Object node, String propertyName) {
        if (Constants.PROP_DEFAULT.equals(propertyName) || Constants.PROP_ENUM.equals(propertyName)) {
            propertyName = propertyName + "_";
        }
        
        if (node instanceof Node) {
            try {
                Field field = node.getClass().getField(propertyName);
                return field.get(node);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
                    | IllegalAccessException e) {
                return null;
            }
        }
        if (node instanceof List) {
            int idx = Integer.parseInt(propertyName);
            List<?> list = (List<?>) node;
            return list.get(idx);
        }
        if (node instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, ?> map = (Map<String, ?>) node;
            return map.get(propertyName);
        }
        
        return null;
    }
    
    /**
     * Sets the value for a given node property.
     * @param node
     * @param propertyName
     * @param newValue
     */
    public static void setProperty(Object node, String propertyName, Object newValue) {
        try {
            Field field = node.getClass().getField(propertyName);
            field.set(node, newValue);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
                | IllegalAccessException e) {
            LoggerCompat.warn("Failed to set property '%s' on Node '%s': %s", propertyName, 
                    node.getClass().getSimpleName(), e.getMessage());
            return;
        }

    }

    /**
     * Figures out the index of the given child by interrogating the parent node's list of 
     * children at the given property name.  For example, this could be called to find the
     * index of a Tag child entity of a Document entity using "tags" as the property name.
     * @param child
     * @param parent
     * @param propertyName
     */
    public static int indexOf(Node child, Node parent, String propertyName) {
        try {
            @SuppressWarnings({ "rawtypes", "unchecked" })
            List<? extends Node> array = (List) NodeCompat.getProperty(parent, propertyName);
            for (int idx = 0; idx < array.size(); idx++) {
                Node node = array.get(idx);
                if (node == child) {
                    return idx;
                }
            }
        } catch (Exception e) {
        }
        return -1;
    }

    /**
     * Tests two objects for equality.
     * @param value1
     * @param value2
     */
    public static boolean equals(Object value1, Object value2) {
        if (value1 == value2) {
            return true;
        }
        return value1 != null && value1.equals(value2);
    }
    
    /**
     * Joins a list of strings into a single string with a given delimiter.
     * @param delim
     * @param values
     */
    public static String join(String delim, List<String> values) {
        return String.join(delim, values);
    }

    /**
     * Joins a list of strings into a single string with a given delimiter.
     * @param delim
     * @param values
     */
    public static String joinArray(String delim, String[] values) {
        return join(delim, Arrays.asList(values));
    }

    /**
     * Return true if the given object is a Node.
     * @param object
     */
    public static boolean isNode(Object object) {
        return object != null && object instanceof Node;
    }

    /**
     * Returns true if the given object is a list.
     * @param object
     */
    public static boolean isList(Object object) {
        return object != null && object instanceof List;
    }
    
    /**
     * Turns a list into an array.
     * @param list
     */
    public static String[] asArray(List<String> list) {
        return list.toArray(new String[list.size()]);
    }
    
    /**
     * Creates a list from a bunch of argument items.
     * @param items
     */
    public static List<String> asList(String ...items) {
        return Arrays.asList(items);
    }
    
    /**
     * Makes a copy of a list.
     * @param list
     */
    public static List<String> copyList(List<String> list) {
        List<String> rval = new ArrayList<>();
        rval.addAll(list);
        return rval;
    }

    /**
     * Returns true if the given value is null or undefined.
     * @param value
     */
    public static boolean isNullOrUndefined(Object value) {
        return value == null;
    }
    
}
