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

import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Rule: AAM-013
 * Validates that messages do not use messageId in AsyncAPI 3.0.
 * The messageId property was removed from messages in AsyncAPI 3.0,
 * so it should not be used.
 *
 * @author eric.wittmann@gmail.com
 */
public class AaMessageNoMessageIdIn30Rule extends ValidationRule {

    /**
     * Constructor.
     *
     * @param ruleInfo
     */
    public AaMessageNoMessageIdIn30Rule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public void visitMessage(AsyncApiMessage node) {
        ModelType modelType = node.root().modelType();

        // Check if this is AsyncAPI 3.0 (where messageId was removed)
        if (modelType == ModelType.ASYNCAPI30) {
            // Check if the message has a messageId property
            if (hasValue(node.getExtraProperty("messageId"))) {
                this.reportIf(true, node, "messageId", map());
            }
        }
    }

}
