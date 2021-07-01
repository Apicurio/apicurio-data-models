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

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.v2.models.Oas20Items;

/**
 * Implements the Unknown Array Collection Format rule.
 * @author eric.wittmann@gmail.com
 */
public class OasUnknownArrayCollectionFormatRule extends OasInvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasUnknownArrayCollectionFormatRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitItems(io.apicurio.datamodels.openapi.v2.models.Oas20Items)
     */
    @Override
    public void visitItems(Oas20Items node) {
        if (hasValue(node.collectionFormat)) {
            this.reportIfInvalid(isValidEnumItem(node.collectionFormat, array("csv", "ssv", "tsv", "pipes")), node, 
                    Constants.PROP_COLLECTION_FORMAT, map());
        }
    }

}