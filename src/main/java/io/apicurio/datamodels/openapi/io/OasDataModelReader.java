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

package io.apicurio.datamodels.openapi.io;

import java.util.List;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.io.DataModelReader;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasSecurityRequirement;

/**
 * A data model reader for the OpenAPI data model.
 * @author eric.wittmann@gmail.com
 */
public abstract class OasDataModelReader<T extends OasDocument> extends DataModelReader<T> {
    
    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readDocument(java.lang.Object, io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void readDocument(Object json, T node) {
        super.readDocument(json, node);
        
        Object paths = JsonCompat.consumeProperty(json, Constants.PROP_PATHS);
        List<Object> security = JsonCompat.consumePropertyArray(json, Constants.PROP_SECURITY);
        
        // TODO read paths
        
        if (security != null) {
            security.forEach(sec -> {
                OasSecurityRequirement secModel = node.createSecurityRequirement();
                this.readSecurityRequirement(sec, secModel);
                node.addSecurityRequirement(secModel);
            });
        }
    }

}
