/*
 * Copyright 2022 Red Hat
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

package io.apicurio.datamodels.io;

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.visitors.AllNodeVisitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class IoTestAllNodeFinder extends AllNodeVisitor {

    List<Node> allNodes = new ArrayList<>();

    /**
     * @see io.apicurio.datamodels.visitors.AllNodeVisitor#visitNode(io.apicurio.datamodels.Node)
     */
    @Override
    protected void visitNode(Node node) {
        allNodes.add(node);
    }

}
