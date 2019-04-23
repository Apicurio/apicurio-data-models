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

package io.apicurio.datamodels.core.models;

import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * Models a single problem detected during validation of the data model.  Each individual problem
 * found during validation of the model will result in one instance of this class.
 * @author eric.wittmann@gmail.com
 */
public class ValidationProblem implements IVisitable {

    public String errorCode;
    public Object nodePath;
    public String property;
    public String message;
    public ValidationProblemSeverity severity;
    
    /**
     * Constructor.
     * @param errorCode
     * @param nodePath
     * @param property
     * @param message
     * @param severity
     */
    public ValidationProblem(String errorCode, Object nodePath, String property, String message, ValidationProblemSeverity severity) {
        this.errorCode = errorCode;
        this.nodePath = nodePath;
        this.property = property;
        this.message = message;
        this.severity = severity;
    }

    /**
     * @see io.apicurio.asyncapi.core.models.IAaiVisitable#accept(io.apicurio.asyncapi.core.visitors.IAaiNodeVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        visitor.visitValidationProblem(this);
    }

}
