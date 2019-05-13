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

package io.apicurio.datamodels.openapi.v2.visitors;

import io.apicurio.datamodels.openapi.v2.models.Oas20Definitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Example;
import io.apicurio.datamodels.openapi.v2.models.Oas20Headers;
import io.apicurio.datamodels.openapi.v2.models.Oas20Items;
import io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Scopes;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityDefinitions;
import io.apicurio.datamodels.openapi.visitors.IOasVisitor;

/**
 * The OpenAPI 2.0 version of the visitor interface.
 * @author eric.wittmann@gmail.com
 */
public interface IOas20Visitor extends IOasVisitor {

    public void visitItems(Oas20Items node);
    public void visitScopes(Oas20Scopes node);
    public void visitSecurityDefinitions(Oas20SecurityDefinitions node);
    public void visitDefinitions(Oas20Definitions node);
    public void visitParameterDefinitions(Oas20ParameterDefinitions node);
    public void visitExample(Oas20Example node);
    public void visitHeaders(Oas20Headers node);
    public void visitResponseDefinitions(Oas20ResponseDefinitions node);

}
