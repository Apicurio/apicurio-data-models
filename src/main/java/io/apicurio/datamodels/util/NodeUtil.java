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
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import io.apicurio.datamodels.models.Node;

/**
 * A utility class containing methods used to interact with data model nodes.  Anything that
 * needs to be handled differently (with respect to data model nodes) in Java vs. TS/JS should be done
 * here.  There is a NodeUtil.ts file that contains the TS/JS specific implementation of this class.
 * @author eric.wittmann@gmail.com
 */
public class NodeUtil {

    /**
     * Returns the value for a given node property.  Also supports List and Map as the input "node".
     * @param node
     * @param propertyName
     */
    public static Object getProperty(Node node, String propertyName) {
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

}
