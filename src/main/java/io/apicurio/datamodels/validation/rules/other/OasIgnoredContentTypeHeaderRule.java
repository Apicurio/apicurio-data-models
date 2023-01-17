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

import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.util.NodeUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Ignored Content-Type Header validation rule.
 * @author eric.wittmann@gmail.com
 */
public class OasIgnoredContentTypeHeaderRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasIgnoredContentTypeHeaderRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitHeader(io.apicurio.datamodels.models.openapi.OpenApiHeader)
     */
    @Override
    public void visitHeader(OpenApiHeader node) {
        String headerName = getMappedNodeName(node);
        this.reportIf(NodeUtil.equals(headerName.toLowerCase(), "content-type"), node, null, null);
    }

}
