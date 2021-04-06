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

import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20SecurityScheme;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.SecurityScheme;

/**
 * A command used to change a security scheme in an aaidocument.
 * @author c.desc2@gmail.com
 */
public class ChangeSecuritySchemeCommand_Aai20 extends ChangeSecuritySchemeCommand {

    ChangeSecuritySchemeCommand_Aai20() {
    }

    ChangeSecuritySchemeCommand_Aai20(SecurityScheme scheme) {
        super(scheme);
    }
    
    /**
     * @see ChangeSecuritySchemeCommand#getSchemeFromDocument(Document)
     */
    @Override
    protected SecurityScheme getSchemeFromDocument(Document document) {
        Aai20Document aai20Document = (Aai20Document) document;
        if (this.isNullOrUndefined(aai20Document.components)) {
            return null;
        }
        return aai20Document.components.getSecurityScheme(this._schemeName);
    }
    
    /**
     * @see ChangeSecuritySchemeCommand#nullScheme(SecurityScheme)
     */
    @Override
    protected void nullScheme(SecurityScheme scheme) {
        super.nullScheme(scheme);
        Aai20SecurityScheme aai20SecurityScheme = (Aai20SecurityScheme) scheme;
        aai20SecurityScheme.$ref = null;
        aai20SecurityScheme.scheme = null;
        aai20SecurityScheme.bearerFormat = null;
        aai20SecurityScheme.flows = null;
        aai20SecurityScheme.openIdConnectUrl = null;
    }

}
