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

package io.apicurio.datamodels.core.validation.rules.invalid.format;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.common.IExample;
import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.v3.models.Oas30Example;
import io.apicurio.datamodels.openapi.v3.models.Oas30ExampleDefinition;

/**
 * Implements the Invalid Example Description Rule
 * @author eric.wittmann@gmail.com
 */
public class OasInvalidExampleDescriptionRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidExampleDescriptionRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitExample(io.apicurio.datamodels.core.models.common.IExample)
     */
    @Override
    public void visitExample(IExample node) {
        Oas30Example example30 = (Oas30Example) node;
        if (hasValue(example30.description)) {
            this.reportIfInvalid(isValidCommonMark(example30.description), example30, Constants.PROP_DESCRIPTION, map());
        }
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitExampleDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30ExampleDefinition)
     */
    @Override
    public void visitExampleDefinition(Oas30ExampleDefinition node) {
        visitExample(node);
    }
}
