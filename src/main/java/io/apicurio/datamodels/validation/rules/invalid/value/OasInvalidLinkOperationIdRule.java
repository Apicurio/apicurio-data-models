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

package io.apicurio.datamodels.validation.rules.invalid.value;

import io.apicurio.datamodels.TraverserDirection;
import io.apicurio.datamodels.VisitorUtil;
import io.apicurio.datamodels.models.openapi.OpenApiLink;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.visitors.OperationFinder;

/**
 * @author eric.wittmann@gmail.com
 */
public class OasInvalidLinkOperationIdRule extends AbstractInvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidLinkOperationIdRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitLink(io.apicurio.datamodels.models.openapi.OpenApiLink)
     */
    @Override
    public void visitLink(OpenApiLink node) {
        if (hasValue(node.getOperationId())) {
            OperationFinder finder = new OperationFinder(node.getOperationId());
            VisitorUtil.visitTree(node.root(), finder, TraverserDirection.down);
            this.reportIfInvalid(finder.isFound(), node, "operationId", map());
        }
    }

}