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

import java.util.List;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.IOasPropertySchema;
import io.apicurio.datamodels.openapi.models.OasSchema;

/**
 * A command used to rename a schema property.
 * @author eric.wittmann@gmail.com
 */
public class RenamePropertyCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _oldPropertyName;
    public String _newPropertyName;
    
    RenamePropertyCommand() {
    }
    
    RenamePropertyCommand(OasSchema parent, String oldPropertyName, String newPropertyName) {
        this._oldPropertyName = oldPropertyName;
        this._newPropertyName = newPropertyName;
        this._parentPath = Library.createNodePath(parent);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[RenamePropertyCommand] Executing.");
        this._doPropertyRename(document, this._oldPropertyName, this._newPropertyName);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[RenamePropertyCommand] Reverting.");
        this._doPropertyRename(document, this._newPropertyName, this._oldPropertyName);
    }

    /**
     * Does the work of renaming a path from one name to another.
     * @param document
     * @param from
     * @param to
     * @private
     */
    private void _doPropertyRename(Document document, String from, String to) {
        OasSchema parent = (OasSchema) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        // Don't do anything if the "to" property already exists.
        if (!this.isNullOrUndefined(parent.getProperty(to))) {
            return;
        }

        OasSchema property = (OasSchema) parent.removeProperty(from);
        if (this.isNullOrUndefined(property)) {
            return;
        }
        
        ((IOasPropertySchema) property).rename(to);
        parent.addProperty(to, property);

        List<String> required = parent.required;
        int reqIdx = ModelUtils.isDefined(required) ? required.indexOf(from) : -1;
        if (reqIdx != -1) {
            parent.required.set(reqIdx, to);
        }
    }
}
