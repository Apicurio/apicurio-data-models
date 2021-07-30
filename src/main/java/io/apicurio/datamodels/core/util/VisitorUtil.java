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

package io.apicurio.datamodels.core.util;

import io.apicurio.datamodels.core.factories.TraverserFactory;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.ITraverser;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.core.visitors.TraverserDirection;

/**
 * @author eric.wittmann@gmail.com
 */
public class VisitorUtil {

    public static void visitNode(Node node, IVisitor visitor) {
        node.accept(visitor);
    }
    
    public static void visitTree(Node node, IVisitor visitor, TraverserDirection direction) {
        ITraverser traverser = TraverserFactory.create(node.ownerDocument(), visitor, direction);
        traverser.traverse(node);
    }

}
