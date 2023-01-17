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

package io.apicurio.datamodels.validation.rules.mutex;

import io.apicurio.datamodels.models.openapi.OpenApiMediaType;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Media Type Example/Examples Mutual Exclusivity Rule.
 * @author eric.wittmann@gmail.com
 */
public class OasMediaTypeExamplesMutualExclusivityRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasMediaTypeExamplesMutualExclusivityRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitMediaType(io.apicurio.datamodels.models.openapi.OpenApiMediaType)
     */
    @Override
    public void visitMediaType(OpenApiMediaType node) {
        this.reportIf(hasValue(node.getExample()) && node.getExamples().size() > 0, node, "example", map());
    }

}
