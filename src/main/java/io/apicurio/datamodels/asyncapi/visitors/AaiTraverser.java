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
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.models.AaiHeaderItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBase;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitExtendedItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitItems;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBase;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitExtendedItem;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitItems;
import io.apicurio.datamodels.asyncapi.models.AaiParameter;
import io.apicurio.datamodels.asyncapi.models.AaiProtocolInfo;
import io.apicurio.datamodels.asyncapi.models.AaiSecurityScheme;
import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.asyncapi.models.AaiServerVariable;
import io.apicurio.datamodels.asyncapi.models.AaiTraitItem;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlows;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.PasswordOAuthFlow;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.visitors.Traverser;

/**
 * An AsyncAPI traverser implementation.
 *
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class AaiTraverser extends Traverser implements IAaiVisitor {

    public AaiTraverser(IAaiVisitor visitor) {
        super(visitor);
    }

    @Override
    protected void traverseDocument(Document node) {
        super.traverseDocument(node);
        AaiDocument aaiNode = (AaiDocument) node;

        this.traverseCollection(aaiNode.getChannels());
        this.traverseCollection(aaiNode.servers);
        this.traverseIfNotNull(aaiNode.components);
    }

    @Override
    public void visitAuthorizationCodeOAuthFlow(AuthorizationCodeOAuthFlow node) {
        this.traverseOAuthFlow(node);
    }

    @Override
    public void visitChannelItem(AaiChannelItem node) {
        node.accept(visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);

        this.traverseIfNotNull(node.subscribe);
        this.traverseIfNotNull(node.publish);
        this.traverseCollection(node.parameters);
        this.traverseCollection(node.getProtocolInfoList());
    }

    @Override
    public void visitClientCredentialsOAuthFlow(ClientCredentialsOAuthFlow node) {
        this.traverseOAuthFlow(node);
    }

    @Override
    public void visitComponents(AaiComponents node) {
        node.accept(visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);

        this.traverseCollection(node.getMessagesList());
        this.traverseCollection(node.getSecuritySchemesList());
        this.traverseCollection(node.getParametersList());
        this.traverseCollection(node.getCorrelationIdsList());
        this.traverseCollection(node.getTraitsList());
    }

    @Override
    public void visitCorrelationId(AaiCorrelationId node) {
        node.accept(visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    @Override
    public void visitHeaderItem(AaiHeaderItem node) {
        node.accept(visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    @Override
    public void visitImplicitOAuthFlow(ImplicitOAuthFlow node) {
        this.traverseOAuthFlow(node);
    }

    @Override
    public void visitMessage(AaiMessage node) {
        this.traverseMessageBase(node);

        this.traverseCollection(node.oneOf);
        this.traverseIfNotNull(node.traits);
    }

    @Override
    public void visitMessageTraitExtendedItem(AaiMessageTraitExtendedItem node) {
        node.accept(visitor);
        this.traverseValidationProblems(node);

        this.traverseIfNotNull(node._trait);
    }

    @Override
    public void visitMessageTraitItems(AaiMessageTraitItems node) {
        node.accept(visitor);
        this.traverseValidationProblems(node);

        this.traverseCollection(node._traitItems);
        this.traverseCollection(node._traitExtendedItems);
    }

    @Override
    public void visitMessageTrait(AaiMessageTrait node) {
        this.traverseMessageBase(node);
    }

    @Override
    public void visitOAuthFlows(OAuthFlows node) {
        node.accept(visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);

        this.traverseIfNotNull(node.implicit);
        this.traverseIfNotNull(node.password);
        this.traverseIfNotNull(node.clientCredentials);
        this.traverseIfNotNull(node.authorizationCode);
    }

    @Override
    public void visitOperation(Operation node) {
        node.accept(visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);

        AaiOperation aaiNode = (AaiOperation) node;
        this.traverseOperationBase(aaiNode);
        this.traverseIfNotNull(aaiNode.traits);
        this.traverseIfNotNull(aaiNode.message);
    }

    @Override
    public void visitOperationTraitExtendedItem(AaiOperationTraitExtendedItem node) {
        node.accept(visitor);
        this.traverseValidationProblems(node);

        this.traverseIfNotNull(node._operationTrait);
    }

    @Override
    public void visitOperationTraitItems(AaiOperationTraitItems node) {
        node.accept(visitor);
        this.traverseValidationProblems(node);

        this.traverseCollection(node._traitItems);
        this.traverseCollection(node._traitExtendedItems);
    }

    @Override
    public void visitOperationTrait(AaiOperationTrait node) {
        this.traverseOperationBase(node);
    }

    @Override
    public void visitPasswordOAuthFlow(PasswordOAuthFlow node) {
        this.traverseOAuthFlow(node);
    }

    @Override
    public void visitProtocolInfo(AaiProtocolInfo node) {
        node.accept(visitor);
        this.traverseValidationProblems(node);
    }

    @Override
    public void visitServer(AaiServer node) {
        node.accept(visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);

        this.traverseCollection(node.getServerVariables());
        this.traverseCollection(node.security);
    }

    @Override
    public void visitServerVariable(AaiServerVariable node) {
        node.accept(visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    @Override
    public void visitTraitItem(AaiTraitItem node) {
        node.accept(visitor);
        this.traverseValidationProblems(node);

        this.traverseIfNotNull(node._messageTrait);
        this.traverseIfNotNull(node._operationTrait);
    }

    @Override
    public void visitAaiParameter(AaiParameter node) {
        node.accept(visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }

    protected void traverseMessageBase(AaiMessageBase node) {
        node.accept(visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);

        this.traverseCollection(node.getHeadersList());
        this.traverseIfNotNull(node.correlationId);
        this.traverseCollection(node.tags);
        this.traverseIfNotNull(node.externalDocs);
        this.traverseCollection(node.getProtocolInfoList());
    }

    protected void traverseOperationBase(AaiOperationBase node) {
        node.accept(visitor);
        this.traverseOperation(node);

        this.traverseCollection(node.tags);
        this.traverseCollection(node.getProtocolInfoList());
    }

    @Override
    protected void traverseSecurityScheme(SecurityScheme node) {
        AaiSecurityScheme aaiNode = (AaiSecurityScheme) node;
        this.traverseIfNotNull(aaiNode.flows);
    }
}
