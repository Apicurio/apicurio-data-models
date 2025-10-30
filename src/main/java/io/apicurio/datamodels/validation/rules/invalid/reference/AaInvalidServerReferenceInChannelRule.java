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

package io.apicurio.datamodels.validation.rules.invalid.reference;

import java.util.List;

import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelItem;
import io.apicurio.datamodels.models.asyncapi.v20.AsyncApi20ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v22.AsyncApi22ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v23.AsyncApi23ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v24.AsyncApi24ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v25.AsyncApi25ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v26.AsyncApi26ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Channel;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Reference;
import io.apicurio.datamodels.refs.ReferenceUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Rule: CHAN-004
 * Validates that server references in channel.servers point to valid servers defined in the document.
 * Applies to AsyncAPI 2.2-2.6 (where servers are string names) and 3.0 (where servers are Reference objects).
 *
 * @author eric.wittmann@gmail.com
 */
public class AaInvalidServerReferenceInChannelRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaInvalidServerReferenceInChannelRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public void visitChannelItem(AsyncApiChannelItem node) {
        if (node instanceof AsyncApi22ChannelItem) {
            AsyncApi22ChannelItem channelItem = (AsyncApi22ChannelItem) node;
            validateServerNames(channelItem.getServers(), node);
        } else if (node instanceof AsyncApi23ChannelItem) {
            AsyncApi23ChannelItem channelItem = (AsyncApi23ChannelItem) node;
            validateServerNames(channelItem.getServers(), node);
        } else if (node instanceof AsyncApi24ChannelItem) {
            AsyncApi24ChannelItem channelItem = (AsyncApi24ChannelItem) node;
            validateServerNames(channelItem.getServers(), node);
        } else if (node instanceof AsyncApi25ChannelItem) {
            AsyncApi25ChannelItem channelItem = (AsyncApi25ChannelItem) node;
            validateServerNames(channelItem.getServers(), node);
        } else if (node instanceof AsyncApi26ChannelItem) {
            AsyncApi26ChannelItem channelItem = (AsyncApi26ChannelItem) node;
            validateServerNames(channelItem.getServers(), node);
        }
    }

    @Override
    public void visitChannel(AsyncApi30Channel node) {
        List<AsyncApi30Reference> servers = node.getServers();
        if (servers != null) {
            for (AsyncApi30Reference serverRef : servers) {
                String ref = serverRef.get$ref();
                if (hasValue(ref)) {
                    this.reportIfInvalid(ReferenceUtil.canResolveRef(ref, node), node, "servers",
                        map("server", ref));
                }
            }
        }
    }

    /**
     * Validates server names in AsyncAPI 2.2-2.6 channels.
     * In these versions, servers is a list of server names (strings) that should exist in document.servers.
     */
    private void validateServerNames(List<String> serverNames, io.apicurio.datamodels.models.Node node) {
        if (serverNames != null) {
            for (String serverName : serverNames) {
                if (hasValue(serverName)) {
                    // For AsyncAPI 2.x, server names should reference servers defined in document.servers
                    // This is validated by checking if the server name exists in the document
                    String ref = "#/servers/" + serverName;
                    this.reportIfInvalid(ReferenceUtil.canResolveRef(ref, node), node, "servers",
                        map("server", serverName));
                }
            }
        }
    }

}
