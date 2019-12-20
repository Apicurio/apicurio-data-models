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

import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlows;
import io.apicurio.datamodels.core.models.common.PasswordOAuthFlow;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
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
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30NotSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema;
import io.apicurio.datamodels.openapi.visitors.IOasVisitor;

/**
 * The OpenAPI 3.0.x version of the visitor interface.
 * @author eric.wittmann@gmail.com
 */
public interface IOas30Visitor extends IOasVisitor {

    public void visitComponents(Components node);
    public void visitCallbackPathItem(Oas30CallbackPathItem node);
    public void visitCallback(Oas30Callback node);
    public void visitLinkServer(Oas30LinkServer node);
    public void visitCallbackDefinition(Oas30CallbackDefinition node);
    public void visitLink(Oas30Link node);
    public void visitLinkRequestBodyExpression(Oas30LinkRequestBodyExpression node);
    public void visitLinkParameterExpression(Oas30LinkParameterExpression node);
    public void visitLinkDefinition(Oas30LinkDefinition node);
    public void visitAuthorizationCodeOAuthFlow(AuthorizationCodeOAuthFlow node);
    public void visitClientCredentialsOAuthFlow(ClientCredentialsOAuthFlow node);
    public void visitPasswordOAuthFlow(PasswordOAuthFlow node);
    public void visitImplicitOAuthFlow(ImplicitOAuthFlow node);
    public void visitOAuthFlows(OAuthFlows node);
    public void visitEncoding(Oas30Encoding node);
    public void visitMediaType(Oas30MediaType node);
    public void visitHeaderDefinition(Oas30HeaderDefinition node);
    public void visitRequestBody(Oas30RequestBody node);
    public void visitRequestBodyDefinition(Oas30RequestBodyDefinition node);
    public void visitExampleDefinition(Oas30ExampleDefinition node);
    public void visitDiscriminator(Oas30Discriminator node);
    public void visitNotSchema(Oas30NotSchema node);
    public void visitOneOfSchema(Oas30OneOfSchema node);
    public void visitAnyOfSchema(Oas30AnyOfSchema node);
    public void visitServer(Server node);
    public void visitServerVariable(ServerVariable node);

}
