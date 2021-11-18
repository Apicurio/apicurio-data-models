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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
 * A command used to delete all extensions from a node.
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllExtensionsCommand extends AbstractCommand {

    public NodePath _parentPath;

    @JsonDeserialize(contentUsing=NullableJsonNodeDeserializer.class)
    public List<Object> _oldExtensions;

    DeleteAllExtensionsCommand() {
    }

    DeleteAllExtensionsCommand(ExtensibleNode parent) {
        this._parentPath = Library.createNodePath(parent);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllExtensionsCommand] Executing.");
        this._oldExtensions = new ArrayList<>();

        ExtensibleNode parent = (ExtensibleNode) this._parentPath.resolve(document);

        Collection<Extension> extensions = parent.getExtensions();
        // Save the old extensions (if any)
        if (!this.isNullOrUndefined(extensions)) {
            extensions.forEach(extension -> {
                this._oldExtensions.add(ModelUtils.marshalExtension(extension));
            });
        }

        parent.clearExtensions();
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteAllExtensionsCommand] Reverting.");
        if (this._oldExtensions.size() == 0) {
            return;
        }

        ExtensibleNode parent = (ExtensibleNode) this._parentPath.resolve(document);

        this._oldExtensions.forEach( oldExtension -> {
            Extension extension = parent.createExtension();
            ModelUtils.unmarshalExtension(oldExtension, extension);
            parent.addExtension(extension.name, extension);
        });
    }
}
