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

package io.apicurio.datamodels.core.validation.rules.other;

import java.util.List;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.models.IOasParameterParent;
import io.apicurio.datamodels.openapi.models.OasParameter;

/**
 * Implements the Body Parameter Uniqueness validation rule (can only have 1 body param).
 * @author eric.wittmann@gmail.com
 */
public class OasBodyParameterUniquenessValidationRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasBodyParameterUniquenessValidationRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitParameter(io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    public void visitParameter(Parameter node) {
        OasParameter param = (OasParameter) node;
        List<OasParameter> params = ((IOasParameterParent) (node.parent())).getParameters();
        if (equals(param.in, "body")) {
            int count = countBodyParams(params);
            this.reportIf(count > 1, node, Constants.PROP_IN, map());
        }
    }

    /**
     * Counts the # of body style params.
     */
    private int countBodyParams(List<OasParameter> params) {
        int count = 0;
        for (OasParameter parameter : params) {
            if (equals(parameter.in, "body")) {
                count++;
            }
        }
        return count;
    }

}
