package io.apicurio.datamodels.models.openapi.visitors;

import io.apicurio.datamodels.models.Components;
import io.apicurio.datamodels.models.Contact;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Example;
import io.apicurio.datamodels.models.ExternalDocumentation;
import io.apicurio.datamodels.models.Info;
import io.apicurio.datamodels.models.License;
import io.apicurio.datamodels.models.Link;
import io.apicurio.datamodels.models.OAuthFlow;
import io.apicurio.datamodels.models.OAuthFlows;
import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.Tag;
import io.apicurio.datamodels.models.openapi.OpenApiCallback;
import io.apicurio.datamodels.models.openapi.OpenApiDefinitions;
import io.apicurio.datamodels.models.openapi.OpenApiDiscriminator;
import io.apicurio.datamodels.models.openapi.OpenApiEncoding;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.OpenApiItems;
import io.apicurio.datamodels.models.openapi.OpenApiMediaType;
import io.apicurio.datamodels.models.openapi.OpenApiParameterDefinitions;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.models.openapi.OpenApiPaths;
import io.apicurio.datamodels.models.openapi.OpenApiRequestBody;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.models.openapi.OpenApiResponseDefinitions;
import io.apicurio.datamodels.models.openapi.OpenApiResponses;
import io.apicurio.datamodels.models.openapi.OpenApiScopes;
import io.apicurio.datamodels.models.openapi.OpenApiSecurityDefinitions;
import io.apicurio.datamodels.models.openapi.OpenApiXML;
import io.apicurio.datamodels.models.openapi.v2x.v20.visitors.OpenApi20Visitor;
import io.apicurio.datamodels.models.openapi.v3x.v30.visitors.OpenApi30Visitor;
import io.apicurio.datamodels.models.openapi.v3x.v31.visitors.OpenApi31Visitor;
import io.apicurio.datamodels.models.openapi.v3x.v32.visitors.OpenApi32Visitor;

public class CombinedOpenApiVisitorAdapter
		implements
			OpenApi20Visitor,
			OpenApi30Visitor,
			OpenApi32Visitor,
			OpenApi31Visitor {

	@Override
	public void visitParameterDefinitions(OpenApiParameterDefinitions node) {
	}

	@Override
	public void visitItems(OpenApiItems node) {
	}

	@Override
	public void visitDefinitions(OpenApiDefinitions node) {
	}

	@Override
	public void visitResponseDefinitions(OpenApiResponseDefinitions node) {
	}

	@Override
	public void visitScopes(OpenApiScopes node) {
	}

	@Override
	public void visitSecurityDefinitions(OpenApiSecurityDefinitions node) {
	}

	@Override
	public void visitLicense(License node) {
	}

	@Override
	public void visitPaths(OpenApiPaths node) {
	}

	@Override
	public void visitTag(Tag node) {
	}

	@Override
	public void visitHeader(OpenApiHeader node) {
	}

	@Override
	public void visitSchema(Schema node) {
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
	}

	@Override
	public void visitXML(OpenApiXML node) {
	}

	@Override
	public void visitParameter(Parameter node) {
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
	}

	@Override
	public void visitPathItem(OpenApiPathItem node) {
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
	}

	@Override
	public void visitExample(Example node) {
	}

	@Override
	public void visitResponse(OpenApiResponse node) {
	}

	@Override
	public void visitContact(Contact node) {
	}

	@Override
	public void visitInfo(Info node) {
	}

	@Override
	public void visitOperation(Operation node) {
	}

	@Override
	public void visitResponses(OpenApiResponses node) {
	}

	@Override
	public void visitDocument(Document node) {
	}

	@Override
	public void visitServer(Server node) {
	}

	@Override
	public void visitLink(Link node) {
	}

	@Override
	public void visitRequestBody(OpenApiRequestBody node) {
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
	}

	@Override
	public void visitMediaType(OpenApiMediaType node) {
	}

	@Override
	public void visitEncoding(OpenApiEncoding node) {
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
	}

	@Override
	public void visitCallback(OpenApiCallback node) {
	}

	@Override
	public void visitComponents(Components node) {
	}

	@Override
	public void visitDiscriminator(OpenApiDiscriminator node) {
	}
}