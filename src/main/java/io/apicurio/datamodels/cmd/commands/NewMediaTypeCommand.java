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
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.v3.models.IOas30MediaTypeParent;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;

/**
 * A command used to create a new media type.
 * @author eric.wittmann@gmail.com
 */
public class NewMediaTypeCommand extends AbstractCommand {

    public NodePath _nodePath;
    public String _newMediaType;

    public boolean _created;
    
    NewMediaTypeCommand() {
    }
    
    NewMediaTypeCommand(IOas30MediaTypeParent parent, String newMediaType) {
        this._nodePath = Library.createNodePath((Node) parent);
        this._newMediaType = newMediaType;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewMediaTypeCommand] Executing.");
        this._created = false;

        IOas30MediaTypeParent node = (IOas30MediaTypeParent) this._nodePath.resolve(document);
        if (this.isNullOrUndefined(node)) {
            return;
        }

        Oas30MediaType mediaType = node.getMediaType(this._newMediaType);
        if (ModelUtils.isDefined(mediaType)) {
            return;
        }

        node.addMediaType(this._newMediaType, node.createMediaType(this._newMediaType));
        this._created = true;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewMediaTypeCommand] Reverting.");
        if (!this._created) {
            LoggerCompat.info("[NewMediaTypeCommand] media type already existed, nothing done so no rollback necessary.");
            return;
        }

        IOas30MediaTypeParent node = (IOas30MediaTypeParent) this._nodePath.resolve(document);
        if (this.isNullOrUndefined(node)) {
            return;
        }

        node.removeMediaType(this._newMediaType);
    }

}
