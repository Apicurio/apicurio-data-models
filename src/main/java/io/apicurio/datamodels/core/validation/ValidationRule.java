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

package io.apicurio.datamodels.core.validation;

import java.util.Map;

import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.ValidationProblem;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * Base class for all validation rule implementations.
 * @author eric.wittmann@gmail.com
 */
public abstract class ValidationRule implements IVisitor {

    private IValidationProblemReporter reporter;
    private ValidationRuleMetaData ruleInfo;
    
    /**
     * Constructor.
     * @param ruleInfo
     */
    public ValidationRule(ValidationRuleMetaData ruleInfo) {
        this.ruleInfo = ruleInfo;
    }
    
    /**
     * Sets the validation problem reporter.
     * @param reporter
     */
    public void setReporter(IValidationProblemReporter reporter) {
        this.reporter = reporter;
    }

    /**
     * Called by validation rules to report an error.
     * @param node
     * @param property
     * @param messageParams
     */
    protected void report(Node node, String property, Map<String, Object> messageParams) {
        String messageTemplate = this.ruleInfo.messageTemplate;
        String message = this.resolveMessage(messageTemplate, messageParams);
        reporter.report(ruleInfo, node, property, message);
    }

    /**
     * Creates a message from a template and set of params.
     * @param messageTemplate
     * @param messageParams
     */
    private String resolveMessage(String messageTemplate, Map<String, Object> messageParams) {
        return messageTemplate;
    }

    /**
     * Reports a validation error if the property is not valid.
     * @param isValid
     * @param node
     * @param property
     * @param messageParams
     */
    protected void reportIfInvalid(boolean isValid, Node node, String property, Map<String, Object> messageParams) {
        if (!isValid) {
            this.report(node, property, messageParams);
        }
    }

    /**
     * Reports a validation error if the given condition is true.
     * @param condition
     * @param node
     * @param property
     * @param messageParams
     */
    protected void reportIf(boolean condition, Node node, String property, Map<String, Object> messageParams) {
        if (condition) {
            this.report(node, property, messageParams);
        }
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitValidationProblem(io.apicurio.datamodels.core.models.ValidationProblem)
     */
    @Override
    public void visitValidationProblem(ValidationProblem problem) {
        // Probably never want to visit/handle a validation problem when visiting for the purpose of validation.
    }

}
