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
import io.apicurio.datamodels.models.openapi.OpenApiExample;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Header;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Header;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements the Header Example/Examples Mutual Exclusivity Rule.
 * @author eric.wittmann@gmail.com
 */
public class OasHeaderExamplesMutualExclusivityRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasHeaderExamplesMutualExclusivityRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitHeader(io.apicurio.datamodels.models.openapi.OpenApiHeader)
     */
    @Override
    public void visitHeader(OpenApiHeader node) {
        JsonNode example = null;
        Map<String, OpenApiExample> examples = new HashMap<>();

        if (ModelTypeUtil.isOpenApi30Model(node)) {
            example = ((OpenApi30Header) node).getExample();
            examples = ((OpenApi30Header) node).getExamples();
        } else if (ModelTypeUtil.isOpenApi31Model(node)) {
            example = ((OpenApi31Header) node).getExample();
            examples = ((OpenApi31Header) node).getExamples();
        }

        this.reportIf(hasValue(example) && !examples.isEmpty(), node, "example", map());
    }

}
