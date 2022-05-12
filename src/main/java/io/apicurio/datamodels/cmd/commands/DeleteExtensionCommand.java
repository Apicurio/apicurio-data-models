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
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * A command used to delete a single extension.
 * @author eric.wittmann@gmail.com
 */
public class DeleteExtensionCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _name;

    public boolean _hasOldValue;
    public int _oldValueIndex;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldValue;
    
    DeleteExtensionCommand() {
    }
    
    DeleteExtensionCommand(Extension extension) {
        this._parentPath = Library.createNodePath(extension.parent());
        this._name = extension.name;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteExtensionCommand] Executing.");
        this._oldValue = null;
        this._hasOldValue = false;

        ExtensibleNode parent = (ExtensibleNode) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        // Find any existing extension with this name
        Extension extension = parent.getExtension(this._name);

        // If found, remove it.
        if (ModelUtils.isDefined(extension)) {
            this._hasOldValue = true;
            this._oldValue = extension.value;

            _oldValueIndex = parent.getExtensionNames().indexOf(this._name);
            parent.removeExtension(this._name);
        } else {
            this._hasOldValue = false;
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteExtensionCommand] Reverting.");

        ExtensibleNode parent = (ExtensibleNode) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        // Find any existing extension with this name
        Extension extension = parent.getExtension(this._name);

        if (this._hasOldValue && ModelUtils.isDefined(extension)) {
            extension.value = this._oldValue;
        }

        if (this._hasOldValue && this.isNullOrUndefined(extension)) {
            Extension ext = parent.createExtension();
            ext.name = this._name;
            ext.value = this._oldValue;
            parent.restoreExtension(this._oldValueIndex, this._name, ext);
        }
    }

}
