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
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * A command used to delete a child node.
 * @author eric.wittmann@gmail.com
 */
public abstract class DeleteNodeCommand<T extends Node> extends AbstractCommand {

    public String _property;
    public NodePath _parentPath;

    @JsonDeserialize(using=JsonNodeDeserializer.class)
    public Object _oldValue;
    
    DeleteNodeCommand() {
    }

    DeleteNodeCommand(String property, Node from) {
        this._property = property;
        this._parentPath = Library.createNodePath(from);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[" + this.type() + "] Executing.");
        Node parent = this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }
        @SuppressWarnings("unchecked")
        T propertyNode = (T) NodeCompat.getProperty(parent, _property);
        if (this.isNullOrUndefined(propertyNode)) {
            this._oldValue = null;
            return;
        } else {
            this._oldValue = Library.writeNode(propertyNode);
        }

        NodeCompat.setProperty(parent, _property, null);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[" + this.type() + "] Reverting.");
        Node parent = this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent) || this.isNullOrUndefined(this._oldValue)) {
            return;
        }

        T restoredNode = this.readNode(document, this._oldValue);
        restoredNode._parent = parent;
        restoredNode._ownerDocument = parent.ownerDocument();

        NodeCompat.setProperty(parent, _property, restoredNode);
    }
    
    protected abstract T readNode(Document doc, Object node);


}
