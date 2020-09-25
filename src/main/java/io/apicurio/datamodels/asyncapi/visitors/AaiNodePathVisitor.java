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
import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindings;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindingsDefinition;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.core.models.common.ISchemaDefinition;
import io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlows;
import io.apicurio.datamodels.core.models.common.PasswordOAuthFlow;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
import io.apicurio.datamodels.core.visitors.NodePathVisitor;

/**
 * A node path visitor for AsyncAPI.
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class AaiNodePathVisitor extends NodePathVisitor implements IAaiVisitor {

    public AaiNodePathVisitor() {
    }

    @Override
    public void visitAuthorizationCodeOAuthFlow(AuthorizationCodeOAuthFlow node) {
        this.path.prependSegment(Constants.PROP_AUTHORIZATION_CODE, false);
    }

    @Override
    public void visitChannelItem(AaiChannelItem node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_CHANNELS, false);
    }

    @Override
    public void visitClientCredentialsOAuthFlow(ClientCredentialsOAuthFlow node) {
        this.path.prependSegment(Constants.PROP_CLIENT_CREDENTIALS, false);
    }

    @Override
    public void visitComponents(Components node) {
        this.path.prependSegment(Constants.PROP_COMPONENTS, false);
    }

    @Override
    public void visitSchemaDefinition(ISchemaDefinition node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_SCHEMAS, false);
    }

    @Override
    public void visitCorrelationId(AaiCorrelationId node) {
        if(node.getName() != null) {
            this.path.prependSegment(node.getName(), true);
            this.path.prependSegment(Constants.PROP_CORRELATION_IDS, false);
        } else {
            this.path.prependSegment(Constants.PROP_CORRELATION_ID, false);
        }
    }

    @Override
    public void visitHeaderItem(AaiHeaderItem node) {
        this.path.prependSegment(Constants.PROP_HEADERS, false);
    }

    @Override
    public void visitImplicitOAuthFlow(ImplicitOAuthFlow node) {
        this.path.prependSegment(Constants.PROP_IMPLICIT, false);
    }

    @Override
    public void visitMessage(AaiMessage node) {
        if(node.getName() != null) {
            this.path.prependSegment(node.getName(), true);
            this.path.prependSegment(Constants.PROP_MESSAGES, false);
        } else {
            if(!node._isOneOfMessage) {
                this.path.prependSegment(Constants.PROP_MESSAGE, false);
            } else {
                int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_ONE_OF);
                if (idx != -1) {
                    this.path.prependSegment(String.valueOf(idx), true);
                    this.path.prependSegment(Constants.PROP_ONE_OF, false);
                } else {
                    throw new IllegalStateException("oneOf message without an index");
                }
            }
        }
    }

    @Override
    public void visitMessageTrait(AaiMessageTrait node) {
        int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_TRAITS);
        if (idx != -1) {
            this.path.prependSegment(String.valueOf(idx), true);
            this.path.prependSegment(Constants.PROP_TRAITS, false);
        } else {
            throw new IllegalStateException("message trait without an index");
        }
    }
    
    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitMessageTraitDefinition(io.apicurio.datamodels.asyncapi.models.AaiMessageTraitDefinition)
     */
    @Override
    public void visitMessageTraitDefinition(AaiMessageTraitDefinition node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_MESSAGE_TRAITS, false);
    }

    @Override
    public void visitOAuthFlows(OAuthFlows node) {
        this.path.prependSegment(Constants.PROP_FLOWS, false);
    }

    @Override
    public void visitOperationTrait(AaiOperationTrait node) {
        int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_TRAITS);
        if (idx != -1) {
            this.path.prependSegment(String.valueOf(idx), true);
            this.path.prependSegment(Constants.PROP_TRAITS, false);
        } else {
            throw new IllegalStateException("operation trait without an index");
        }
    }
    
    @Override
    public void visitOperationTraitDefinition(AaiOperationTraitDefinition node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_OPERATION_TRAITS, false);
    }

    @Override
    public void visitPasswordOAuthFlow(PasswordOAuthFlow node) {
        this.path.prependSegment(Constants.PROP_PASSWORD, false);
    }

    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        this.path.prependSegment(node.getSchemeName(), true);
        this.path.prependSegment(Constants.PROP_SECURITY_SCHEMES, false);
    }

    @Override
    public void visitServer(Server node) {
        this.path.prependSegment(((AaiServer) node).getName(), true);
        this.path.prependSegment(Constants.PROP_SERVERS, false);
    }

    @Override
    public void visitServerVariable(ServerVariable node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_VARIABLES, false);
    }

    @Override
    public void visitAaiParameter(AaiParameter node) {
        if(node.getName() != null) {
            this.path.prependSegment(node.getName(), true);
            this.path.prependSegment(Constants.PROP_PARAMETERS, false);
        } else {
            int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_PARAMETERS);
            if (idx != -1) {
                this.path.prependSegment(String.valueOf(idx), true);
                this.path.prependSegment(Constants.PROP_PARAMETERS, false);
            }
        }
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitServerBindings(io.apicurio.datamodels.asyncapi.models.AaiServerBindings)
     */
    @Override
    public void visitServerBindings(AaiServerBindings node) {
        this.path.prependSegment(Constants.PROP_BINDINGS, false);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitServerBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiServerBindingsDefinition)
     */
    @Override
    public void visitServerBindingsDefinition(AaiServerBindingsDefinition node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_SERVER_BINDINGS, false);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitOperationBindings(io.apicurio.datamodels.asyncapi.models.AaiOperationBindings)
     */
    @Override
    public void visitOperationBindings(AaiOperationBindings node) {
        this.path.prependSegment(Constants.PROP_BINDINGS, false);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitOperationBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiOperationBindingsDefinition)
     */
    @Override
    public void visitOperationBindingsDefinition(AaiOperationBindingsDefinition node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_OPERATION_BINDINGS, false);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitMessageBindings(io.apicurio.datamodels.asyncapi.models.AaiMessageBindings)
     */
    @Override
    public void visitMessageBindings(AaiMessageBindings node) {
        this.path.prependSegment(Constants.PROP_BINDINGS, false);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitMessageBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiMessageBindingsDefinition)
     */
    @Override
    public void visitMessageBindingsDefinition(AaiMessageBindingsDefinition node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_MESSAGE_BINDINGS, false);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitChannelBindings(io.apicurio.datamodels.asyncapi.models.AaiChannelBindings)
     */
    @Override
    public void visitChannelBindings(AaiChannelBindings node) {
        this.path.prependSegment(Constants.PROP_BINDINGS, false);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitChannelBindingsDefinition(io.apicurio.datamodels.asyncapi.models.AaiChannelBindingsDefinition)
     */
    @Override
    public void visitChannelBindingsDefinition(AaiChannelBindingsDefinition node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_CHANNEL_BINDINGS, false);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitAllOfSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitAllOfSchema(AaiSchema node) {
        int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_ALL_OF);
        if (idx != -1) {
            this.path.prependSegment(String.valueOf(idx), true);
            this.path.prependSegment(Constants.PROP_ALL_OF, false);
        }
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitOneOfSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitOneOfSchema(AaiSchema node) {
        int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_ONE_OF);
        if (idx != -1) {
            this.path.prependSegment(String.valueOf(idx), true);
            this.path.prependSegment(Constants.PROP_ONE_OF, false);
        }
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitAnyOfSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitAnyOfSchema(AaiSchema node) {
        int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_ANY_OF);
        if (idx != -1) {
            this.path.prependSegment(String.valueOf(idx), true);
            this.path.prependSegment(Constants.PROP_ANY_OF, false);
        }
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitNotSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitNotSchema(AaiSchema node) {
        this.path.prependSegment(Constants.PROP_NOT, false);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitPropertySchema(io.apicurio.datamodels.asyncapi.models.IAaiPropertySchema)
     */
    @Override
    public void visitPropertySchema(IPropertySchema node) {
        this.path.prependSegment(node.getPropertyName(), true);
        this.path.prependSegment(Constants.PROP_PROPERTIES, false);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitItemsSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitItemsSchema(AaiSchema node) {
        if (node.hasItemsSchemas()) {
            int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_ITEMS);
            if (idx != -1) {
                this.path.prependSegment(String.valueOf(idx), true);
                this.path.prependSegment(Constants.PROP_ITEMS, false);
            }
        } else {
            this.path.prependSegment(Constants.PROP_ITEMS, false);
        }
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitAdditionalPropertiesSchema(io.apicurio.datamodels.asyncapi.models.AaiSchema)
     */
    @Override
    public void visitAdditionalPropertiesSchema(AaiSchema node) {
        this.path.prependSegment(Constants.PROP_ADDITIONAL_PROPERTIES, false);
    }
}
