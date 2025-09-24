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

package io.apicurio.datamodels.validation.rules.mutex;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Parameter;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Parameter;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

import java.util.Map;

/**
 * Implements the Parameter Example/Examples Mutual Exclusivity Rule.
 * @author eric.wittmann@gmail.com
 */
public class OasParameterExamplesMutualExclusivityRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasParameterExamplesMutualExclusivityRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitParameter(io.apicurio.datamodels.models.Parameter)
     */
    @Override
    public void visitParameter(Parameter node) {
        JsonNode example = null;
        Map<String, ?> examples = Map.of();
        if (ModelTypeUtil.isOpenApi30Model(node)) {
            example = ((OpenApi30Parameter) node).getExample();
            examples = ((OpenApi30Parameter) node).getExamples();
        } else if (ModelTypeUtil.isOpenApi31Model(node)) {
            example = ((OpenApi31Parameter) node).getExample();
            examples = ((OpenApi31Parameter) node).getExamples();
        }
        this.reportIf(hasValue(example) && !examples.isEmpty(), node, "example", map());
    }

}
