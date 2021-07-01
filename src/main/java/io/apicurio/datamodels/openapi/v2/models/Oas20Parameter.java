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

import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.openapi.models.OasParameter;

/**
 * Models an OpenAPi 2.0 parameter.
 * @author eric.wittmann@gmail.com
 */
public class Oas20Parameter extends OasParameter {

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
     * Constructor.
     */
    public Oas20Parameter() {
    }
    
    /**
     * Constructor.
     */
    public Oas20Parameter(String name) {
        super(name);
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.Parameter#createSchema()
     */
    @Override
    public Schema createSchema() {
        Schema rval = new Oas20Schema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }
    
    /**
     * Creates an Items.
     */
    public Oas20Items createItems() {
        Oas20Items rval = new Oas20Items();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

}
