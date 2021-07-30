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

import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;

/**
 * Implements the Security Requirement Scopes Must Be Empty rule.
 * @author eric.wittmann@gmail.com
 */
public class OasSecurityRequirementScopesMustBeEmptyRule extends OasInvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasSecurityRequirementScopesMustBeEmptyRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    private SecurityScheme findSecurityScheme(OasDocument document, String schemeName) {
        if (document.is2xDocument()) {
            Oas20Document doc20 = (Oas20Document) document;
            if (hasValue(doc20.securityDefinitions)) {
                return doc20.securityDefinitions.getSecurityScheme(schemeName);
            }
        } else {
            Oas30Document doc30 = (Oas30Document) document;
            if (hasValue(doc30.components)) {
                return doc30.components.getSecurityScheme(schemeName);
            }
        }

        return null;
    }

    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitSecurityRequirement(io.apicurio.datamodels.core.models.common.SecurityRequirement)
     */
    @Override
    public void visitSecurityRequirement(SecurityRequirement node) {
        List<String> allowedTypes = new ArrayList<>();
        allowedTypes.add("oauth2");
        String options = "\"oauth2\"";
        if (node.ownerDocument().getDocumentType() == DocumentType.openapi3) {
            allowedTypes.add("openIdConnect");
            options = "\"oauth2\" or \"openIdConnect\"";
        }
        List<String> snames = node.getSecurityRequirementNames();
        for (String sname : snames) {
            SecurityScheme scheme = findSecurityScheme((OasDocument) node.ownerDocument(), sname);
            if (hasValue(scheme)) {
                if (allowedTypes.indexOf(scheme.type) == -1) {
                    List<String> scopes = node.getScopes(sname);
                    this.reportIfInvalid(hasValue(scopes) && scopes.size() == 0, node, null, map("sname", sname, "options", options));
                }
            }
        };
    }

}