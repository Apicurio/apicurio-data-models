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
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.NodeUtil;

/**
 * A command used to modify the description of a document.
 * @author eric.wittmann@gmail.com
 */
public class ChangeDescriptionCommand extends AbstractCommand {
    
    public String _newDescription;

    public String _oldDescription;
    public boolean _nullInfo;
    
    /**
     * Constructor.
     */
    public ChangeDescriptionCommand() {
    }
    
    /**
     * Constructor.
     * @param newDescription
     */
    public ChangeDescriptionCommand(String newDescription) {
        this._newDescription = newDescription;
    }
    
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[ChangeDescriptionCommand] Executing.");
        if (NodeUtil.isNullOrUndefined(document.getInfo())) {
            document.setInfo(document.createInfo());
            this._nullInfo = true;
            this._oldDescription = null;
        } else {
            this._oldDescription = document.getInfo().getDescription();
        }
        document.getInfo().setDescription(this._newDescription);
    }
    
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[ChangeDescriptionCommand] Reverting.");
        if (this._nullInfo) {
            document.setInfo(null);
        } else {
            document.getInfo().setDescription(this._oldDescription);
        }
    }

}
