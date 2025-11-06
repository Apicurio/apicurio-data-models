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

import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.AsyncApiReferenceable;
import io.apicurio.datamodels.refs.ReferenceUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Rule: AASS-001
 * Validates that security scheme references point to valid security schemes in components.
 * In AsyncAPI, security schemes can be referenced using $ref.
 *
 * @author eric.wittmann@gmail.com
 */
public class AaInvalidSecuritySchemeReferenceRule extends ValidationRule {

    /**
     * Constructor.
     *
     * @param ruleInfo
     */
    public AaInvalidSecuritySchemeReferenceRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        if (ModelTypeUtil.isAsyncApiModel(node)) {
            if (node instanceof AsyncApiReferenceable) {
                String ref = ((AsyncApiReferenceable) node).get$ref();
                if (hasValue(ref)) {
                    this.reportIfInvalid(ReferenceUtil.canResolveRef(ref, node), node, "$ref", map());
                }
            }
        }
    }

}
