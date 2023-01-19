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

package io.apicurio.datamodels.validation.rules.required;

import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.util.NodeUtil;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * @author eric.wittmann@gmail.com
 */
public class OasMissingResponseDescriptionRule extends RequiredPropertyValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasMissingResponseDescriptionRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.AllNodeVisitor#visitResponse(io.apicurio.datamodels.models.openapi.OpenApiResponse)
     */
    @Override
    public void visitResponse(OpenApiResponse node) {
        if (hasValue(NodeUtil.getNodeProperty(node, "$ref"))) {
            return;
        }
        if (!isDefinition(node)) {
            String statusCode = getStatusCode(node);
            this.requireProperty(node, "description", map("statusCode", statusCode));
        }
    }

}