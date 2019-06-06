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
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.models.SimplifiedType;
import io.apicurio.datamodels.cmd.util.SimplifiedTypeUtil;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;

/**
 * @author eric.wittmann@gmail.com
 */
public class ChangeMediaTypeTypeCommand extends AbstractCommand {

    public NodePath _mediaTypePath;
    public String _mediaTypeName;
    public SimplifiedType _newType;

    public boolean _changed;
    @JsonDeserialize(using=JsonNodeDeserializer.class)
    public Object _oldMediaTypeSchema;
    
    ChangeMediaTypeTypeCommand() {
    }

    ChangeMediaTypeTypeCommand(Oas30MediaType mediaType, SimplifiedType newType) {
        this._mediaTypeName = mediaType.getName();
        this._mediaTypePath = Library.createNodePath(mediaType);
        this._newType = newType;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangeMediaTypeTypeCommand] Executing.");
        this._changed = false;

        Oas30MediaType mediaType = (Oas30MediaType) this._mediaTypePath.resolve(document);
        if (this.isNullOrUndefined(mediaType)) {
            return;
        }

        // Save the old info (for later undo operation)
        if (this.isNullOrUndefined(mediaType.schema)) {
            this._oldMediaTypeSchema = null;
            mediaType.schema = mediaType.createSchema();
        } else {
            this._oldMediaTypeSchema = Library.writeNode(mediaType.schema);
        }

        // Update the media type schema's type
        SimplifiedTypeUtil.setSimplifiedType(mediaType.schema, this._newType);

        this._changed = true;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangeMediaTypeTypeCommand] Reverting.");
        if (!this._changed) {
            return;
        }

        Oas30MediaType mediaType = (Oas30MediaType) this._mediaTypePath.resolve(document);
        if (this.isNullOrUndefined(mediaType)) {
            return;
        }

        if (this.isNullOrUndefined(this._oldMediaTypeSchema)) {
            mediaType.schema = null;
        } else {
            mediaType.schema = mediaType.createSchema();
            Library.readNode(this._oldMediaTypeSchema, mediaType.schema);
        }
    }

}
