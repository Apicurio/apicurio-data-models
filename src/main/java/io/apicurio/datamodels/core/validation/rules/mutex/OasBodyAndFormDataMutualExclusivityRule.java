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

package io.apicurio.datamodels.core.validation.rules.mutex;

import java.util.List;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.models.IOasParameterParent;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * Implements the Body and Form Data Mutual Exclusivity Rule.
 * @author eric.wittmann@gmail.com
 */
public class OasBodyAndFormDataMutualExclusivityRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasBodyAndFormDataMutualExclusivityRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    private void visitParameterParent(IOasParameterParent paramParent) {
        List<OasParameter> parameters = paramParent.getParameters();
        if (hasValue(parameters)) {
            boolean hasBodyParam = false;
            boolean hasFormDataParam = false;
            for (OasParameter param : parameters) {
                if (equals(param.in, "body")) {
                    hasBodyParam = true;
                }
                if (equals(param.in, "formData")) {
                    hasFormDataParam = true;
                }
            }
            this.reportIf(hasBodyParam && hasFormDataParam, (Node) paramParent, Constants.PROP_IN, map());
        }
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitOperation(io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        visitParameterParent((OasOperation) node);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitPathItem(io.apicurio.datamodels.openapi.models.OasPathItem)
     */
    @Override
    public void visitPathItem(OasPathItem node) {
        visitParameterParent(node);
    }

}
