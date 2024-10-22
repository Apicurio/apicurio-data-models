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
import io.apicurio.datamodels.asyncapi.models.AaiTag;
import io.apicurio.datamodels.asyncapi.models.IAaiTagged;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Tag;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.Tag;

import java.util.List;

/**
 * A command used to create a new tag on AsyncAPI 2 operation base and message base nodes.
 */
public class NewTagCommand_Aai20 extends AbstractCommand {

    public String _tagName;
    public String _tagDescription;
    public NodePath _nodePath;

    public boolean _created;
    
    NewTagCommand_Aai20() {
    }
    
    NewTagCommand_Aai20(String name, String description, Node node) {
        this._tagName = name;
        this._tagDescription = description;
        this._nodePath = Library.createNodePath(node);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewTagCommand_Aai20] Executing.");

        this._created = false;
        Node node = this._nodePath.resolve(document);
        
        if (node instanceof IAaiTagged) {
            AaiTag tag = new Aai20Tag(node);
            tag.name = this._tagName;
            tag.description = this._tagDescription;
            ((IAaiTagged) node).addTag(tag);
            this._created = true;
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewTagCommand_Aai20] Reverting.");
        if (!this._created) {
            return;
        }

        Node node = this._nodePath.resolve(document);
        if (node instanceof IAaiTagged) {
            IAaiTagged taggedNode = (IAaiTagged) node;
            List<Tag> tags = taggedNode.getTags();
            int index = tags.indexOf(IAaiTagged.getTag(taggedNode, this._tagName));
            tags.remove(index);
        }
    }
}
