/*
 * Copyright 2019 JBoss Inc
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
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * A command used to modify the simple property of a node.  Should not be used
 * to modify complex (object) properties, only simple property types like
 * string, boolean, number, etc.
 * 
 * @author eric.wittmann@gmail.com
 */
public class ChangePropertyCommand<T> extends AbstractCommand {

    public NodePath _nodePath;
    public String _property;
    public T _newValue;

    public T _oldValue;
    
    /**
     * Constructor.
     */
    ChangePropertyCommand() {
    }

    /**
     * C'tor.
     */
    ChangePropertyCommand(Node node, String property, T newValue) {
        super();
        if (ModelUtils.isDefined(node)) {
            this._nodePath = Library.createNodePath(node);
        }
        this._property = property;
        this._newValue = newValue;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangePropertyCommand] Executing.");
        Node node = this._nodePath.resolve(document);
        if (this.isNullOrUndefined(node)) {
            return;
        }

        this._oldValue = (T) NodeCompat.getProperty(node, this._property);
        NodeCompat.setProperty(node, this._property, this._newValue);
    }
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangePropertyCommand] Reverting.");
        Node node = this._nodePath.resolve(document);
        if (this.isNullOrUndefined(node)) {
            return;
        }

        NodeCompat.setProperty(node, this._property, this._oldValue);
        this._oldValue = null;
    }

}
