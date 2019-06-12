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

import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.v2.models.Oas20Parameter;

/**
 * @author eric.wittmann@gmail.com
 */
public class NewRequestBodyCommand_20 extends NewRequestBodyCommand {
    
    NewRequestBodyCommand_20() {
    }
    
    NewRequestBodyCommand_20(OasOperation operation) {
        super(operation);
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.NewRequestBodyCommand#hasRequestBody(io.apicurio.datamodels.openapi.models.OasOperation)
     */
    @Override
    protected boolean hasRequestBody(OasOperation operation) {
        return ModelUtils.isDefined(operation) && operation.getParameters("body").size() > 0;
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.NewRequestBodyCommand#doCreateRequestBody(io.apicurio.datamodels.openapi.models.OasOperation)
     */
    @Override
    protected void doCreateRequestBody(OasOperation operation) {
        if (this.isNullOrUndefined(operation.parameters)) {
            operation.parameters = new ArrayList<>();
        }
        Oas20Parameter param = (Oas20Parameter) operation.createParameter();
        param.in = "body";
        param.name = "body";
        operation.addParameter(param);
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.NewRequestBodyCommand#doRemoveRequestBody(io.apicurio.datamodels.openapi.models.OasOperation)
     */
    @Override
    protected void doRemoveRequestBody(OasOperation operation) {
        List<OasParameter> parameters = operation.getParameters();
        List<OasParameter> bodyParams = operation.getParameters("body");
        for (OasParameter bodyParam : bodyParams) {
            parameters.remove(parameters.indexOf(bodyParam));
        }
        
        parameters = operation.getParameters();
        if (parameters.size() == 0) {
            NodeCompat.setProperty(operation, Constants.PROP_PARAMETERS, null);
        }
    }

}
