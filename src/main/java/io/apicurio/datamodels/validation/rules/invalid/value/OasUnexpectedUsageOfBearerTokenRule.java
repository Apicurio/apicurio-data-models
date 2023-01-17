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

import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30SecurityScheme;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31SecurityScheme;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * @author eric.wittmann@gmail.com
 */
public class OasUnexpectedUsageOfBearerTokenRule extends AbstractInvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasUnexpectedUsageOfBearerTokenRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitSecurityScheme(io.apicurio.datamodels.models.SecurityScheme)
     */
    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        if (node.root().modelType() == ModelType.OPENAPI30) {
            OpenApi30SecurityScheme scheme = (OpenApi30SecurityScheme) node;
            if (hasValue(scheme.getBearerFormat())) {
                this.reportIfInvalid(equals(scheme.getType(), "http") && equals(scheme.getScheme(), "bearer"), node,
                        "bearerFormat", map());
            }
        } else if (node.root().modelType() == ModelType.OPENAPI31) {
            OpenApi31SecurityScheme scheme = (OpenApi31SecurityScheme) node;
            if (hasValue(scheme.getBearerFormat())) {
                this.reportIfInvalid(equals(scheme.getType(), "http") && equals(scheme.getScheme(), "bearer"), node,
                        "bearerFormat", map());
            }
        }
    }

}