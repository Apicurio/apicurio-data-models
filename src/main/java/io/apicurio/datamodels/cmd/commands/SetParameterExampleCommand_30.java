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
import io.apicurio.datamodels.openapi.v3.models.Oas30Parameter;

/**
 * @author laurent.broudoux@gmail.com
 */
public class SetParameterExampleCommand_30 extends SetExampleCommand {

    public String _newExampleName;

    public boolean _nullExample;

    SetParameterExampleCommand_30() {
    }

    SetParameterExampleCommand_30(Oas30Parameter parent, Object example, String exampleName) {
        super(parent, example);
        this._newExampleName = exampleName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[SetParameterExampleCommand_30] Executing.");
        this._oldValue = null;
        this._nullExample = false;

        Oas30Parameter parameter = (Oas30Parameter) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parameter)) {
            return;
        }

        if (!this.isNullOrUndefined(this._newExampleName)) {
            if (this.isNullOrUndefined(parameter.getExample(this._newExampleName))) {
                parameter.addExample(parameter.createExample(this._newExampleName));
                this._nullExample = true;
            } else {
                this._oldValue = parameter.getExample(this._newExampleName).getValue();
            }
            parameter.getExample(this._newExampleName).setValue(this._newExample);
        } else {
            this._oldValue = parameter.getExample();
            parameter.setExample(this._newExample);
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[SetParameterExampleCommand_30] Reverting.");
        Oas30Parameter parameter = (Oas30Parameter) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parameter)) {
            return;
        }

        if (!this.isNullOrUndefined(this._newExampleName)) {
            if (this._nullExample) {
                parameter.removeExample(this._newExampleName);
            } else {
                parameter.getExample(this._newExampleName).setValue(this._oldValue);
            }
        } else {
            parameter.setExample(this._oldValue);
            this._oldValue = null;
        }
    }

}
