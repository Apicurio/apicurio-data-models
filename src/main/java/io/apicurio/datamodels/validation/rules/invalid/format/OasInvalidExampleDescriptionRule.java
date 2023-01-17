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

package io.apicurio.datamodels.validation.rules.invalid.format;

import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.openapi.OpenApiExample;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Example;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Example;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Example Description Rule
 * @author eric.wittmann@gmail.com
 */
public class OasInvalidExampleDescriptionRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidExampleDescriptionRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public void visitExample(OpenApiExample node) {
        if (node.root().modelType() == ModelType.OPENAPI30) {
            OpenApi30Example example30 = (OpenApi30Example) node;
            if (hasValue(example30.getDescription())) {
                this.reportIfInvalid(isValidCommonMark(example30.getDescription()), example30, "description", map());
            }
        } else {
            OpenApi31Example example31 = (OpenApi31Example) node;
            if (hasValue(example31.getDescription())) {
                this.reportIfInvalid(isValidCommonMark(example31.getDescription()), example31, "description", map());
            }
        }
    }
}
