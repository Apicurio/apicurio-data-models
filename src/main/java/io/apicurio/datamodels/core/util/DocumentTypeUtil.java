/*
 * Copyright 2021 Red Hat Inc
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

package io.apicurio.datamodels.core.util;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.DocumentType;

/**
 * @author eric.wittmann@gmail.com
 */
public class DocumentTypeUtil {

    /**
     * Called to discover what type of document the given JSON data represents.  This method
     * interrogates the following appropriate top level properties:
     *
     * - asyncapi
     * - openapi
     * - swagger
     *
     * Based on which property is defined, and the value of that property, the correct document
     * type is determined and returned.
     *
     * @param json
     */
    public static DocumentType discoverDocumentType(Object json) {
        String asyncapi = JsonCompat.getPropertyString(json, Constants.PROP_ASYNCAPI);
        String openapi = JsonCompat.getPropertyString(json, Constants.PROP_OPENAPI);
        String swagger = JsonCompat.getPropertyString(json, Constants.PROP_SWAGGER);
        if (asyncapi != null && asyncapi.startsWith("2.")) {
            return DocumentType.asyncapi2;
        }
        if (openapi != null) {
            if (openapi.startsWith("2.")) {
                return DocumentType.openapi2;
            }
            if (openapi.startsWith("3.0")) {
                return DocumentType.openapi3;
            }
        }
        if (swagger != null) {
            if (swagger.startsWith("2.")) {
                return DocumentType.openapi2;
            }
        }

        throw new RuntimeException("Unknown data model type or version.");
    }

}
