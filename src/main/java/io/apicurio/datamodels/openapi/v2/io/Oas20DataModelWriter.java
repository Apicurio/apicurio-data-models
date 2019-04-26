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

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.io.OasDataModelWriter;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class Oas20DataModelWriter extends OasDataModelWriter implements IOas20Visitor {

    /**
     * @see io.apicurio.datamodels.core.io.DataModelWriter#writeDocument(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected void writeDocument(Document node, Object json) {
        Oas20Document doc = (Oas20Document) node;
        JsonCompat.setPropertyString(json, Constants.PROP_SWAGGER, doc.swagger);
        JsonCompat.setPropertyNull(json, Constants.PROP_INFO);
        JsonCompat.setPropertyString(json, Constants.PROP_HOST, doc.host);
        JsonCompat.setPropertyString(json, Constants.PROP_BASE_PATH, doc.basePath);
        JsonCompat.setPropertyStringArray(json, Constants.PROP_SCHEMES, doc.schemes);
        JsonCompat.setPropertyStringArray(json, Constants.PROP_CONSUMES, doc.consumes);
        JsonCompat.setPropertyStringArray(json, Constants.PROP_PRODUCES, doc.produces);
        JsonCompat.setPropertyNull(json, Constants.PROP_PATHS);
        JsonCompat.setPropertyNull(json, Constants.PROP_DEFINITIONS);
        JsonCompat.setPropertyNull(json, Constants.PROP_PARAMETERS);
        JsonCompat.setPropertyNull(json, Constants.PROP_RESPONSES);
        JsonCompat.setPropertyNull(json, Constants.PROP_SECURITY_DEFINITIONS);
        JsonCompat.setPropertyNull(json, Constants.PROP_SECURITY);
        JsonCompat.setPropertyNull(json, Constants.PROP_TAGS);
        JsonCompat.setPropertyNull(json, Constants.PROP_EXTERNAL_DOCS);
        
        writeExtraProperties(json, node);
    }

}
