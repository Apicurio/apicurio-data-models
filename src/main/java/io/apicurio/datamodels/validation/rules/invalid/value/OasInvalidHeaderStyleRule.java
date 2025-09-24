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

import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Header;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Header;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * @author eric.wittmann@gmail.com
 */
public class OasInvalidHeaderStyleRule extends AbstractInvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidHeaderStyleRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitHeader(io.apicurio.datamodels.models.openapi.OpenApiHeader)
     */
    @Override
    public void visitHeader(OpenApiHeader node) {
        String style = null;
        if (ModelTypeUtil.isOpenApi30Model(node)) {
            style = ((OpenApi30Header) node).getStyle();
        } else if (ModelTypeUtil.isOpenApi31Model(node)) {
            style = ((OpenApi31Header) node).getStyle();
        }

        if (hasValue(style)) {
            this.reportIfInvalid(isValidEnumItem(style, array("simple")), node, "style", map());
        }
    }

}