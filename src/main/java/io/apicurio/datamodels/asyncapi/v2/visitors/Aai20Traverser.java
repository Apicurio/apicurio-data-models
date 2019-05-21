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

package io.apicurio.datamodels.asyncapi.v2.visitors;

import io.apicurio.datamodels.asyncapi.visitors.AaiTraverser;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.v2.models.Oas20Definitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20Example;
import io.apicurio.datamodels.openapi.v2.models.Oas20Header;
import io.apicurio.datamodels.openapi.v2.models.Oas20Headers;
import io.apicurio.datamodels.openapi.v2.models.Oas20Items;
import io.apicurio.datamodels.openapi.v2.models.Oas20Parameter;
import io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Response;
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Scopes;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityScheme;
import io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor;
import io.apicurio.datamodels.openapi.visitors.OasTraverser;

/**
 * An OpenAPI 2.0 data model traverser.
 * @author eric.wittmann@gmail.com
 */
public class Aai20Traverser extends AaiTraverser implements IAai20Visitor {

    /**
     * Constructor.
     * @param visitor
     */
    public Aai20Traverser(IAai20Visitor visitor) {
        super(visitor);
    }
}
