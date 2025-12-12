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

import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Validates that operation traits in AsyncAPI 3.0 do not have the operationId property.
 * The operationId property was removed from operation traits in AsyncAPI 3.0.
 * @author eric.wittmann@gmail.com
 */
public class AaOperationTraitOperationIdIn2xOnlyRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaOperationTraitOperationIdIn2xOnlyRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.AllNodeVisitor#visitOperationTrait(io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait)
     */
    @Override
    public void visitOperationTrait(AsyncApiOperationTrait node) {
        // Check if this is AsyncAPI 3.0
        if (ModelTypeUtil.isAsyncApi3Model(node)) {
            // In 3.0, the operationId property doesn't exist on operation traits,
            // but it might be present as an extra property
            if (node.getExtraProperty("operationId") != null) {
                this.reportIf(true, node, "operationId", map());
            }
        }
    }

}
