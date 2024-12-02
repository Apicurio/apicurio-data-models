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

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Info;
import io.apicurio.datamodels.models.License;

/**
 * A command used to delete the license.
 * 
 * @author eric.wittmann@gmail.com
 */
public class DeleteLicenseCommand extends DeleteNodeCommand<License> {
    
    public DeleteLicenseCommand() {
    }

    public DeleteLicenseCommand(Info info) {
        super("license", info);
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.DeleteNodeCommand#readNode(Document, ObjectNode)
     */
    @Override
    protected License readNode(Document doc, ObjectNode node) {
        License license = doc.getInfo().createLicense();
        Library.readNode(node, license);
        return license;
    }

}
