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

import io.apicurio.datamodels.models.openapi.v20.OpenApi20Items;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Unknown Array Format rule.
 * @author eric.wittmann@gmail.com
 */
public class OasUnknownArrayFormatRule extends AbstractInvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasUnknownArrayFormatRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitItems(io.apicurio.datamodels.models.openapi.v20.OpenApi20Items)
     */
    @Override
    public void visitItems(OpenApi20Items node) {
        if (hasValue(node.getFormat())) {
            this.reportIfInvalid(isValidEnumItem(node.getFormat(), array("int32", "int64", "float", "double", "byte", "binary", "date", "date-time", "password")),
                    node, "format", map());
        }
    }

}