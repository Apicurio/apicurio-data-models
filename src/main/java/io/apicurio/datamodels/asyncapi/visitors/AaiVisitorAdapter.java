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
import io.apicurio.datamodels.asyncapi.models.AaiServerBindings;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindingsDefinition;
import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlows;
import io.apicurio.datamodels.core.models.common.PasswordOAuthFlow;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
import io.apicurio.datamodels.core.visitors.VisitorAdapter;

/**
 * Visitor adapter for AsyncAPI visitors.
 *
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class AaiVisitorAdapter extends VisitorAdapter implements IAaiVisitor {

    @Override
    public void visitAuthorizationCodeOAuthFlow(AuthorizationCodeOAuthFlow node) {
        // NOOP
    }

    @Override
    public void visitChannelItem(AaiChannelItem node) {
        // NOOP
    }

    @Override
    public void visitClientCredentialsOAuthFlow(ClientCredentialsOAuthFlow node) {
        // NOOP
    }

    @Override
    public void visitComponents(Components node) {
        // NOOP
    }

    @Override
    public void visitCorrelationId(AaiCorrelationId node) {
        // NOOP
    }

    @Override
    public void visitHeaderItem(AaiHeaderItem node) {
        // NOOP
    }

    @Override
    public void visitImplicitOAuthFlow(ImplicitOAuthFlow node) {
        // NOOP
    }

    @Override
    public void visitMessage(AaiMessage node) {
        // NOOP
    }

    @Override
    public void visitMessageTrait(AaiMessageTrait node) {
        // NOOP
    }

    @Override
    public void visitOAuthFlows(OAuthFlows node) {
        // NOOP
    }

    @Override
    public void visitOperationTrait(AaiOperationTrait node) {
        // NOOP
    }

    @Override
    public void visitPasswordOAuthFlow(PasswordOAuthFlow node) {
        // NOOP
    }

    @Override
    public void visitServer(Server node) {
        // NOOP
    }

    @Override
    public void visitServerVariable(ServerVariable node) {
        // NOOP
    }

    @Override
    public void visitAaiParameter(AaiParameter node) {
        // NOOP
    }

    @Override
    public void visitServerBindings(AaiServerBindings node) {
        // NOOP
    }

    @Override
    public void visitServerBindingsDefinition(AaiServerBindingsDefinition node) {
        // NOOP
    }

    @Override
    public void visitOperationBindings(AaiOperationBindings node) {
        // NOOP
    }

    @Override
    public void visitOperationBindingsDefinition(AaiOperationBindingsDefinition node) {
        // NOOP
    }

    @Override
    public void visitMessageBindings(AaiMessageBindings node) {
        // NOOP
    }

    @Override
    public void visitMessageBindingsDefinition(AaiMessageBindingsDefinition node) {
        // NOOP
    }

    @Override
    public void visitChannelBindings(AaiChannelBindings node) {
        // NOOP
    }

    @Override
    public void visitChannelBindingsDefinition(AaiChannelBindingsDefinition node) {
        // NOOP
    }

    @Override
    public void visitAllOfSchema(Schema node) {
        // NOOP
    }

    @Override
    public void visitOneOfSchema(Schema node) {
        // NOOP
    }

    @Override
    public void visitAnyOfSchema(Schema node) {
        // NOOP
    }

    @Override
    public void visitNotSchema(Schema node) {
        // NOOP
    }

    @Override
    public void visitPropertySchema(IPropertySchema node) {
        // NOOP
    }

    @Override
    public void visitItemsSchema(Schema node) {
        // NOOP
    }

    @Override
    public void visitAdditionalPropertiesSchema(Schema node) {
        // NOOP
    }

    @Override
    public void visitMessageTraitDefinition(AaiMessageTraitDefinition node) {
        // NOOP
    }

    @Override
    public void visitOperationTraitDefinition(AaiOperationTraitDefinition node) {
        // NOOP
    }
}
