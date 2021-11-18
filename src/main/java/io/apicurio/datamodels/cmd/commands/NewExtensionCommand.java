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
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * A command used to create a new tag.
 * @author eric.wittmann@gmail.com
 */
public class NewExtensionCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _name;
    public Object _value;

    public boolean _created;

    NewExtensionCommand() {
    }

    NewExtensionCommand(ExtensibleNode parent, String name, Object value) {
        this._parentPath = Library.createNodePath(parent);
        this._name = name;
        this._value = value;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewExtensionCommand] Executing.");

        this._created = false;

        ExtensibleNode parent = (ExtensibleNode) this._parentPath.resolve(document);
        Extension extension = parent.getExtension(_name);

        // If the extension already exists, do nothing.
        if (!this.isNullOrUndefined(extension)) {
            return;
        }

        extension = parent.createExtension();
        extension.name = _name;
        extension.value = _value;
        parent.addExtension(_name, extension);
        this._created = true;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewExtensionCommand] Reverting.");
        if (!this._created) {
            return;
        }

        ExtensibleNode parent = (ExtensibleNode) this._parentPath.resolve(document);

        if (!_created) {
            return;
        }

        parent.removeExtension(_name);
    }

}
