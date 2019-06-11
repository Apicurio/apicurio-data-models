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
import io.apicurio.datamodels.openapi.v2.models.Oas20Response;

/**
 * @author eric.wittmann@gmail.com
 */
public class SetExampleCommand_20 extends SetExampleCommand {

    public String _newContentType;

    public boolean _nullExamples;

    SetExampleCommand_20() {
    }
    
    SetExampleCommand_20(Oas20Response parent, Object example, String contentType) {
        super(parent, example);
        this._newContentType = contentType;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[SetExampleCommand_20] Executing.");
        this._oldValue = null;

        Oas20Response response = (Oas20Response) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(response)) {
            return;
        }

        if (this.isNullOrUndefined(response.examples)) {
            response.examples = response.createExample();
            this._nullExamples = true;
        }

        this._oldValue = response.examples.getExample(this._newContentType);
        response.examples.addExample(this._newContentType, this._newExample);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[SetExampleCommand_20] Reverting.");

        Oas20Response response = (Oas20Response) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(response)) {
            return;
        }

        if (this.isNullOrUndefined(response.examples)) {
            return;
        }

        if (this._nullExamples) {
            response.examples = null;
            return;
        }

        if (this.isNullOrUndefined(this._oldValue)) {
            response.examples.removeExample(this._newContentType);
        } else {
            response.examples.addExample(this._newContentType, this._oldValue);
        }
    }

}
