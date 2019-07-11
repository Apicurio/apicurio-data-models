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
import io.apicurio.datamodels.asyncapi.models.AaiUnknownTrait;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.Components;
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
        this.path.prependSegment(node.getName(), true);
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
    public void visitMessageTraitExtendedItem(AaiMessageTraitExtendedItem node) {
        int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_TRAIT_EXTENDED_ITEMS);
        if (idx != -1) {
            this.path.prependSegment(String.valueOf(idx), true);
            this.path.prependSegment(Constants.PROP_TRAIT_EXTENDED_ITEMS, false);
        }
    }

    @Override
    public void visitMessageTraitItems(AaiMessageTraitItems node) {
        this.path.prependSegment(Constants.PROP_TRAITS, false);
    }

    @Override
    public void visitMessageTrait(AaiMessageTrait node) {
        if (node.parent() instanceof AaiMessageTraitItems) {
            int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_TRAIT_ITEMS);
            if (idx != -1) {
                this.path.prependSegment(String.valueOf(idx), true);
                this.path.prependSegment(Constants.PROP_TRAIT_ITEMS, false);
            }
        } else if (node.parent() instanceof AaiMessageTraitExtendedItem) {
            this.path.prependSegment(Constants.PROP__TRAIT, false);
        } else if (node.parent() instanceof AaiComponents) {
            this.path.prependSegment(node.getName(), true);
            this.path.prependSegment(Constants.PROP_TRAITS, false);
        }
    }

    @Override
    public void visitOAuthFlows(OAuthFlows node) {
        this.path.prependSegment(Constants.PROP_FLOWS, false);
    }

    @Override
    public void visitOperationTraitExtendedItem(AaiOperationTraitExtendedItem node) {
        if(node.getName() != null) {
            this.path.prependSegment(node.getName(), true);
            this.path.prependSegment(Constants.PROP_TRAIT_EXTENDED_ITEMS, false);
        } else {
            int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_TRAIT_EXTENDED_ITEMS);
            if (idx != -1) {
                this.path.prependSegment(String.valueOf(idx), true);
                this.path.prependSegment(Constants.PROP_TRAIT_EXTENDED_ITEMS, false);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    @Override
    public void visitOperationTraitItems(AaiOperationTraitItems node) {
        this.path.prependSegment(Constants.PROP_TRAITS, false);
    }

    @Override
    public void visitOperationTrait(AaiOperationTrait node) {
        if (node.parent() instanceof AaiOperationTraitItems) {
            int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_TRAIT_ITEMS);
            if (idx != -1) {
                this.path.prependSegment(String.valueOf(idx), true);
                this.path.prependSegment(Constants.PROP_TRAIT_ITEMS, false);
            }
        } else if (node.parent() instanceof AaiOperationTraitExtendedItem) {
            this.path.prependSegment(Constants.PROP__OPERATION_TRAIT, false);
        } else if (node.parent() instanceof AaiComponents) {
            this.path.prependSegment(node.getType(), true);
            this.path.prependSegment(Constants.PROP_TRAITS, false);
        }
    }

    @Override
    public void visitPasswordOAuthFlow(PasswordOAuthFlow node) {
        this.path.prependSegment(Constants.PROP_PASSWORD, false);
    }

    @Override
    public void visitProtocolInfo(AaiProtocolInfo node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_PROTOCOL_INFO, false);
    }

    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        this.path.prependSegment(node.getSchemeName(), true);
        this.path.prependSegment(Constants.PROP_SECURITY_SCHEMES, false);
    }

    @Override
    public void visitServer(Server node) {
        int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_SERVERS);
        if (idx != -1) {
            this.path.prependSegment(String.valueOf(idx), true);
            this.path.prependSegment(Constants.PROP_SERVERS, false);
        }
    }

    @Override
    public void visitServerVariable(ServerVariable node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_VARIABLES, false);
    }

    @Override
    public void visitUnknownTrait(AaiUnknownTrait node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_TRAITS, false);
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
}
