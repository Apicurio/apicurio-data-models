/*
 * Copyright 2020 Red Hat
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

package io.apicurio.datamodels.core.validation.rules.invalid.format;

import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Channel Path Rule
 * @author eric.wittmann@gmail.com
 */
public class AaiInvalidChannelPathRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaiInvalidChannelPathRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitChannelItem(io.apicurio.datamodels.asyncapi.models.AaiChannelItem)
     */
    @Override
    public void visitChannelItem(AaiChannelItem node) {
        String channelPath = node.getName();
        this.reportIfInvalid(isValidPath(channelPath), node, null, map());
    }

    private boolean isValidPath(String channelPath) {
        // TODO implement this - path must be in the form of a RFC 6570 URI template
        return false;
    }

}
