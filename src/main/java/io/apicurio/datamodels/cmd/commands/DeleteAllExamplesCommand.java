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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.v3.models.Oas30Example;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;

/**
 * A command used to delete a single mediaType from an operation.
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllExamplesCommand extends AbstractCommand {

    public NodePath _mediaTypePath;

    @JsonDeserialize(contentUsing=NullableJsonNodeDeserializer.class)
    public Map<String, Object> _oldExamples;
    
    DeleteAllExamplesCommand() {
    }
    
    DeleteAllExamplesCommand(Oas30MediaType mediaType) {
        this._mediaTypePath = Library.createNodePath(mediaType);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllExamplesCommand] Executing.");

        if (this.isNullOrUndefined(document)) {
            LoggerCompat.debug("[DeleteAllExamplesCommand] Could not execute the command, invalid argument.");
            return;
        }

        if (this.isNullOrUndefined(this._mediaTypePath)) {
            LoggerCompat.debug("[DeleteAllExamplesCommand] Could not execute the command, problem when unmarshalling.");
            return;
        }

        Oas30MediaType mediaType = (Oas30MediaType) this._mediaTypePath.resolve(document);

        if (this.isNullOrUndefined(mediaType)) {
            LoggerCompat.debug("[DeleteAllExamplesCommand] Media type not found.");
            return;
        }

        this._oldExamples = new HashMap<>();
        mediaType.getExamples().forEach(e -> {
            this._oldExamples.put(e.getName(), Library.writeNode(e));
        });
        mediaType.examples = new LinkedHashMap<>();
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        if (this.isNullOrUndefined(document)) {
            LoggerCompat.debug("[DeleteAllExamplesCommand] Could not revert the command, invalid argument.");
            return;
        }

        if (this.isNullOrUndefined(this._mediaTypePath)) {
            LoggerCompat.debug("[DeleteAllExamplesCommand] Could not revert the command, problem when unmarshalling.");
            return;
        }

        LoggerCompat.info("[DeleteAllExamplesCommand] Reverting.");

        Oas30MediaType mediaType = (Oas30MediaType) this._mediaTypePath.resolve(document);
        if (this.isNullOrUndefined(mediaType)) {
            LoggerCompat.info("[DeleteAllExamplesCommand] No media type found.");
            return;
        }

        if (this.isNullOrUndefined(this._oldExamples)) {
            LoggerCompat.info("[DeleteAllExamplesCommand] Could not revert. Previous data is not available.");
            return;
        }

        for (String k : this._oldExamples.keySet()) {
            Oas30Example example = mediaType.createExample(k);
            Library.readNode(this._oldExamples.get(k), example);
            mediaType.addExample(example);
        }
    }

}
