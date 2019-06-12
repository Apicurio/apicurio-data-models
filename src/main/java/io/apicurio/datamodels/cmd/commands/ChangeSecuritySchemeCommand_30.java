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
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30SecurityScheme;

/**
 * @author eric.wittmann@gmail.com
 */
public class ChangeSecuritySchemeCommand_30 extends ChangeSecuritySchemeCommand {

    ChangeSecuritySchemeCommand_30() {
    }

    ChangeSecuritySchemeCommand_30(SecurityScheme scheme) {
        super(scheme);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ChangeSecuritySchemeCommand#getSchemeFromDocument(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    protected SecurityScheme getSchemeFromDocument(Document document) {
        Oas30Document doc30 = (Oas30Document) document;
        if (this.isNullOrUndefined(doc30.components)) {
            return null;
        }
        return doc30.components.getSecurityScheme(this._schemeName);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.commands.ChangeSecuritySchemeCommand#nullScheme(io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    @Override
    protected void nullScheme(SecurityScheme scheme) {
        super.nullScheme(scheme);
        Oas30SecurityScheme scheme30 = (Oas30SecurityScheme) scheme;
        scheme30.$ref = null;
        scheme30.scheme = null;
        scheme30.bearerFormat = null;
        scheme30.flows = null;
        scheme30.openIdConnectUrl = null;
    }

}
