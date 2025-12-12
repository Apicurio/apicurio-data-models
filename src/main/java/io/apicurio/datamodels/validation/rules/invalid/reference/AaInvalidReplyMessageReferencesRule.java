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

import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30OperationReply;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Reference;
import io.apicurio.datamodels.refs.ReferenceUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Reply Message References rule for AsyncAPI 3.0.
 * @author eric.wittmann@gmail.com
 */
public class AaInvalidReplyMessageReferencesRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaInvalidReplyMessageReferencesRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.AllNodeVisitor#visitOperationReply(io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30OperationReply)
     */
    @Override
    public void visitOperationReply(AsyncApi30OperationReply node) {
        List<AsyncApi30Reference> messages = node.getMessages();
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
