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

import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
import io.apicurio.datamodels.openapi.v3.models.Oas30AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.openapi.v3.models.Oas30Callback;
import io.apicurio.datamodels.openapi.v3.models.Oas30CallbackDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30CallbackPathItem;
import io.apicurio.datamodels.openapi.v3.models.Oas30ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.openapi.v3.models.Oas30Discriminator;
import io.apicurio.datamodels.openapi.v3.models.Oas30Encoding;
import io.apicurio.datamodels.openapi.v3.models.Oas30ExampleDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30ImplicitOAuthFlow;
import io.apicurio.datamodels.openapi.v3.models.Oas30Link;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkParameterExpression;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkRequestBodyExpression;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkServer;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;
import io.apicurio.datamodels.openapi.v3.models.Oas30OAuthFlows;
import io.apicurio.datamodels.openapi.v3.models.Oas30PasswordOAuthFlow;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30NotSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema;
import io.apicurio.datamodels.openapi.visitors.OasAllNodeVisitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class Oas30AllNodeVisitor extends OasAllNodeVisitor implements IOas30Visitor {
    
    /**
     * Constructor.
     */
    public Oas30AllNodeVisitor() {
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitComponents(io.apicurio.datamodels.core.models.common.Components)
     */
    @Override
    public void visitComponents(Components node) {
        visitNode(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitCallbackPathItem(io.apicurio.datamodels.openapi.v3.models.Oas30CallbackPathItem)
     */
    @Override
    public void visitCallbackPathItem(Oas30CallbackPathItem node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitCallback(io.apicurio.datamodels.openapi.v3.models.Oas30Callback)
     */
    @Override
    public void visitCallback(Oas30Callback node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkServer(io.apicurio.datamodels.openapi.v3.models.Oas30LinkServer)
     */
    @Override
    public void visitLinkServer(Oas30LinkServer node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitCallbackDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30CallbackDefinition)
     */
    @Override
    public void visitCallbackDefinition(Oas30CallbackDefinition node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLink(io.apicurio.datamodels.openapi.v3.models.Oas30Link)
     */
    @Override
    public void visitLink(Oas30Link node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkRequestBodyExpression(io.apicurio.datamodels.openapi.v3.models.Oas30LinkRequestBodyExpression)
     */
    @Override
    public void visitLinkRequestBodyExpression(Oas30LinkRequestBodyExpression node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkParameterExpression(io.apicurio.datamodels.openapi.v3.models.Oas30LinkParameterExpression)
     */
    @Override
    public void visitLinkParameterExpression(Oas30LinkParameterExpression node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30LinkDefinition)
     */
    @Override
    public void visitLinkDefinition(Oas30LinkDefinition node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitAuthorizationCodeOAuthFlow(io.apicurio.datamodels.openapi.v3.models.Oas30AuthorizationCodeOAuthFlow)
     */
    @Override
    public void visitAuthorizationCodeOAuthFlow(Oas30AuthorizationCodeOAuthFlow node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitClientCredentialsOAuthFlow(io.apicurio.datamodels.openapi.v3.models.Oas30ClientCredentialsOAuthFlow)
     */
    @Override
    public void visitClientCredentialsOAuthFlow(Oas30ClientCredentialsOAuthFlow node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitPasswordOAuthFlow(io.apicurio.datamodels.openapi.v3.models.Oas30PasswordOAuthFlow)
     */
    @Override
    public void visitPasswordOAuthFlow(Oas30PasswordOAuthFlow node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitImplicitOAuthFlow(io.apicurio.datamodels.openapi.v3.models.Oas30ImplicitOAuthFlow)
     */
    @Override
    public void visitImplicitOAuthFlow(Oas30ImplicitOAuthFlow node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitOAuthFlows(io.apicurio.datamodels.openapi.v3.models.Oas30OAuthFlows)
     */
    @Override
    public void visitOAuthFlows(Oas30OAuthFlows node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitEncoding(io.apicurio.datamodels.openapi.v3.models.Oas30Encoding)
     */
    @Override
    public void visitEncoding(Oas30Encoding node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitMediaType(io.apicurio.datamodels.openapi.v3.models.Oas30MediaType)
     */
    @Override
    public void visitMediaType(Oas30MediaType node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitHeaderDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition)
     */
    @Override
    public void visitHeaderDefinition(Oas30HeaderDefinition node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitRequestBody(io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody)
     */
    @Override
    public void visitRequestBody(Oas30RequestBody node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitRequestBodyDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition)
     */
    @Override
    public void visitRequestBodyDefinition(Oas30RequestBodyDefinition node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitExampleDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30ExampleDefinition)
     */
    @Override
    public void visitExampleDefinition(Oas30ExampleDefinition node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitDiscriminator(io.apicurio.datamodels.openapi.v3.models.Oas30Discriminator)
     */
    @Override
    public void visitDiscriminator(Oas30Discriminator node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitNotSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30NotSchema)
     */
    @Override
    public void visitNotSchema(Oas30NotSchema node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitOneOfSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema)
     */
    @Override
    public void visitOneOfSchema(Oas30OneOfSchema node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitAnyOfSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema)
     */
    @Override
    public void visitAnyOfSchema(Oas30AnyOfSchema node) {
        visitNode(node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitServer(io.apicurio.datamodels.core.models.common.Server)
     */
    @Override
    public void visitServer(Server node) {
        visitNode(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitServerVariable(io.apicurio.datamodels.core.models.common.ServerVariable)
     */
    @Override
    public void visitServerVariable(ServerVariable node) {
        visitNode(node);
    }

}
