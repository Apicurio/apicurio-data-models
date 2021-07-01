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
public abstract class AaiOperationTraitDefinition extends AaiOperationTrait implements IDefinition {
    
    /**
     * Constructor.
     * @param name
     */
    public AaiOperationTraitDefinition(String name) {
        super(name);
    }

    /**
     * Constructor.
     * @param parent
     */
    public AaiOperationTraitDefinition(Node parent) {
        super(parent);
    }

    /**
     * Constructor.
     * @param parent
     * @param name
     */
    public AaiOperationTraitDefinition(Node parent, String name) {
        super(parent, name);
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasHeader#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IAaiVisitor viz = (IAaiVisitor) visitor;
        viz.visitOperationTraitDefinition(this);
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#getName()
     */
    @Override
    public String getName() {
        return this.getType();
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#rename(java.lang.String)
     */
    @Override
    public void rename(String newName) {
        this._type = newName;
    }
}
