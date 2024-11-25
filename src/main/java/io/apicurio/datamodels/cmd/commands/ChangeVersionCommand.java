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
 * A command used to modify the version of a document.
 * @author eric.wittmann@gmail.com
 */
public class ChangeVersionCommand extends AbstractCommand {
    
    public String _newVersion;

    public String _oldVersion;
    public boolean _nullInfo;
    
    /**
     * Constructor.
     */
    public ChangeVersionCommand() {
    }
    
    /**
     * Constructor.
     * @param newVersion
     */
    public ChangeVersionCommand(String newVersion) {
        this._newVersion = newVersion;
    }
    
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[ChangeVersionCommand] Executing.");
        if (NodeUtil.isNullOrUndefined(document.getInfo())) {
            document.setInfo(document.createInfo());
            this._nullInfo = true;
            this._oldVersion = null;
        } else {
            this._oldVersion = document.getInfo().getVersion();
        }
        document.getInfo().setVersion(this._newVersion);
    }

    @Override
    public void undo(Document document) {
        LoggerUtil.info("[ChangeVersionCommand] Reverting.");
        if (this._nullInfo) {
            document.setInfo(null);
        } else {
            document.getInfo().setVersion(this._oldVersion);
        }
    }

}
