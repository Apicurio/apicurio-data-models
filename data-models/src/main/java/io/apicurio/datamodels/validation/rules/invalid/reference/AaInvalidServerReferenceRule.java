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

import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.asyncapi.AsyncApiReferenceable;
import io.apicurio.datamodels.refs.ReferenceUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Rule: SRV-008
 * Validates that a Server's $ref property points to a valid server definition in components.
 * Servers became referenceable in AsyncAPI 2.3, so this applies to 2.3-2.6 and 3.0.
 *
 * @author eric.wittmann@gmail.com
 */
public class AaInvalidServerReferenceRule extends ValidationRule {

    /**
     * Constructor.
     *
     * @param ruleInfo
     */
    public AaInvalidServerReferenceRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public void visitServer(Server node) {
        if (node instanceof AsyncApiReferenceable) {
            String ref = ((AsyncApiReferenceable) node).get$ref();
            if (hasValue(ref)) {
                this.reportIfInvalid(ReferenceUtil.canResolveRef(ref, node), node, "$ref", map());
            }
        }
    }

}
