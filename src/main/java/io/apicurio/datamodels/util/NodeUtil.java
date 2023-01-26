/*
 * Copyright 2022 Red Hat
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

package io.apicurio.datamodels.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import io.apicurio.datamodels.models.MappedNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.union.Union;
import io.apicurio.datamodels.visitors.DefinitionDetectionVisitor;

/**
 * A utility class containing methods used to interact with data model nodes.  Anything that
 * needs to be handled differently (with respect to data model nodes) in Java vs. TS/JS should be done
 * here.  There is a NodeUtil.ts file that contains the TS/JS specific implementation of this class.
 * @author eric.wittmann@gmail.com
 */
public class NodeUtil {

    public static Object getProperty(Object node, String propertyName) {
        if (isNode(node)) {
            return getNodeProperty((Node) node, propertyName);
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
     * Returns the value for a given node property.
     * @param node
     * @param propertyName
     */
    public static Object getNodeProperty(Node node, String propertyName) {
        // Access the value of the property by invoking its Getter
        String getterName = "get" + StringUtils.capitalize(propertyName);
        try {
            Method method = node.getClass().getMethod(getterName);
            return method.invoke(node);
        } catch (NoSuchMethodException nsme) {
            // Might be a boolean getter...
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e1) {
            return null;
        }

        // If that didn't work, try it again with the boolean form of the getter.
        getterName = "is" + StringUtils.capitalize(propertyName);
        try {
            Method method = node.getClass().getMethod(getterName);
            return method.invoke(node);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e1) {
            return null;
        }
    }

    /**
     * Return true if the given object is a Node.
     * @param object
     */
    public static boolean isNode(Object object) {
        return object != null && object instanceof Node;
    }

    /**
     * Return true if the given object is a MappedNode.
     * @param object
     */
    public static boolean isMappedNode(Object object) {
        return object != null && object instanceof MappedNode;
    }

    /**
     * Return true if the given node is a definition.  A node is a definition in both AsyncAPI
     * and OpenAPI if it is defined in a way that makes it reusable elsewhere in the document.
     * @param node
     */
    public static boolean isDefinition(Node node) {
        DefinitionDetectionVisitor detector = new DefinitionDetectionVisitor();
        if (node.parent() != null) {
            node.parent().accept(detector);
        }
        return detector.isDefinitionParentDetected();
    }

    /**
     * Return true if the given object is a Union type.
     * @param object
     */
    public static boolean isUnion(Object object) {
        return object != null && object instanceof Union;
    }

    /**
     * Returns true if the given object is a list.
     * @param object
     */
    public static boolean isList(Object object) {
        return object != null && object instanceof List;
    }

    /**
     * Returns true if the given object is a list.
     * @param object
     */
    public static boolean isMap(Object object) {
        return object != null && object instanceof Map;
    }

    /**
     * Gets an item out of a map.  Needed due to transpilation wierdness.
     * @param map
     * @param key
     */
    @SuppressWarnings("rawtypes")
    public static Object getMapItem(Map map, String key) {
        return map.get(key);
    }

    /**
     * Converts a string value to an integer (it's expected to be formatted as an int).
     * @param value
     */
    public static int toInteger(String value) {
        return Integer.parseInt(value);
    }

    /**
     * Returns true if the given value is null or undefined.
     * @param value
     */
    public static boolean isNullOrUndefined(Object value) {
        return value == null;
    }

    /**
     * Returns true if the given value is defined (ie if the value is NOT null or undefined).
     * @param value
     */
    public static boolean isDefined(Object value) {
        return !isNullOrUndefined(value);
    }

    /**
     * Creates a list from a bunch of argument items.
     * @param items
     */
    public static List<String> asList(String ...items) {
        return Arrays.asList(items);
    }

    /**
     * Turns a list into an array.
     * @param list
     */
    public static String[] asArray(List<String> list) {
        return list.toArray(new String[list.size()]);
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
     * Gets the keys from the given map.
     * @param map
     */
    public static Collection<String> getMapKeys(Map<String, ?> map) {
        if (map == null) {
            return Collections.emptyList();
        }
        return map.keySet();
    }

    /**
     * Gets the values from the given map.
     * @param map
     */
    public static <T> Collection<T> getMapValues(Map<String, T> map) {
        if (map == null) {
            return Collections.emptyList();
        }
        return map.values();
    }

}
