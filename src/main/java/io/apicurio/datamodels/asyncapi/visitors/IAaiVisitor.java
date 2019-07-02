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
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
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
import io.apicurio.datamodels.core.visitors.IVisitor;

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

    void visitComponents(AaiComponents node);

    void visitCorrelationId(AaiCorrelationId node);

    void visitHeaderItem(AaiHeaderItem node);

    void visitImplicitOAuthFlow(ImplicitOAuthFlow node);

    void visitMessage(AaiMessage node);

    void visitMessageTraitExtendedItem(AaiMessageTraitExtendedItem node);

    void visitMessageTraitItems(AaiMessageTraitItems node);

    void visitMessageTrait(AaiMessageTrait node);

    void visitOAuthFlows(OAuthFlows node);

    void visitOperationTraitExtendedItem(AaiOperationTraitExtendedItem node);

    void visitOperationTraitItems(AaiOperationTraitItems node);

    void visitOperationTrait(AaiOperationTrait node);

    void visitPasswordOAuthFlow(PasswordOAuthFlow node);

    void visitProtocolInfo(AaiProtocolInfo node);

    void visitServer(AaiServer node);

    void visitServerVariable(AaiServerVariable node);

    void visitTraitItem(AaiTraitItem node);

    void visitAaiParameter(AaiParameter aaiParameter);
}
