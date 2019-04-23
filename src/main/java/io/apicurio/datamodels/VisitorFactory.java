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

import io.apicurio.datamodels.asyncapi.io.AaiDataModelReader;
import io.apicurio.datamodels.asyncapi.io.AaiDataModelWriter;
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.validation.AaiValidationProblemsResetVisitor;
import io.apicurio.datamodels.asyncapi.validation.AaiValidationVisitor;
import io.apicurio.datamodels.core.io.DataModelReader;
import io.apicurio.datamodels.core.io.DataModelWriter;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.validation.ValidationProblemsResetVisitor;
import io.apicurio.datamodels.core.validation.ValidationVisitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class VisitorFactory {
    
    public static final ValidationProblemsResetVisitor createValidationProblemsResetVisitor(Document doc) {
        switch (doc.getDocumentType()) {
            case asyncapi2:
                return new AaiValidationProblemsResetVisitor();
            case openapi2:
            case openapi3:
            default:
                throw new RuntimeException("Failed to create a reset validation problems visitor for type: " + doc.getDocumentType());
        }
    }
    
    public static final ValidationVisitor createValidationVisitor(Document doc) {
        switch (doc.getDocumentType()) {
            case asyncapi2:
                return new AaiValidationVisitor((AaiDocument) doc);
            case openapi2:
            case openapi3:
            default:
                throw new RuntimeException("Failed to create a validation visitor for type: " + doc.getDocumentType());
        }
    }
    
    public static final DataModelReader<?> createDataModelReader(DocumentType type) {
        switch (type) {
            case asyncapi2:
                return new AaiDataModelReader();
            case openapi2:
            case openapi3:
            default:
                throw new RuntimeException("Failed to create a data model reader for type: " + type);
        }
    }

    public static DataModelWriter createDataModelWriter(Document doc) {
        switch (doc.getDocumentType()) {
            case asyncapi2:
                return new AaiDataModelWriter();
            case openapi2:
            case openapi3:
            default:
                throw new RuntimeException("Failed to create a validation visitor for type: " + doc.getDocumentType());
        }
    }

}
