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

import java.util.List;

import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.Tag;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasPaths;
import io.apicurio.datamodels.openapi.models.OasSecurityRequirement;

/**
 * Models the root document of the OpenAPI 2.0 (aka Swagger) data model.
 * @author eric.wittmann@gmail.com
 */
public class Oas20Document extends OasDocument {

    public final String swagger = "2.0";
    public String host;
    public String basePath;
    public List<String> schemes;
    public List<String> consumes;
    public List<String> produces;
//    public definitions: Oas20Definitions;
//    public parameters: Oas20ParametersDefinitions;
//    public responses: Oas20ResponsesDefinitions;
//    public securityDefinitions: Oas20SecurityDefinitions;

    /**
     * @see io.apicurio.datamodels.core.models.Document#getDocumentType()
     */
    @Override
    public final DocumentType getDocumentType() {
        return DocumentType.openapi2;
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.models.OasDocument#createPaths()
     */
    @Override
    public OasPaths createPaths() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.models.OasDocument#createSecurityRequirement()
     */
    @Override
    public OasSecurityRequirement createSecurityRequirement() {
        OasSecurityRequirement requirement = new Oas20SecurityRequirement();
        requirement._ownerDocument = this.ownerDocument();
        requirement._parent = this;
        return requirement;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Document#createInfo()
     */
    @Override
    public Info createInfo() {
        Info info = new Oas20Info();
        info._ownerDocument = this.ownerDocument();
        info._parent = this;
        return info;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Document#createTag()
     */
    @Override
    public Tag createTag() {
        Tag tag = new Oas20Tag();
        tag._ownerDocument = this.ownerDocument();
        tag._parent = this;
        return tag;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Document#createExternalDocumentation()
     */
    @Override
    public ExternalDocumentation createExternalDocumentation() {
        ExternalDocumentation ed = new Oas20ExternalDocumentation();
        ed._ownerDocument = this.ownerDocument();
        ed._parent = this;
        return ed;
    }

}
