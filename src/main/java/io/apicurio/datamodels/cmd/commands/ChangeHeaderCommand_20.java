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

import io.apicurio.datamodels.cmd.models.SimplifiedParameterType;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.cmd.util.SimplifiedTypeUtil;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.v2.models.Oas20Header;
import io.apicurio.datamodels.openapi.v2.models.Oas20Parameter;
import io.apicurio.datamodels.openapi.v2.models.Oas20Schema;

/**
 * OAI 2.0 Impl.
 * @author vvilerio
 */
public class ChangeHeaderCommand_20 extends ChangeHeaderCommand {

    ChangeHeaderCommand_20() {
    }

    ChangeHeaderCommand_20(Oas20Header header, OasHeader newHeader, String name) {
        super(header, newHeader, name);
    }

    /**
     * @see ChangeHeaderCommand#doChangeHeader(Document, OasHeader, String, String)
     */
    @Override
    protected void doChangeHeader(Document document, OasHeader pHeader,  String pDescription, String pExampleName) {
        Oas20Header header = (Oas20Header) pHeader;

//        header.schema = header.setAttribute("schema",);
        header.description = pDescription;
//        header.example = header.setAttribute("example",pExampleName);

    }

    /**
     * @see ChangeParameterTypeCommand#doRestoreParameter(Parameter, Parameter)
     */
    @Override
    protected void doRestoreHeader(OasHeader pHeader, OasHeader pOldHeader) {
        Oas20Header header = (Oas20Header) pHeader;
        Oas20Header pOlHeader = (Oas20Header) pOldHeader;

        header.type = pOlHeader.type;
        header.format = pOlHeader.format;
        header.items = pOlHeader.items;
        if (ModelUtils.isDefined(header.items)) {
            header.items._parent = header;
            header.items._ownerDocument = header.ownerDocument();
        }
        header.description = pOlHeader.description;

    }

}
