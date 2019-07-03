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

package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * Models an AsyncAPI server.
 *
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko<jsenko@redhat.com>
 */
public class Aai20Server extends AaiServer {

    /**
     * Constructor.
     */
    public Aai20Server() {
    }

    public Aai20Server(Node parent) {
        super(parent);
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
     * @see Server#createServerVariable(String)
     */
    @Override
    public Aai20ServerVariable createServerVariable(String name) {
        return new Aai20ServerVariable(this, name);
    }

    /**
     * Creates a security requirement.
     */
    @Override
    public Aai20SecurityRequirement createSecurityRequirement() {
        return new Aai20SecurityRequirement(this);
    }
}
