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
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitDefinition;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20NodeFactory;
import io.apicurio.datamodels.core.models.Document;

/**
 * A command used to replace an operation trait definition with a newer version.
 */
public class ReplaceOperationTraitDefinitionCommand extends ReplaceNodeCommand<AaiOperationTraitDefinition> {

    public String _operationTraitName;

    ReplaceOperationTraitDefinitionCommand() {
    }
    
    ReplaceOperationTraitDefinitionCommand(AaiOperationTraitDefinition old, AaiOperationTraitDefinition replacement) {
        super(old, replacement);
        this._operationTraitName = replacement.getName();
    }
    
    /**
     * @see ReplaceNodeCommand#replaceNode(Document, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    protected void replaceNode(Document doc, AaiOperationTraitDefinition newNode) {
        AaiDocument aaiDocument = (AaiDocument) doc;
        aaiDocument.components.operationTraits.put(this._operationTraitName, newNode);
    }
    
    /**
     * @see ReplaceNodeCommand#readNode(Document, Object)
     */
    @Override
    protected AaiOperationTraitDefinition readNode(Document doc, Object node) {
        final AaiDocument aaiDocument = (AaiDocument) doc;
        AaiOperationTraitDefinition operation = new Aai20NodeFactory().createOperationTraitDefinition(aaiDocument.components, this._operationTraitName);
        Library.readNode(node, operation);
        return operation;
    }
}
