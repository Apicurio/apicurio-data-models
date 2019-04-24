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

package io.apicurio.datamodels.core.visitors;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.ValidationProblem;

/**
 * Base class for all reverse traversers. A reverse traverser simply traverses the data model
 * up rather than down.  So it starts with a node and then traverse up the tree until it reaches
 * the root.
 * @author eric.wittmann@gmail.com
 */
public class ReverseTraverser implements IVisitor, ITraverser {
    
    protected IVisitor visitor;

    /**
     * Constructor.
     */
    public ReverseTraverser(IVisitor visitor) {
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
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitDocument(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void visitDocument(Document node) {
        node.accept(this.visitor);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitExtension(io.apicurio.datamodels.core.models.Extension)
     */
    @Override
    public void visitExtension(Extension node) {
        node.accept(this.visitor);
        this.traverse(node.parent());
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitValidationProblem(io.apicurio.datamodels.core.models.ValidationProblem)
     */
    @Override
    public void visitValidationProblem(ValidationProblem problem) {
        // Validation problems are not traversed
    }

}
