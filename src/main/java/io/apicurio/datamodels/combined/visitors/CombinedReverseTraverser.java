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

package io.apicurio.datamodels.combined.visitors;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.ITraverser;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * A reverse traverser.
 * @author eric.wittmann@gmail.com
 */
public class CombinedReverseTraverser extends CombinedAllNodeVisitor implements ITraverser {
    
    protected IVisitor visitor;

    /**
     * Constructor.
     */
    public CombinedReverseTraverser(IVisitor visitor) {
        this.visitor = visitor;
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.ITraverser#traverse(io.apicurio.datamodels.core.models.Node)
     */
    @Override
    public void traverse(Node node) {
        node.accept(this);
    }

    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitNode(io.apicurio.datamodels.core.models.Node)
     */
    @Override
    protected void visitNode(Node node) {
        node.accept(this.visitor);
        this.traverse(node.parent());
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitDocument(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void visitDocument(Document node) {
        node.accept(this.visitor);
    }

}
