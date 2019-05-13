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

import java.util.Map;

import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Node;

/**
 * Base class for all "required property" style validation rules.  In other words, any rule that
 * requires a particular property be present on a data model node.
 * @author eric.wittmann@gmail.com
 */
public abstract class RequiredPropertyValidationRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public RequiredPropertyValidationRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * Called when a required property is missing.
     * @param node
     * @param propertyName
     * @param messageProperties
     */
    protected void requireProperty(Node node, String propertyName, Map<String, String> messageProperties) {
        Object propertyValue = NodeCompat.getProperty(node, propertyName);
        if (propertyValue == null) {
            this.report(node, propertyName, messageProperties);
        }
    }

}
