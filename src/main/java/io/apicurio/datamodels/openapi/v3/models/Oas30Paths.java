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

package io.apicurio.datamodels.openapi.v3.models;

import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.models.OasPaths;

/**
 * @author eric.wittmann@gmail.com
 */
public class Oas30Paths extends OasPaths {

    /**
     * @see io.apicurio.datamodels.openapi.models.OasPaths#createPathItem(java.lang.String)
     */
    @Override
    public OasPathItem createPathItem(String path) {
        Oas30PathItem pathItem = new Oas30PathItem(path);
        pathItem._ownerDocument = this.ownerDocument();
        pathItem._parent = this;
        return pathItem;
    }

}
