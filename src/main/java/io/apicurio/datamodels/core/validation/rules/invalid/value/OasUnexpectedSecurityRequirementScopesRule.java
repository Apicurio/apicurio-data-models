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

package io.apicurio.datamodels.core.validation.rules.invalid.value;

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20Scopes;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityScheme;

/**
 * Implements the Unexpected Security Requirement Scope(s) rule.
 * @author eric.wittmann@gmail.com
 */
public class OasUnexpectedSecurityRequirementScopesRule extends OasInvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasUnexpectedSecurityRequirementScopesRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * Returns true if the given required scopes are all actually defined by the security definition.
     * @param requiredScopes
     * @param definedScopes
     */
    protected boolean isValidScopes(List<String> requiredScopes, Oas20Scopes definedScopes) {
        boolean rval = true;
        List<String> dscopes = new ArrayList<>();
        if (hasValue(definedScopes)) {
            dscopes = definedScopes.getScopeNames();
        }
        for (String requiredScope : requiredScopes) {
            if (dscopes.indexOf(requiredScope) == -1) {
                rval = false;
            }
        }
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitSecurityRequirement(io.apicurio.datamodels.core.models.common.SecurityRequirement)
     */
    @Override
    public void visitSecurityRequirement(SecurityRequirement node) {
        List<String> snames = node.getSecurityRequirementNames();
        snames.forEach( sname -> {
            Oas20SecurityDefinitions sdefs = ((Oas20Document) node.ownerDocument()).securityDefinitions;
            if (hasValue(sdefs)) {
                Oas20SecurityScheme scheme = sdefs.getSecurityScheme(sname);
                if (hasValue(scheme)) {
                    if (equals(scheme.type, "oauth2")) {
                        Oas20Scopes definedScopes = scheme.scopes;
                        List<String> requiredScopes = node.getScopes(sname);
                        this.reportIfInvalid(isValidScopes(requiredScopes, definedScopes), node, null, map("sname", sname));
                    }
                }
            }
        });
    }
}