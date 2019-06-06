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
import io.apicurio.datamodels.cmd.models.SimplifiedParameterType;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.openapi.models.IOasParameterParent;

/**
 * A command used to modify the type of a parameter of an operation.
 * @author eric.wittmann@gmail.com
 */
public abstract class ChangeParameterTypeCommand extends AbstractCommand {

    public NodePath _paramPath;
    public SimplifiedParameterType _newType;

    @JsonDeserialize(using=JsonNodeDeserializer.class)
    public Object _oldParameter;

    ChangeParameterTypeCommand() {
    }
    
    ChangeParameterTypeCommand(Parameter parameter, SimplifiedParameterType newType) {
        this._paramPath = Library.createNodePath(parameter);
        this._newType = newType;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangeParameterTypeCommand] Executing.");
        Parameter param = (Parameter) this._paramPath.resolve(document);
        if (this.isNullOrUndefined(param)) {
            return;
        }

        // Save the old info (for later undo operation)
        this._oldParameter = Library.writeNode(param);

        // Change the parameter type
        this.doChangeParameter(document, param);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangeParameterTypeCommand] Reverting.");
        Parameter param = (Parameter) this._paramPath.resolve(document);
        if (this.isNullOrUndefined(param)) {
            return;
        }

        IOasParameterParent parent = (IOasParameterParent) param.parent();

        Parameter oldParam = parent.createParameter();
        Library.readNode(this._oldParameter, oldParam);
        this.doRestoreParameter(param, oldParam);
        //let pindex: number = parent.parameters.indexOf(param);
        //parent.parameters.splice(pindex, 1, oldParam);
    }

    /**
     * Changes the parameter.
     * @param document
     * @param parameter
     */
    protected abstract void doChangeParameter(Document document, Parameter parameter);

    /**
     * Called to resotre the given parameter back to the old settings (provided in oldParameter).
     * @param param
     * @param oldParam
     */
    protected abstract void doRestoreParameter(Parameter param, Parameter oldParam);

}
