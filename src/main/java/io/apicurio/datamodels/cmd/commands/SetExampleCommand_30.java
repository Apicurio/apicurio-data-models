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
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.IExampleParent;
import io.apicurio.datamodels.core.models.common.IExamplesParent;

/**
 * @author eric.wittmann@gmail.com
 */
public class SetExampleCommand_30 extends SetExampleCommand {

    public String _newExampleName;

    public boolean _nullExample;

    SetExampleCommand_30() {
    }

    SetExampleCommand_30(IExamplesParent parent, Object example, String exampleName) {
        super((Node) parent, example);
        this._newExampleName = exampleName;
    }

    SetExampleCommand_30(IExampleParent parent, Object example, String exampleName) {
        super((Node) parent, example);
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

        if (!this.isNullOrUndefined(this._newExampleName)) {
            IExamplesParent parent = (IExamplesParent) this._parentPath.resolve(document);
            if (this.isNullOrUndefined(parent)) {
                return;
            }

            if (this.isNullOrUndefined(parent.getExample(this._newExampleName))) {
                parent.addExample(parent.createExample(this._newExampleName));
                this._nullExample = true;
            } else {
                this._oldValue = parent.getExample(this._newExampleName).getValue();
            }
            parent.getExample(this._newExampleName).setValue(this._newExample);
        } else {
            IExampleParent parent = (IExampleParent) this._parentPath.resolve(document);
            if (this.isNullOrUndefined(parent)) {
                return;
            }

            this._oldValue = parent.getExample();
            parent.setExample(this._newExample);
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[SetExampleCommand_30] Reverting.");

        if (!this.isNullOrUndefined(this._newExampleName)) {
            IExamplesParent parent = (IExamplesParent) this._parentPath.resolve(document);
            if (this.isNullOrUndefined(parent)) {
                return;
            }
            if (this._nullExample) {
                parent.removeExample(this._newExampleName);
            } else {
                parent.getExample(this._newExampleName).setValue(this._oldValue);
            }
        } else {
            IExampleParent parent = (IExampleParent) this._parentPath.resolve(document);
            if (this.isNullOrUndefined(parent)) {
                return;
            }
            parent.setExample(this._oldValue);
            this._oldValue = null;
        }
    }

}
