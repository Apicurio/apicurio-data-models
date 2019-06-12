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
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30ParameterDefinition;

/**
 * OAI 3.0 impl specialized for changing parameter definitions.  Differs primarily in
 * the undo logic.
 * @author eric.wittmann@gmail.com
 */
public class ChangeParameterDefinitionTypeCommand_30 extends ChangeParameterTypeCommand_30 {

    ChangeParameterDefinitionTypeCommand_30() {
    }
    
    ChangeParameterDefinitionTypeCommand_30(Parameter parameter, SimplifiedParameterType newType) {
        super(parameter, newType);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ChangeParameterTypeCommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangeParameterDefinitionType] Reverting.");
        Oas30ParameterDefinition param = (Oas30ParameterDefinition) this._paramPath.resolve(document);
        if (this.isNullOrUndefined(param)) {
            return;
        }

        Oas30Document doc30 = (Oas30Document) document;
        
        // Remove the old/updated parameter.
        doc30.components.removeParameterDefinition(param.getName());

        // Restore the parameter from before the command executed.
        Oas30ParameterDefinition oldParam = doc30.components.createParameterDefinition(param.getName());
        Library.readNode(this._oldParameter, oldParam);
        doc30.components.addParameterDefinition(param.getName(), oldParam);
    }

}
