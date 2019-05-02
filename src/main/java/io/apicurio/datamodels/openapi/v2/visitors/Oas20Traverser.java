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

package io.apicurio.datamodels.openapi.v2.visitors;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.openapi.v2.models.Oas20Definitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20Items;
import io.apicurio.datamodels.openapi.v2.models.Oas20Parameter;
import io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinition;
import io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Scopes;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityScheme;
import io.apicurio.datamodels.openapi.visitors.OasTraverser;

/**
 * An OpenAPI 2.0 data model traverser.
 * @author eric.wittmann@gmail.com
 */
public class Oas20Traverser extends OasTraverser implements IOas20Visitor {

    /**
     * Constructor.
     * @param visitor
     */
    public Oas20Traverser(IOas20Visitor visitor) {
        super(visitor);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.visitors.OasTraverser#doVisitDocument(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    protected void doVisitDocument(Document node) {
        super.doVisitDocument(node);
        
        Oas20Document doc = (Oas20Document) node;
        this.traverseIfNotNull(doc.definitions);
        this.traverseIfNotNull(doc.parameters);
        this.traverseIfNotNull(doc.securityDefinitions);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitParameterDefinition(io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinition)
     */
    @Override
    public void visitParameterDefinition(Oas20ParameterDefinition node) {
        this.visitParameter(node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.Traverser#traverseParameter(io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    protected void traverseParameter(Parameter node) {
        super.traverseParameter(node);
        Oas20Parameter param = (Oas20Parameter) node;
        this.traverseIfNotNull(param.items);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitItems(io.apicurio.datamodels.openapi.v2.models.Oas20Items)
     */
    @Override
    public void visitItems(Oas20Items node) {
        node.accept(this.visitor);
        this.traverseIfNotNull(node.items);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitScopes(io.apicurio.datamodels.openapi.v2.models.Oas20Scopes)
     */
    @Override
    public void visitScopes(Oas20Scopes node) {
        node.accept(this.visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitSecurityDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20SecurityDefinitions)
     */
    @Override
    public void visitSecurityDefinitions(Oas20SecurityDefinitions node) {
        node.accept(this.visitor);
        this.traverseIndexedNode(node);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSecurityScheme(io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        Oas20SecurityScheme node20 = (Oas20SecurityScheme) node;

        node.accept(this.visitor);
        this.traverseIfNotNull(node20.scopes);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20Definitions)
     */
    @Override
    public void visitDefinitions(Oas20Definitions node) {
        node.accept(this.visitor);
        this.traverseIndexedNode(node);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitParameterDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinitions)
     */
    @Override
    public void visitParameterDefinitions(Oas20ParameterDefinitions node) {
        node.accept(this.visitor);
        this.traverseIndexedNode(node);
        this.traverseValidationProblems(node);
    }

}
