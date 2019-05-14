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
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * Models an AsyncAPI server.
 * @author eric.wittmann@gmail.com
 */
public class AaiServer extends Server {
    
    public String protocol;
    public String protocolVersion;
    public String baseChannel;
    public List<AaiSecurityRequirement> security;

    /**
     * Constructor.
     */
    public AaiServer() {
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IAaiVisitor viz = (IAaiVisitor) visitor;
        viz.visitServer(this);
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.Server#createServerVariable(java.lang.String)
     */
    @Override
    public ServerVariable createServerVariable(String name) {
        AaiServerVariable variable = new AaiServerVariable(name);
        variable._ownerDocument = this.ownerDocument();
        variable._parent = this;
        return variable;
    }
    
    /**
     * Creates a security requirement.
     */
    public AaiSecurityRequirement createSecurityRequirement() {
        AaiSecurityRequirement requirement = new AaiSecurityRequirement();
        requirement._ownerDocument = this.ownerDocument();
        requirement._parent = this;
        return requirement;
    }

    /**
     * Adds a security requirement child.
     * @param securityRequirement
     */
    public AaiSecurityRequirement addSecurityRequirement(AaiSecurityRequirement securityRequirement) {
        if (this.security == null) {
            this.security = new ArrayList<>();
        }
        this.security.add(securityRequirement);
        return securityRequirement;
    }

}
