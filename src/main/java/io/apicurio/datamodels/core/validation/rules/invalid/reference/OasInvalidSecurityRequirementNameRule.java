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

package io.apicurio.datamodels.core.validation.rules.invalid.reference;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;

/**
 * Implements the Invalid Security Requirement Name rule.
 * @author eric.wittmann@gmail.com
 */
public class OasInvalidSecurityRequirementNameRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidSecurityRequirementNameRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * Returns true if the security requirement name is valid.  It does this by looking up a declared
     * security scheme definition in the document.  If no security scheme definition exists with the
     * given name, then it is invalid.
     * @param securityReqName
     * @param doc
     */
    private boolean isValidSecurityRequirementName(String securityReqName, Document doc) {
        DocumentType dt = doc.getDocumentType();
        if (dt == DocumentType.openapi2) {
            Oas20Document doc20 = (Oas20Document) doc;
            return hasValue(doc20.securityDefinitions) && isDefined(doc20.securityDefinitions.getSecurityScheme(securityReqName));
        } else if (dt == DocumentType.openapi3) {
            Oas30Document doc30 = (Oas30Document) doc;
            return hasValue(doc30.components) && isDefined(doc30.components.getSecurityScheme(securityReqName));
        } else if (dt == DocumentType.asyncapi2) {
            // TODO implement this
        }
        return false;
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitSecurityRequirement(io.apicurio.datamodels.core.models.common.SecurityRequirement)
     */
    @Override
    public void visitSecurityRequirement(SecurityRequirement node) {
        node.getSecurityRequirementNames().forEach( name -> {
            this.reportIfInvalid(isValidSecurityRequirementName(name, node.ownerDocument()), node, null, map("name", name));
        });
    }

}
