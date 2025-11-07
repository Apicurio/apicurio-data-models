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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperation;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Rule: AAO-005
 * Validates that operation IDs are unique across all operations in an AsyncAPI document.
 * This handles both AsyncAPI 2.x (operations in channels) and AsyncAPI 3.0 (operations at document level).
 *
 * @author eric.wittmann@gmail.com
 */
public class AaOperationIdUniquenessRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaOperationIdUniquenessRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    private Map<String, List<Operation>> indexedOperations = new HashMap<>();

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitOperation(io.apicurio.datamodels.models.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        if (ModelTypeUtil.isAsyncApiModel(node)) {
            String operationId = null;

            if (node instanceof AsyncApiOperation) {
                operationId = ((AsyncApiOperation) node).getOperationId();
            }

            if (hasValue(operationId)) {
                List<Operation> dupes = this.indexedOperations.get(operationId);
                if (hasValue(dupes)) {
                    this.reportIfInvalid(dupes.size() > 1, dupes.get(0), "operationId", map("operationId", operationId));
                    this.report(node, "operationId", map("operationId", operationId));
                    dupes.add(node);
                } else {
                    dupes = new ArrayList<>();
                    dupes.add(node);
                    this.indexedOperations.put(operationId, dupes);
                }
            }
        }
    }

}
