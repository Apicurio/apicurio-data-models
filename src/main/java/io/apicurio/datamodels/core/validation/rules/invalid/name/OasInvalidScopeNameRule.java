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

import java.util.List;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.v2.models.Oas20Scopes;

/**
 * Implements the Invalid Scope Name Rule.
 * @author eric.wittmann@gmail.com
 */
public class OasInvalidScopeNameRule extends InvalidPropertyNameRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidScopeNameRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitScopes(io.apicurio.datamodels.openapi.v2.models.Oas20Scopes)
     */
    @Override
    public void visitScopes(Oas20Scopes node) {
        List<String> scopeNames = node.getScopeNames();
        scopeNames.forEach(scope -> {
            this.reportIfInvalid(isValidScopeName(scope), node, Constants.PROP_SCOPES, map("scope", scope));
        });
    }

}
