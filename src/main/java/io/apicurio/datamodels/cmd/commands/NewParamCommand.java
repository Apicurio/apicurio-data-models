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
import io.apicurio.datamodels.cmd.models.SimplifiedParameterType;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.cmd.util.SimplifiedTypeUtil;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.IOasParameterParent;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.v2.models.Oas20Parameter;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema;

/**
 * A command used to create a new parameter.
 * @author eric.wittmann@gmail.com
 */
public class NewParamCommand extends AbstractCommand {

    public String _paramName;
    public String _paramType;
    public NodePath _parentPath;
    public String _description;
    public SimplifiedParameterType _newType;
    public boolean _override;

    public boolean _created;
    
    NewParamCommand() {
    }
    
    NewParamCommand(IOasParameterParent parent, String paramName, String paramType, String description, 
            SimplifiedParameterType newType, boolean override) {
        this._parentPath = Library.createNodePath((Node) parent);
        this._paramName = paramName;
        this._paramType = paramType;
        this._description = description;
        this._newType = newType;
        this._override = override;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewParamCommand] Executing.");

        this._created = false;

        IOasParameterParent parent = (IOasParameterParent) this._parentPath.resolve(document);

        if (this.isNullOrUndefined(parent)) {
            LoggerCompat.info("[NewParamCommand] Parent node (operation or path item) is null.");
            return;
        }

        if (this.hasParam(this._paramName, this._paramType, parent)) {
            LoggerCompat.info("[NewParamCommand] Param %s of type %s already exists.", this._paramName, this._paramType);
            return;
        }

        List<OasParameter> parameters = parent.getParameters();
        
        if (this.isNullOrUndefined(parameters)) {
            parameters = new ArrayList<>();
            NodeCompat.setProperty(parent, Constants.PROP_PARAMETERS, parameters);
        }

        OasParameter param = parent.createParameter();
        boolean configured = false;
        // If overriding a param from the path level, clone it!
        if (this._override) {
            OasParameter oparam = this.findOverridableParam((OasOperation) parent);
            if (ModelUtils.isDefined(oparam)) {
                Library.readNode(Library.writeNode(oparam), param);
                configured = true;
            }
        }
        // If not overriding, then set the basics only.
        if (!configured) {
            param.in = this._paramType;
            param.name = this._paramName;
            if (NodeCompat.equals(param.in, "path")) {
                param.required = true;
            }
            if (ModelUtils.isDefined(this._description)) {
                param.description = this._description;
            }
            if (ModelUtils.isDefined(this._newType)) {
                this._setParameterType(param);
            }
        }
        parent.addParameter(param);
        LoggerCompat.info("[NewParamCommand] Param %s of type %s created successfully.", param.name, param.in);

        this._created = true;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewParamCommand] Reverting.");
        if (!this._created) {
            return;
        }

        IOasParameterParent parent = (IOasParameterParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        OasParameter theParam = null;
        List<OasParameter> parameters = parent.getParameters();
        
        for (OasParameter param : parameters) {
            if (NodeCompat.equals(param.in, this._paramType) && NodeCompat.equals(param.name, this._paramName)) {
                theParam = param;
                break;
            }
        }

        // If found, remove it from the params.
        if (ModelUtils.isDefined(theParam)) {
            parameters.remove(parameters.indexOf(theParam));

            if (parameters.size() == 0) {
                NodeCompat.setProperty(parent, Constants.PROP_PARAMETERS, null);
            }
        }
    }

    /**
     * Sets the parameter type.
     * @param parameter
     */
    protected void _setParameterType(OasParameter parameter) {
        if (parameter.ownerDocument().getDocumentType() == DocumentType.openapi2) {
            Oas20Parameter param = (Oas20Parameter) parameter;
            SimplifiedTypeUtil.setSimplifiedTypeOnParam(param, this._newType);
        } else {
            Oas30Schema schema = (Oas30Schema) parameter.createSchema();
            SimplifiedTypeUtil.setSimplifiedType(schema, this._newType);
            parameter.schema = schema;
        }

        Boolean required = this._newType.required;
        if (NodeCompat.equals(parameter.in, "path")) {
            required = true;
        }
        if (ModelUtils.isDefined(required)) {
            parameter.required = required;
        }
    }

    /**
     * Returns true if the given param already exists in the parent.
     * @param paramName
     * @param paramType
     * @param parent
     * @returns {boolean}
     */
    private boolean hasParam(String paramName, String paramType, IOasParameterParent parent) {
        return ModelUtils.isDefined(parent.getParameter(paramType, paramName));
    }

    /**
     * Tries to find the parameter being overridden (if any).  Returns null if it can't
     * find something.
     */
    public OasParameter findOverridableParam(OasOperation operation) {
        OasParameter rval = null;
        OasPathItem pathItem = (OasPathItem) operation.parent();
        
        if (ModelUtils.isDefined(pathItem)) {
            rval = pathItem.getParameter(this._paramType, this._paramName);
        }
        return rval;
    }

}
