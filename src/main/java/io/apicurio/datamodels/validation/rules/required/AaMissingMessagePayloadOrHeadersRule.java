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

import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.AsyncApiReferenceable;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Missing Message Payload or Headers rule for AsyncAPI.
 * Messages should have either payload or headers defined.
 * @author eric.wittmann@gmail.com
 */
public class AaMissingMessagePayloadOrHeadersRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaMissingMessagePayloadOrHeadersRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.AllNodeVisitor#visitMessage(io.apicurio.datamodels.models.asyncapi.AsyncApiMessage)
     */
    @Override
    public void visitMessage(AsyncApiMessage node) {
        // Skip if it's a reference
        if (node instanceof AsyncApiReferenceable && hasValue(((AsyncApiReferenceable) node).get$ref())) {
            return;
        }

        // Check for payload or headers using getExtraProperty since they're not in the base interface
        Object payload = node.getExtraProperty("payload");
        Object headers = node.getExtraProperty("headers");

        // Message should have at least one of payload or headers
        if (!hasValue(payload) && !hasValue(headers)) {
            this.reportIfInvalid(false, node, null, map("property", "payload or headers"));
        }
    }

}
