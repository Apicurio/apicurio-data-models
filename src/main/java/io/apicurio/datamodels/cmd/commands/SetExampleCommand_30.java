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

import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;

/**
 * @author eric.wittmann@gmail.com
 */
public class SetExampleCommand_30 extends SetExampleCommand {

    public String _newExampleName;

    public boolean _nullExample;

    SetExampleCommand_30() {
    }
    
    SetExampleCommand_30(Oas30MediaType parent, Object example, String exampleName) {
        super(parent, example);
        this._newExampleName = exampleName;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[SetExampleCommand_30] Executing.");
        this._oldValue = null;
        this._nullExample = false;

        Oas30MediaType mediaType = (Oas30MediaType) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(mediaType)) {
            return;
        }

        if (!this.isNullOrUndefined(this._newExampleName)) {
            if (this.isNullOrUndefined(mediaType.getExample(this._newExampleName))) {
                mediaType.addExample(mediaType.createExample(this._newExampleName));
                this._nullExample = true;
            } else {
                this._oldValue = mediaType.getExample(this._newExampleName).value;
            }
            mediaType.getExample(this._newExampleName).value = this._newExample;
        } else {
            this._oldValue = mediaType.example;
            mediaType.example = this._newExample;
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[SetExampleCommand_30] Reverting.");
        Oas30MediaType mediaType = (Oas30MediaType) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(mediaType)) {
            return;
        }

        if (!this.isNullOrUndefined(this._newExampleName)) {
            if (this._nullExample) {
                mediaType.removeExample(this._newExampleName);
            } else {
                mediaType.getExample(this._newExampleName).value = this._oldValue;
            }
        } else {
            mediaType.example = this._oldValue;
            this._oldValue = null;
        }
    }
    
}
