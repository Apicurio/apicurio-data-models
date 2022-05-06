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
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.IOasParameterParent;
import io.apicurio.datamodels.openapi.models.OasParameter;

/**
 * A command used to delete a single parameter from an operation.
 * @author eric.wittmann@gmail.com
 */
public class DeleteParameterCommand extends AbstractCommand {
    
    public NodePath _parameterPath;
    public NodePath _parentPath;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldParameter;
    public Integer _oldParameterIndex; // nullable for backwards compatibility
    
    DeleteParameterCommand() {
    }
    
    DeleteParameterCommand(OasParameter parameter) {
        this._parameterPath = Library.createNodePath(parameter);
        this._parentPath = Library.createNodePath(parameter.parent());
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteParameterCommand] Executing.");
        this._oldParameter = null;

        OasParameter param = (OasParameter) this._parameterPath.resolve(document);
        if (this.isNullOrUndefined(param)) {
            return;
        }

        IOasParameterParent parent = (IOasParameterParent) param.parent();
        List<OasParameter> parameters = parent.getParameters();
        int idx = parameters.indexOf(param);
        if (idx == -1) {
            return;
        }
        parameters.remove(idx);

        this._oldParameter = Library.writeNode(param);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteParameterCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldParameter)) {
            return;
        }

        IOasParameterParent parent = (IOasParameterParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        List<OasParameter> parameters = parent.getParameters();
        if (this.isNullOrUndefined(parameters)) {
            parameters = new ArrayList<>();
            NodeCompat.setProperty(parent, Constants.PROP_PARAMETERS, parameters);
        }

        OasParameter param = parent.createParameter();
        Library.readNode(this._oldParameter, param);
        parent.restoreParameter(this._oldParameterIndex,  param);
    }

}
