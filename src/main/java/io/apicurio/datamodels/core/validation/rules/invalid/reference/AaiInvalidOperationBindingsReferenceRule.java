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

package io.apicurio.datamodels.core.validation.rules.invalid.reference;

import io.apicurio.datamodels.asyncapi.models.AaiOperationBindings;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBindingsDefinition;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.util.ReferenceUtil;
import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Schema Reference rule.
 * @author eric.wittmann@gmail.com
 */
public class AaiInvalidOperationBindingsReferenceRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaiInvalidOperationBindingsReferenceRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitOperationBindings(io.apicurio.datamodels.asyncapi.models.AaiOperationBindings)
     */
    @Override
    public void visitOperationBindings(AaiOperationBindings node) {
        if (hasValue(node.$ref)) {
            this.reportIfInvalid(ReferenceUtil.canResolveRef(node.$ref, node), node, Constants.PROP_$REF, map());
        }
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitOperationBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiOperationBindingsDefinition)
     */
    @Override
    public void visitOperationBindingsDefinition(AaiOperationBindingsDefinition node) {
        visitOperationBindings(node);
    }

}
