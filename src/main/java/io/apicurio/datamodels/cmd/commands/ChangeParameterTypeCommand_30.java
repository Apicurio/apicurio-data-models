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

import io.apicurio.datamodels.cmd.models.SimplifiedParameterType;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.cmd.util.SimplifiedTypeUtil;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.openapi.v3.models.Oas30Parameter;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema;

/**
 * OAI 3.0 impl.
 * @author eric.wittmann@gmail.com
 */
public class ChangeParameterTypeCommand_30 extends ChangeParameterTypeCommand {

    ChangeParameterTypeCommand_30() {
    }
    
    ChangeParameterTypeCommand_30(Parameter parameter, SimplifiedParameterType newType) {
        super(parameter, newType);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ChangeParameterTypeCommand#doChangeParameter(io.apicurio.datamodels.core.models.Document, io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    protected void doChangeParameter(Document document, Parameter parameter) {
        Oas30Parameter param = (Oas30Parameter) parameter;

        Oas30Schema schema = (Oas30Schema) param.createSchema();
        SimplifiedTypeUtil.setSimplifiedType(schema, this._newType);
        parameter.schema = schema;
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
        Oas30Parameter param = (Oas30Parameter) parameter;
        Oas30Parameter oldParam = (Oas30Parameter) oldParameter;

        param.schema = oldParam.schema;
        if (ModelUtils.isDefined(param.schema)) {
            param.schema._parent = param;
            param.schema._ownerDocument = param.ownerDocument();
        }
        param.required = oldParam.required;        
    }

}
