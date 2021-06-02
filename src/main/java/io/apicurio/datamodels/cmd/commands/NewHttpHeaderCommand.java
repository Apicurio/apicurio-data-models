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
import io.apicurio.datamodels.openapi.v3.models.IOas30HttpHeaderParent;
import io.apicurio.datamodels.openapi.v3.models.Oas30Header;

/**
 * A command used to create a new http header.
 * @author vvilerio
 */
public class NewHttpHeaderCommand extends AbstractCommand {

    public NodePath _nodePath;
    public String _newHttpHeader;

    public boolean _created;

    NewHttpHeaderCommand() {
    }

    NewHttpHeaderCommand(IOas30HttpHeaderParent parent, String newHttpHeader) {
        this._nodePath = Library.createNodePath((Node) parent);
        this._newHttpHeader = newHttpHeader;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewHttpHeaderCommand] Executing.");
        this._created = false;

        IOas30HttpHeaderParent node = (IOas30HttpHeaderParent) this._nodePath.resolve(document);
        if (this.isNullOrUndefined(node)) {
            return;
        }

        Oas30Header httpHeader = node.getHttpHeader(this._newHttpHeader);
        if (ModelUtils.isDefined(httpHeader)) {
            return;
        }

        node.addHttpHeader(this._newHttpHeader, node.createHttpHeader(this._newHttpHeader));
        this._created = true;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewHttpHeaderCommand] Reverting.");
        if (!this._created) {
            LoggerCompat.info("[NewHttpHeaderCommand] http header already existed, nothing done so no rollback necessary.");
            return;
        }

        IOas30HttpHeaderParent node = (IOas30HttpHeaderParent) this._nodePath.resolve(document);
        if (this.isNullOrUndefined(node)) {
            return;
        }

        node.removeHttpHeader(this._newHttpHeader);
    }

}
