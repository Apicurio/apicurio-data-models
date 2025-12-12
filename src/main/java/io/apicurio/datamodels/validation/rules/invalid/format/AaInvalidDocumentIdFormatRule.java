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

package io.apicurio.datamodels.validation.rules.invalid.format;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.asyncapi.AsyncApiDocument;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Rule: AAD-005
 * Validates that the AsyncAPI document ID property is a valid URI/identifier if provided.
 *
 * @author eric.wittmann@gmail.com
 */
public class AaInvalidDocumentIdFormatRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaInvalidDocumentIdFormatRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public void visitDocument(Document node) {
        if (ModelTypeUtil.isAsyncApiModel(node)) {
            AsyncApiDocument doc = (AsyncApiDocument) node;
            if (hasValue(doc.getId())) {
                // Document ID should be a valid URI
                this.reportIfInvalid(isValidUrl(doc.getId()), node, "id", map());
            }
        }
    }

}
