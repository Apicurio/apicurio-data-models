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
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Operation;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Reference;
import io.apicurio.datamodels.refs.ReferenceUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Operation Channel Reference rule for AsyncAPI 3.0.
 * @author eric.wittmann@gmail.com
 */
public class AaInvalidOperationChannelReferenceRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaInvalidOperationChannelReferenceRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitOperation(io.apicurio.datamodels.models.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        if (ModelTypeUtil.isAsyncApi3Model(node)) {
            AsyncApi30Operation op = (AsyncApi30Operation) node;
            AsyncApi30Reference channelRef = op.getChannel();
            if (hasValue(channelRef)) {
                String ref = channelRef.get$ref();
                if (hasValue(ref)) {
                    this.reportIfInvalid(ReferenceUtil.canResolveRef(ref, channelRef), node, "channel", map());
                }
            }
        }
    }

}
