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

import io.apicurio.datamodels.models.openapi.OpenApiEncoding;
import io.apicurio.datamodels.models.openapi.OpenApiMediaType;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Allow Reserved Not Allowed rule.
 * @author eric.wittmann@gmail.com
 */
public class OasAllowReservedNotAllowedRule extends AbstractInvalidPropertyValueRule {

    private String mediaTypeName;

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasAllowReservedNotAllowedRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public void visitMediaType(OpenApiMediaType node) {
        mediaTypeName = getMappedNodeName(node);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitEncoding(io.apicurio.datamodels.models.openapi.OpenApiEncoding)
     */
    @Override
    public void visitEncoding(OpenApiEncoding node) {
        if (hasValue(node.isAllowReserved())) {
            this.reportIf(!equals(mediaTypeName, "application/x-www-form-urlencoded"), node,
                    "allowReserved", map("name", mediaTypeName));
        }
    }

}