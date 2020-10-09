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

package io.apicurio.datamodels.core.validation.rules.invalid.name;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition;

/**
 * Implements the Invalid Header Definition Name Rule.
 * @author eric.wittmann@gmail.com
 */
public class OasInvalidHeaderDefinitionNameRule extends InvalidPropertyNameRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidHeaderDefinitionNameRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitHeaderDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition)
     */
    @Override
    public void visitHeaderDefinition(Oas30HeaderDefinition node) {
        this.reportIfInvalid(isValidDefinitionName(node.getName()), node, Constants.PROP_NAME, map());
    }

}
