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

import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.asyncapi.AsyncApiParameter;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Parameter Location Value rule for AsyncAPI 3.0.
 * In AsyncAPI 3.0, parameter location must be a valid runtime expression.
 * @author eric.wittmann@gmail.com
 */
public class AaInvalidParameterLocationValueRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaInvalidParameterLocationValueRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.AllNodeVisitor#visitParameter(io.apicurio.datamodels.models.Parameter)
     */
    @Override
    public void visitParameter(Parameter node) {
        if (ModelTypeUtil.isAsyncApi3Model(node)) {
            AsyncApiParameter parameter = (AsyncApiParameter) node;
            String location = parameter.getLocation();

            if (hasValue(location)) {
                // Runtime expressions must start with $
                boolean isValid = location.startsWith("$");
                this.reportIfInvalid(isValid, node, "location", map());
            }
        }
    }

}
