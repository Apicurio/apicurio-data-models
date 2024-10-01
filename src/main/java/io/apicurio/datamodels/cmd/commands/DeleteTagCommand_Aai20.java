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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.models.IAaiTagged;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Tag;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.Tag;

import java.util.List;

/**
 * A command used to delete a single tag definition from operation base and message base nodes on AsyncApi 2 document.
 */
public class DeleteTagCommand_Aai20 extends AbstractCommand {

    public String _tagName;
    public NodePath _nodePath;

    public int _oldIndex;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldTag;
    
    DeleteTagCommand_Aai20() {
    }
    
    DeleteTagCommand_Aai20(String tagName, Node node) {
        this._tagName = tagName;
        this._nodePath = Library.createNodePath(node);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteTagCommand_Aai20] Executing.");

        Node node = this._nodePath.resolve(document);
        if (node instanceof IAaiTagged) {
            IAaiTagged tagged = (IAaiTagged) node;
            List<Tag> tags = tagged.getTags();
            Tag tag = IAaiTagged.getTag(tagged, this._tagName);
            this._oldIndex = tags.indexOf(tag);
            tags.remove(this._oldIndex);
            this._oldTag = Library.writeNode(tag);
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteTagCommand_Aai20] Reverting.");

        Node node = this._nodePath.resolve(document);
        if (node instanceof IAaiTagged) {
            IAaiTagged tagged = (IAaiTagged) node;
            List<Tag> tags = tagged.getTags();

            Tag tag = new Aai20Tag(node);
            Library.readNode(this._oldTag, tag);
            if (tags != null && tags.size() >= this._oldIndex) {
                tags.add(_oldIndex, tag);
            } else {
                tags.add(tag);
            }
        }
    }

}
