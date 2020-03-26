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

package io.apicurio.datamodels.core.validation.rules.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.common.ISecurityRequirementParent;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * @author cfoskin@redhat.com
 */
public class SecurityRequirementUniquenessValidationRule extends ValidationRule {

    /**
     * Constructor.
     * 
     * @param ruleInfo
     */
    public SecurityRequirementUniquenessValidationRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @param currentSecurityRequirement
     * @param otherSecurityRequirement
     * @return returns true if the two security requirements are equal
     */
    private boolean isEqualTo(SecurityRequirement currentSecurityRequirement,
            SecurityRequirement otherSecurityRequirement) {
        // compare security requirement names
        List<String> currentSecurityRequirementNames = currentSecurityRequirement
                .getSecurityRequirementNames();
        List<String> otherSecurityRequirementNames = otherSecurityRequirement.getSecurityRequirementNames();
        Collections.sort(currentSecurityRequirementNames);
        Collections.sort(otherSecurityRequirementNames);

        if (!currentSecurityRequirementNames.equals(otherSecurityRequirementNames)) {
            return false;
        }

        // get and compare the scopes for each of the sec req names that are the same
        for (String secReqName : currentSecurityRequirementNames) {
            List<String> secReqScopes = currentSecurityRequirement.getScopes(secReqName);
            List<String> otherSecReqScopes = otherSecurityRequirement.getScopes(secReqName);
            Collections.sort(secReqScopes);
            Collections.sort(otherSecReqScopes);

            if (!secReqScopes.equals(otherSecReqScopes)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param currentSecurityRequirement
     * @param allRequirements
     * @return true if duplicates are found
     */
    private boolean checkForDuplicates(SecurityRequirement currentSecurityRequirement,
            List<SecurityRequirement> allRequirements) {
        ArrayList<SecurityRequirement> clonedList = new ArrayList<SecurityRequirement>(allRequirements);
        clonedList.remove(currentSecurityRequirement); // remove the current one to examine siblings

        for (SecurityRequirement otherSecurityRequirement : clonedList) {
            if (isEqualTo(currentSecurityRequirement, otherSecurityRequirement)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param node
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitSecurityRequirement(io.apicurio.datamodels.core.models.common.SecurityRequirement)
     */
    public void visitSecurityRequirement(SecurityRequirement node) {
        ISecurityRequirementParent parent = (ISecurityRequirementParent) node.parent();
        List<SecurityRequirement> securityRequirements = parent.getSecurityRequirements();

        if (securityRequirements.size() <= 1) {
            return;
        }

        if (checkForDuplicates(node, securityRequirements)) {
            this.report(node, Constants.PROP_SECURITY,
                    map("securityReq", node.getSecurityRequirementNames().toString()));
        }
        ;
    }
}