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

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Tag;

/**
 * A command used to delete a single tag definition from the document.
 * @author eric.wittmann@gmail.com
 */
public class DeleteTagCommand extends AbstractCommand {

    public String _tagName;

    public int _oldIndex;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldTag;
    
    DeleteTagCommand() {
    }
    
    DeleteTagCommand(String tagName) {
        this._tagName = tagName;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteTagCommand] Executing.");

        if (this.isNullOrUndefined(document.tags)) {
            return;
        }

        List<Tag> tags = document.tags;
        Tag tag = null;
        for (Tag t : tags) {
            if (NodeCompat.equals(t.name, this._tagName)) {
                tag = t;
            }
        }
        this._oldIndex = tags.indexOf(tag);
        tags.remove(this._oldIndex);
        this._oldTag = Library.writeNode(tag);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteTagCommand] Reverting.");

        if (this.isNullOrUndefined(document.tags)) {
            document.tags = new ArrayList<>();
        }

        Tag tag = document.createTag();
        Library.readNode(this._oldTag, tag);
        if (document.tags.size() >= this._oldIndex) {
            document.tags.add(_oldIndex, tag);
        } else {
            document.tags.add(tag);
        }
    }

}
