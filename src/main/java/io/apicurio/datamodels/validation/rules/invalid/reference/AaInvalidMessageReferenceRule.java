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

import java.util.List;

import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.Referenceable;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.v20.AsyncApi20Operation;
import io.apicurio.datamodels.models.asyncapi.v21.AsyncApi21Operation;
import io.apicurio.datamodels.models.asyncapi.v22.AsyncApi22Operation;
import io.apicurio.datamodels.models.asyncapi.v23.AsyncApi23Operation;
import io.apicurio.datamodels.models.asyncapi.v24.AsyncApi24Operation;
import io.apicurio.datamodels.models.asyncapi.v25.AsyncApi25Operation;
import io.apicurio.datamodels.models.asyncapi.v26.AsyncApi26Operation;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Operation;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Reference;
import io.apicurio.datamodels.refs.ReferenceUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Message Reference rule for AsyncAPI.
 * Validates message references in both 2.x (message property) and 3.0 (messages array).
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
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitOperation(io.apicurio.datamodels.models.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        // AsyncAPI 2.x: single message property
        if (ModelTypeUtil.isAsyncApi2Model(node)) {
            AsyncApiMessage message = getMessage2x(node);
            if (hasValue(message) && message instanceof Referenceable) {
                String ref = ((Referenceable) message).get$ref();
                if (hasValue(ref)) {
                    this.reportIfInvalid(ReferenceUtil.canResolveRef(ref, message), node, "message", map());
                }
            }
        }

        // AsyncAPI 3.0: messages array
        if (ModelTypeUtil.isAsyncApi3Model(node)) {
            AsyncApi30Operation op = (AsyncApi30Operation) node;
            List<AsyncApi30Reference> messages = op.getMessages();
            if (hasValue(messages)) {
                for (AsyncApi30Reference messageRef : messages) {
                    if (hasValue(messageRef)) {
                        String ref = messageRef.get$ref();
                        if (hasValue(ref)) {
                            this.reportIfInvalid(ReferenceUtil.canResolveRef(ref, messageRef), node, "messages", map());
                        }
                    }
                }
            }
        }
    }

    /**
     * Gets the message from an AsyncAPI 2.x operation.
     */
    private AsyncApiMessage getMessage2x(Operation node) {
        if (node instanceof AsyncApi20Operation) {
            return ((AsyncApi20Operation) node).getMessage();
        } else if (node instanceof AsyncApi21Operation) {
            return ((AsyncApi21Operation) node).getMessage();
        } else if (node instanceof AsyncApi22Operation) {
            return ((AsyncApi22Operation) node).getMessage();
        } else if (node instanceof AsyncApi23Operation) {
            return ((AsyncApi23Operation) node).getMessage();
        } else if (node instanceof AsyncApi24Operation) {
            return ((AsyncApi24Operation) node).getMessage();
        } else if (node instanceof AsyncApi25Operation) {
            return ((AsyncApi25Operation) node).getMessage();
        } else if (node instanceof AsyncApi26Operation) {
            return ((AsyncApi26Operation) node).getMessage();
        }
        return null;
    }

}
