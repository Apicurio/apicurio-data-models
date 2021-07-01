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
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.openapi.models.IOasHeaderParent;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasSchema;

import java.util.List;

/**
 * A command used to rename a schema header.
 * @author vvilerio
 */
public class RenameHeaderCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _oldHeaderName;
    public String _newHeaderName;

    RenameHeaderCommand() {
    }

    RenameHeaderCommand(OasHeader parent, String oldHeaderName, String newHeaderName) {
        this._oldHeaderName = oldHeaderName;
        this._newHeaderName = newHeaderName;
        this._parentPath = Library.createNodePath(parent);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[RenameHeaderCommand] Executing.");
        this._doHeaderRename(document, this._oldHeaderName, this._newHeaderName);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[RenameHeaderCommand] Reverting.");
        this._doHeaderRename(document, this._newHeaderName, this._oldHeaderName);
    }

    /**
     * Does the work of renaming a path from one name to another.
     * @param document
     * @param from
     * @param to
     * @private
     */
    private void _doHeaderRename(Document document, String from, String to) {
        IOasHeaderParent parent = (IOasHeaderParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        // Don't do anything if the "to" header already exists.
        if (!this.isNullOrUndefined(parent.getHeader(to))) {
            return;
        }

        OasHeader header = (OasHeader) parent.removeHeader(from);
        if (this.isNullOrUndefined(header)) {
            return;
        }

        header.rename(to);
        parent.addHeader(to, header);

    }
}
