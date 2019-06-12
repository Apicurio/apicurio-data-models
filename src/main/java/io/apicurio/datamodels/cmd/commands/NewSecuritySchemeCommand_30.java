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
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30SecurityScheme;

/**
 * @author eric.wittmann@gmail.com
 */
public class NewSecuritySchemeCommand_30 extends NewSecuritySchemeCommand {

    public boolean _nullComponents;

    NewSecuritySchemeCommand_30() {
    }
    
    NewSecuritySchemeCommand_30(SecurityScheme scheme) {
        super(scheme);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewSecuritySchemeCommand] Executing.");
        Oas30Document doc30 = (Oas30Document) document;
        this._nullComponents = false;
        if (this.isNullOrUndefined(doc30.components)) {
            doc30.components = doc30.createComponents();
            this._nullComponents = true;
        }

        if (this.isNullOrUndefined(doc30.components.getSecurityScheme(this._schemeName))) {
            Oas30SecurityScheme scheme = doc30.components.createSecurityScheme(this._schemeName);
            Library.readNode(this._scheme, scheme);
            doc30.components.addSecurityScheme(this._schemeName, scheme);
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
        Oas30Document doc30 = (Oas30Document) document;
        if (this._nullComponents) {
            doc30.components = null;
            return;
        }
        doc30.components.removeSecurityScheme(this._schemeName);
    }
    
}
