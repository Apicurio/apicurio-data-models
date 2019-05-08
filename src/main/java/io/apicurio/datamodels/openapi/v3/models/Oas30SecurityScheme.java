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

package io.apicurio.datamodels.openapi.v3.models;

import io.apicurio.datamodels.core.models.common.ModernSecurityScheme;
import io.apicurio.datamodels.core.models.common.OAuthFlows;

/**
 * Models an OpenAPI 3.0.x security scheme.
 * @author eric.wittmann@gmail.com
 */
public class Oas30SecurityScheme extends ModernSecurityScheme {

    /**
     * Constructor.
     * @param schemeName
     */
    public Oas30SecurityScheme(String schemeName) {
        super(schemeName);
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.ModernSecurityScheme#createOAuthFlows()
     */
    @Override
    public OAuthFlows createOAuthFlows() {
        Oas30OAuthFlows rval = new Oas30OAuthFlows();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

}
