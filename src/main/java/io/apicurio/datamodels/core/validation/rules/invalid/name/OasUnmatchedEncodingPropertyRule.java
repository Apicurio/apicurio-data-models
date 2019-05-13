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

import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Encoding;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;

/**
 * Implements the Unmatched Encoding Property Rule.
 * @author eric.wittmann@gmail.com
 */
public class OasUnmatchedEncodingPropertyRule extends OasInvalidPropertyNameRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasUnmatchedEncodingPropertyRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * Returns true if the given schema has a property defined with the given name.
     * @param {Oas30Schema} schema
     * @param {string} propertyName
     * @return {boolean}
     */
    private boolean isValidSchemaProperty(OasSchema schema, String propertyName) {
        if (isNullOrUndefined(schema)) {
            return false;
        }
        return !isNullOrUndefined(schema.getProperty(propertyName));
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitEncoding(io.apicurio.datamodels.openapi.v3.models.Oas30Encoding)
     */
    @Override
    public void visitEncoding(Oas30Encoding node) {
        String name = node.getName();
        OasSchema schema = ((Oas30MediaType) (node.parent())).schema;

        this.reportIfInvalid(isValidSchemaProperty(schema, name), node, name, map("name", name));
    }

}
