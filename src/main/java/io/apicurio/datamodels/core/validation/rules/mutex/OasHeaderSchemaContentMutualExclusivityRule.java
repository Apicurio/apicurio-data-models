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

package io.apicurio.datamodels.core.validation.rules.mutex;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.v3.models.Oas30Header;
import io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition;

/**
 * Implements the Header Schema/Content Mutual Exclusivity Rule.
 * @author eric.wittmann@gmail.com
 */
public class OasHeaderSchemaContentMutualExclusivityRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasHeaderSchemaContentMutualExclusivityRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    private boolean hasContent(Oas30Header contentParent) {
        return contentParent.getMediaTypes().size() > 0;
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitHeader(io.apicurio.datamodels.openapi.models.OasHeader)
     */
    @Override
    public void visitHeader(OasHeader node) {
        Oas30Header header = (Oas30Header) node;
        this.reportIf(hasValue(header.schema) && hasContent(header), node, Constants.PROP_SCHEMA, map());
    }

    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitHeaderDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition)
     */
    @Override
    public void visitHeaderDefinition(Oas30HeaderDefinition node) {
        visitHeader(node);
    }
}
