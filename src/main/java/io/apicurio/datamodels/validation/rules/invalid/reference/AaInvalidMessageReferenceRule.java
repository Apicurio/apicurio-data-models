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

package io.apicurio.datamodels.validation.rules.invalid.reference;

import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.AsyncApiReferenceable;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Operation;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Reference;
import io.apicurio.datamodels.refs.ReferenceUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

import java.util.List;

/**
 * Implements the Invalid Message Reference rule for AsyncAPI (AAO-008).
 * Validates that when a Message object has a $ref property, it points to a valid message in components.
 * This applies to both AsyncAPI 2.x and 3.0.
 *
 * @author eric.wittmann@gmail.com
 */
public class AaInvalidMessageReferenceRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaInvalidMessageReferenceRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.AllNodeVisitor#visitMessage(io.apicurio.datamodels.models.asyncapi.AsyncApiMessage)
     */
    @Override
    public void visitMessage(AsyncApiMessage node) {
        // Check if this message has a $ref
        if (node instanceof AsyncApiReferenceable) {
            String ref = ((AsyncApiReferenceable) node).get$ref();
            if (hasValue(ref)) {
                this.reportIfInvalid(ReferenceUtil.canResolveRef(ref, node), node, "$ref", map());
            }
        }
    }

    @Override
    public void visitOperation(Operation node) {
        if (ModelTypeUtil.isAsyncApi3Model(node)) {
            AsyncApi30Operation op = (AsyncApi30Operation) node;
            List<AsyncApi30Reference> messages = op.getMessages();
            if (hasValue(messages)) {
                for (int i = 0; i < messages.size(); i++) {
                    AsyncApi30Reference message = messages.get(i);
                    String ref = message.get$ref();
                    if (hasValue(ref)) {
                        this.reportIfInvalid(ReferenceUtil.canResolveRef(ref, message), message, "$ref", map());
                    }
                }
            }
        }
    }
}
