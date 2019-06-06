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

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityScheme;

/**
 * @author eric.wittmann@gmail.com
 */
public class ChangeSecuritySchemeCommand_20 extends ChangeSecuritySchemeCommand {

    ChangeSecuritySchemeCommand_20() {
    }

    ChangeSecuritySchemeCommand_20(SecurityScheme scheme) {
        super(scheme);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ChangeSecuritySchemeCommand#getSchemeFromDocument(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    protected SecurityScheme getSchemeFromDocument(Document document) {
        Oas20Document doc20 = (Oas20Document) document;
        if (this.isNullOrUndefined(doc20.securityDefinitions)) {
            return null;
        }
        return doc20.securityDefinitions.getSecurityScheme(this._schemeName);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ChangeSecuritySchemeCommand#nullScheme(io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    @Override
    protected void nullScheme(SecurityScheme scheme) {
        super.nullScheme(scheme);
        Oas20SecurityScheme scheme20 = (Oas20SecurityScheme) scheme;
        scheme20.tokenUrl = null;
        scheme20.authorizationUrl = null;
        scheme20.flow = null;
        scheme20.scopes = null;
    }

}
