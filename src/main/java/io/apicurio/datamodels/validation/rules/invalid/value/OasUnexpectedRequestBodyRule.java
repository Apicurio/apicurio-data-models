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
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Operation;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * @author eric.wittmann@gmail.com
 */
public class OasUnexpectedRequestBodyRule extends AbstractInvalidPropertyValueRule {

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
    protected boolean isValidRequestBodyOperation(OpenApi30Operation operation) {
        String method = getOperationMethod(operation);
        return isValidEnumItem(method, array("put", "post", "options", "patch"));
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitOperation(io.apicurio.datamodels.models.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        OpenApi30Operation operation = (OpenApi30Operation) node;
        if (hasValue(operation.getRequestBody())) {
            String method = getOperationMethod(operation);
            this.reportIfInvalid(isValidRequestBodyOperation(operation), operation,
                    "requestBody", map("method", method.toUpperCase()));
        }
    }

}