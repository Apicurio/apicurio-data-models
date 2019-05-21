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

package io.apicurio.datamodels.core.models;

import java.util.List;

/**
 * This interface must be implemented by nodes that represent a `Map[string, T]` data type
 * in the specification, and therefore can't be modeled as a node containing
 * a known collection of named fields.
 *
 * Examples include:
 *
 * Oas20Paths
 * Oas20Definitions
 * Oas20Reponses
 *
 * @author eric.wittmann@gmail.com
 */
public interface IIndexedNode<T extends Node> {

    /**
     * Gets a single item (indexed child) by name. Returns undefined if not found.
     * 
     * @param name
     */
    public T getItem(String name);

    /**
     * Returns an array of all the child items.
     */
    public List<T> getItems();

    /**
     * Gets a list of the names of all indexed children.
     */
    public List<String> getItemNames();

    /**
     * Adds a child item.
     * 
     * @param name
     * @param item
     */
    public void addItem(String name, T item);

    /**
     * Deletes a child item by name and returns the deleted child or undefined if there wasn't one.
     * 
     * @param name
     */
    public T deleteItem(String name);

}
