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

import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor;

/**
 * Models an OpenAPI 3.0.x example definition.
 * @author eric.wittmann@gmail.com
 */
public class Oas30ExampleDefinition extends Oas30Example implements IDefinition {

    /**
     * Constructor.
     * @param name
     */
    public Oas30ExampleDefinition(String name) {
        super(name);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.v3.models.Oas30Example#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOas30Visitor viz = (IOas30Visitor) visitor;
        viz.visitExampleDefinition(this);
    }

}
