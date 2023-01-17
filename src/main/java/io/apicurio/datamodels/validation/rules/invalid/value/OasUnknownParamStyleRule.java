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
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Parameter;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * @author eric.wittmann@gmail.com
 */
public class OasUnknownParamStyleRule extends AbstractInvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasUnknownParamStyleRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitParameter(io.apicurio.datamodels.models.Parameter)
     */
    @Override
    public void visitParameter(Parameter node) {
        OpenApi30Parameter param = (OpenApi30Parameter) node;
        if (hasValue(param.getStyle())) {
            this.reportIfInvalid(isValidEnumItem(param.getStyle(), array("matrix", "label", "form", "simple", "spaceDelimited", "pipeDelimited", "deepObject")),
                    node, "style", map("style", param.getStyle()));
        }
    }

}