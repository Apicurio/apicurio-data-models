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
import java.util.List;

import io.apicurio.datamodels.core.models.Node;

/**
 * A compatibility layer containing methods used to interact with data model nodes.  Anything that
 * needs to be handled differently (with respect to data model nodes) in Java vs. TS/JS should be done 
 * here.  There is a NodeCompat.ts file that contains the TS/JS specific implementation of this class.
 * @author eric.wittmann@gmail.com
 */
public class NodeCompat {
    
    /**
     * Returns the value for a given node property.
     * @param node
     * @param propertyName
     */
    public static Object property(Node node, String propertyName) {
        try {
            Field field = node.getClass().getField(propertyName);
            return field.get(node);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
                | IllegalAccessException e) {
            return null;
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
            List<? extends Node> array = (List) NodeCompat.property(parent, propertyName);
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
    
}
