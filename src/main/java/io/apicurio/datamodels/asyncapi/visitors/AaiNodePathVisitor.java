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

package io.apicurio.datamodels.asyncapi.visitors;

import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
import io.apicurio.datamodels.core.visitors.NodePathVisitor;

/**
 * A node path visitor for AsyncAPI.
 * @author eric.wittmann@gmail.com
 */
public class AaiNodePathVisitor extends NodePathVisitor implements IAaiVisitor {

    /**
     * Constructor.
     */
    public AaiNodePathVisitor() {
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitServer(io.apicurio.datamodels.core.models.common.Server)
     */
    @Override
    public void visitServer(Server node) {
        int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_SERVERS);
        if (idx != -1) {
            this.path.prependSegment(String.valueOf(idx), true);
            this.path.prependSegment(Constants.PROP_SERVERS, false);
        }
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitServerVariable(io.apicurio.datamodels.core.models.common.ServerVariable)
     */
    @Override
    public void visitServerVariable(ServerVariable node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_VARIABLES, false);
    }
    
}
