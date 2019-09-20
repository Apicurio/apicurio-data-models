/*
 * Copyright 2019 JBoss Inc
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

package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class AaiMessageTraitDefinition extends AaiMessageTrait implements IDefinition {

    /**
     * Constructor.
     */
    public AaiMessageTraitDefinition(String name) {
        super(name);
    }

    /**
     * Constructor.
     * @param parent
     */
    public AaiMessageTraitDefinition(Node parent) {
        super(parent);
    }

    /**
     * Constructor.
     * @param parent
     * @param name
     */
    public AaiMessageTraitDefinition(Node parent, String name) {
        super(parent, name);
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasHeader#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IAaiVisitor viz = (IAaiVisitor) visitor;
        viz.visitMessageTraitDefinition(this);
    }
}
