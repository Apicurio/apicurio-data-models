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

import java.util.Map;
import java.util.Set;

import io.apicurio.datamodels.asyncapi.models.AaiComponents;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Component Name Rule.
 * @author eric.wittmann@gmail.com
 */
public class AaiInvalidComponentNameRule extends InvalidPropertyNameRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaiInvalidComponentNameRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitComponents(io.apicurio.datamodels.core.models.common.Components)
     */
    @Override
    public void visitComponents(Components node) {
        AaiComponents comps = (AaiComponents) node;
        validateComponentNames(comps.schemas);
        validateComponentNames(comps.messages);
        validateComponentNames(comps.securitySchemes);
        validateComponentNames(comps.parameters);
        validateComponentNames(comps.correlationIds);
        validateComponentNames(comps.operationTraits);
        validateComponentNames(comps.messageTraits);
        validateComponentNames(comps.serverBindings);
        validateComponentNames(comps.channelBindings);
        validateComponentNames(comps.operationBindings);
        validateComponentNames(comps.messageBindings);
    }

    /**
     * Validates that the name of each component is allowed.
     * @param definitions
     */
    private void validateComponentNames(Map<String, ? extends Node> components) {
        if (!isDefined(components)) {
            return;
        }
        Set<String> names = components.keySet();
        for (String name : names) {
            this.reportIfInvalid(isValidDefinitionName(name), components.get(name), null, map("name", name));
        }
    }

}
