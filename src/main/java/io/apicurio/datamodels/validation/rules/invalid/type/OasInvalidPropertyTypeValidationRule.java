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

package io.apicurio.datamodels.validation.rules.invalid.type;

import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Property Type validation rule.  This rule is responsible
 * for reporting whenever the **type** and **items** of a property fails to conform to the required
 * format defined by the specification
 * @author eric.wittmann@gmail.com
 */
public abstract class OasInvalidPropertyTypeValidationRule extends ValidationRule {

    protected static final String[] ALLOWED_TYPES = {"string", "number", "integer", "boolean", "array", "object"};

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidPropertyTypeValidationRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * Returns true if the type node has a valid type.
     * @param type
     * @return {boolean}
     */
    protected boolean isValidType(String type) {
        if (hasValue(type)) {
            return isValidEnumItem(type, ALLOWED_TYPES);
        }
        return true;
    }

    @Override
    public void visitSchema(Schema node) {
        if (!isValidType(node.getType())) {
            // TODO implement this!
        }
    }
}
