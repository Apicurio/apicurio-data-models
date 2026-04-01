package io.apicurio.datamodels.models.openapi.visitors;

import io.apicurio.datamodels.models.openapi.v2x.v20.visitors.OpenApi20Visitor;
import io.apicurio.datamodels.models.openapi.v3x.v30.visitors.OpenApi30Visitor;
import io.apicurio.datamodels.models.openapi.v3x.v31.visitors.OpenApi31Visitor;
import io.apicurio.datamodels.models.openapi.v3x.v32.visitors.OpenApi32Visitor;

public interface CombinedOpenApiVisitor extends OpenApi20Visitor, OpenApi30Visitor, OpenApi32Visitor, OpenApi31Visitor {
}