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
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;

/**
 * @author eric.wittmann@gmail.com
 */
public class ChangeContactCommand extends AbstractCommand {

    public String _newName;
    public String _newEmail;
    public String _newUrl;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldContact;
    public boolean _nullInfo;
    
    ChangeContactCommand() {
    }
    
    ChangeContactCommand(String name, String email, String url) {
        this._newName = name;
        this._newEmail = email;
        this._newUrl = url;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangeContactCommand] Executing.");
        this._oldContact = null;
        this._nullInfo = false;

        if (this.isNullOrUndefined(document.info)) {
            this._nullInfo = true;
            document.info = document.createInfo();
            this._oldContact = null;
        } else {
            this._oldContact = null;
            if (ModelUtils.isDefined(document.info.contact)) {
                this._oldContact = Library.writeNode(document.info.contact);
            }
        }

        document.info.contact = document.info.createContact();
        document.info.contact.name = this._newName;
        document.info.contact.url = this._newUrl;
        document.info.contact.email = this._newEmail;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangeContactCommand] Reverting.");
        if (this._nullInfo) {
            document.info = null;
        } else if (ModelUtils.isDefined(this._oldContact)) {
            document.info.contact = document.info.createContact();
            Library.readNode(this._oldContact, document.info.contact);
        } else {
            document.info.contact = null;
        }
    }
    

}
