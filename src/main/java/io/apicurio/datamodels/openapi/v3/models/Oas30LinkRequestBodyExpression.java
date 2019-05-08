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

package io.apicurio.datamodels.openapi.v3.models;

import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor;

/**
 * Models a link request body expression.
 * @author eric.wittmann@gmail.com
 */
public class Oas30LinkRequestBodyExpression extends Node implements IOas30Expression {

    private String _value;
    
    /**
     * Constructor.
     * @param value
     */
    public Oas30LinkRequestBodyExpression(String value) {
        this._value = value;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOas30Visitor viz = (IOas30Visitor) visitor;
        viz.visitLinkRequestBodyExpression(this);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.v3.models.IOas30Expression#getValue()
     */
    @Override
    public String getValue() {
        return this._value;
    }

    /**
     * Returns true if the expression is a dynamic link parameter expression that must be evaluated.
     */
    public boolean isExpression() {
        return this.getValue() != null && this.getValue().indexOf("$") == 0;
    }

    /**
     * Returns true if the expression is just a constant value.
     */
    public boolean isConstant() {
        return !this.isExpression();
    }

}
