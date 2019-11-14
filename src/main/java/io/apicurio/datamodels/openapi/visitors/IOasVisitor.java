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

package io.apicurio.datamodels.openapi.visitors;

import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.models.common.IExample;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.models.IOasPropertySchema;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.models.OasPaths;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.models.OasResponses;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.models.OasXML;

/**
 * The common/shared OpenAPI visitor interface.  All OpenAPI visitors must implement this.
 * @author eric.wittmann@gmail.com
 */
public interface IOasVisitor extends IVisitor {

    public void visitPaths(OasPaths node);
    public void visitPathItem(OasPathItem node);
    public void visitResponse(OasResponse node);
    public void visitResponses(OasResponses node);
    public void visitXML(OasXML node);
    public void visitAllOfSchema(OasSchema node);
    public void visitItemsSchema(OasSchema node);
    public void visitAdditionalPropertiesSchema(OasSchema node);
    public void visitPropertySchema(IOasPropertySchema node);
    public void visitHeader(OasHeader node);
    public void visitResponseDefinition(IDefinition node);
    void visitExample(IExample node);

}
