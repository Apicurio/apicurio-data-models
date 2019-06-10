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
import io.apicurio.datamodels.openapi.v3.models.Oas30Example;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;

/**
 * A command used to add an Example for a 3.0 MediaType.  If an example with the same name
 * already exists, this command does nothing.
 * @author eric.wittmann@gmail.com
 */
public class AddExampleCommand_30 extends AbstractCommand {

    public NodePath _parentPath;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _newExampleValue;
    public String _newExampleName;
    public String _newExampleSummary;
    public String _newExampleDescription;

    public boolean _exampleAdded;

    AddExampleCommand_30() {
    }

    AddExampleCommand_30(Oas30MediaType parent, Object example, String exampleName) {
        this._parentPath = Library.createNodePath(parent);
        this._newExampleValue = example;
        this._newExampleName = exampleName;
    }

    AddExampleCommand_30(Oas30MediaType parent, Object example, String exampleName, String exampleSummary, String exampleDescription) {
        this._parentPath = Library.createNodePath(parent);
        this._newExampleValue = example;
        this._newExampleName = exampleName;
        this._newExampleSummary = exampleSummary;
        this._newExampleDescription = exampleDescription;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[AddExampleCommand_30] Executing.");
        this._exampleAdded = false;

        Oas30MediaType mediaType = (Oas30MediaType) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(mediaType)) {
            return;
        }
        if (!this.isNullOrUndefined(mediaType.getExample(this._newExampleName))) {
            return;
        }

        Oas30Example example = mediaType.createExample(this._newExampleName);
        example.summary = this._newExampleSummary;
        example.description = this._newExampleDescription;
        example.value = this._newExampleValue;
        mediaType.addExample(example);
        this._exampleAdded = true;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[AddExampleCommand_30] Reverting.");
        if (!this._exampleAdded) {
            return;
        }
        Oas30MediaType mediaType = (Oas30MediaType) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(mediaType) || this.isNullOrUndefined(mediaType.examples)) {
            return;
        }

        mediaType.removeExample(this._newExampleName);
    }

}
