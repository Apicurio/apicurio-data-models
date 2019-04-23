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

import java.util.Collection;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.IVisitable;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.ValidationProblem;

/**
 * A base class for all traversers.  All domain specific traversers should extend this and then
 * provide domain specific functionality.
 * @author eric.wittmann@gmail.com
 */
public class Traverser implements ITraverser, IVisitor {
	
	protected IVisitor visitor;
	
	/**
	 * C'tor.
	 * @param visitor
	 */
	public Traverser(IVisitor visitor) {
		this.visitor = visitor;
	}

    /**
     * Traverse the items of the given array.
     * @param items
     */
    protected void traverseCollection(Collection<? extends IVisitable> items) {
        if (items != null) {
            for (IVisitable node : items) {
                this.traverseIfNotNull(node);
            }
        }
    }

    /**
     * Traverse the extension nodes, if any are found.
     * @param node
     */
    protected void traverseExtensions(ExtensibleNode node) {
        this.traverseCollection(node.getExtensions());
    }

    /**
     * Traverse the validation problems, if any are found.
     * @param node
     */
    protected void traverseValidationProblems(Node node) {
        this.traverseCollection(node.getValidationProblems());
    }

    /**
     * Called to traverse the data model starting at the given node and traversing
     * down until this node and all child nodes have been visited.
     * @param node
     */
    public void traverse(Node node) {
        node.accept(this);
    }

    /**
     * Traverse into the given node, unless it's null.
     * @param node
     */
    protected void traverseIfNotNull(IVisitable node) {
        if (node != null) {
            node.accept(this);
        }
    }

    /**
     * @see io.apicurio.asyncapi.core.visitors.INodeVisitor#visitDocument(io.apicurio.datamodels.asyncapi.models.Document)
     */
	@Override
	public void visitDocument(Document node) {
		node.accept(this.visitor);
		this.doVisitDocument(node);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
	}
	
	/**
	 * Subclasses can override this to provide version-specific traversal of the root document.
     * @param node
     */
    protected void doVisitDocument(Document node) {
    }

    /**
	 * @see io.apicurio.asyncapi.core.visitors.INodeVisitor#visitExtension(io.apicurio.asyncapi.core.models.Extension)
	 */
	@Override
	public void visitExtension(Extension node) {
	    node.accept(this.visitor);
        this.traverseValidationProblems(node);
	}

    /**
     * @see io.apicurio.asyncapi.core.visitors.INodeVisitor#visitValidationProblem(io.apicurio.asyncapi.core.validation.ValidationProblem)
     */
    @Override
    public void visitValidationProblem(ValidationProblem problem) {
        problem.accept(this.visitor);
    }

}
