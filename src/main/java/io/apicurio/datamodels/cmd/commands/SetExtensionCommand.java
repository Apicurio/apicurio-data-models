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

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * Command that sets an extension.
 * @author eric.wittmann@gmail.com
 */
public class SetExtensionCommand extends AbstractCommand {
    // TODO: Ordering on undo
    public NodePath _parentPath;
    public String _name;
    public Object _value;

    public boolean _hasOldValue;
    public Object _oldValue;
    
    SetExtensionCommand() {
    }
    
    SetExtensionCommand(ExtensibleNode parent, String name, Object value) {
        this._parentPath = Library.createNodePath(parent);
        this._name = name;
        this._value = value;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[SetExtensionCommand] Executing.");
        this._oldValue = null;
        this._hasOldValue = false;

        ExtensibleNode parent = (ExtensibleNode) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        // Find any existing extension with this name
        Extension extension = parent.getExtension(this._name);

        // Either update the existing extension or add a new one
        if (ModelUtils.isDefined(extension)) {
            this._hasOldValue = true;
            this._oldValue = extension.value;

            extension.value = this._value;
        } else {
            this._hasOldValue = false;
            this._oldValue = null;

            extension = parent.createExtension();
            extension.name = this._name;
            extension.value = this._value;
            parent.addExtension(this._name, extension);
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[SetExtensionCommand] Reverting.");

        ExtensibleNode parent = (ExtensibleNode) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        // Find any existing extension with this name
        Extension extension = parent.getExtension(this._name);

        if (this._hasOldValue && ModelUtils.isDefined(extension)) {
            extension.value = this._oldValue;
        }

        if (!this._hasOldValue && ModelUtils.isDefined(extension)) {
            parent.removeExtension(this._name);
        }
    }

}
