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

package io.apicurio.datamodels.openapi.v2.io;

import java.util.List;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.openapi.io.OasDataModelReader;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;

/**
 * A data model reader for the OpenAPI 2.0 data model.
 * @author eric.wittmann@gmail.com
 */
public class Oas20DataModelReader extends OasDataModelReader<Oas20Document> {
    
    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readDocument(java.lang.Object, io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void readDocument(Object json, Oas20Document node) {
        super.readDocument(json, node);

        JsonCompat.consumePropertyString(json, Constants.PROP_SWAGGER);
        String host = JsonCompat.consumePropertyString(json, Constants.PROP_HOST);
        String basePath = JsonCompat.consumePropertyString(json, Constants.PROP_BASE_PATH);
        List<String> schemes = JsonCompat.consumePropertyStringArray(json, Constants.PROP_SCHEMES);
        List<String> consumes = JsonCompat.consumePropertyStringArray(json, Constants.PROP_CONSUMES);
        List<String> produces = JsonCompat.consumePropertyStringArray(json, Constants.PROP_PRODUCES);
        Object definitions = JsonCompat.consumeProperty(json, Constants.PROP_DEFINITIONS);
        Object parameters = JsonCompat.consumeProperty(json, Constants.PROP_PARAMETERS);
        Object responses = JsonCompat.consumeProperty(json, Constants.PROP_RESPONSES);
        List<Object> securityDefinitions = JsonCompat.consumePropertyArray(json, Constants.PROP_SECURITY_DEFINITIONS);
        
        node.host = host;
        node.basePath = basePath;
        node.schemes = schemes;
        node.consumes = consumes;
        node.produces = produces;
        
        // TODO read all this shit!
        this.readExtraProperties(json, node);
    }
}
