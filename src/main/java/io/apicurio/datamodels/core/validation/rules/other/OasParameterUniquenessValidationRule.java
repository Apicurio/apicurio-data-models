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

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.util.ReferenceUtil;
import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * Implements the Parameter Uniqueness validation rule.
 * @author eric.wittmann@gmail.com
 */
public class OasParameterUniquenessValidationRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasParameterUniquenessValidationRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * Given a 'in' and a 'name' for a parameter, return the # of parameters in the list
     * of parameters that match.
     */
    private int getParamCount(List<OasParameter> params, String paramName, String paramIn) {
        int count = 0;
        for (OasParameter param : params) {
            if (hasValue(param) && equals(param.name, paramName) && equals(param.in, paramIn)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Validates that all parameter name and "in" combinations are unique
     * @param params
     */
    private void ensureUnique(List<OasParameter> params) {
        if (!hasValue(params)) {
            return;
        }

        // Must validate against resolved params in the case where we're using $ref
        List<OasParameter> resolvedParams = new ArrayList<>(params.size());
        params.forEach(param -> {
            Node resolveNodeRef = ReferenceUtil.resolveNodeRef(param);
            if (resolveNodeRef instanceof OasParameter) {
                resolvedParams.add((OasParameter) resolveNodeRef);
            } else {
                resolvedParams.add(param);
            }
        });

        // Loop through the resolved params looking for duplicates.
        int idx = 0;
        for (OasParameter resolvedParam : resolvedParams) {
            if (hasValue(resolvedParam) && hasValue(resolvedParam.in) && hasValue(resolvedParam.name) && !equals(resolvedParam.in, "body")) {
                int count = getParamCount(resolvedParams, resolvedParam.name, resolvedParam.in);
                if (count > 1) {
                    // Report the error on the original param - not the resolved param.
                    OasParameter param = params.get(idx);
                    this.report(param, "in", map("paramIn", resolvedParam.in, "paramName", resolvedParam.name));
                }
            }
            idx++;
        }
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitPathItem(io.apicurio.datamodels.openapi.models.OasPathItem)
     */
    @Override
    public void visitPathItem(OasPathItem node) {
        this.ensureUnique(node.parameters);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitOperation(io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        OasOperation op = (OasOperation) node;
        this.ensureUnique(op.parameters);
    }

}
