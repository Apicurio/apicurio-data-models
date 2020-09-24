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

import io.apicurio.datamodels.asyncapi.models.AaiChannelBindings;
import io.apicurio.datamodels.asyncapi.models.AaiChannelBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiCorrelationId;
import io.apicurio.datamodels.asyncapi.models.AaiHeaderItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBindings;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBindings;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiParameter;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindings;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindingsDefinition;
import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.ValidationProblem;
import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.models.common.Contact;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.models.common.IExample;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.License;
import io.apicurio.datamodels.core.models.common.OAuthFlows;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.PasswordOAuthFlow;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
import io.apicurio.datamodels.core.models.common.Tag;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.models.OasPaths;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.models.OasResponses;
import io.apicurio.datamodels.openapi.models.OasXML;
import io.apicurio.datamodels.openapi.v2.models.Oas20Definitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Headers;
import io.apicurio.datamodels.openapi.v2.models.Oas20Items;
import io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Scopes;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityDefinitions;
import io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor;
import io.apicurio.datamodels.openapi.v3.models.Oas30Callback;
import io.apicurio.datamodels.openapi.v3.models.Oas30CallbackDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30CallbackPathItem;
import io.apicurio.datamodels.openapi.v3.models.Oas30Discriminator;
import io.apicurio.datamodels.openapi.v3.models.Oas30Encoding;
import io.apicurio.datamodels.openapi.v3.models.Oas30ExampleDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Link;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkParameterExpression;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkRequestBodyExpression;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkServer;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition;
import io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor;

/**
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class CombinedVisitorAdapter implements IOas20Visitor, IOas30Visitor, IAai20Visitor {

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitPaths(io.apicurio.datamodels.openapi.models.OasPaths)
     */
    @Override
    public void visitPaths(OasPaths node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitPathItem(io.apicurio.datamodels.openapi.models.OasPathItem)
     */
    @Override
    public void visitPathItem(OasPathItem node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitResponse(io.apicurio.datamodels.openapi.models.OasResponse)
     */
    @Override
    public void visitResponse(OasResponse node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitResponses(io.apicurio.datamodels.openapi.models.OasResponses)
     */
    @Override
    public void visitResponses(OasResponses node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitXML(io.apicurio.datamodels.openapi.models.OasXML)
     */
    @Override
    public void visitXML(OasXML node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitAllOfSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitAllOfSchema(Schema node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitItemsSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitItemsSchema(Schema node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitAdditionalPropertiesSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitAdditionalPropertiesSchema(Schema node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitPropertySchema(io.apicurio.datamodels.core.models.common.IPropertySchema)
     */
    @Override
    public void visitPropertySchema(IPropertySchema node) {


    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitDocument(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void visitDocument(Document node) {


    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitInfo(io.apicurio.datamodels.core.models.common.Info)
     */
    @Override
    public void visitInfo(Info node) {


    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitContact(io.apicurio.datamodels.core.models.common.Contact)
     */
    @Override
    public void visitContact(Contact node) {


    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitLicense(io.apicurio.datamodels.core.models.common.License)
     */
    @Override
    public void visitLicense(License node) {


    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitTag(io.apicurio.datamodels.core.models.common.Tag)
     */
    @Override
    public void visitTag(Tag node) {


    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitExternalDocumentation(io.apicurio.datamodels.core.models.common.ExternalDocumentation)
     */
    @Override
    public void visitExternalDocumentation(ExternalDocumentation node) {


    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitExtension(io.apicurio.datamodels.core.models.Extension)
     */
    @Override
    public void visitExtension(Extension node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitServer(io.apicurio.datamodels.core.models.common.Server)
     */
    @Override
    public void visitServer(Server node) {


    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSecurityRequirement(io.apicurio.datamodels.core.models.common.SecurityRequirement)
     */
    @Override
    public void visitSecurityRequirement(SecurityRequirement node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitServerVariable(io.apicurio.datamodels.core.models.common.ServerVariable)
     */
    @Override
    public void visitServerVariable(ServerVariable node) {


    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitValidationProblem(io.apicurio.datamodels.core.models.ValidationProblem)
     */
    @Override
    public void visitValidationProblem(ValidationProblem problem) {


    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSchema(io.apicurio.datamodels.core.models.common.Schema)
     */
    @Override
    public void visitSchema(Schema node) {


    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSchemaDefinition(io.apicurio.datamodels.core.models.common.IDefinition)
     */
    @Override
    public void visitSchemaDefinition(IDefinition node) {


    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitParameter(io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    public void visitParameter(Parameter node) {


    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitOperation(io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    public void visitOperation(Operation node) {


    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSecurityScheme(io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    @Override
    public void visitSecurityScheme(SecurityScheme node) {


    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitParameterDefinition(io.apicurio.datamodels.core.models.common.IDefinition)
     */
    @Override
    public void visitParameterDefinition(IDefinition node) {

    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitItems(io.apicurio.datamodels.openapi.v2.models.Oas20Items)
     */
    @Override
    public void visitItems(Oas20Items node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitScopes(io.apicurio.datamodels.openapi.v2.models.Oas20Scopes)
     */
    @Override
    public void visitScopes(Oas20Scopes node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitSecurityDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20SecurityDefinitions)
     */
    @Override
    public void visitSecurityDefinitions(Oas20SecurityDefinitions node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20Definitions)
     */
    @Override
    public void visitDefinitions(Oas20Definitions node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitParameterDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinitions)
     */
    @Override
    public void visitParameterDefinitions(Oas20ParameterDefinitions node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitHeader(io.apicurio.datamodels.openapi.models.OasHeader)
     */
    @Override
    public void visitHeader(OasHeader node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitResponseDefinition(io.apicurio.datamodels.core.models.common.IDefinition)
     */
    @Override
    public void visitResponseDefinition(IDefinition node) {


    }
    
    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitExample(io.apicurio.datamodels.core.models.common.IExample)
     */
    @Override
    public void visitExample(IExample node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitHeaders(io.apicurio.datamodels.openapi.v2.models.Oas20Headers)
     */
    @Override
    public void visitHeaders(Oas20Headers node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitResponseDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinitions)
     */
    @Override
    public void visitResponseDefinitions(Oas20ResponseDefinitions node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitCallbackPathItem(io.apicurio.datamodels.openapi.v3.models.Oas30CallbackPathItem)
     */
    @Override
    public void visitCallbackPathItem(Oas30CallbackPathItem node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitCallback(io.apicurio.datamodels.openapi.v3.models.Oas30Callback)
     */
    @Override
    public void visitCallback(Oas30Callback node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkServer(io.apicurio.datamodels.openapi.v3.models.Oas30LinkServer)
     */
    @Override
    public void visitLinkServer(Oas30LinkServer node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitCallbackDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30CallbackDefinition)
     */
    @Override
    public void visitCallbackDefinition(Oas30CallbackDefinition node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLink(io.apicurio.datamodels.openapi.v3.models.Oas30Link)
     */
    @Override
    public void visitLink(Oas30Link node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkRequestBodyExpression(io.apicurio.datamodels.openapi.v3.models.Oas30LinkRequestBodyExpression)
     */
    @Override
    public void visitLinkRequestBodyExpression(Oas30LinkRequestBodyExpression node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkParameterExpression(io.apicurio.datamodels.openapi.v3.models.Oas30LinkParameterExpression)
     */
    @Override
    public void visitLinkParameterExpression(Oas30LinkParameterExpression node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30LinkDefinition)
     */
    @Override
    public void visitLinkDefinition(Oas30LinkDefinition node) {
    }


    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitEncoding(io.apicurio.datamodels.openapi.v3.models.Oas30Encoding)
     */
    @Override
    public void visitEncoding(Oas30Encoding node) {
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitMediaType(io.apicurio.datamodels.openapi.v3.models.Oas30MediaType)
     */
    @Override
    public void visitMediaType(Oas30MediaType node) {
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitHeaderDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition)
     */
    @Override
    public void visitHeaderDefinition(Oas30HeaderDefinition node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitRequestBody(io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody)
     */
    @Override
    public void visitRequestBody(Oas30RequestBody node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitRequestBodyDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition)
     */
    @Override
    public void visitRequestBodyDefinition(Oas30RequestBodyDefinition node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitExampleDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30ExampleDefinition)
     */
    @Override
    public void visitExampleDefinition(Oas30ExampleDefinition node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitDiscriminator(io.apicurio.datamodels.openapi.v3.models.Oas30Discriminator)
     */
    @Override
    public void visitDiscriminator(Oas30Discriminator node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitNotSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30NotSchema)
     */
    @Override
    public void visitNotSchema(Schema node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitOneOfSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema)
     */
    @Override
    public void visitOneOfSchema(Schema node) {


    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitAnyOfSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema)
     */
    @Override
    public void visitAnyOfSchema(Schema node) {


    }

    @Override
    public void visitAuthorizationCodeOAuthFlow(AuthorizationCodeOAuthFlow node) {

    }

    @Override
    public void visitChannelItem(AaiChannelItem node) {

    }

    @Override
    public void visitClientCredentialsOAuthFlow(ClientCredentialsOAuthFlow node) {

    }

    @Override
    public void visitComponents(Components node) {

    }

    @Override
    public void visitCorrelationId(AaiCorrelationId node) {

    }

    @Override
    public void visitHeaderItem(AaiHeaderItem node) {

    }

    @Override
    public void visitImplicitOAuthFlow(ImplicitOAuthFlow node) {

    }

    @Override
    public void visitMessage(AaiMessage node) {

    }

    @Override
    public void visitMessageTrait(AaiMessageTrait node) {

    }

    @Override
    public void visitOAuthFlows(OAuthFlows node) {

    }

    @Override
    public void visitOperationTrait(AaiOperationTrait node) {

    }

    @Override
    public void visitPasswordOAuthFlow(PasswordOAuthFlow node) {

    }

    @Override
    public void visitAaiParameter(AaiParameter aaiParameter) {

    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitServerBindings(io.apicurio.datamodels.asyncapi.models.AaiServerBindings)
     */
    @Override
    public void visitServerBindings(AaiServerBindings node) {
        
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitServerBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiServerBindingsDefinition)
     */
    @Override
    public void visitServerBindingsDefinition(AaiServerBindingsDefinition node) {
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitOperationBindings(io.apicurio.datamodels.asyncapi.models.AaiOperationBindings)
     */
    @Override
    public void visitOperationBindings(AaiOperationBindings node) {
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitOperationBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiOperationBindingsDefinition)
     */
    @Override
    public void visitOperationBindingsDefinition(AaiOperationBindingsDefinition node) {
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitMessageBindings(io.apicurio.datamodels.asyncapi.models.AaiMessageBindings)
     */
    @Override
    public void visitMessageBindings(AaiMessageBindings node) {
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitMessageBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiMessageBindingsDefinition)
     */
    @Override
    public void visitMessageBindingsDefinition(AaiMessageBindingsDefinition node) {
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitChannelBindings(io.apicurio.datamodels.asyncapi.models.AaiChannelBindings)
     */
    @Override
    public void visitChannelBindings(AaiChannelBindings node) {
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitChannelBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiChannelBindingsDefinition)
     */
    @Override
    public void visitChannelBindingsDefinition(AaiChannelBindingsDefinition node) {
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitMessageTraitDefinition(io.apicurio.datamodels.asyncapi.models.AaiMessageTraitDefinition)
     */
    @Override
    public void visitMessageTraitDefinition(AaiMessageTraitDefinition node) {
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitOperationTraitDefinition(io.apicurio.datamodels.asyncapi.models.AaiOperationTraitDefinition)
     */
    @Override
    public void visitOperationTraitDefinition(AaiOperationTraitDefinition node) {
    }
}
