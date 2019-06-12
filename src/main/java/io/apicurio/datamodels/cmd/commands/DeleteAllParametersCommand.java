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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.IOasParameterParent;
import io.apicurio.datamodels.openapi.models.OasParameter;

/**
 * A command used to delete all parameters from an operation.
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllParametersCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _paramType;

    @JsonDeserialize(contentUsing=NullableJsonNodeDeserializer.class)
    public List<Object> _oldParams;

    DeleteAllParametersCommand() {
    }
    
    DeleteAllParametersCommand(IOasParameterParent parent, String type) {
        this._parentPath = Library.createNodePath((Node) parent);
        this._paramType = type;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllParameters] Executing.");
        this._oldParams = new ArrayList<>();

        IOasParameterParent parent = (IOasParameterParent) this._parentPath.resolve(document);
        List<OasParameter> parameters = parent.getParameters();
        if (this.isNullOrUndefined(parent) || this.isNullOrUndefined(parameters) || parameters.size() == 0) {
            return;
        }

        // Save the params we're about to delete for later undd
        List<OasParameter> paramsToRemove = new ArrayList<>();
        for (OasParameter param : parameters) {
            if (NodeCompat.equals(param.in, this._paramType)) {
                this._oldParams.add(Library.writeNode(param));
                paramsToRemove.add(param);
            }
        }

        if (this._oldParams.size() == 0) {
            return;
        }

        paramsToRemove.forEach(paramToRemove -> {
            parameters.remove(paramToRemove);
        });
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteAllParameters] Reverting.");

        if (this.isNullOrUndefined(this._oldParams) || this._oldParams.size() == 0) {
            return;
        }

        IOasParameterParent parent = (IOasParameterParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        this._oldParams.forEach( param -> {
            OasParameter p = parent.createParameter();
            Library.readNode(param, p);
            parent.addParameter(p);
        });        
    }
}
