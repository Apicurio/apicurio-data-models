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

package io.apicurio.datamodels.validation.rules.required;

import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Rule: AAO-002
 * Validates that AsyncAPI 3.0 Operation has a required 'channel' reference.
 *
 * @author eric.wittmann@gmail.com
 */
public class AaMissingOperationChannelReferenceRule extends RequiredPropertyValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaMissingOperationChannelReferenceRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public void visitOperation(Operation node) {
        if (ModelTypeUtil.isAsyncApi3Model(node)) {
            this.requireProperty(node, "channel", map());
        }
    }

}
