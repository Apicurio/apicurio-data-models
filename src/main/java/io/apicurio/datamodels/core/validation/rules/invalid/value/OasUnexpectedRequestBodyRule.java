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
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.v3.models.Oas30Operation;

/**
 * @author eric.wittmann@gmail.com
 */
public class OasUnexpectedRequestBodyRule extends OasInvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasUnexpectedRequestBodyRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * Returns true if the given operation is one of:  POST, PUT, OPTIONS
     * @param operation
     */
    protected boolean isValidRequestBodyOperation(Oas30Operation operation) {
        String method = operation.getMethod();
        return isValidEnumItem(method, array("put", "post", "options", "patch"));
    }

    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitOperation(io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        Oas30Operation operation = (Oas30Operation) node;
        if (hasValue(operation.requestBody)) {
            this.reportIfInvalid(isValidRequestBodyOperation(operation), operation,
                    Constants.PROP_REQUEST_BODY, map("method", operation.getMethod().toUpperCase()));
        }
    }

}