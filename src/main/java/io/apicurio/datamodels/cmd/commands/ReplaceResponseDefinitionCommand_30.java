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
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30ResponseDefinition;

/**
 * A command used to replace a response definition with a newer version.
 * @author eric.wittmann@gmail.com
 */
public class ReplaceResponseDefinitionCommand_30 extends ReplaceNodeCommand<Oas30ResponseDefinition> {

    public String _defName;

    
    ReplaceResponseDefinitionCommand_30() {
    }
    
    ReplaceResponseDefinitionCommand_30(Oas30ResponseDefinition old, Oas30ResponseDefinition replacement) {
        super(old, replacement);
        this._defName = old.getName();
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ReplaceNodeCommand#replaceNode(Document, io.apicurio.datamodels.core.models.Node, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    protected void replaceNode(Document doc, Oas30ResponseDefinition oldNode,
            Oas30ResponseDefinition newNode) {
        Oas30Document doc30 = (Oas30Document) doc;

        if (!oldNode.getName().equals(newNode.getName())) {
            // TODO: Can this even happen?
            doc30.components.removeResponseDefinition(oldNode.getName());
        }

        newNode._ownerDocument = doc;
        newNode._parent = doc30.components;
        doc30.components.addResponseDefinition(newNode.getName(), newNode);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ReplaceNodeCommand#readNode(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected Oas30ResponseDefinition readNode(Document doc, Object node) {
        Oas30Document doc30 = (Oas30Document) doc;
        Oas30ResponseDefinition definition = doc30.components.createResponseDefinition(this._defName);
        Library.readNode(node, definition);
        return definition;
    }
}
