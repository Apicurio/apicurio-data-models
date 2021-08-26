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

import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.factories.DocumentFactory;
import io.apicurio.datamodels.core.factories.VisitorFactory;
import io.apicurio.datamodels.core.io.DataModelReader;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;

/**
 * @author eric.wittmann@gmail.com
 */
public class DocumentUtil {

    /**
     * Creates a new, empty document of the given type.
     * @param type
     */
    public static Document createDocument(DocumentType type) {
        switch (type) {
            case asyncapi2:
                return new Aai20Document();
            case openapi2:
                return new Oas20Document();
            case openapi3:
                return new Oas30Document();
            default:
                throw new RuntimeException("Unknown document type: " + type);
        }
    }

    /**
     * Reads an entire document from JSON data.  The JSON data (already parsed, not in string format) is
     * read as a data model {@link Document} and returned.
     * @param json
     */
    public static Document readDocument(Object json) {
        // Clone the input because the reader is destructive to the source data.
        Object clonedJson = JsonCompat.clone(json);
        DocumentType type = DocumentTypeUtil.discoverDocumentType(clonedJson);
        DataModelReader reader = VisitorFactory.createDataModelReader(type);
        Document document = DocumentFactory.create(type);
        reader.readDocument(clonedJson, document);
        return document;
    }

}
