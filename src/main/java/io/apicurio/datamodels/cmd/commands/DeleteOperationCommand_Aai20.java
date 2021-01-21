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
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20NodeFactory;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;

/**
 * A command used to delete an operation to a Channel.
 * @author laurent.broudoux@gmail.com
 */
public class DeleteOperationCommand_Aai20 extends DeleteNodeCommand<AaiOperation> {

    DeleteOperationCommand_Aai20() {
    }

    DeleteOperationCommand_Aai20(String opType, Node channelItem) {
        super(opType, channelItem);
    }
    
    /**
     * @see DeleteNodeCommand#readNode(Document, Object)
     */
    @Override
    protected AaiOperation readNode(Document doc, Object node) {
        AaiChannelItem channelItem = (AaiChannelItem) this._parentPath.resolve(doc);
        Aai20NodeFactory nodeFactory = new Aai20NodeFactory();
        AaiOperation operation = nodeFactory.createOperation(channelItem, this._property);
        Library.readNode(node, operation);
        return operation;
    }
    
}
