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
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30SecurityScheme;

/**
 * @author eric.wittmann@gmail.com
 */
public class DeleteSecuritySchemeCommand_30 extends DeleteSecuritySchemeCommand {
    
    public int _oldSchemeIndex;

    DeleteSecuritySchemeCommand_30() {
    }
    
    DeleteSecuritySchemeCommand_30(String schemeName) {
        super(schemeName);
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.DeleteSecuritySchemeCommand#doDeleteScheme(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    protected Object doDeleteScheme(Document document) {
        Oas30Document doc30 = (Oas30Document) document;
        if (ModelUtils.isDefined(doc30.components)) {
            this._oldSchemeIndex = doc30.components.getSecuritySchemeNames().indexOf(this._schemeName);
            return Library.writeNode(doc30.components.removeSecurityScheme(this._schemeName));
        } else {
            return null;
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.DeleteSecuritySchemeCommand#doRestoreScheme(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected void doRestoreScheme(Document document, Object oldScheme) {
        Oas30Document doc30 = (Oas30Document) document;
        if (ModelUtils.isDefined(doc30.components)) {
            Oas30SecurityScheme scheme = doc30.components.createSecurityScheme(this._schemeName);
            Library.readNode(oldScheme, scheme);
            doc30.components.restoreSecurityScheme(this._oldSchemeIndex, this._schemeName, scheme);
        }
    }
    
}
