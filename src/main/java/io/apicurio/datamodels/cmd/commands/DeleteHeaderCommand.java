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
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.v3.models.IOasHeaderParent;

/**
 * A command used to delete a single header .
 * @author vvilerio
 */
public class DeleteHeaderCommand extends AbstractCommand {

    public String _headerName;
    public NodePath _headerPath;
    public NodePath _parentPath;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldHeader;

    DeleteHeaderCommand() {
    }

    DeleteHeaderCommand(OasHeader header) {
        this._headerName = header.getName();
        this._headerPath = Library.createNodePath(header);
        this._parentPath = Library.createNodePath(header.parent());
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteHeaderCommand] Executing.");
        this._oldHeader = null;

        OasHeader header = (OasHeader) this._headerPath.resolve(document);
        if (this.isNullOrUndefined(header)) {
            return;
        }

        IOasHeaderParent parent = (IOasHeaderParent) header.parent();
        parent.removeHttpHeader(this._headerName);

        this._oldHeader = Library.writeNode(header);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteHeaderCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldHeader)) {
            return;
        }

        IOasHeaderParent parent = (IOasHeaderParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        OasHeader header = parent.createHttpHeader(this._headerName);
        Library.readNode(this._oldHeader, header);
        parent.addHttpHeader(this._headerName, header);
    }

}
