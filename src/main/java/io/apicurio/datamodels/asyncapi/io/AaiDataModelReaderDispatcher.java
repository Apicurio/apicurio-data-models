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

package io.apicurio.datamodels.asyncapi.io;

import io.apicurio.datamodels.asyncapi.models.*;
import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.core.io.DataModelReaderDispatcher;
import io.apicurio.datamodels.core.models.common.*;

/**
 * Async API implementation of a data model reader dispatcher.
 *
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiDataModelReaderDispatcher extends DataModelReaderDispatcher implements IAaiVisitor {

    private AaiDataModelReader aaiReader;

    /**
     * Constructor.
     */
    public AaiDataModelReaderDispatcher(Object json, AaiDataModelReader reader) {
        super(json, reader);
        aaiReader = reader;
    }

    @Override
    public void visitAuthorizationCodeOAuthFlow(AuthorizationCodeOAuthFlow node) {
        aaiReader.readOAuthFlow(this.json, node);
    }

    @Override
    public void visitChannelItem(AaiChannelItem node) {
        aaiReader.readChannelItem(this.json, node);
    }

    @Override
    public void visitClientCredentialsOAuthFlow(ClientCredentialsOAuthFlow node) {
        aaiReader.readOAuthFlow(this.json, node);
    }

    @Override
    public void visitComponents(Components node) {
        aaiReader.readComponents(this.json, node);
    }

    @Override
    public void visitCorrelationId(AaiCorrelationId node) {
        aaiReader.readCorrelationId(this.json, node);
    }

    @Override
    public void visitHeaderItem(AaiHeaderItem node) {
        aaiReader.readHeaderItem(this.json, node);
    }

    @Override
    public void visitImplicitOAuthFlow(ImplicitOAuthFlow node) {
        aaiReader.readOAuthFlow(this.json, node);
    }

    @Override
    public void visitMessage(AaiMessage node) {
        aaiReader.readMessage(this.json, node);
    }

    @Override
    public void visitMessageTrait(AaiMessageTrait node) {
        aaiReader.readMessageTrait(this.json, node);
    }
    
    @Override
    public void visitMessageTraitDefinition(AaiMessageTraitDefinition node) {
        aaiReader.readMessageTrait(this.json, node);
    }

    @Override
    public void visitOAuthFlows(OAuthFlows node) {
        aaiReader.readOAuthFlows(this.json, node);
    }

    @Override
    public void visitOperationTrait(AaiOperationTrait node) {
        aaiReader.readOperationTrait(this.json, node);
    }
    
    @Override
    public void visitOperationTraitDefinition(AaiOperationTraitDefinition node) {
        aaiReader.readOperationTrait(this.json, node);
    }

    @Override
    public void visitPasswordOAuthFlow(PasswordOAuthFlow node) {
        aaiReader.readOAuthFlow(this.json, node);
    }

    @Override
    public void visitServer(Server node) {
        aaiReader.readServer(this.json, node);
    }

    @Override
    public void visitServerVariable(ServerVariable node) {
        aaiReader.readServerVariable(this.json, node);
    }
    
    @Override
    public void visitAaiParameter(AaiParameter node) {
        aaiReader.readAaiParameter(this.json, node);
    }

    @Override
    public void visitServerBindings(AaiServerBindings node) {
        aaiReader.readServerBindings(this.json, node);
    }

    @Override
    public void visitServerBindingsDefinition(AaiServerBindingsDefinition node) {
        aaiReader.readServerBindings(this.json, node);
    }

    @Override
    public void visitOperationBindings(AaiOperationBindings node) {
        aaiReader.readOperationBindings(this.json, node);
    }

    @Override
    public void visitOperationBindingsDefinition(AaiOperationBindingsDefinition node) {
        aaiReader.readOperationBindings(this.json, node);
    }

    @Override
    public void visitMessageBindings(AaiMessageBindings node) {
        aaiReader.readMessageBindings(this.json, node);
    }

    @Override
    public void visitMessageBindingsDefinition(AaiMessageBindingsDefinition node) {
        aaiReader.readMessageBindings(this.json, node);
    }

    @Override
    public void visitChannelBindings(AaiChannelBindings node) {
        aaiReader.readChannelBindings(this.json, node);
    }

    @Override
    public void visitChannelBindingsDefinition(AaiChannelBindingsDefinition node) {
        aaiReader.readChannelBindings(this.json, node);
    }

    @Override
    public void visitAllOfSchema(AaiSchema node) {
        aaiReader.readSchema(this.json, node);
    }

    @Override
    public void visitOneOfSchema(AaiSchema node) {
        aaiReader.readSchema(this.json, node);
    }

    @Override
    public void visitAnyOfSchema(AaiSchema node) {
        aaiReader.readSchema(this.json, node);
    }

    @Override
    public void visitNotSchema(AaiSchema node) {
        aaiReader.readSchema(this.json, node);
    }

    @Override
    public void visitPropertySchema(IAaiPropertySchema node) {
        aaiReader.readSchema(this.json, (Schema) node);
    }

    @Override
    public void visitItemsSchema(AaiSchema node) {
        aaiReader.readSchema(this.json, node);
    }

    @Override
    public void visitAdditionalPropertiesSchema(AaiSchema node) {
        aaiReader.readSchema(this.json, node);
    }
}
