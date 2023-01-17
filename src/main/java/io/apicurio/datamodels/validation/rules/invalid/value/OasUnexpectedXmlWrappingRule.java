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

package io.apicurio.datamodels.validation.rules.invalid.value;

import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.openapi.OpenApiXML;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Unexpected XML Wrapping rule.
 * @author eric.wittmann@gmail.com
 */
public class OasUnexpectedXmlWrappingRule extends AbstractInvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasUnexpectedXmlWrappingRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * Returns true if it's OK to use "wrapped" in the XML node.  It's only OK to do this if
     * the type being defined is an 'array' type.
     * @param xml
     */
    protected boolean isWrappedOK(OpenApiXML xml) {
        Schema schema = (Schema) xml.parent();
        return equals(schema.getType(), "array");
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitXML(io.apicurio.datamodels.models.openapi.OpenApiXML)
     */
    @Override
    public void visitXML(OpenApiXML node) {
        if (hasValue(node.isWrapped())) {
            this.reportIfInvalid(this.isWrappedOK(node), node, "wrapped", map());
        }
    }

}