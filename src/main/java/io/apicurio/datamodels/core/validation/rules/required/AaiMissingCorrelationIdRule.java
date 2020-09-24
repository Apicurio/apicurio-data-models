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

package io.apicurio.datamodels.core.validation.rules.required;

import io.apicurio.datamodels.asyncapi.v2.models.Aai20CorrelationId;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * @author cfoskin@redhat.com
 */
public class AaiMissingCorrelationIdRule extends RequiredPropertyValidationRule {

    public AaiMissingCorrelationIdRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    public void visitCorrelationId(Aai20CorrelationId node) {
        this.requireProperty(node, Constants.PROP_CORRELATION_ID, map());
    }

}
