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
import io.apicurio.datamodels.models.asyncapi.v20.AsyncApi20Message;
import io.apicurio.datamodels.models.asyncapi.v21.AsyncApi21Message;
import io.apicurio.datamodels.models.asyncapi.v22.AsyncApi22Message;
import io.apicurio.datamodels.models.asyncapi.v23.AsyncApi23Message;
import io.apicurio.datamodels.models.asyncapi.v24.AsyncApi24Message;
import io.apicurio.datamodels.models.asyncapi.v25.AsyncApi25Message;
import io.apicurio.datamodels.models.asyncapi.v26.AsyncApi26Message;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Message;
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

        // Check for payload or headers based on version
        Object payload = null;
        Object headers = null;

        if (node instanceof AsyncApi20Message) {
            payload = ((AsyncApi20Message) node).getPayload();
            headers = ((AsyncApi20Message) node).getHeaders();
        } else if (node instanceof AsyncApi21Message) {
            payload = ((AsyncApi21Message) node).getPayload();
            headers = ((AsyncApi21Message) node).getHeaders();
        } else if (node instanceof AsyncApi22Message) {
            payload = ((AsyncApi22Message) node).getPayload();
            headers = ((AsyncApi22Message) node).getHeaders();
        } else if (node instanceof AsyncApi23Message) {
            payload = ((AsyncApi23Message) node).getPayload();
            headers = ((AsyncApi23Message) node).getHeaders();
        } else if (node instanceof AsyncApi24Message) {
            payload = ((AsyncApi24Message) node).getPayload();
            headers = ((AsyncApi24Message) node).getHeaders();
        } else if (node instanceof AsyncApi25Message) {
            payload = ((AsyncApi25Message) node).getPayload();
            headers = ((AsyncApi25Message) node).getHeaders();
        } else if (node instanceof AsyncApi26Message) {
            payload = ((AsyncApi26Message) node).getPayload();
            headers = ((AsyncApi26Message) node).getHeaders();
        } else if (node instanceof AsyncApi30Message) {
            payload = ((AsyncApi30Message) node).getPayload();
            headers = ((AsyncApi30Message) node).getHeaders();
        }

        // Message should have at least one of payload or headers
        if (!hasValue(payload) && !hasValue(headers)) {
            this.report(node, null, map("property", "payload or headers"));
        }
    }

}
