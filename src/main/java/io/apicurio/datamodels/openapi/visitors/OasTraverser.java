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

package io.apicurio.datamodels.openapi.visitors;

import java.util.List;

import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.models.common.IExample;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.core.visitors.Traverser;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.models.OasPaths;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.models.OasResponses;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.models.OasXML;

/**
 * An OpenAPI data model traverser.
 * @author eric.wittmann@gmail.com
 */
public class OasTraverser extends Traverser implements IOasVisitor {

    /**
     * Constructor.
     * @param visitor
     */
    public OasTraverser(IVisitor visitor) {
        super(visitor);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.Traverser#traverseDocument(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    protected void traverseDocument(Document node) {
        OasDocument doc = (OasDocument) node;
        super.traverseDocument(node);
        this.traverseIfNotNull(doc.paths);
        this.traverseCollection(doc.security);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitPaths(io.apicurio.datamodels.openapi.models.OasPaths)
     */
    @Override
    public void visitPaths(OasPaths node) {
        node.accept(this.visitor);
        this.traverseIndexedNode(node);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitPathItem(io.apicurio.datamodels.openapi.models.OasPathItem)
     */
    @Override
    public void visitPathItem(OasPathItem node) {
        node.accept(this.visitor);
        traversePathItem(node);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    /**
     * Traverses a path item.
     */
    protected void traversePathItem(OasPathItem node) {
        this.traverseIfNotNull(node.get);
        this.traverseIfNotNull(node.put);
        this.traverseIfNotNull(node.post);
        this.traverseIfNotNull(node.delete);
        this.traverseIfNotNull(node.options);
        this.traverseIfNotNull(node.head);
        this.traverseIfNotNull(node.patch);
        this.traverseCollection(node.parameters);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitResponse(io.apicurio.datamodels.openapi.models.OasResponse)
     */
    @Override
    public void visitResponse(OasResponse node) {
        node.accept(this.visitor);
        this.traverseResponse(node);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }
    protected void traverseResponse(OasResponse node) {
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitResponses(io.apicurio.datamodels.openapi.models.OasResponses)
     */
    @Override
    public void visitResponses(OasResponses node) {
        node.accept(this.visitor);
        this.traverseIndexedNode(node);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitXML(io.apicurio.datamodels.openapi.models.OasXML)
     */
    @Override
    public void visitXML(OasXML node) {
        node.accept(this.visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitAllOfSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitAllOfSchema(Schema node) {
        this.visitSchema(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitItemsSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitItemsSchema(Schema node) {
        this.visitSchema(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitAdditionalPropertiesSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitAdditionalPropertiesSchema(Schema node) {
        this.visitSchema(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitPropertySchema(io.apicurio.datamodels.core.models.common.IPropertySchema)
     */
    @Override
    public void visitPropertySchema(IPropertySchema node) {
        this.visitSchema((Schema) node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.Traverser#traverseSchema(io.apicurio.datamodels.core.models.common.Schema)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void traverseSchema(Schema node) {
        super.traverseSchema(node);
        OasSchema schema = (OasSchema) node;
        if (NodeCompat.isList(schema.items)) {
            this.traverseCollection((List<Node>) schema.items);
        } else {
            this.traverseIfNotNull((Node) schema.items);
        }
        this.traverseCollection(schema.allOf);
        this.traverseCollection(schema.getProperties());
        if (NodeCompat.isNode(schema.additionalProperties)) {
            this.traverseIfNotNull((Node) schema.additionalProperties);
        }
        this.traverseIfNotNull(schema.xml);
        this.traverseIfNotNull(schema.externalDocs);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitHeader(io.apicurio.datamodels.openapi.models.OasHeader)
     */
    @Override
    public void visitHeader(OasHeader node) {
        node.accept(this.visitor);
        traverseHeader(node);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }
    protected void traverseHeader(OasHeader node) {
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitResponseDefinition(io.apicurio.datamodels.core.models.common.IDefinition)
     */
    @Override
    public void visitResponseDefinition(IDefinition node) {
        this.visitResponse((OasResponse) node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.Traverser#traverseOperation(io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    protected void traverseOperation(Operation node) {
        OasOperation operation = (OasOperation) node;
        
        this.traverseCollection(operation.parameters);
        this.traverseIfNotNull(operation.responses);
        this.traverseCollection(operation.security);
        
        super.traverseOperation(node);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitExample(io.apicurio.datamodels.core.models.common.IExample)
     */
    @Override
    public void visitExample(IExample node) {
        throw new RuntimeException("Must be implemented by subclasses.");
    }

}
