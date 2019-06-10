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
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * A command used to create a new path item in a document.
 * @author eric.wittmann@gmail.com
 */
public class NewPathCommand extends AbstractCommand {

    public String _newPath;

    public boolean _pathExisted;
    public boolean _nullPaths;
    
    NewPathCommand() {
    }
    
    NewPathCommand(String newPath) {
        this._newPath = newPath;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewPathCommand] Executing.");
        OasDocument odoc = (OasDocument) document;
        if (this.isNullOrUndefined(odoc.paths)) {
            odoc.paths = odoc.createPaths();
            this._nullPaths = true;
        }

        if (this.isNullOrUndefined(odoc.paths.getPathItem(this._newPath))) {
            OasPathItem pathItem = odoc.paths.createPathItem(this._newPath);
            odoc.paths.addPathItem(this._newPath, pathItem);
            this._pathExisted = false;
        } else {
            this._pathExisted = true;
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewPathCommand] Reverting.");
        OasDocument odoc = (OasDocument) document;
        if (this._pathExisted) {
            LoggerCompat.info("[NewPathCommand] path already existed, nothing done so no rollback necessary.");
            return;
        }
        if (this._nullPaths) {
            LoggerCompat.info("[NewPathCommand] Paths was null, deleting it.");
            odoc.paths = null;
        } else {
            LoggerCompat.info("[NewPathCommand] Removing a path item: %s", this._newPath);
            odoc.paths.removePathItem(this._newPath);
        }
    }

}
