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
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityScheme;

/**
 * @author eric.wittmann@gmail.com
 */
public class NewSecuritySchemeCommand_20 extends NewSecuritySchemeCommand {

    public boolean _nullSecurityDefinitions;

    NewSecuritySchemeCommand_20() {
    }
    
    NewSecuritySchemeCommand_20(SecurityScheme scheme) {
        super(scheme);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewSecuritySchemeCommand] Executing.");
        
        Oas20Document doc20 = (Oas20Document) document;

        this._nullSecurityDefinitions = false;
        if (this.isNullOrUndefined(doc20.securityDefinitions)) {
            doc20.securityDefinitions = doc20.createSecurityDefinitions();
            this._nullSecurityDefinitions = true;
        }

        if (this.isNullOrUndefined(doc20.securityDefinitions.getSecurityScheme(this._schemeName))) {
            Oas20SecurityScheme scheme = doc20.securityDefinitions.createSecurityScheme(this._schemeName);
            Library.readNode(this._scheme, scheme);
            doc20.securityDefinitions.addSecurityScheme(this._schemeName, scheme);
            this._schemeExisted = false;
        } else {
            this._schemeExisted = true;
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewSecuritySchemeCommand] Reverting.");
        if (this._schemeExisted) {
            return;
        }
        Oas20Document doc20 = (Oas20Document) document;
        if (this._nullSecurityDefinitions) {
            doc20.securityDefinitions = null;
            return;
        }
        doc20.securityDefinitions.removeSecurityScheme(this._schemeName);
    }

}
