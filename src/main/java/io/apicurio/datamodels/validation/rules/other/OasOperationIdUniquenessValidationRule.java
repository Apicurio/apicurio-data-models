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
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperation;
import io.apicurio.datamodels.models.asyncapi.v20.AsyncApi20Operation;
import io.apicurio.datamodels.models.asyncapi.v21.AsyncApi21Operation;
import io.apicurio.datamodels.models.asyncapi.v22.AsyncApi22Operation;
import io.apicurio.datamodels.models.asyncapi.v23.AsyncApi23Operation;
import io.apicurio.datamodels.models.asyncapi.v24.AsyncApi24Operation;
import io.apicurio.datamodels.models.asyncapi.v25.AsyncApi25Operation;
import io.apicurio.datamodels.models.asyncapi.v26.AsyncApi26Operation;
import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Operation ID Uniqueness validation rule for both OpenAPI and AsyncAPI.
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
        String operationId = getOperationId(node);
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

    /**
     * Gets the operation ID from an operation node (works for both OpenAPI and AsyncAPI 2.x).
     */
    private String getOperationId(Operation node) {
        if (node instanceof OpenApiOperation) {
            return ((OpenApiOperation) node).getOperationId();
        } else if (node instanceof AsyncApi20Operation) {
            return ((AsyncApi20Operation) node).getOperationId();
        } else if (node instanceof AsyncApi21Operation) {
            return ((AsyncApi21Operation) node).getOperationId();
        } else if (node instanceof AsyncApi22Operation) {
            return ((AsyncApi22Operation) node).getOperationId();
        } else if (node instanceof AsyncApi23Operation) {
            return ((AsyncApi23Operation) node).getOperationId();
        } else if (node instanceof AsyncApi24Operation) {
            return ((AsyncApi24Operation) node).getOperationId();
        } else if (node instanceof AsyncApi25Operation) {
            return ((AsyncApi25Operation) node).getOperationId();
        } else if (node instanceof AsyncApi26Operation) {
            return ((AsyncApi26Operation) node).getOperationId();
        }
        return null;
    }

}
