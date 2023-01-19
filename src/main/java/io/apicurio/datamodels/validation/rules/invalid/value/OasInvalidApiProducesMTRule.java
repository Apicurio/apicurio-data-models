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

package io.apicurio.datamodels.validation.rules.invalid.value;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Document;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid API 'Produces' Mime-Type rule.
 * @author eric.wittmann@gmail.com
 */
public class OasInvalidApiProducesMTRule extends AbstractInvalidPropertyValueRule {
    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidApiProducesMTRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitDocument(io.apicurio.datamodels.models.Document)
     */
    @Override
    public void visitDocument(Document node) {
        OpenApi20Document doc = (OpenApi20Document) node;
        if (hasValue(doc.getProduces())) {
            this.reportIfInvalid(isValidMimeType(doc.getProduces()), node, "produces", map());
        }
    }

}