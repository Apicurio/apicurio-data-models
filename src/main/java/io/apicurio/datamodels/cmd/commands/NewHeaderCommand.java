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
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.v3.models.IOasHeaderParent;

/**
 * A command used to create a new http header.
 * @author vvilerio
 */
public class NewHeaderCommand extends AbstractCommand {

    public NodePath _nodePath;
    public String _name;
    public Object _newHeader;

    public boolean _created;

    NewHeaderCommand() {
    }

    NewHeaderCommand(IOasHeaderParent parent, OasHeader newHeader) {
        this._nodePath = Library.createNodePath((Node) parent);
        this._newHeader = Library.writeNode(newHeader);
        this._name = newHeader.getName();
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewHttpHeaderCommand] Executing.");
        this._created = false;

        IOasHeaderParent node = (IOasHeaderParent) this._nodePath.resolve(document);
        if (this.isNullOrUndefined(node)) {
            return;
        }

        OasHeader httpHeader = node.getHttpHeader(this._name);
        if (ModelUtils.isDefined(httpHeader)) {
            return;
        }

        node.addHttpHeader(this._name,node.createHttpHeader(this._name));
        this._created = true;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewHeaderCommand] Reverting.");
        if (!this._created) {
            LoggerCompat.info("[NewHeaderCommand] http header already existed, nothing done so no rollback necessary.");
            return;
        }

        IOasHeaderParent node = (IOasHeaderParent) this._nodePath.resolve(document);
        if (this.isNullOrUndefined(node)) {
            return;
        }

        node.removeHttpHeader(this._name);
    }

}
