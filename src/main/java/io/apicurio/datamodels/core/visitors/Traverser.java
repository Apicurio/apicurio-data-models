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
import io.apicurio.datamodels.core.models.IIndexedNode;
import io.apicurio.datamodels.core.models.IVisitable;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.ValidationProblem;
import io.apicurio.datamodels.core.models.common.Contact;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.IParameterDefinition;
import io.apicurio.datamodels.core.models.common.ISchemaDefinition;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.License;
import io.apicurio.datamodels.core.models.common.OAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlows;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Tag;

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
            items.forEach(node -> {
                this.traverseIfNotNull(node);
            });
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
     * Traverse all children of the indexed node.
     * @param node
     */
    protected void traverseIndexedNode(IIndexedNode<? extends Node> node) {
        this.traverseCollection(node.getItems());
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
	 * @see io.apicurio.datamodels.core.visitors.IVisitor#visitDocument(io.apicurio.datamodels.core.models.Document)
	 */
	@Override
	public final void visitDocument(Document node) {
		node.accept(this.visitor);
		this.traverseDocument(node);
	}
	
	/**
	 * Subclasses can override this to provide version-specific traversal of the root document.
     * @param node
     */
    protected void traverseDocument(Document node) {
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
        this.traverseIfNotNull(node.info);
        this.traverseCollection(node.tags);
        this.traverseIfNotNull(node.externalDocs);
    }

	/**
	 * @see io.apicurio.datamodels.core.visitors.IVisitor#visitExtension(io.apicurio.datamodels.core.models.Extension)
	 */
	@Override
	public void visitExtension(Extension node) {
	    node.accept(this.visitor);
        this.traverseValidationProblems(node);
	}

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitValidationProblem(io.apicurio.datamodels.core.models.ValidationProblem)
     */
    @Override
    public void visitValidationProblem(ValidationProblem problem) {
        problem.accept(this.visitor);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitInfo(io.apicurio.datamodels.core.models.common.Info)
     */
    @Override
    public void visitInfo(Info node) {
        node.accept(this.visitor);
        this.traverseIfNotNull(node.contact);
        this.traverseIfNotNull(node.license);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitContact(io.apicurio.datamodels.core.models.common.Contact)
     */
    @Override
    public void visitContact(Contact node) {
        node.accept(this.visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitLicense(io.apicurio.datamodels.core.models.common.License)
     */
    @Override
    public void visitLicense(License node) {
        node.accept(this.visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitTag(io.apicurio.datamodels.core.models.common.Tag)
     */
    @Override
    public void visitTag(Tag node) {
        node.accept(this.visitor);
        this.traverseIfNotNull(node.externalDocs);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSecurityRequirement(io.apicurio.datamodels.core.models.common.SecurityRequirement)
     */
    @Override
    public void visitSecurityRequirement(SecurityRequirement node) {
        node.accept(this.visitor);
        this.traverseValidationProblems(node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitExternalDocumentation(io.apicurio.datamodels.core.models.common.ExternalDocumentation)
     */
    @Override
    public void visitExternalDocumentation(ExternalDocumentation node) {
        node.accept(this.visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSchema(io.apicurio.datamodels.core.models.common.Schema)
     */
    @Override
    public void visitSchema(Schema node) {
        node.accept(this.visitor);
        traverseSchema(node);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }
    protected void traverseSchema(Schema node) {
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitParameter(io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    public void visitParameter(Parameter node) {
        node.accept(this.visitor);
        traverseParameter(node);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }
    protected void traverseParameter(Parameter node) {
        this.traverseIfNotNull(node.schema);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitParameterDefinition(io.apicurio.datamodels.core.models.common.IParameterDefinition)
     */
    @Override
    public void visitParameterDefinition(IParameterDefinition node) {
        this.visitParameter((Parameter) node);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitOperation(io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        node.accept(this.visitor);
        traverseOperation(node);
    }

    protected void traverseOperation(Operation node) {
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
        this.traverseIfNotNull(node.externalDocs);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSecurityScheme(io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        node.accept(this.visitor);
        this.traverseSecurityScheme(node);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }
    protected void traverseSecurityScheme(SecurityScheme node) {
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSchemaDefinition(io.apicurio.datamodels.core.models.common.ISchemaDefinition)
     */
    @Override
    public void visitSchemaDefinition(ISchemaDefinition node) {
        Schema schema = (Schema) node;
        this.visitSchema(schema);
    }
    
    /**
     * Traverse an OAuth flow.
     * @param node
     */
    protected void traverseOAuthFlow(OAuthFlow node) {
        node.accept(this.visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    /**
     * Traverses an oauth flows node.
     * @param node
     */
    protected void traverseOAuthFlows(OAuthFlows node) {
        node.accept(this.visitor);
        this.traverseIfNotNull(node.implicit);
        this.traverseIfNotNull(node.password);
        this.traverseIfNotNull(node.clientCredentials);
        this.traverseIfNotNull(node.authorizationCode);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

}
