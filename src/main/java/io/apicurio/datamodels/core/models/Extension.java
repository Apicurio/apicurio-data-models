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

import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * Models a single extension property of a node in the data model.  For example, if the
 * Info node has a property called "x-vendor-zipcode" with a value of "90210", that would
 * result in an instance of this class with appropriate values for "name" and "value".
 * @author eric.wittmann@gmail.com
 */
public class Extension extends Node {

    public String name;
    public Object value;

    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        visitor.visitExtension(this);
    }
}
