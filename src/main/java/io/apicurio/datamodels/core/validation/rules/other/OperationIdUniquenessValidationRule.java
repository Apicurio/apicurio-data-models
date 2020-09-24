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

package io.apicurio.datamodels.core.validation.rules.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * Implements the Operation ID Uniqueness validation rule.
 * @author eric.wittmann@gmail.com
 */
public class OperationIdUniquenessValidationRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OperationIdUniquenessValidationRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    private Map<String, List<Operation>> indexedOperations = new HashMap<>();
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitOperation(io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        if (hasValue(node.operationId)) {
            List<Operation> dupes = this.indexedOperations.get(node.operationId);
            if (hasValue(dupes)) {
                this.reportIfInvalid(dupes.size() > 1, dupes.get(0), Constants.PROP_OPERATION_ID, map("operationId", node.operationId));
                this.report(node, Constants.PROP_OPERATION_ID, map("operationId", node.operationId));
                dupes.add(node);
            } else {
                dupes = new ArrayList<>();
                dupes.add(node);
                this.indexedOperations.put(node.operationId, dupes);
            }
        }
    }

}
