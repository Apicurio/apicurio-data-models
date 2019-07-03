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

package io.apicurio.datamodels.core.models.common;

import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * Models a Security Scheme.
 * @author eric.wittmann@gmail.com
 */
public abstract class SecurityScheme extends ExtensibleNode implements INamed {
    
    public String _schemeName;
    public String type;
    public String description;
    public String name;
    public String in;
    
    /**
     * Constructor.
     * @param schemeName
     */
    public SecurityScheme(String schemeName) {
        this._schemeName = schemeName;
    }
    
    /**
     * Gets the scheme name.
     */
    public String getSchemeName() {
        return this._schemeName;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#getName()
     */
    @Override
    public String getName() {
        return this.getSchemeName();
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#rename(java.lang.String)
     */
    @Override
    public void rename(String newName) {
        this._schemeName = newName;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        visitor.visitSecurityScheme(this);
    }
    
}
