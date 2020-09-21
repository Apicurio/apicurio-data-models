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
import io.apicurio.datamodels.asyncapi.models.AaiSchema;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindings;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.IAaiPropertySchema;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlows;
import io.apicurio.datamodels.core.models.common.PasswordOAuthFlow;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
import io.apicurio.datamodels.core.visitors.AllNodeVisitor;

/**
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class AaiAllNodeVisitor extends AllNodeVisitor implements IAaiVisitor {

    @Override
    public void visitAuthorizationCodeOAuthFlow(AuthorizationCodeOAuthFlow node) {
        visitNode(node);
    }

    @Override
    public void visitChannelItem(AaiChannelItem node) {
        visitNode(node);
    }

    @Override
    public void visitClientCredentialsOAuthFlow(ClientCredentialsOAuthFlow node) {
        visitNode(node);
    }

    @Override
    public void visitComponents(Components node) {
        visitNode(node);
    }

    @Override
    public void visitCorrelationId(AaiCorrelationId node) {
        visitNode(node);
    }

    @Override
    public void visitHeaderItem(AaiHeaderItem node) {
        visitNode(node);
    }

    @Override
    public void visitImplicitOAuthFlow(ImplicitOAuthFlow node) {
        visitNode(node);
    }

    @Override
    public void visitMessage(AaiMessage node) {
        visitNode(node);
    }

    @Override
    public void visitMessageTrait(AaiMessageTrait node) {
        visitNode(node);
    }

    @Override
    public void visitOAuthFlows(OAuthFlows node) {
        visitNode(node);
    }

    @Override
    public void visitOperationTrait(AaiOperationTrait node) {
        visitNode(node);
    }

    @Override
    public void visitPasswordOAuthFlow(PasswordOAuthFlow node) {
        visitNode(node);
    }

    @Override
    public void visitServer(Server node) {
        visitNode(node);
    }

    @Override
    public void visitServerVariable(ServerVariable node) {
        visitNode(node);
    }

    @Override
    public void visitAaiParameter(AaiParameter node) {
        visitNode(node);
    }

    @Override
    public void visitServerBindings(AaiServerBindings node) {
        visitNode(node);
    }

    @Override
    public void visitServerBindingsDefinition(AaiServerBindingsDefinition node) {
        visitNode(node);
    }

    @Override
    public void visitOperationBindings(AaiOperationBindings node) {
        visitNode(node);
    }

    @Override
    public void visitOperationBindingsDefinition(AaiOperationBindingsDefinition node) {
        visitNode(node);
    }

    @Override
    public void visitMessageBindings(AaiMessageBindings node) {
        visitNode(node);
    }

    @Override
    public void visitMessageBindingsDefinition(AaiMessageBindingsDefinition node) {
        visitNode(node);
    }

    @Override
    public void visitChannelBindings(AaiChannelBindings node) {
        visitNode(node);
    }

    @Override
    public void visitChannelBindingsDefinition(AaiChannelBindingsDefinition node) {
        visitNode(node);
    }

    @Override
    public void visitAllOfSchema(AaiSchema node) {
        visitNode(node);
    }

    @Override
    public void visitOneOfSchema(AaiSchema node) {
        visitNode(node);
    }

    @Override
    public void visitAnyOfSchema(AaiSchema node) {
        visitNode(node);
    }

    @Override
    public void visitNotSchema(AaiSchema node) {
        visitNode(node);
    }

    @Override
    public void visitPropertySchema(IAaiPropertySchema node) {
        visitNode((Node) node);
    }

    @Override
    public void visitItemsSchema(AaiSchema node) {
        visitNode(node);
    }

    @Override
    public void visitAdditionalPropertiesSchema(AaiSchema node) {
        visitNode(node);
    }

    @Override
    public void visitMessageTraitDefinition(AaiMessageTraitDefinition node) {
        visitNode(node);
    }

    @Override
    public void visitOperationTraitDefinition(AaiOperationTraitDefinition node) {
        visitNode(node);
    }
}
