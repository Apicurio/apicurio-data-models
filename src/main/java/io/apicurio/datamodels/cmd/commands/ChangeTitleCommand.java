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
 * A command used to modify the title of a document.
 * @author eric.wittmann@gmail.com
 */
public class ChangeTitleCommand extends AbstractCommand implements ICommand {
    
    public static final ChangeTitleCommand create(String newTitle) {
        return new ChangeTitleCommand(newTitle);
    }

    private String _newTitle;

    private String _oldTitle;
    private boolean _nullInfo;
    
    /**
     * Constructor.
     * @param newTitle
     */
    ChangeTitleCommand(String newTitle) {
        this._newTitle = newTitle;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangeTitleCommand] Executing.");
        if (ModelUtils.isNullOrUndefined(document.info)) {
            document.info = document.createInfo();
            this._nullInfo = true;
            this._oldTitle = null;
        } else {
            this._oldTitle = document.info.title;
        }
        document.info.title = this._newTitle;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangeTitleCommand] Reverting.");
        if (this._nullInfo) {
            document.info = null;
        } else {
            document.info.title = this._oldTitle;
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.AbstractCommand#marshall()
     */
    @Override
    public Object marshall() {
        Object to = super.marshall();
        JsonCompat.setPropertyString(to, "_newTitle", this._newTitle);
        return to;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.AbstractCommand#unmarshall(java.lang.Object)
     */
    @Override
    public void unmarshall(Object from) {
        super.unmarshall(from);
        this._newTitle = JsonCompat.getPropertyString(from, "_newTitle");
    }

}
