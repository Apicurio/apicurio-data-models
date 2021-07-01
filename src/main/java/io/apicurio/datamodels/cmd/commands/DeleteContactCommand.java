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
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Contact;
import io.apicurio.datamodels.core.models.common.Info;

/**
 * A command used to delete the contact.
 * @author eric.wittmann@gmail.com
 */
public class DeleteContactCommand extends DeleteNodeCommand<Contact> {
    
    DeleteContactCommand() {
    }
    
    DeleteContactCommand(Info info) {
        super(Constants.PROP_CONTACT, info);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.DeleteNodeCommand#readNode(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected Contact readNode(Document doc, Object node) {
        Contact contact = doc.info.createContact();
        Library.readNode(node, contact);
        return contact;
    }

}
