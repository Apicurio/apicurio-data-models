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

package io.apicurio.datamodels.validation.rules.invalid.name;

import io.apicurio.datamodels.models.openapi.OpenApiExample;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Example Definition Name Rule.
 * @author eric.wittmann@gmail.com
 */
public class OasInvalidExampleDefinitionNameRule extends OasInvalidPropertyNameRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidExampleDefinitionNameRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitExample(io.apicurio.datamodels.models.openapi.OpenApiExample)
     */
    @Override
    public void visitExample(OpenApiExample node) {
        if (isDefinition(node)) {
            String name = getDefinitionName(node);
            this.reportIfInvalid(isValidDefinitionName(name), node, "name", map());
        }
    }


}
