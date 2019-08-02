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
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinition;

/**
 * A command used to replace a response definition with a newer version.
 * @author eric.wittmann@gmail.com
 */
public class ReplaceResponseDefinitionCommand_20 extends ReplaceNodeCommand<Oas20ResponseDefinition> {

    public String _defName;
    
    ReplaceResponseDefinitionCommand_20() {
    }
    
    ReplaceResponseDefinitionCommand_20(Oas20ResponseDefinition old, Oas20ResponseDefinition replacement) {
        super(old, replacement);
        this._defName = old.getName();
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ReplaceNodeCommand#removeNode(io.apicurio.datamodels.core.models.Document, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    protected void removeNode(Document doc, Oas20ResponseDefinition node) {
        Oas20Document doc20 = (Oas20Document) doc;
        doc20.responses.removeResponse(node.getName());
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ReplaceNodeCommand#addNode(io.apicurio.datamodels.core.models.Document, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    protected void addNode(Document doc, Oas20ResponseDefinition node) {
        Oas20Document doc20 = (Oas20Document) doc;
        node._ownerDocument = doc20;
        node._parent = doc20.responses;
        doc20.responses.addResponse(node.getName(), node);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ReplaceNodeCommand#readNode(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected Oas20ResponseDefinition readNode(Document doc, Object node) {
        Oas20Document doc20 = (Oas20Document) doc;
        Oas20ResponseDefinition definition = doc20.responses.createResponse(this._defName);
        Library.readNode(node, definition);
        return definition;
    }

}
