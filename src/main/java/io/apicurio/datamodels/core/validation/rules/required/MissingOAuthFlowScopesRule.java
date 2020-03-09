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

package io.apicurio.datamodels.core.validation.rules.required;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlow;
import io.apicurio.datamodels.core.models.common.PasswordOAuthFlow;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * @author eric.wittmann@gmail.com
 */
public class MissingOAuthFlowScopesRule extends RequiredPropertyValidationRule {
    
    /**
     * Constructor.
     * @param ruleInfo
     */
    public MissingOAuthFlowScopesRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * Require the 'scopes' property.
     * @param node
     */
    private void visitOAuthFlow(OAuthFlow node) {
        this.requireProperty(node, Constants.PROP_SCOPES, map());
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitImplicitOAuthFlow(io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow)
     */
    @Override
    public void visitImplicitOAuthFlow(ImplicitOAuthFlow node) {
        this.visitOAuthFlow(node);
    }

    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitPasswordOAuthFlow(io.apicurio.datamodels.core.models.common.PasswordOAuthFlow)
     */
    @Override
    public void visitPasswordOAuthFlow(PasswordOAuthFlow node) {
        this.visitOAuthFlow(node);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitClientCredentialsOAuthFlow(io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow)
     */
    @Override
    public void visitClientCredentialsOAuthFlow(ClientCredentialsOAuthFlow node) {
        this.visitOAuthFlow(node);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitAuthorizationCodeOAuthFlow(io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow)
     */
    @Override
    public void visitAuthorizationCodeOAuthFlow(AuthorizationCodeOAuthFlow node) {
        this.visitOAuthFlow(node);
    }

}
