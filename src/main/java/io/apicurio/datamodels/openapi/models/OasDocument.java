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

package io.apicurio.datamodels.openapi.models;

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;

/**
 * Models an OpenAPI document.
 * @author eric.wittmann@gmail.com
 */
public abstract class OasDocument extends Document {

    public OasPaths paths;
    public List<OasSecurityRequirement> security;

    /**
     * Creates an OAS Paths object.
     */
    public abstract OasPaths createPaths();

    /**
     * Creates an OAS Security Requirement object.
     */
    public abstract OasSecurityRequirement createSecurityRequirement();

    /**
     * Adds a security requirement child.
     * @param securityRequirement
     */
    public OasSecurityRequirement addSecurityRequirement(OasSecurityRequirement securityRequirement) {
        if (this.security == null) {
            this.security = new ArrayList<>();
        }
        this.security.add(securityRequirement);
        return securityRequirement;
    }

    /**
     * Returns true if the document is an OpenAPI/Swagger 2.0 document.
     */
    public boolean is2xDocument() {
        return this.getDocumentType() == DocumentType.openapi2;
    }

    /**
     * Returns true if the document is an OpenAPI 3.x document.
     */
    public boolean is3xDocument() {
        return this.getDocumentType() == DocumentType.openapi3;
    }

}
