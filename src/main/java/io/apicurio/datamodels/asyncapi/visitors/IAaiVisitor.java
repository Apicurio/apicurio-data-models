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

package io.apicurio.datamodels.asyncapi.visitors;

import io.apicurio.datamodels.asyncapi.models.*;
import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlows;
import io.apicurio.datamodels.core.models.common.PasswordOAuthFlow;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.models.OasSchema;

/**
 * A node visitor for the AsyncAPI data model.
 *
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public interface IAaiVisitor extends IVisitor {

    void visitAuthorizationCodeOAuthFlow(AuthorizationCodeOAuthFlow node);
    void visitChannelItem(AaiChannelItem node);
    void visitClientCredentialsOAuthFlow(ClientCredentialsOAuthFlow node);
    void visitComponents(Components node);
    void visitCorrelationId(AaiCorrelationId node);
    void visitHeaderItem(AaiHeaderItem node);
    void visitImplicitOAuthFlow(ImplicitOAuthFlow node);
    void visitMessage(AaiMessage node);
    void visitMessageTrait(AaiMessageTrait node);
    void visitMessageTraitDefinition(AaiMessageTraitDefinition node);
    void visitOAuthFlows(OAuthFlows node);
    void visitOperationTrait(AaiOperationTrait node);
    void visitOperationTraitDefinition(AaiOperationTraitDefinition node);
    void visitPasswordOAuthFlow(PasswordOAuthFlow node);
    void visitServer(Server node);
    void visitServerVariable(ServerVariable node);
    void visitAaiParameter(AaiParameter node);
    void visitServerBindings(AaiServerBindings node);
    void visitServerBindingsDefinition(AaiServerBindingsDefinition node);
    void visitOperationBindings(AaiOperationBindings node);
    void visitOperationBindingsDefinition(AaiOperationBindingsDefinition node);
    void visitMessageBindings(AaiMessageBindings node);
    void visitMessageBindingsDefinition(AaiMessageBindingsDefinition node);
    void visitChannelBindings(AaiChannelBindings node);
    void visitChannelBindingsDefinition(AaiChannelBindingsDefinition node);
    void visitAllOfSchema(AaiSchema node);
    void visitOneOfSchema(AaiSchema node);
    void visitAnyOfSchema(AaiSchema node);
    void visitNotSchema(AaiSchema node);
    void visitPropertySchema(IAaiPropertySchema node);
    void visitItemsSchema(AaiSchema node);
    void visitAdditionalPropertiesSchema(AaiSchema node);
}
