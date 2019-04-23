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

package io.apicurio.datamodels.core.validation;

import io.apicurio.datamodels.core.models.ValidationProblemSeverity;

/**
 * A default severity registry.  Sets all spec-mandated validation rules to "medium" severity.  All
 * other rules are ignored.
 * 
 * @author eric.wittmann@gmail.com
 */
public class DefaultSeverityRegistry implements IValidationSeverityRegistry {

    /**
     * @see io.apicurio.asyncapi.core.validation.IValidationSeverityRegistry#lookupSeverity(io.apicurio.asyncapi.core.validation.ValidationRuleMetaData)
     */
    @Override
    public ValidationProblemSeverity lookupSeverity(ValidationRuleMetaData rule) {
        if (rule.specMandated) {
            return ValidationProblemSeverity.medium;
        }
        return ValidationProblemSeverity.ignore;
    }

}
