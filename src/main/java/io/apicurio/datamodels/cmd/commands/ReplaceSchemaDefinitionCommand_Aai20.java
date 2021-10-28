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
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20SchemaDefinition;
import io.apicurio.datamodels.core.models.Document;

/**
 * A command used to replace a definition schema with a newer version.
 * @author vvilerio
 */
public class ReplaceSchemaDefinitionCommand_Aai20 extends ReplaceNodeCommand<Aai20SchemaDefinition> {

    public String _defName;


    ReplaceSchemaDefinitionCommand_Aai20() {
    }

    ReplaceSchemaDefinitionCommand_Aai20(Aai20SchemaDefinition old, Aai20SchemaDefinition replacement) {
        super(old, replacement);
        this._defName = old.getName();
    }
    
    /**
     * @see ReplaceNodeCommand#removeNode(Document, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    protected void removeNode(Document doc, Aai20SchemaDefinition node) {
        Aai20Document doc20 = (Aai20Document) doc;
        doc20.components.removeSchemaDefinition(node.getName());
    }
    
    /**
     * @see ReplaceNodeCommand#addNode(Document, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    protected void addNode(Document doc, Aai20SchemaDefinition node) {
        Aai20Document doc20 = (Aai20Document) doc;
        node._ownerDocument = doc;
        node._parent = doc20.components;
        doc20.components.addSchemaDefinition(node.getName(), node);
    }
    
    /**
     * @see ReplaceNodeCommand#readNode(Document, Object)
     */
    @Override
    protected Aai20SchemaDefinition readNode(Document doc, Object node) {
        Aai20Document doc20 = (Aai20Document) doc;
        Aai20SchemaDefinition definition = doc20.components.createSchemaDefinition(this._defName);
        Library.readNode(node, definition);
        return definition;
    }
}
