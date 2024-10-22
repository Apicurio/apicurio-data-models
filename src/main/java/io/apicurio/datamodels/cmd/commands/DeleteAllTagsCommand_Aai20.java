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
import io.apicurio.datamodels.asyncapi.models.AaiTag;
import io.apicurio.datamodels.asyncapi.models.IAaiTagged;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Tag;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * A command used to delete all tags from asyncapi operation base or message base nodes.
 */
public class DeleteAllTagsCommand_Aai20 extends AbstractCommand {

    @JsonDeserialize(contentUsing=NullableJsonNodeDeserializer.class)
    public List<Object> _oldTags;
    public NodePath _nodePath;
    
    DeleteAllTagsCommand_Aai20() {
    }

    public DeleteAllTagsCommand_Aai20(Node node) {
        this._nodePath = Library.createNodePath(node);

    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllTagsCommand_Aai20] Executing.");

        Node node = this._nodePath.resolve(document);
        if (node instanceof IAaiTagged) {
            IAaiTagged taggedNode = (IAaiTagged) node;
            List<Tag> tags = taggedNode.getTags();
            this._oldTags = new ArrayList<>();
            // Save the old tags (if any)
            if (!this.isNullOrUndefined(tags)) {
                tags.forEach( tag -> {
                    this._oldTags.add(Library.writeNode(tag));
                });
            }
            taggedNode.deleteAllTags();
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteAllTagsCommand_Aai20] Reverting.");
        if (this._oldTags.size() == 0) {
            return;
        }

        Node node = this._nodePath.resolve(document);
        if (node instanceof IAaiTagged) {
            this._oldTags.forEach(oldTag -> {
                AaiTag tag = new Aai20Tag(node);
                Library.readNode(oldTag, tag);
                ((IAaiTagged) node).addTag(tag);
            });
        }
    }
}
