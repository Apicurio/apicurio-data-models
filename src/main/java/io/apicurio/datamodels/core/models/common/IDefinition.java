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

package io.apicurio.datamodels.core.models.common;

import io.apicurio.datamodels.core.models.IVisitable;
import io.apicurio.datamodels.core.models.Node;

/**
 * Any node model that represents a re-usable definition.  Examples include
 * Schema Definitions, Parameter Definitions, etc.
 *
 * Any class implementing this interface MUST have {@link io.apicurio.datamodels.core.models.Node} as it's supertype.
 *
 * @author eric.wittmann@gmail.com
 *
 * @param <T> Type of the node that this definition represents.
 *           <T> SHOULD usually be a superclass of a class implementing this interface.
 *           This way we only need a one-way conversion.
 */
public interface IDefinition/*<T extends Node>*/ extends INamed, IVisitable {

//    /**
//     * Create a <b>new</b> definition of the same type as this one.
//     * It has the same name ({@link INamed#getName()}), and the data is copied from the represented node.
//     *
//     * The new node is detached, i.e. it does not belong to a document and does not have a parent.
//     *
//     * This method does not affect the state of this object,
//     * but borrows the .
//     */
//    IDefinition<T> newFromRepresentedNode(String name, T representedNode);

    // wrap method instead?
}
