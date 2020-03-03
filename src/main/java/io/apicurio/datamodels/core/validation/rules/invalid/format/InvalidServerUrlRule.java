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

package io.apicurio.datamodels.core.validation.rules.invalid.format;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Server URL Rule
 * @author eric.wittmann@gmail.com
 */
public class InvalidServerUrlRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public InvalidServerUrlRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitServer(io.apicurio.datamodels.core.models.common.Server)
     */
    @Override
    public void visitServer(Server node) {
        if (hasValue(node.url)) {
            this.reportIfInvalid(isValidUrlTemplate(node.url), node, Constants.PROP_URL, map());
        }
    }

}
