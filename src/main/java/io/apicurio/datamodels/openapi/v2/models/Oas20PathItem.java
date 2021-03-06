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

import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * Models an OpenAPI 2.0 path item.
 * @author eric.wittmann@gmail.com
 */
public class Oas20PathItem extends OasPathItem {
    
    /**
     * Constructor.
     * @param path
     */
    public Oas20PathItem(String path) {
        super(path);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.models.OasPathItem#createOperation(java.lang.String)
     */
    @Override
    public OasOperation createOperation(String method) {
        OasOperation rval = new Oas20Operation(method);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.models.OasPathItem#createParameter()
     */
    @Override
    public OasParameter createParameter() {
        OasParameter rval = new Oas20Parameter();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

}
