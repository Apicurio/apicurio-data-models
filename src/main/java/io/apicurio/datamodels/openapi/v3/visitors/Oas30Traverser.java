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

package io.apicurio.datamodels.openapi.v3.visitors;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.models.common.ModernSecurityScheme;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.v3.models.Oas30AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.openapi.v3.models.Oas30Callback;
import io.apicurio.datamodels.openapi.v3.models.Oas30CallbackDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30CallbackPathItem;
import io.apicurio.datamodels.openapi.v3.models.Oas30ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.openapi.v3.models.Oas30Components;
import io.apicurio.datamodels.openapi.v3.models.Oas30Discriminator;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Encoding;
import io.apicurio.datamodels.openapi.v3.models.Oas30Example;
import io.apicurio.datamodels.openapi.v3.models.Oas30ExampleDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Header;
import io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30ImplicitOAuthFlow;
import io.apicurio.datamodels.openapi.v3.models.Oas30Link;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkParameterExpression;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkRequestBodyExpression;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkServer;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;
import io.apicurio.datamodels.openapi.v3.models.Oas30OAuthFlows;
import io.apicurio.datamodels.openapi.v3.models.Oas30Operation;
import io.apicurio.datamodels.openapi.v3.models.Oas30Parameter;
import io.apicurio.datamodels.openapi.v3.models.Oas30PasswordOAuthFlow;
import io.apicurio.datamodels.openapi.v3.models.Oas30PathItem;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Response;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30NotSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema;
import io.apicurio.datamodels.openapi.visitors.OasTraverser;

/**
 * @author eric.wittmann@gmail.com
 */
public class Oas30Traverser extends OasTraverser implements IOas30Visitor {

    /**
     * Constructor.
     * @param visitor
     */
    public Oas30Traverser(IOas30Visitor visitor) {
        super(visitor);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.visitors.OasTraverser#traverseDocument(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    protected void traverseDocument(Document node) {
        Oas30Document doc = (Oas30Document) node;
        super.traverseDocument(doc);
        this.traverseCollection(doc.servers);
        this.traverseIfNotNull(doc.components);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.Traverser#traverseParameter(io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    protected void traverseParameter(Parameter node) {
        super.traverseParameter(node);
        Oas30Parameter param = (Oas30Parameter) node;
        this.traverseCollection(param.getExamples());
        this.traverseCollection(param.getMediaTypes());
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.visitors.OasTraverser#traverseResponse(io.apicurio.datamodels.openapi.models.OasResponse)
     */
    @Override
    protected void traverseResponse(OasResponse node) {
        super.traverseResponse(node);
        Oas30Response response = (Oas30Response) node;
        this.traverseCollection(response.getHeaders());
        this.traverseCollection(response.getMediaTypes());
        this.traverseCollection(response.getLinks());
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.visitors.OasTraverser#traverseSchema(io.apicurio.datamodels.core.models.common.Schema)
     */
    @Override
    protected void traverseSchema(Schema node) {
        super.traverseSchema(node);
        Oas30Schema schema = (Oas30Schema) node;
        this.traverseCollection(schema.oneOf);
        this.traverseCollection(schema.anyOf);
        this.traverseIfNotNull(schema.not);
        this.traverseIfNotNull(schema.discriminator);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.visitors.OasTraverser#traverseOperation(io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    protected void traverseOperation(Operation node) {
        super.traverseOperation(node);
        Oas30Operation op = (Oas30Operation) node;
        this.traverseIfNotNull(op.requestBody);
        this.traverseCollection(op.getCallbacks());
        this.traverseCollection(op.servers);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.visitors.OasTraverser#traverseHeader(io.apicurio.datamodels.openapi.models.OasHeader)
     */
    @Override
    protected void traverseHeader(OasHeader node) {
        super.traverseHeader(node);
        Oas30Header header = (Oas30Header) node;
        this.traverseIfNotNull(header.schema);
        this.traverseCollection(header.getExamples());
        this.traverseCollection(header.getMediaTypes());
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitComponents(io.apicurio.datamodels.core.models.common.Components)
     */
    @Override
    public void visitComponents(Components node) {
        Oas30Components components = (Oas30Components) node;
        node.accept(this.visitor);
        this.traverseCollection(components.getSchemaDefinitions());
        this.traverseCollection(components.getResponseDefinitions());
        this.traverseCollection(components.getParameterDefinitions());
        this.traverseCollection(components.getExampleDefinitions());
        this.traverseCollection(components.getRequestBodyDefinitions());
        this.traverseCollection(components.getHeaderDefinitions());
        this.traverseCollection(components.getSecuritySchemes());
        this.traverseCollection(components.getLinkDefinitions());
        this.traverseCollection(components.getCallbackDefinitions());
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.visitors.OasTraverser#traversePathItem(io.apicurio.datamodels.openapi.models.OasPathItem)
     */
    @Override
    protected void traversePathItem(OasPathItem node) {
        super.traversePathItem(node);
        
        Oas30PathItem pathItem = (Oas30PathItem) node;
        this.traverseIfNotNull(pathItem.trace);
        this.traverseCollection(pathItem.servers);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitCallbackPathItem(io.apicurio.datamodels.openapi.v3.models.Oas30CallbackPathItem)
     */
    @Override
    public void visitCallbackPathItem(Oas30CallbackPathItem node) {
        this.visitPathItem(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitCallback(io.apicurio.datamodels.openapi.v3.models.Oas30Callback)
     */
    @Override
    public void visitCallback(Oas30Callback node) {
        node.accept(this.visitor);
        this.traverseIndexedNode(node);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }
 
    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkServer(io.apicurio.datamodels.openapi.v3.models.Oas30LinkServer)
     */
    @Override
    public void visitLinkServer(Oas30LinkServer node) {
        this.visitServer(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitCallbackDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30CallbackDefinition)
     */
    @Override
    public void visitCallbackDefinition(Oas30CallbackDefinition node) {
        this.visitCallback(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLink(io.apicurio.datamodels.openapi.v3.models.Oas30Link)
     */
    @Override
    public void visitLink(Oas30Link node) {
        node.accept(this.visitor);
        this.traverseCollection(node.getLinkParameterExpressions());
        this.traverseIfNotNull(node.requestBody);
        this.traverseIfNotNull(node.server);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkRequestBodyExpression(io.apicurio.datamodels.openapi.v3.models.Oas30LinkRequestBodyExpression)
     */
    @Override
    public void visitLinkRequestBodyExpression(Oas30LinkRequestBodyExpression node) {
        node.accept(this.visitor);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkParameterExpression(io.apicurio.datamodels.openapi.v3.models.Oas30LinkParameterExpression)
     */
    @Override
    public void visitLinkParameterExpression(Oas30LinkParameterExpression node) {
        node.accept(this.visitor);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30LinkDefinition)
     */
    @Override
    public void visitLinkDefinition(Oas30LinkDefinition node) {
        this.visitLink(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitAuthorizationCodeOAuthFlow(io.apicurio.datamodels.openapi.v3.models.Oas30AuthorizationCodeOAuthFlow)
     */
    @Override
    public void visitAuthorizationCodeOAuthFlow(Oas30AuthorizationCodeOAuthFlow node) {
        this.traverseOAuthFlow(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitClientCredentialsOAuthFlow(io.apicurio.datamodels.openapi.v3.models.Oas30ClientCredentialsOAuthFlow)
     */
    @Override
    public void visitClientCredentialsOAuthFlow(Oas30ClientCredentialsOAuthFlow node) {
        this.traverseOAuthFlow(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitPasswordOAuthFlow(io.apicurio.datamodels.openapi.v3.models.Oas30PasswordOAuthFlow)
     */
    @Override
    public void visitPasswordOAuthFlow(Oas30PasswordOAuthFlow node) {
        this.traverseOAuthFlow(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitImplicitOAuthFlow(io.apicurio.datamodels.openapi.v3.models.Oas30ImplicitOAuthFlow)
     */
    @Override
    public void visitImplicitOAuthFlow(Oas30ImplicitOAuthFlow node) {
        this.traverseOAuthFlow(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitOAuthFlows(io.apicurio.datamodels.openapi.v3.models.Oas30OAuthFlows)
     */
    @Override
    public void visitOAuthFlows(Oas30OAuthFlows node) {
        traverseOAuthFlows(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitExample(io.apicurio.datamodels.openapi.v3.models.Oas30Example)
     */
    @Override
    public void visitExample(Oas30Example node) {
        node.accept(this.visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
   }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitEncoding(io.apicurio.datamodels.openapi.v3.models.Oas30Encoding)
     */
    @Override
    public void visitEncoding(Oas30Encoding node) {
        node.accept(this.visitor);
        this.traverseCollection(node.getHeaders());
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitMediaType(io.apicurio.datamodels.openapi.v3.models.Oas30MediaType)
     */
    @Override
    public void visitMediaType(Oas30MediaType node) {
        node.accept(this.visitor);
        this.traverseIfNotNull(node.schema);
        this.traverseCollection(node.getExamples());
        this.traverseCollection(node.getEncodings());
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitHeaderDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition)
     */
    @Override
    public void visitHeaderDefinition(Oas30HeaderDefinition node) {
        this.visitHeader(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitRequestBody(io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody)
     */
    @Override
    public void visitRequestBody(Oas30RequestBody node) {
        node.accept(this.visitor);
        this.traverseCollection(node.getMediaTypes());
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitRequestBodyDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition)
     */
    @Override
    public void visitRequestBodyDefinition(Oas30RequestBodyDefinition node) {
        this.visitRequestBody(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitExampleDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30ExampleDefinition)
     */
    @Override
    public void visitExampleDefinition(Oas30ExampleDefinition node) {
        this.visitExample(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitDiscriminator(io.apicurio.datamodels.openapi.v3.models.Oas30Discriminator)
     */
    @Override
    public void visitDiscriminator(Oas30Discriminator node) {
        node.accept(this.visitor);
        this.traverseValidationProblems(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitNotSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30NotSchema)
     */
    @Override
    public void visitNotSchema(Oas30NotSchema node) {
        this.visitSchema(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitOneOfSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema)
     */
    @Override
    public void visitOneOfSchema(Oas30OneOfSchema node) {
        this.visitSchema(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitAnyOfSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema)
     */
    @Override
    public void visitAnyOfSchema(Oas30AnyOfSchema node) {
        this.visitSchema(node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.Traverser#traverseSecurityScheme(io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    @Override
    protected void traverseSecurityScheme(SecurityScheme node) {
        super.traverseSecurityScheme(node);
        ModernSecurityScheme scheme = (ModernSecurityScheme) node;
        this.traverseIfNotNull(scheme.flows);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitServer(io.apicurio.datamodels.core.models.common.Server)
     */
    @Override
    public void visitServer(Server node) {
        node.accept(this.visitor);
        this.traverseCollection(node.getServerVariables());
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitServerVariable(io.apicurio.datamodels.core.models.common.ServerVariable)
     */
    @Override
    public void visitServerVariable(ServerVariable node) {
        node.accept(this.visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

}
