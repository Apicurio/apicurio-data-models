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

package io.apicurio.datamodels.core.validation.rules.invalid.value;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * Implements the Operation Summary Too Long rule.
 * @author eric.wittmann@gmail.com
 */
public class OasOperationSummaryTooLongRule extends OasInvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasOperationSummaryTooLongRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitOperation(io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        if (hasValue(node.summary)) {
            this.reportIfInvalid(node.summary.length() < 120, node, Constants.PROP_SUMMARY, map());
        }
    }

}
