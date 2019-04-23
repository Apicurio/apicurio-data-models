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

package io.apicurio.datamodels.core.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.apicurio.datamodels.asyncapi.validation.rules.AaiMissingApiTitleRule;
import io.apicurio.datamodels.asyncapi.validation.rules.AaiMissingApiVersionRule;
import io.apicurio.datamodels.compat.ValidationCompat;
import io.apicurio.datamodels.core.models.Document;

/**
 * The full set of validation rules.
 * @author eric.wittmann@gmail.com
 */
public class ValidationRuleSet {
    
    private static String[] VERSION_2 = { "2.0.0" };
    public static ValidationRuleSet instance = new ValidationRuleSet();

    public static ValidationRuleMetaData md(String code, String name, String type, String entity,
            String[] versions, boolean specMandated, String messageTemplate, Class<?> ruleClass) {
        return new ValidationRuleMetaData(code, name, type, entity, versions, specMandated,
                messageTemplate, ruleClass);
    }

    private List<ValidationRuleMetaData> rules;

    /**
     * Constructor.
     */
    public ValidationRuleSet() {
        this.rules = new ArrayList<>();
        this.rules.add(md("INF-001", "Missing API Title", "Required Property", "Info", VERSION_2, true, "API is missing a title.", AaiMissingApiTitleRule.class));
        this.rules.add(md("INF-002", "Missing API Version", "Required Property", "Info", VERSION_2, true, "API is missing a version.", AaiMissingApiVersionRule.class));
        
        this.validateRuleData();
    }

    /**
     * Verify that there are no duplicate codes in the set of rules.
     */
    private void validateRuleData() {
        Set<String> codes = new HashSet<>();
        Set<String> names = new HashSet<>();
        
        for (ValidationRuleMetaData rule : this.rules) {
            if (codes.contains(rule.code)) {
                throw new RuntimeException("Duplicate rule code found: " + rule.code);
            } else {
                codes.add(rule.code);
            }
            if (names.contains(rule.name)) {
                throw new RuntimeException("Duplicate rule name found: " + rule.name);
            } else {
                names.add(rule.name);
            }
        }
    }

    /**
     * Gets all of the registered rules.
     */
    public List<ValidationRuleMetaData> getAllRules() {
        return this.rules;
    }

    /**
     * Gets the actual rule instances (visitors) that should be applied to the given document.
     * @param document
     */
    public List<ValidationRule> getRulesFor(Document document) {
        List<ValidationRule> rval = new ArrayList<>();
        for (ValidationRuleMetaData rule : this.rules) {
            // TODO check if the rule applies to the version of the document we have - right now there is only one version
            ValidationRule rc = ValidationCompat.instantiate(rule);
            rval.add(rc);
        }
        return rval;
    }


}
