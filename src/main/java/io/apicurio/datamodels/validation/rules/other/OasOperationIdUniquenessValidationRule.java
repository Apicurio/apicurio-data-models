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

package io.apicurio.datamodels.validation.rules.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Operation ID Uniqueness validation rule.
 * @author eric.wittmann@gmail.com
 */
public class OasOperationIdUniquenessValidationRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasOperationIdUniquenessValidationRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    private Map<String, List<Operation>> indexedOperations = new HashMap<>();

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitOperation(io.apicurio.datamodels.models.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        OpenApiOperation operation = (OpenApiOperation) node;
        if (hasValue(operation.getOperationId())) {
            List<Operation> dupes = this.indexedOperations.get(operation.getOperationId());
            if (hasValue(dupes)) {
                this.reportIfInvalid(dupes.size() > 1, dupes.get(0), "operationId", map("operationId", operation.getOperationId()));
                this.report(node, "operationId", map("operationId", operation.getOperationId()));
                dupes.add(node);
            } else {
                dupes = new ArrayList<>();
                dupes.add(node);
                this.indexedOperations.put(operation.getOperationId(), dupes);
            }
        }
    }

}
