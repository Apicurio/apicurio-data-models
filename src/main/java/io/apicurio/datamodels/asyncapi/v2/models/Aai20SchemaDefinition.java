/*
 * Copyright 2020 Red Hat
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

package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.ISchemaDefinition;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * @author laurent.broudoux@gmail.com
 */
public class Aai20SchemaDefinition extends Aai20Schema implements ISchemaDefinition {
    
    private String _name;
    
    /**
     * Constructor.
     */
    public Aai20SchemaDefinition(String name) {
        this._name = name;
    }

    /**
     * Constructor.
     * @param parent
     * @param name
     */
    public Aai20SchemaDefinition(Node parent, String name) {
        this._name = name;
        if (parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#getName()
     */
    @Override
    public String getName() {
        return this._name;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#rename(java.lang.String)
     */
    @Override
    public void rename(String newName) {
        this._name = newName;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.common.ISchemaDefinition#asSchema()
     */
    @Override
    public Schema asSchema() {
        return this;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        visitor.visitSchemaDefinition(this);
    }
}
