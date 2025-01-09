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
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30PathItem;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31PathItem;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Path Item Description Rule
 * @author eric.wittmann@gmail.com
 */
public class OasInvalidPathItemDescriptionRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidPathItemDescriptionRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public void visitPathItem(OpenApiPathItem node) {
        String description = null;
        if (node.root().modelType() == ModelType.OPENAPI30) {
            description = ((OpenApi30PathItem) node).getDescription();
        } else {
            description = ((OpenApi31PathItem) node).getDescription();
        }
        if (hasValue(description)) {
            this.reportIfInvalid(isValidCommonMark(description), node, "description", map());
        }
    }

}
