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
import io.apicurio.datamodels.openapi.v3.models.IOas30MediaTypeParent;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;

/**
 * A command used to delete a single mediaType from an operation.
 * @author eric.wittmann@gmail.com
 */
public class DeleteMediaTypeCommand extends AbstractCommand {

    public String _mediaTypeName;
    public NodePath _mediaTypePath;
    public NodePath _parentPath;

    public int _oldMediaTypeIndex;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldMediaType;
    
    DeleteMediaTypeCommand() {
    }
    
    DeleteMediaTypeCommand(Oas30MediaType mediaType) {
        this._mediaTypeName = mediaType.getName();
        this._mediaTypePath = Library.createNodePath(mediaType);
        this._parentPath = Library.createNodePath(mediaType.parent());
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteMediaTypeCommand] Executing.");
        this._oldMediaType = null;

        Oas30MediaType mediaType = (Oas30MediaType) this._mediaTypePath.resolve(document);
        if (this.isNullOrUndefined(mediaType)) {
            return;
        }

        IOas30MediaTypeParent parent = (IOas30MediaTypeParent) mediaType.parent();
        this._oldMediaTypeIndex = parent.getMediaTypes().indexOf(mediaType);
        parent.removeMediaType(this._mediaTypeName);

        this._oldMediaType = Library.writeNode(mediaType);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteMediaTypeCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldMediaType)) {
            return;
        }

        IOas30MediaTypeParent parent = (IOas30MediaTypeParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        Oas30MediaType mediaType = parent.createMediaType(this._mediaTypeName);
        Library.readNode(this._oldMediaType, mediaType);
        parent.restoreMediaType(this._oldMediaTypeIndex, this._mediaTypeName, mediaType);
    }

}
