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

package io.apicurio.datamodels;

import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;

/**
 * Factory used to create documents.
 * @author eric.wittmann@gmail.com
 */
public class DocumentFactory {
    
    public static final Document create(DocumentType type) {
        switch (type) {
            case asyncapi2:
                AaiDocument doc = new AaiDocument();
                doc.asyncapi = "2.0.0";
                return doc;
            case openapi2:
            case openapi3:
            default:
                throw new RuntimeException("Failed to create a Document for type: " + type);
            
        }
    }

}
