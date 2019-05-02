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

package io.apicurio.datamodels.core.visitors;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.ValidationProblem;
import io.apicurio.datamodels.core.models.common.Contact;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.ISchemaDefinition;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.License;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
import io.apicurio.datamodels.core.models.common.Tag;

/**
 * A basic visitor interface used to visit data model instances.
 * @author eric.wittmann@gmail.com
 */
public interface IVisitor {

    void visitDocument(Document node);
    void visitInfo(Info node);
    void visitContact(Contact node);
    void visitLicense(License node);
    void visitTag(Tag node);
    void visitExternalDocumentation(ExternalDocumentation node);
    void visitExtension(Extension node);
    void visitServer(Server node);
    void visitSecurityRequirement(SecurityRequirement node);
    void visitServerVariable(ServerVariable node);
    void visitValidationProblem(ValidationProblem problem);
    void visitSchema(Schema node);
    void visitSchemaDefinition(ISchemaDefinition node);
    void visitParameter(Parameter node);
    void visitOperation(Operation node);
    void visitSecurityScheme(SecurityScheme node);

}
