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
import io.apicurio.datamodels.cmd.models.SimplifiedParameterType;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.cmd.util.SimplifiedTypeUtil;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.openapi.v2.models.Oas20Parameter;
import io.apicurio.datamodels.openapi.v2.models.Oas20Schema;

/**
 * OAI 2.0 Impl.
 * @author eric.wittmann@gmail.com
 */
public class ChangeParameterTypeCommand_20 extends ChangeParameterTypeCommand {
    
    ChangeParameterTypeCommand_20() {
    }
    
    ChangeParameterTypeCommand_20(Parameter parameter, SimplifiedParameterType newType) {
        super(parameter, newType);
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.ChangeParameterTypeCommand#doChangeParameter(io.apicurio.datamodels.core.models.Document, io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    protected void doChangeParameter(Document document, Parameter parameter) {
        Oas20Parameter param = (Oas20Parameter) parameter;
        // If it's a body param, change the schema child.  Otherwise change the param itself.
        if (NodeCompat.equals(param.in, "body")) {
            param.schema = param.createSchema();
            SimplifiedTypeUtil.setSimplifiedType((Oas20Schema) param.schema, this._newType);
        } else {
            SimplifiedTypeUtil.setSimplifiedTypeOnParam(param, this._newType);
        }

        Boolean required = this._newType.required;
        if (NodeCompat.equals(param.in, "path")) {
            required = true;
        }
        if (!this.isNullOrUndefined(required)) {
            param.required = required;
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.ChangeParameterTypeCommand#doRestoreParameter(io.apicurio.datamodels.core.models.common.Parameter, io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    protected void doRestoreParameter(Parameter parameter, Parameter oldParameter) {
        Oas20Parameter param = (Oas20Parameter) parameter;
        Oas20Parameter oldParam = (Oas20Parameter) oldParameter;
        if (NodeCompat.equals(param.in, "body")) {
            param.schema = oldParam.schema;
            if (ModelUtils.isDefined(param.schema)) {
                param.schema._parent = param;
                param.schema._ownerDocument = param.ownerDocument();
            }
        } else {
            param.type = oldParam.type;
            param.format = oldParam.format;
            param.items = oldParam.items;
            if (ModelUtils.isDefined(param.items)) {
                param.items._parent = param;
                param.items._ownerDocument = param.ownerDocument();
            }
        }
        param.required = oldParam.required;
    }

}
