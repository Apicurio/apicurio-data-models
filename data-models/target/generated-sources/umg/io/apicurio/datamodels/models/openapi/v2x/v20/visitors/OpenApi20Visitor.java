package io.apicurio.datamodels.models.openapi.v2x.v20.visitors;

import io.apicurio.datamodels.models.openapi.OpenApiDefinitions;
import io.apicurio.datamodels.models.openapi.OpenApiItems;
import io.apicurio.datamodels.models.openapi.OpenApiParameterDefinitions;
import io.apicurio.datamodels.models.openapi.OpenApiResponseDefinitions;
import io.apicurio.datamodels.models.openapi.OpenApiScopes;
import io.apicurio.datamodels.models.openapi.OpenApiSecurityDefinitions;
import io.apicurio.datamodels.models.openapi.visitors.OpenApiVisitor;

public interface OpenApi20Visitor extends OpenApiVisitor {

	public void visitParameterDefinitions(OpenApiParameterDefinitions node);

	public void visitItems(OpenApiItems node);

	public void visitDefinitions(OpenApiDefinitions node);

	public void visitResponseDefinitions(OpenApiResponseDefinitions node);

	public void visitScopes(OpenApiScopes node);

	public void visitSecurityDefinitions(OpenApiSecurityDefinitions node);
}