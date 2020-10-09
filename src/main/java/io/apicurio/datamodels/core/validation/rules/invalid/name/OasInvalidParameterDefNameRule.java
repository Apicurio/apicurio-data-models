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
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Parameter Definition Name Rule.
 * @author eric.wittmann@gmail.com
 */
public class OasInvalidParameterDefNameRule extends InvalidPropertyNameRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidParameterDefNameRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitParameterDefinition(io.apicurio.datamodels.core.models.common.IDefinition)
     */
    @Override
    public void visitParameterDefinition(IDefinition node) {
        this.reportIfInvalid(isValidDefinitionName(node.getName()), (Node) node, Constants.PROP_NAME, map());
    }

}
