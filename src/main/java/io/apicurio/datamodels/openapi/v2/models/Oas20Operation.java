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

package io.apicurio.datamodels.openapi.v2.models;

import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasResponses;
import io.apicurio.datamodels.openapi.models.OasSecurityRequirement;

/**
 * Models an OpenAPI 2.0 operation.
 * @author eric.wittmann@gmail.com
 */
public class Oas20Operation extends OasOperation {

    /**
     * Constructor.
     * @param method
     */
    public Oas20Operation(String method) {
        super(method);
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasOperation#createParameter()
     */
    @Override
    public OasParameter createParameter() {
        OasParameter param = new Oas20Parameter();
        param._ownerDocument = this.ownerDocument();
        param._parent = this;
        return param;
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasOperation#createResponses()
     */
    @Override
    public OasResponses createResponses() {
        OasResponses responses = new Oas20Responses();
        responses._ownerDocument = this.ownerDocument();
        responses._parent = this;
        return responses;
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasOperation#createSecurityRequirement()
     */
    @Override
    public OasSecurityRequirement createSecurityRequirement() {
        OasSecurityRequirement requirement = new Oas20SecurityRequirement();
        requirement._ownerDocument = this.ownerDocument();
        requirement._parent = this;
        return requirement;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.Operation#createExternalDocumentation()
     */
    @Override
    public ExternalDocumentation createExternalDocumentation() {
        ExternalDocumentation externalDocs = new Oas20ExternalDocumentation();
        externalDocs._ownerDocument = this.ownerDocument();
        externalDocs._parent = this;
        return externalDocs;
    }

}
