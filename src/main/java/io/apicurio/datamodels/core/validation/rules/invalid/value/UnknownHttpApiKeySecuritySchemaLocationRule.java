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
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * Implements the Unknown HTTP API-Key Location rule.
 * @author eric.wittmann@gmail.com
 */
public class UnknownHttpApiKeySecuritySchemaLocationRule extends OasInvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public UnknownHttpApiKeySecuritySchemaLocationRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitSecurityScheme(io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        if (hasValue(node.in)) {
            if (node.ownerDocument().getDocumentType() == DocumentType.asyncapi2) {
                this.reportIfInvalid(isValidEnumItem(node.in, array("query", "header", "cookie")), node, Constants.PROP_IN, 
                        map("options", "query, header, cookie"));
            }
        }
    }

}