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
import io.apicurio.datamodels.openapi.v3.models.Oas30SchemaDefinition;

/**
 * A command used to replace a definition schema with a newer version.
 * @author eric.wittmann@gmail.com
 */
public class ReplaceSchemaDefinitionCommand_30 extends ReplaceNodeCommand<Oas30SchemaDefinition> {

    public String _defName;


    ReplaceSchemaDefinitionCommand_30() {
    }
    
    ReplaceSchemaDefinitionCommand_30(Oas30SchemaDefinition old, Oas30SchemaDefinition replacement) {
        super(old, replacement);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ReplaceNodeCommand#removeNode(io.apicurio.datamodels.core.models.Document, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    protected void removeNode(Document doc, Oas30SchemaDefinition node) {
        Oas30Document doc30 = (Oas30Document) doc;
        doc30.components.removeSchemaDefinition(node.getName());
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ReplaceNodeCommand#addNode(io.apicurio.datamodels.core.models.Document, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    protected void addNode(Document doc, Oas30SchemaDefinition node) {
        Oas30Document doc30 = (Oas30Document) doc;
        node._ownerDocument = doc;
        node._parent = doc30.components;
        doc30.components.addSchemaDefinition(node.getName(), node);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ReplaceNodeCommand#readNode(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected Oas30SchemaDefinition readNode(Document doc, Object node) {
        Oas30Document doc30 = (Oas30Document) doc;
        Oas30SchemaDefinition definition = doc30.components.createSchemaDefinition(this._defName);
        Library.readNode(node, definition);
        return definition;
    }
}
