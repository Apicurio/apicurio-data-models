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

import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.Referenceable;
import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.refs.ReferenceUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Security Scheme Reference rule.
 * @author eric.wittmann@gmail.com
 */
public class OasInvalidSecuritySchemeReferenceRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidSecuritySchemeReferenceRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitSecurityScheme(io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        if (node.root().modelType() == ModelType.OPENAPI30 || node.root().modelType() == ModelType.OPENAPI31) {
            String ref = ((Referenceable) node).get$ref();
            if (hasValue(ref)) {
                this.reportIfInvalid(ReferenceUtil.canResolveRef(ref, node), node, "$ref", map());
            }
        }
    }
}
