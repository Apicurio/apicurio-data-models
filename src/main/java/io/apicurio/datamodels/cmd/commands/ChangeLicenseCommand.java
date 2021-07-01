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
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;

/**
 * A command used to modify the license information of a document.
 * @author eric.wittmann@gmail.com
 */
public class ChangeLicenseCommand extends AbstractCommand {

    public String _newLicenseName;
    public String _newLicenseUrl;

    public Object _oldLicense;
    public boolean _nullInfo;
    
    ChangeLicenseCommand() {
    }

    ChangeLicenseCommand(String name, String url) {
        this._newLicenseName = name;
        this._newLicenseUrl = url;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangeLicenseCommand] Executing.");
        this._oldLicense = null;
        this._nullInfo = false;

        if (this.isNullOrUndefined(document.info)) {
            this._nullInfo = true;
            document.info = document.createInfo();
            this._oldLicense = null;
        } else {
            this._oldLicense = null;
            if (!this.isNullOrUndefined(document.info.license)) {
                this._oldLicense = Library.writeNode(document.info.license);
            }
        }
        document.info.license = document.info.createLicense();
        document.info.license.name = this._newLicenseName;
        document.info.license.url = this._newLicenseUrl;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangeLicenseCommand] Reverting.");
        if (this._nullInfo) {
            document.info = null;
        } else if (ModelUtils.isDefined(this._oldLicense)) {
            document.info.license = document.info.createLicense();
            Library.readNode(this._oldLicense, document.info.license);
        } else {
            document.info.license = null;
        }
    }

}
