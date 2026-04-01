package io.apicurio.datamodels.models.openapi.v3x.visitors;

import io.apicurio.datamodels.models.Components;
import io.apicurio.datamodels.models.Link;
import io.apicurio.datamodels.models.OAuthFlow;
import io.apicurio.datamodels.models.OAuthFlows;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.openapi.OpenApiCallback;
import io.apicurio.datamodels.models.openapi.OpenApiDiscriminator;
import io.apicurio.datamodels.models.openapi.OpenApiEncoding;
import io.apicurio.datamodels.models.openapi.OpenApiMediaType;
import io.apicurio.datamodels.models.openapi.OpenApiRequestBody;
import io.apicurio.datamodels.models.openapi.visitors.OpenApiVisitor;

public interface OpenApi3xVisitor extends OpenApiVisitor {

	public void visitServer(Server node);

	public void visitLink(Link node);

	public void visitRequestBody(OpenApiRequestBody node);

	public void visitOAuthFlows(OAuthFlows node);

	public void visitServerVariable(ServerVariable node);

	public void visitMediaType(OpenApiMediaType node);

	public void visitEncoding(OpenApiEncoding node);

	public void visitOAuthFlow(OAuthFlow node);

	public void visitCallback(OpenApiCallback node);

	public void visitComponents(Components node);

	public void visitDiscriminator(OpenApiDiscriminator node);
}