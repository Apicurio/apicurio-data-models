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

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.ICommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;

/**
 * A command used to modify the version of a document.
 * @author eric.wittmann@gmail.com
 */
public class ChangeVersionCommand extends AbstractCommand implements ICommand {
    
    public static final ChangeVersionCommand create(String newVersion) {
        return new ChangeVersionCommand(newVersion);
    }

    private String _newVersion;

    private String _oldVersion;
    private Boolean _nullInfo;
    
    /**
     * Constructor.
     * @param newVersion
     */
    ChangeVersionCommand(String newVersion) {
        this._newVersion = newVersion;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangeVersionCommand] Executing.");
        if (ModelUtils.isNullOrUndefined(document.info)) {
            document.info = document.createInfo();
            this._nullInfo = true;
            this._oldVersion = null;
        } else {
            this._oldVersion = document.info.version;
        }
        document.info.version = this._newVersion;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangeVersionCommand] Reverting.");
        if (this._nullInfo == Boolean.TRUE) {
            document.info = null;
        } else {
            document.info.version = this._oldVersion;
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.AbstractCommand#marshall()
     */
    @Override
    public Object marshall() {
        Object to = super.marshall();
        JsonCompat.setPropertyString(to, "_newVersion", this._newVersion);
        JsonCompat.setPropertyString(to, "_oldVersion", this._oldVersion);
        JsonCompat.setPropertyBoolean(to, "_nullInfo", this._nullInfo);
        return to;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.AbstractCommand#unmarshall(java.lang.Object)
     */
    @Override
    public void unmarshall(Object from) {
        super.unmarshall(from);
        this._newVersion = JsonCompat.getPropertyString(from, "_newVersion");
        this._oldVersion = JsonCompat.getPropertyString(from, "_oldVersion");
        this._nullInfo = JsonCompat.getPropertyBoolean(from, "_nullInfo");
    }

}
