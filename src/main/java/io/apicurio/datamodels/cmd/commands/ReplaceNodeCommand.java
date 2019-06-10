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
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * Base class for commands that replace entire nodes.
 * @author eric.wittmann@gmail.com
 */
public abstract class ReplaceNodeCommand<T extends Node> extends AbstractCommand {
    
    public NodePath _nodePath;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _new;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _old;
    
    ReplaceNodeCommand() {
    }
    
    ReplaceNodeCommand(T old, T replacement) {
        if (ModelUtils.isDefined(old)) {
            this._nodePath = Library.createNodePath(old);
        }
        if (ModelUtils.isDefined(replacement)) {
            this._new = Library.writeNode(replacement);
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[AbstractReplaceNodeCommand] Executing.");
        this._old = null;

        @SuppressWarnings("unchecked")
        T oldNode = (T) this._nodePath.resolve(document);
        if (this.isNullOrUndefined(oldNode)) {
            return;
        }

        this._old = Library.writeNode(oldNode);
        this.removeNode(document, oldNode);

        T newNode = this.readNode(document, this._new);
        this.addNode(document, newNode);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[AbstractReplaceNodeCommand] Reverting.");
        if (this.isNullOrUndefined(this._old)) {
            return;
        }

        @SuppressWarnings("unchecked")
        T node = (T) this._nodePath.resolve(document);
        if (this.isNullOrUndefined(node)) {
            return;
        }

        this.removeNode(document, node);

        T restoreNode = this.readNode(document, this._old);
        this.addNode(document, restoreNode);
    }

    /**
     * Removes the given node from the data model.
     */
    protected abstract void removeNode(Document doc, T node);

    /**
     * Adds the given node to the data model.
     */
    protected abstract void addNode(Document doc, T node);

    /**
     * Unmarshalls a node into the appropriate type.
     * @param node
     */
    protected abstract T readNode(Document doc, Object node);

}
