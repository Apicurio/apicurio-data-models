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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.models.OasPaths;

/**
 * A command used to delete a path.
 * @author eric.wittmann@gmail.com
 */
public class DeletePathCommand extends AbstractCommand {

    public String _path;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldPath;
    
    DeletePathCommand() {
    }
    
    DeletePathCommand(String path) {
        this._path = path;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeletePathCommand] Executing for path: %s", this._path);
        OasDocument odoc = (OasDocument) document;
        this._oldPath = null;
        OasPaths paths = odoc.paths;
        if (this.isNullOrUndefined(paths)) {
            return;
        }

        this._oldPath = Library.writeNode(paths.removePathItem(this._path));
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeletePathCommand] Reverting.");
        OasDocument odoc = (OasDocument) document;
        OasPaths paths = odoc.paths;
        if (this.isNullOrUndefined(paths) || this.isNullOrUndefined(this._oldPath)) {
            return;
        }

        OasPathItem pathItem = paths.createPathItem(this._path);
        Library.readNode(this._oldPath, pathItem);
        paths.addPathItem(this._path, pathItem);
    }

}
