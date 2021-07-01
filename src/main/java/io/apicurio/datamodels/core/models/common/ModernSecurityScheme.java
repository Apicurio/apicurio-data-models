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

package io.apicurio.datamodels.core.models.common;

import io.apicurio.datamodels.core.models.IReferenceNode;

/**
 * Implements the slightly more modern approach to a security scheme.
 * @author eric.wittmann@gmail.com
 */
public abstract class ModernSecurityScheme extends SecurityScheme implements IReferenceNode, IDefinition {

    public String $ref;
    public String scheme;
    public String bearerFormat;
    public OAuthFlows flows;
    public String openIdConnectUrl;

    /**
     * Constructor.
     * @param schemeName
     */
    public ModernSecurityScheme(String schemeName) {
        super(schemeName);
    }

    @Override
    public String getReference() {
        return $ref;
    }

    @Override
    public void setReference(String reference) {
        $ref = reference;
    }

    public abstract OAuthFlows createOAuthFlows();

}
