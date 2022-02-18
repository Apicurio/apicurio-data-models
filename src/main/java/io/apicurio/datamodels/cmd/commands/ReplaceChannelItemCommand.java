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

package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20NodeFactory;
import io.apicurio.datamodels.core.models.Document;

/**
 * A command used to replace a channel item with a newer version.
 * @author c.desc2@gmail.com
 */
public class ReplaceChannelItemCommand extends ReplaceNodeCommand<AaiChannelItem> {

    public String _channelName;

    ReplaceChannelItemCommand() {
    }
    
    ReplaceChannelItemCommand(AaiChannelItem old, AaiChannelItem replacement) {
        super(old, replacement);
        this._channelName = replacement.getName();
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ReplaceNodeCommand#replaceNode(Document, io.apicurio.datamodels.core.models.Node, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    protected void replaceNode(Document doc, AaiChannelItem oldNode,
            AaiChannelItem newNode) {
        AaiDocument aaiDocument = (AaiDocument) doc;

        if (!oldNode.getName().equals(newNode.getName())) {
            // TODO: Can this even happen?
            aaiDocument.channels.remove(oldNode.getName());
        }

        newNode._ownerDocument = aaiDocument;
        newNode._parent = aaiDocument;
        aaiDocument.channels.put(this._channelName, newNode);
    }
    
    /**
     * @see ReplaceNodeCommand#readNode(Document, Object)
     */
    @Override
    protected AaiChannelItem readNode(Document doc, Object node) {
        final AaiDocument aaiDocument = (AaiDocument) doc;
        AaiChannelItem channelItem = new Aai20NodeFactory().createChannelItem(aaiDocument, this._channelName);
        Library.readNode(node, channelItem);
        return channelItem;
    }
}
