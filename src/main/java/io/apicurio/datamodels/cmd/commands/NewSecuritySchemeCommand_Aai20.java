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
import io.apicurio.datamodels.asyncapi.models.AaiSecurityScheme;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20NodeFactory;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.SecurityScheme;

/**
 * A command used to create a new security scheme in an aaidocument.
 * @author c.desc2@gmail.com
 */
public class NewSecuritySchemeCommand_Aai20 extends NewSecuritySchemeCommand {

    public boolean _nullComponents;

    NewSecuritySchemeCommand_Aai20() {
    }

    NewSecuritySchemeCommand_Aai20(SecurityScheme scheme) {
        super(scheme);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[NewSecuritySchemeCommand_Aai20] Executing.");
        Aai20Document aai20Document = (Aai20Document) document;
        this._nullComponents = false;
        if (this.isNullOrUndefined(aai20Document.components)) {
            aai20Document.components = aai20Document.createComponents();
            this._nullComponents = true;
        }

        if (this.isNullOrUndefined(aai20Document.components.getSecurityScheme(this._schemeName))) {
            AaiSecurityScheme scheme = new Aai20NodeFactory().createSecurityScheme(aai20Document.components, this._schemeName);
            Library.readNode(this._scheme, scheme);
            aai20Document.components.addSecurityScheme(this._schemeName, scheme);
            this._schemeExisted = false;
        } else {
            this._schemeExisted = true;
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[NewSecuritySchemeCommand_Aai20] Reverting.");
        if (this._schemeExisted) {
            return;
        }
        Aai20Document aai20Document = (Aai20Document) document;
        if (this._nullComponents) {
            aai20Document.components = null;
            return;
        }
        aai20Document.components.removeSecurityScheme(this._schemeName);
    }
    
}
