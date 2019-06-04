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
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * A command used to replace an operation with a newer version.
 * @author eric.wittmann@gmail.com
 */
public class ReplaceOperationCommand extends ReplaceNodeCommand<OasOperation> {
    
    public static final ReplaceOperationCommand create(OasOperation old, OasOperation replacement) {
        return new ReplaceOperationCommand(old, replacement);
    }

    public String _method;
    public String _path;

    ReplaceOperationCommand() {
    }

    ReplaceOperationCommand(OasOperation old, OasOperation replacement) {
        super(old, replacement);
        if (ModelUtils.isDefined(old)) {
            this._method = old.getMethod();
            this._path = ((OasPathItem) old.parent()).getPath();
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ReplaceNodeCommand#removeNode(io.apicurio.datamodels.core.models.Document, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    protected void removeNode(Document doc, OasOperation node) {
        OasDocument odoc = (OasDocument) doc;
        OasPathItem path = odoc.paths.getPathItem(this._path);
        NodeCompat.setProperty(path, node.getMethod(), null);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ReplaceNodeCommand#addNode(io.apicurio.datamodels.core.models.Document, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    protected void addNode(Document doc, OasOperation node) {
        OasDocument odoc = (OasDocument) doc;
        OasPathItem path = odoc.paths.getPathItem(this._path);
        node._parent = path;
        node._ownerDocument = path.ownerDocument();
        NodeCompat.setProperty(path, node.getMethod(), node);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ReplaceNodeCommand#readNode(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected OasOperation readNode(Document doc, Object node) {
        OasDocument odoc = (OasDocument) doc;
        OasPathItem parent = odoc.paths.getPathItem(this._path);
        OasOperation operation = parent.createOperation(this._method);
        Library.readNode(node, operation);
        return operation;
    }

}
