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

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Tag;

/**
 * A command used to create a new tag.
 * @author eric.wittmann@gmail.com
 */
public class NewTagCommand extends AbstractCommand {

    public String _tagName;
    public String _tagDescription;

    public boolean _created;
    
    NewTagCommand() {
    }
    
    NewTagCommand(String name, String description) {
        this._tagName = name;
        this._tagDescription = description;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewTagCommand] Executing.");

        this._created = false;

        if (this.isNullOrUndefined(document.tags)) {
            document.tags = new ArrayList<>();
        }

        Tag tag = this.findTag(document, this._tagName);
        if (this.isNullOrUndefined(tag)) {
            document.addTag(this._tagName, this._tagDescription);
            this._created = true;
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewTagCommand] Reverting.");
        if (!this._created) {
            return;
        }

        Tag tag = this.findTag(document, this._tagName);
        if (this.isNullOrUndefined(tag)) {
            return;
        }
        document.tags.remove(document.tags.indexOf(tag));
    }

    /**
     * Finds a single tag by its name.  No way to do this but iterate through the
     * tags array.
     * @param {OasDocument} doc
     * @param {string} tagName
     * @return {OasTag}
     */
    private Tag findTag(Document doc, String tagName) {
        for (Tag dt : doc.tags) {
            if (NodeCompat.equals(dt.name, tagName)) {
                return dt;
            }
        }
        return null;
    }

}
