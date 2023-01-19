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

package io.apicurio.datamodels.validation.rules.invalid.reference;

import io.apicurio.datamodels.models.openapi.OpenApiLink;
import io.apicurio.datamodels.refs.ReferenceUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Link Operation Reference rule.
 * @author eric.wittmann@gmail.com
 */
public class OasInvalidLinkOperationReferenceRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidLinkOperationReferenceRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitLink(io.apicurio.datamodels.models.openapi.OpenApiLink)
     */
    @Override
    public void visitLink(OpenApiLink node) {
        if (hasValue(node.getOperationRef())) {
            this.reportIfInvalid(ReferenceUtil.canResolveRef(node.getOperationRef(), node), node, "operationRef", map());
        }
    }

}
