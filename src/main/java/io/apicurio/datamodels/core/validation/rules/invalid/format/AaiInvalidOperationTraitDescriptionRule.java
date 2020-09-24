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

package io.apicurio.datamodels.core.validation.rules.invalid.format;

import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitDefinition;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Operation Trait Description Rule
 * @author eric.wittmann@gmail.com
 */
public class AaiInvalidOperationTraitDescriptionRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaiInvalidOperationTraitDescriptionRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitOperationTrait(io.apicurio.datamodels.asyncapi.models.AaiOperationTrait)
     */
    @Override
    public void visitOperationTrait(AaiOperationTrait node) {
        if (hasValue(node.description)) {
            this.reportIfInvalid(isValidGFM(node.description), node, Constants.PROP_DESCRIPTION, map());
        }
    }
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitOperationTraitDefinition(io.apicurio.datamodels.asyncapi.models.AaiOperationTraitDefinition)
     */
    @Override
    public void visitOperationTraitDefinition(AaiOperationTraitDefinition node) {
        this.visitOperationTrait(node);
    }

}
