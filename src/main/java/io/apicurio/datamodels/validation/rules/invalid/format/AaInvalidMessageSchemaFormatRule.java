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

package io.apicurio.datamodels.validation.rules.invalid.format;

import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.AsyncApiReferenceable;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Message Schema Format rule for AsyncAPI.
 * Schema format must be a valid format identifier (e.g., "application/vnd.aai.asyncapi;version=2.0.0").
 * @author eric.wittmann@gmail.com
 */
public class AaInvalidMessageSchemaFormatRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaInvalidMessageSchemaFormatRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.AllNodeVisitor#visitMessage(io.apicurio.datamodels.models.asyncapi.AsyncApiMessage)
     */
    @Override
    public void visitMessage(AsyncApiMessage node) {
        // Skip if it's a reference
        if (node instanceof AsyncApiReferenceable && hasValue(((AsyncApiReferenceable) node).get$ref())) {
            return;
        }

        // Get schemaFormat using getExtraProperty since it's not in the base interface
        Object schemaFormatObj = node.getExtraProperty("schemaFormat");
        if (hasValue(schemaFormatObj)) {
            String schemaFormat = schemaFormatObj.toString();
            // Schema format should follow a pattern like "type/subtype" or "type/subtype;params"
            // Common formats include:
            // - application/vnd.aai.asyncapi;version=2.0.0
            // - application/schema+json;version=draft-07
            boolean isValid = schemaFormat.contains("/");
            this.reportIfInvalid(isValid, node, "schemaFormat", map());
        }
    }

}
