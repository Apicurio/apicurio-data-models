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
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Parameter;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

import java.util.List;

/**
 * Implements the Parameter Default Not in Enum rule for AsyncAPI 3.0.
 * Validates that if a parameter has both a default value and an enum,
 * the default value must be one of the enum values.
 * @author eric.wittmann@gmail.com
 */
public class AaParameterDefaultNotInEnumRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaParameterDefaultNotInEnumRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.AllNodeVisitor#visitParameter(Parameter)
     */
    @Override
    public void visitParameter(Parameter node) {
        if (ModelTypeUtil.isAsyncApi3Model(node)) {
            AsyncApi30Parameter parameter = (AsyncApi30Parameter) node;
            String defaultValue = parameter.getDefault();
            List<String> enumValues = parameter.getEnum();

            if (hasValue(defaultValue) && hasValue(enumValues)) {
                boolean isValid = enumValues.contains(defaultValue);
                this.reportIfInvalid(isValid, node, "default", map());
            }
        }
    }

}
