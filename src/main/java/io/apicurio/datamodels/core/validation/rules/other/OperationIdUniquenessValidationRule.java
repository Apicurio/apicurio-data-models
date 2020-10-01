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

import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitDefinition;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Node;
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

    private Map<String, List<Node>> indexedOperations = new HashMap<>();
    
    private void processOperationId(Node node, String operationId) {
        if (hasValue(operationId)) {
            List<Node> dupes = this.indexedOperations.get(operationId);
            if (hasValue(dupes)) {
                this.reportIfInvalid(dupes.size() > 1, dupes.get(0), Constants.PROP_OPERATION_ID, map("operationId", operationId));
                this.report(node, Constants.PROP_OPERATION_ID, map("operationId", operationId));
                dupes.add(node);
            } else {
                dupes = new ArrayList<>();
                dupes.add(node);
                this.indexedOperations.put(operationId, dupes);
            }
        }
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitOperation(io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        this.processOperationId(node, node.operationId);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitOperationTrait(io.apicurio.datamodels.asyncapi.models.AaiOperationTrait)
     */
    @Override
    public void visitOperationTrait(AaiOperationTrait node) {
        this.processOperationId(node, node.operationId);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitOperationTraitDefinition(io.apicurio.datamodels.asyncapi.models.AaiOperationTraitDefinition)
     */
    @Override
    public void visitOperationTraitDefinition(AaiOperationTraitDefinition node) {
        super.visitOperationTrait(node);
    }

}
