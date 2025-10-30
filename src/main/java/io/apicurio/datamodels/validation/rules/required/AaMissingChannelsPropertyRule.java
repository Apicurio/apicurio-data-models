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

package io.apicurio.datamodels.validation.rules.required;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Rule: AAD-003
 * Validates that AsyncAPI 2.x documents have the required 'channels' property.
 * Note: In AsyncAPI 3.0, channels are optional.
 *
 * @author eric.wittmann@gmail.com
 */
public class AaMissingChannelsPropertyRule extends RequiredPropertyValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaMissingChannelsPropertyRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public void visitDocument(Document node) {
        ModelType modelType = node.root().modelType();

        // Channels are required in AsyncAPI 2.x but optional in 3.0
        if (modelType == ModelType.ASYNCAPI20 ||
            modelType == ModelType.ASYNCAPI21 ||
            modelType == ModelType.ASYNCAPI22 ||
            modelType == ModelType.ASYNCAPI23 ||
            modelType == ModelType.ASYNCAPI24 ||
            modelType == ModelType.ASYNCAPI25 ||
            modelType == ModelType.ASYNCAPI26) {

            this.requireProperty(node, "channels", map());
        }
    }

}
