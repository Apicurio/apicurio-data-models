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

import java.util.List;

import io.apicurio.datamodels.asyncapi.models.AaiChannelBindings;
import io.apicurio.datamodels.asyncapi.models.AaiChannelBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiComponents;
import io.apicurio.datamodels.asyncapi.models.AaiCorrelationId;
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.models.AaiHeaderItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBase;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBindings;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBase;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBindings;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiParameter;
import io.apicurio.datamodels.asyncapi.models.AaiSchema;
import io.apicurio.datamodels.asyncapi.models.AaiSecurityScheme;
import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindings;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindingsDefinition;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlows;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.PasswordOAuthFlow;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
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
        this.traverseCollection(aaiNode.getServers());
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
        this.traverseCollection(node.getParameterlist());
        this.traverseIfNotNull(node.bindings);
    }

    @Override
    public void visitClientCredentialsOAuthFlow(ClientCredentialsOAuthFlow node) {
        this.traverseOAuthFlow(node);
    }

    @Override
    public void visitComponents(Components node) {
        AaiComponents components = (AaiComponents) node;
        
        node.accept(visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);

        this.traverseCollection(components.getMessagesList());
        this.traverseCollection(components.getSecuritySchemesList());
        this.traverseCollection(components.getParametersList());
        this.traverseCollection(components.getCorrelationIdsList());
        this.traverseCollection(components.getMessageTraitDefinitionsList());
        this.traverseCollection(components.getOperationTraitDefinitionsList());
        this.traverseCollection(components.getServerBindingsDefinitionList());
        this.traverseCollection(components.getChannelBindingsDefinitionList());
        this.traverseCollection(components.getOperationBindingsDefinitionList());
        this.traverseCollection(components.getMessageBindingsDefinitionList());
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
        this.traverseCollection(node.traits);
    }


    @Override
    public void visitMessageTrait(AaiMessageTrait node) {
        this.traverseMessageBase(node);
    }
    
    @Override
    public void visitMessageTraitDefinition(AaiMessageTraitDefinition node) {
        this.visitMessageTrait(node);
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
        this.traverseCollection(aaiNode.traits);
        this.traverseIfNotNull(aaiNode.message);
    }

    @Override
    public void visitOperationTrait(AaiOperationTrait node) {
        this.traverseOperationBase(node);
    }
    
    @Override
    public void visitOperationTraitDefinition(AaiOperationTraitDefinition node) {
        this.visitOperationTrait(node);
    }

    @Override
    public void visitPasswordOAuthFlow(PasswordOAuthFlow node) {
        this.traverseOAuthFlow(node);
    }

    @Override
    public void visitServer(Server node) {
        node.accept(visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);

        this.traverseCollection(node.getServerVariables());
        this.traverseCollection(((AaiServer) node).security);
        this.traverseIfNotNull(((AaiServer) node).bindings);
    }

    @Override
    public void visitServerVariable(ServerVariable node) {
        node.accept(visitor);
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
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

        this.traverseIfNotNull(node.headers);
        this.traverseIfNotNull(node.correlationId);
        this.traverseCollection(node.tags);
        this.traverseIfNotNull(node.externalDocs);
        this.traverseIfNotNull(node.bindings);
    }

    protected void traverseOperationBase(AaiOperationBase node) {
        node.accept(visitor);
        this.traverseOperation(node);

        this.traverseCollection(node.tags);
        this.traverseIfNotNull(node.bindings);
    }

    @Override
    protected void traverseSecurityScheme(SecurityScheme node) {
        super.traverseSecurityScheme(node);
        AaiSecurityScheme aaiNode = (AaiSecurityScheme) node;
        this.traverseIfNotNull(aaiNode.flows);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitServerBindings(io.apicurio.datamodels.asyncapi.models.AaiServerBindings)
     */
    @Override
    public void visitServerBindings(AaiServerBindings node) {
        node.accept(visitor);
        this.traverseValidationProblems(node);
        
//        this.traverseIfNotNull(node.http);
//        this.traverseIfNotNull(node.ws);
//        this.traverseIfNotNull(node.kafka);
//        this.traverseIfNotNull(node.amqp);
//        this.traverseIfNotNull(node.amqp1);
//        this.traverseIfNotNull(node.mqtt);
//        this.traverseIfNotNull(node.mqtt5);
//        this.traverseIfNotNull(node.nats);
//        this.traverseIfNotNull(node.jms);
//        this.traverseIfNotNull(node.sns);
//        this.traverseIfNotNull(node.sqs);
//        this.traverseIfNotNull(node.stomp);
//        this.traverseIfNotNull(node.redis);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitServerBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiServerBindingsDefinition)
     */
    @Override
    public void visitServerBindingsDefinition(AaiServerBindingsDefinition node) {
        this.visitServerBindings(node);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitOperationBindings(io.apicurio.datamodels.asyncapi.models.AaiOperationBindings)
     */
    @Override
    public void visitOperationBindings(AaiOperationBindings node) {
        node.accept(visitor);
        this.traverseValidationProblems(node);
        
//        this.traverseIfNotNull(node.http);
//        this.traverseIfNotNull(node.ws);
//        this.traverseIfNotNull(node.kafka);
//        this.traverseIfNotNull(node.amqp);
//        this.traverseIfNotNull(node.amqp1);
//        this.traverseIfNotNull(node.mqtt);
//        this.traverseIfNotNull(node.mqtt5);
//        this.traverseIfNotNull(node.nats);
//        this.traverseIfNotNull(node.jms);
//        this.traverseIfNotNull(node.sns);
//        this.traverseIfNotNull(node.sqs);
//        this.traverseIfNotNull(node.stomp);
//        this.traverseIfNotNull(node.redis);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitOperationBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiOperationBindingsDefinition)
     */
    @Override
    public void visitOperationBindingsDefinition(AaiOperationBindingsDefinition node) {
        this.visitOperationBindings(node);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitMessageBindings(io.apicurio.datamodels.asyncapi.models.AaiMessageBindings)
     */
    @Override
    public void visitMessageBindings(AaiMessageBindings node) {
        node.accept(visitor);
        this.traverseValidationProblems(node);
        
//        this.traverseIfNotNull(node.http);
//        this.traverseIfNotNull(node.ws);
//        this.traverseIfNotNull(node.kafka);
//        this.traverseIfNotNull(node.amqp);
//        this.traverseIfNotNull(node.amqp1);
//        this.traverseIfNotNull(node.mqtt);
//        this.traverseIfNotNull(node.mqtt5);
//        this.traverseIfNotNull(node.nats);
//        this.traverseIfNotNull(node.jms);
//        this.traverseIfNotNull(node.sns);
//        this.traverseIfNotNull(node.sqs);
//        this.traverseIfNotNull(node.stomp);
//        this.traverseIfNotNull(node.redis);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitMessageBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiMessageBindingsDefinition)
     */
    @Override
    public void visitMessageBindingsDefinition(AaiMessageBindingsDefinition node) {
        this.visitMessageBindings(node);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitChannelBindings(io.apicurio.datamodels.asyncapi.models.AaiChannelBindings)
     */
    @Override
    public void visitChannelBindings(AaiChannelBindings node) {
        node.accept(visitor);
        this.traverseValidationProblems(node);
        
//        this.traverseIfNotNull(node.http);
//        this.traverseIfNotNull(node.ws);
//        this.traverseIfNotNull(node.kafka);
//        this.traverseIfNotNull(node.amqp);
//        this.traverseIfNotNull(node.amqp1);
//        this.traverseIfNotNull(node.mqtt);
//        this.traverseIfNotNull(node.mqtt5);
//        this.traverseIfNotNull(node.nats);
//        this.traverseIfNotNull(node.jms);
//        this.traverseIfNotNull(node.sns);
//        this.traverseIfNotNull(node.sqs);
//        this.traverseIfNotNull(node.stomp);
//        this.traverseIfNotNull(node.redis);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitChannelBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiChannelBindingsDefinition)
     */
    @Override
    public void visitChannelBindingsDefinition(AaiChannelBindingsDefinition node) {
        this.visitChannelBindings(node);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitAllOfSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitAllOfSchema(AaiSchema node) {
        this.visitSchema(node);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitOneOfSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitOneOfSchema(AaiSchema node) {
        this.visitSchema(node);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitAnyOfSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitAnyOfSchema(AaiSchema node) {
        this.visitSchema(node);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitNotSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitNotSchema(AaiSchema node) {
        this.visitSchema(node);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitPropertySchema(io.apicurio.datamodels.asyncapi.models.IAaiPropertySchema)
     */
    @Override
    public void visitPropertySchema(IPropertySchema node) {
        this.visitSchema((Schema) node);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitItemsSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitItemsSchema(AaiSchema node) {
        this.visitSchema(node);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitAdditionalPropertiesSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitAdditionalPropertiesSchema(AaiSchema node) {
        this.visitSchema(node);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.Traverser#traverseSchema(io.apicurio.datamodels.core.models.common.Schema)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void traverseSchema(Schema node) {
        super.traverseSchema(node);
        AaiSchema schema = (AaiSchema) node;
        if (NodeCompat.isList(schema.items)) {
            this.traverseCollection((List<Node>) schema.items);
        } else {
            this.traverseIfNotNull((Node) schema.items);
        }
        this.traverseCollection(schema.allOf);
        this.traverseCollection(schema.oneOf);
        this.traverseCollection(schema.anyOf);
        this.traverseIfNotNull(schema.not);
        this.traverseCollection(schema.getProperties());
        if (NodeCompat.isNode(schema.additionalProperties)) {
            this.traverseIfNotNull((Node) schema.additionalProperties);
        }
        this.traverseIfNotNull(schema.externalDocs);
    }
}
