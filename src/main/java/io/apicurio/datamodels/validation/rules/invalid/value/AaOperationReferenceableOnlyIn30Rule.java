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

import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Validates that the operation $ref property is only used in AsyncAPI 3.0.
 * Operations gained the Referenceable trait in AsyncAPI 3.0.
 * @author eric.wittmann@gmail.com
 */
public class AaOperationReferenceableOnlyIn30Rule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaOperationReferenceableOnlyIn30Rule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitOperation(io.apicurio.datamodels.models.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        // Check if this is AsyncAPI 2.x
        if (ModelTypeUtil.isAsyncApi2Model(node)) {
            // In 2.x, the $ref property doesn't exist on operations,
            // but it might be present as an extra property
            if (node.getExtraProperty("$ref") != null) {
                this.reportIf(true, node, "$ref", map());
            }
        }
    }

}
