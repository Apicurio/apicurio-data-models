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
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityScheme;

/**
 * @author eric.wittmann@gmail.com
 */
public class DeleteSecuritySchemeCommand_20 extends DeleteSecuritySchemeCommand {

    public int _oldSchemeIndex;

    DeleteSecuritySchemeCommand_20() {
    }
    
    DeleteSecuritySchemeCommand_20(String schemeName) {
        super(schemeName);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.DeleteSecuritySchemeCommand#doDeleteScheme(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    protected Object doDeleteScheme(Document document) {
        Oas20Document doc20 = (Oas20Document) document;
        Oas20SecurityDefinitions definitions = doc20.securityDefinitions;
        if (this.isNullOrUndefined(definitions)) {
            return null;
        }

        this._oldSchemeIndex = definitions.getItemNames().indexOf(this._schemeName);
        return Library.writeNode(definitions.removeSecurityScheme(this._schemeName));
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.DeleteSecuritySchemeCommand#doRestoreScheme(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected void doRestoreScheme(Document document, Object oldScheme) {
        Oas20Document doc20 = (Oas20Document) document;
        Oas20SecurityDefinitions definitions = doc20.securityDefinitions;
        if (this.isNullOrUndefined(definitions) || this.isNullOrUndefined(oldScheme)) {
            return;
        }

        Oas20SecurityScheme scheme = definitions.createSecurityScheme(this._schemeName);
        Library.readNode(oldScheme, scheme);
        definitions.restoreSecurityScheme(this._oldSchemeIndex, this._schemeName, scheme);
    }

}
