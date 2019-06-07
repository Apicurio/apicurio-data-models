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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Tag;
import io.apicurio.datamodels.openapi.models.OasDocument;

/**
 * A command used to delete all tags from a document.
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllTagsCommand extends AbstractCommand {

    @JsonDeserialize(contentUsing=JsonNodeDeserializer.class)
    public List<Object> _oldTags;
    
    DeleteAllTagsCommand() {
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllTagsCommand] Executing.");
        this._oldTags = new ArrayList<>();
        // Save the old tags (if any)
        if (!this.isNullOrUndefined(document.tags)) {
            document.tags.forEach( tag -> {
                this._oldTags.add(Library.writeNode(tag));
            });
        }

        OasDocument odoc = (OasDocument) document;
        odoc.tags = new ArrayList<>();
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteAllTagsCommand] Reverting.");
        if (this._oldTags.size() == 0) {
            return;
        }

        OasDocument odoc = (OasDocument) document;

        if (this.isNullOrUndefined(document.tags)) {
            odoc.tags = new ArrayList<>();
        }
        this._oldTags.forEach( oldTag -> {
            Tag tag = document.createTag();
            Library.readNode(oldTag, tag);
            document.tags.add(tag);
        });
    }
}
