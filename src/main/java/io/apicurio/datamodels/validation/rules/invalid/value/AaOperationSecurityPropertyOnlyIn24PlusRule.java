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

import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.asyncapi.v20.AsyncApi20Operation;
import io.apicurio.datamodels.models.asyncapi.v21.AsyncApi21Operation;
import io.apicurio.datamodels.models.asyncapi.v22.AsyncApi22Operation;
import io.apicurio.datamodels.models.asyncapi.v23.AsyncApi23Operation;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Validates that the operation security property is only used in AsyncAPI 2.4+.
 * The security property was added in AsyncAPI 2.4.
 * @author eric.wittmann@gmail.com
 */
public class AaOperationSecurityPropertyOnlyIn24PlusRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaOperationSecurityPropertyOnlyIn24PlusRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitOperation(io.apicurio.datamodels.models.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        // Check if this is AsyncAPI 2.0-2.3
        if (node instanceof AsyncApi20Operation ||
            node instanceof AsyncApi21Operation ||
            node instanceof AsyncApi22Operation ||
            node instanceof AsyncApi23Operation) {

            // In 2.0-2.3, the security property doesn't exist on the interface,
            // but it might be present as an extra property
            if (node.getExtraProperty("security") != null) {
                this.reportIf(true, node, "security", map());
            }
        }
    }

}
