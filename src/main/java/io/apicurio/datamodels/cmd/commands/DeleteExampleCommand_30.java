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
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.v3.models.Oas30Example;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;

/**
 * A command used to delete a single example.
 * @author eric.wittmann@gmail.com
 */
public class DeleteExampleCommand_30 extends AbstractCommand {

    public String _exampleName;
    public NodePath _mediaTypePath;

    @JsonDeserialize(using=JsonNodeDeserializer.class)
    public Object _oldExample;
    
    DeleteExampleCommand_30() {
    }
    
    DeleteExampleCommand_30(Oas30Example example) {
        this._exampleName = example.getName();
        this._mediaTypePath = Library.createNodePath(example.parent());
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteExampleCommand] Executing.");
        this._oldExample = null;

        Oas30MediaType mediaType = (Oas30MediaType) this._mediaTypePath.resolve(document);
        if (this.isNullOrUndefined(mediaType) || this.isNullOrUndefined(mediaType.getExample(this._exampleName))) {
            LoggerCompat.debug("[DeleteExampleCommand] No example named: " + this._exampleName);
            return;
        }

        Oas30Example example = mediaType.removeExample(this._exampleName);
        this._oldExample = Library.writeNode(example);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteExampleCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldExample)) {
            return;
        }

        Oas30MediaType mediaType = (Oas30MediaType) this._mediaTypePath.resolve(document);
        if (this.isNullOrUndefined(mediaType)) {
            LoggerCompat.info("[DeleteExampleCommand] No media type found.");
            return;
        }

        Oas30Example example = mediaType.createExample(this._exampleName);
        Library.readNode(this._oldExample, example);
        mediaType.addExample(example);
    }

}
