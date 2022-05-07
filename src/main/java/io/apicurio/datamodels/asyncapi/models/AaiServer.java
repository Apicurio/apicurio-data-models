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

package io.apicurio.datamodels.asyncapi.models;

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.INamed;
import io.apicurio.datamodels.core.models.common.ISecurityRequirementParent;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * Models an AsyncAPI server.
 *
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiServer extends Server implements INamed, ISecurityRequirementParent {

    public String _name;
    public String protocol;
    public String protocolVersion;
    public List<SecurityRequirement> security;
    public AaiServerBindings bindings;

    /**
     * Constructor.
     */
    public AaiServer(String name) {
        this._name = name;
    }

    /**
     * Constructor.
     * @param parent
     */
    public AaiServer(Node parent) {
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    /**
     * Constructor.
     * @param parent
     * @param name
     */
    public AaiServer(Node parent, String name) {
        this(parent);
        this._name = name;
    }

    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IAaiVisitor viz = (IAaiVisitor) visitor;
        viz.visitServer(this);
    }

    /**
     * Creates a security requirement child node.
     */
    public abstract AaiSecurityRequirement createSecurityRequirement();

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

    @Override
    public List<SecurityRequirement> getSecurityRequirements() {
        return this.security;
    }
    
    /**
     * Adds a security requirement child.
     *
     * @param securityRequirement
     */
    @Override
    public SecurityRequirement addSecurityRequirement(SecurityRequirement securityRequirement) {
        if (this.security == null) {
            this.security = new ArrayList<>();
        }
        this.security.add(((AaiSecurityRequirement) securityRequirement));
        return securityRequirement;
    }
    
    /**
     * see {@link io.apicurio.datamodels.core.models.common.ISecurityRequirementParent#restoreSecurityRequirement(Integer, SecurityRequirement)}
     */
    @Override
    public void restoreSecurityRequirement(Integer index, SecurityRequirement securityRequirement) {
        this.security = ModelUtils.restoreListEntry(index, securityRequirement, this.security);
    }
}
