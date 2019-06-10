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
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * A command used to create a new operation in a path.
 * @author eric.wittmann@gmail.com
 */
public class NewOperationCommand extends AbstractCommand {

    public String _path;
    public String _method;

    public boolean _created;
    
    NewOperationCommand() {
    }
    
    NewOperationCommand(String path, String method) {
        this._path = path;
        this._method = method;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewOperationCommand] Executing.");
        
        OasDocument odoc = (OasDocument) document;

        this._created = false;

        if (this.isNullOrUndefined(odoc.paths)) {
            return;
        }

        OasPathItem path = odoc.paths.getPathItem(this._path);
        if (this.isNullOrUndefined(path)) {
            return;
        }

        OasOperation operation = path.createOperation(this._method);
        NodeCompat.setProperty(path, this._method, operation);

        this._created = true;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewOperationCommand] Reverting.");
        if (!this._created) {
            return;
        }
        
        OasDocument odoc = (OasDocument) document;

        if (this.isNullOrUndefined(odoc.paths)) {
            return;
        }

        OasPathItem path = odoc.paths.getPathItem(this._path);
        if (this.isNullOrUndefined(path)) {
            return;
        }

        NodeCompat.setProperty(path, this._method, null);
    }

}
