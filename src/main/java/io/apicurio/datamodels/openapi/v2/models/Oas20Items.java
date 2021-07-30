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

package io.apicurio.datamodels.openapi.v2.models;

import java.util.List;

import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor;

/**
 * Models an OpenAPI 2.0 items.
 * @author eric.wittmann@gmail.com
 */
public class Oas20Items extends ExtensibleNode {
    
    public String type;
    public String format;
    public Oas20Items items;
    public String collectionFormat;
    public Object default_;
    public Number maximum;
    public Boolean exclusiveMaximum;
    public Number minimum;
    public Boolean exclusiveMinimum;
    public Number maxLength;
    public Number minLength;
    public String pattern;
    public Number maxItems;
    public Number minItems;
    public Boolean uniqueItems;
    public List<String> enum_;
    public Number multipleOf;

    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOas20Visitor viz = (IOas20Visitor) visitor;
        viz.visitItems(this);
    }

    /**
     * Creates a child items model.
     */
    public Oas20Items createItems() {
        Oas20Items rval = new Oas20Items();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }
}
