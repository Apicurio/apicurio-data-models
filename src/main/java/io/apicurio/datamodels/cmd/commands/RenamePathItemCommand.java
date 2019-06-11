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

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * A command used to rename a path item, along with all references to it.
 * @author eric.wittmann@gmail.com
 */
public class RenamePathItemCommand extends AbstractCommand {

    public String _oldPath;
    public String _newPath;
    public boolean _alsoRenameSubpaths;
    
    RenamePathItemCommand() {
    }
    
    RenamePathItemCommand(String oldPath, String newPath, boolean alsoRenameSubpaths) {
        this._oldPath = oldPath;
        this._newPath = newPath;
        this._alsoRenameSubpaths = alsoRenameSubpaths;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[RenamePathItemCommand] Executing.");
        this._doPathRename(document, this._oldPath, this._newPath, this._alsoRenameSubpaths);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[RenamePathItemCommand] Reverting.");
        this._doPathRename(document, this._newPath, this._oldPath, this._alsoRenameSubpaths);
    }

    /**
     * Does the work of renaming a path from one name to another.
     * @param document
     * @param from
     * @param to
     * @param alsoRenameSubpaths
     */
    private void _doPathRename(Document document, String from, String to, boolean alsoRenameSubpaths) {
        List<String[]> pathsToRename = new ArrayList<>();
        pathsToRename.add(new String[] { from, to });
        
        OasDocument odoc = (OasDocument) document;
        if (this._alsoRenameSubpaths && ModelUtils.isDefined(odoc.paths)) {
            List<String> pathItemNames = odoc.paths.getPathItemNames();
            for (String pathName : pathItemNames) {
                if (pathName.indexOf(from) == 0 && !NodeCompat.equals(pathName, from)) {
                    pathsToRename.add(new String[] { pathName, to + pathName.substring(from.length()) });
                }
            }
        }

        pathsToRename.forEach( p2r -> {
            this._renamePath(odoc, p2r[0], p2r[1]);
        });
    }

    /**
     * Does the work of renaming a path.
     * @param from
     * @param to
     */
    private void _renamePath(OasDocument document, String from, String to) {
        List<String> fromPathParamNames = ModelUtils.detectPathParamNames(from);
        List<String> toPathParamNames = ModelUtils.detectPathParamNames(to);

        if (fromPathParamNames.size() != toPathParamNames.size()) {
            // TODO uh oh - what to do here?
        }
        // First, rename the path itself
        OasPathItem path = document.paths.removePathItem(from);
        path.rename(to);
        document.paths.addPathItem(to, path);

        // Next, rename all of the path params (if necessary)
        for (int idx = 0; idx < fromPathParamNames.size(); idx++) {
            String fromParamName = fromPathParamNames.get(idx);
            String toParamName = toPathParamNames.get(idx);
            if (ModelUtils.isDefined(toParamName)) {
                this._renamePathParameter(path, fromParamName, toParamName);
            } else {
                this._removePathParameter(path, fromParamName);
            }
        }
    }

    /**
     * Rename a path parameter.
     * @param path
     * @param fromParamName
     * @param toParamName
     */
    private void _renamePathParameter(OasPathItem path, String fromParamName, String toParamName) {
        if (!NodeCompat.equals(fromParamName, toParamName) && ModelUtils.isDefined(path.parameters)) {
            path.parameters.forEach( param -> {
                if (NodeCompat.equals(param.in, "path") && NodeCompat.equals(param.name, fromParamName)) {
                    param.name = toParamName;
                }
            });
        }
    }

    /**
     * Remove a path parameter.
     * @param path
     * @param fromParamName
     */
    private void _removePathParameter(OasPathItem path, String fromParamName) {
        if (this.isNullOrUndefined(path.parameters)) {
            return;
        }
        int paramIdx = -1;
        for (int idx = 0; idx < path.parameters.size(); idx++) {
            OasParameter param = path.parameters.get(idx);
            if (NodeCompat.equals(param.name, fromParamName) && NodeCompat.equals(param.in, "path")) {
                paramIdx = idx;
            }
        }
        // TODO save the parameter that was deleted so it can be restored on undo()
        // TODO ALT: or perhaps save the whole path to be easily restored?
        if (paramIdx != -1) {
            path.parameters.remove(paramIdx);
        }
    }
}
