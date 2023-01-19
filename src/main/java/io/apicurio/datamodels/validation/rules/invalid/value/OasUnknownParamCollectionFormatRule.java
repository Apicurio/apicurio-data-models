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

import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Parameter;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Unknown Parameter Collection Format rule.
 * @author eric.wittmann@gmail.com
 */
public class OasUnknownParamCollectionFormatRule extends AbstractInvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasUnknownParamCollectionFormatRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitParameter(io.apicurio.datamodels.models.Parameter)
     */
    @Override
    public void visitParameter(Parameter node) {
        OpenApi20Parameter param = (OpenApi20Parameter) node;
        if (hasValue(param.getCollectionFormat())) {
            this.reportIfInvalid(isValidEnumItem(param.getCollectionFormat(), array("csv", "ssv", "tsv", "pipes", "multi")),
                    param, "collectionFormat", map());
        }
    }

}