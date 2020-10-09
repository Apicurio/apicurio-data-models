/*
 * Copyright 2020 Red Hat
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

import io.apicurio.datamodels.asyncapi.models.AaiHeaderItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Headers Schema rule.
 * @author eric.wittmann@gmail.com
 */
public class AaiInvalidHeadersSchemaRule extends InvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaiInvalidHeadersSchemaRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitMessage(io.apicurio.datamodels.asyncapi.models.AaiMessage)
     */
    @Override
    public void visitMessage(AaiMessage node) {
        if (!isDefined(node.headers)) {
            return;
        }
        this.reportIfInvalid(validateHeadersSchema(node.headers), node.headers, null, map());
    }

    /**
     * Validate the headers schema.  The spec requires the following:
     * 
     *   > Schema MUST be of type "object". It MUST NOT define the protocol headers.
     * 
     * @param headers
     */
    private boolean validateHeadersSchema(AaiHeaderItem headers) {
        // TODO We need to implement this.  But we can't do that until we properly model the schema.  The
        // header schema should not be an Object but rather an AaiSchema
        return true;
    }
    
}
