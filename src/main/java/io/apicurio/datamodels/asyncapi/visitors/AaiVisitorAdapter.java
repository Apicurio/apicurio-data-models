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

import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiComponents;
import io.apicurio.datamodels.asyncapi.models.AaiCorrelationId;
import io.apicurio.datamodels.asyncapi.models.AaiHeaderItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitExtendedItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitItems;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitExtendedItem;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitItems;
import io.apicurio.datamodels.asyncapi.models.AaiParameter;
import io.apicurio.datamodels.asyncapi.models.AaiProtocolInfo;
import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.asyncapi.models.AaiServerVariable;
import io.apicurio.datamodels.asyncapi.models.AaiTraitItem;
import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlows;
import io.apicurio.datamodels.core.models.common.PasswordOAuthFlow;
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
    public void visitComponents(AaiComponents node) {
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
    public void visitMessageTraitExtendedItem(AaiMessageTraitExtendedItem node) {
        // NOOP
    }

    @Override
    public void visitMessageTraitItems(AaiMessageTraitItems node) {
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
    public void visitOperationTraitExtendedItem(AaiOperationTraitExtendedItem node) {
        // NOOP
    }

    @Override
    public void visitOperationTraitItems(AaiOperationTraitItems node) {
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
    public void visitProtocolInfo(AaiProtocolInfo node) {
        // NOOP
    }

    @Override
    public void visitServer(AaiServer node) {
        // NOOP
    }

    @Override
    public void visitServerVariable(AaiServerVariable node) {
        // NOOP
    }

    @Override
    public void visitTraitItem(AaiTraitItem node) {
        // NOOP
    }

    @Override
    public void visitAaiParameter(AaiParameter node) {
        // NOOP
    }
}
