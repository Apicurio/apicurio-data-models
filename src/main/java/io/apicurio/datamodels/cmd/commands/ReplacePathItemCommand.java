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
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * A command used to replace a path item with a newer version.
 * @author eric.wittmann@gmail.com
 */
public class ReplacePathItemCommand extends ReplaceNodeCommand<OasPathItem> {

    public String _pathName;

    ReplacePathItemCommand() {
    }
    
    ReplacePathItemCommand(OasPathItem old, OasPathItem replacement) {
        super(old, replacement);
        this._pathName = replacement.getPath();
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ReplaceNodeCommand#removeNode(io.apicurio.datamodels.core.models.Document, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    protected void removeNode(Document doc, OasPathItem node) {
        OasDocument odoc = (OasDocument) doc;
        odoc.paths.removePathItem(node.getPath());
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ReplaceNodeCommand#addNode(io.apicurio.datamodels.core.models.Document, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    protected void addNode(Document doc, OasPathItem node) {
        OasDocument odoc = (OasDocument) doc;

        node._ownerDocument = odoc;
        node._parent = odoc.paths;
        odoc.paths.addPathItem(this._pathName, node);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ReplaceNodeCommand#readNode(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected OasPathItem readNode(Document doc, Object node) {
        OasDocument odoc = (OasDocument) doc;
        OasPathItem pathItem = odoc.paths.createPathItem(this._pathName);
        Library.readNode(node, pathItem);
        return pathItem;
    }
}
