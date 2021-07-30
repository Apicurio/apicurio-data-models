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
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.core.models.Document;

/**
 * A command used to delete a security scheme from an aaidocument.
 * @author c.desc2@gmail.com
 */
public class DeleteSecuritySchemeCommand_Aai20 extends DeleteSecuritySchemeCommand {

    DeleteSecuritySchemeCommand_Aai20() {
    }

    DeleteSecuritySchemeCommand_Aai20(String schemeName) {
        super(schemeName);
    }

    /**
     * @see DeleteSecuritySchemeCommand#doDeleteScheme(Document)
     */
    @Override
    protected Object doDeleteScheme(Document document) {
        Aai20Document aai20Document = (Aai20Document) document;
        if (ModelUtils.isDefined(aai20Document.components)) {
            return Library.writeNode(aai20Document.components.removeSecurityScheme(this._schemeName));
        } else {
            return null;
        }
    }

    /**
     * @see DeleteSecuritySchemeCommand#doRestoreScheme(Document, Object)
     */
    @Override
    protected void doRestoreScheme(Document document, Object oldScheme) {
        Aai20Document aai20Document = (Aai20Document) document;
        if (ModelUtils.isDefined(aai20Document.components)) {
            AaiSecurityScheme scheme = new Aai20NodeFactory().createSecurityScheme(aai20Document.components, this._schemeName);
            Library.readNode(oldScheme, scheme);
            aai20Document.components.addSecurityScheme(this._schemeName, scheme);
        }
    }
    
}
