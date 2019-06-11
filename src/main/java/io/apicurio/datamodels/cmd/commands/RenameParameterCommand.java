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
import java.util.List;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.util.VisitorUtil;
import io.apicurio.datamodels.openapi.models.IOasParameterParent;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * A command used to rename a parameter.
 * @author eric.wittmann@gmail.com
 */
public class RenameParameterCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _oldParamName;
    public String _newParamName;
    public String _paramIn;
    
    private boolean isPathItem;
    private boolean isOperation;

    RenameParameterCommand() {
    }
    
    RenameParameterCommand(IOasParameterParent parent, String oldParamName, String newParamName, String paramIn) {
        this._oldParamName = oldParamName;
        this._newParamName = newParamName;
        this._paramIn = paramIn;
        this._parentPath = Library.createNodePath((Node) parent);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[RenameParameterCommand] Executing.");
        this._doParameterRename(document, this._oldParamName, this._newParamName);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[RenameParameterCommand] Reverting.");
        this._doParameterRename(document, this._newParamName, this._oldParamName);
    }

    /**
     * Does the work of renaming a param from one name to another.
     * @param document
     * @param from
     * @param to
     */
    private void _doParameterRename(Document document, String from, String to) {
        IOasParameterParent parent = (IOasParameterParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        // Find the param being changed, if not present bail.
        OasParameter param = parent.getParameter(this._paramIn, from);
        if (this.isNullOrUndefined(param)) {
            return;
        }

        // Start a list of all the params we're going to rename.
        List<OasParameter> allParams = new ArrayList<>();
        allParams.add(param);
        // param.name = to;

        // Detect what type of parent we're dealing with.
        isPathItem = false;
        isOperation = false;
        VisitorUtil.visitNode((Node) parent, new CombinedVisitorAdapter() {
            @Override
            public void visitPathItem(OasPathItem node) {
                isPathItem = true;
            }
            @Override
            public void visitOperation(Operation node) {
                isOperation = true;
            }
        });

        List<String> methods = NodeCompat.asList("get", "put", "post", "delete", "options", "head", "patch", "trace");

        // If the parent is a path item, then we also need to rename any overriding operation params.
        if (isPathItem) {
            OasPathItem pathItem = (OasPathItem) parent;
            for (String method : methods) {
                OasOperation op = (OasOperation) NodeCompat.getProperty(pathItem, method);
                if (!this.isNullOrUndefined(op)) {
                    OasParameter opParam = op.getParameter(_paramIn, from);
                    if (!this.isNullOrUndefined(opParam)) {
                        allParams.add(opParam);
                    }
                }
            }
        }

        // If the parent is an operation, then we also need to rename any param defined at the path level.  And if
        // there IS a param defined at the path level, we'll also need to rename all params in our peer operations.
        if (isOperation) {
            OasOperation operation = (OasOperation) parent;
            OasPathItem pathItem = (OasPathItem) operation.parent();
            OasParameter pparam = pathItem.getParameter(_paramIn, from);
            if (!this.isNullOrUndefined(pparam)) {
                allParams.add(pparam);
                for (String method : methods) {
                    OasOperation peerOperation = (OasOperation) NodeCompat.getProperty(pathItem, method);
                    if (ModelUtils.isDefined(peerOperation) && peerOperation != operation) {
                        OasParameter opParam = peerOperation.getParameter(_paramIn, from);
                        if (!this.isNullOrUndefined(opParam)) {
                            allParams.add(opParam);
                        }
                    }
                }
            }
        }

        // Now actually do the rename.
        allParams.forEach( p -> {
            p.name = to;
        });
    }

}
