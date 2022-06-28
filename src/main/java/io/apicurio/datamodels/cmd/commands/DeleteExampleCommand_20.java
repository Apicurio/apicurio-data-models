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
import io.apicurio.datamodels.openapi.v2.models.Oas20Response;

/**
 * A command used to delete a single example.
 * @author eric.wittmann@gmail.com
 */
public class DeleteExampleCommand_20 extends AbstractCommand {

    public String _exampleContentType;
    public NodePath _responsePath;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldExample;
    
    public Integer _oldExampleIndex; // nullable for backwards compatibility

    DeleteExampleCommand_20() {
    }
    
    DeleteExampleCommand_20(Oas20Response response, String contentType) {
        this._responsePath = Library.createNodePath(response);
        this._exampleContentType = contentType;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteExampleCommand] Executing.");
        this._oldExample = null;

        Oas20Response response = (Oas20Response) this._responsePath.resolve(document);
        if (this.isNullOrUndefined(response) || this.isNullOrUndefined(response.examples) ||
            this.isNullOrUndefined(response.examples.getExample(this._exampleContentType)))
        {
            LoggerCompat.debug("[DeleteExampleCommand] No example with content-type: " + this._exampleContentType);
            return;
        }

        this._oldExampleIndex = response.examples.getExampleContentTypes().indexOf(this._exampleContentType);
        this._oldExample = response.examples.removeExample(this._exampleContentType);
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

        Oas20Response response = (Oas20Response) this._responsePath.resolve(document);
        if (this.isNullOrUndefined(response)) {
            return;
        }

        if (this.isNullOrUndefined(response.examples)) {
            response.examples = response.createExample();
        }

        response.examples.restoreExample(this._oldExampleIndex, this._exampleContentType, this._oldExample);
    }
}
