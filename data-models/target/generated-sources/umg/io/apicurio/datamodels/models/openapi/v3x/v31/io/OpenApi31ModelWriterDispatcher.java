package io.apicurio.datamodels.models.openapi.v3x.v31.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
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
import io.apicurio.datamodels.models.openapi.OpenApiDiscriminator;
import io.apicurio.datamodels.models.openapi.OpenApiEncoding;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.OpenApiMediaType;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.models.openapi.OpenApiPaths;
import io.apicurio.datamodels.models.openapi.OpenApiRequestBody;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.models.openapi.OpenApiResponses;
import io.apicurio.datamodels.models.openapi.OpenApiXML;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Callback;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Components;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Contact;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Discriminator;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Document;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Encoding;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Example;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31ExternalDocumentation;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Header;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Info;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31License;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Link;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31MediaType;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31OAuthFlow;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31OAuthFlows;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Operation;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Parameter;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31PathItem;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Paths;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31RequestBody;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Response;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Responses;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Schema;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31SecurityRequirement;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31SecurityScheme;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Server;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31ServerVariable;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Tag;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31XML;
import io.apicurio.datamodels.models.openapi.v3x.v31.visitors.OpenApi31Visitor;

public class OpenApi31ModelWriterDispatcher implements OpenApi31Visitor {

	private final ObjectNode json;
	private final OpenApi31ModelWriter writer;

	public OpenApi31ModelWriterDispatcher(ObjectNode json, OpenApi31ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitServer(Server node) {
		this.writer.writeServer((OpenApi31Server) node, this.json);
	}

	@Override
	public void visitLink(Link node) {
		this.writer.writeLink((OpenApi31Link) node, this.json);
	}

	@Override
	public void visitRequestBody(OpenApiRequestBody node) {
		this.writer.writeRequestBody((OpenApi31RequestBody) node, this.json);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.writer.writeOAuthFlows((OpenApi31OAuthFlows) node, this.json);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.writer.writeServerVariable((OpenApi31ServerVariable) node, this.json);
	}

	@Override
	public void visitMediaType(OpenApiMediaType node) {
		this.writer.writeMediaType((OpenApi31MediaType) node, this.json);
	}

	@Override
	public void visitEncoding(OpenApiEncoding node) {
		this.writer.writeEncoding((OpenApi31Encoding) node, this.json);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.writer.writeOAuthFlow((OpenApi31OAuthFlow) node, this.json);
	}

	@Override
	public void visitCallback(OpenApiCallback node) {
		this.writer.writeCallback((OpenApi31Callback) node, this.json);
	}

	@Override
	public void visitComponents(Components node) {
		this.writer.writeComponents((OpenApi31Components) node, this.json);
	}

	@Override
	public void visitDiscriminator(OpenApiDiscriminator node) {
		this.writer.writeDiscriminator((OpenApi31Discriminator) node, this.json);
	}

	@Override
	public void visitLicense(License node) {
		this.writer.writeLicense((OpenApi31License) node, this.json);
	}

	@Override
	public void visitPaths(OpenApiPaths node) {
		this.writer.writePaths((OpenApi31Paths) node, this.json);
	}

	@Override
	public void visitTag(Tag node) {
		this.writer.writeTag((OpenApi31Tag) node, this.json);
	}

	@Override
	public void visitHeader(OpenApiHeader node) {
		this.writer.writeHeader((OpenApi31Header) node, this.json);
	}

	@Override
	public void visitSchema(Schema node) {
		this.writer.writeSchema((OpenApi31Schema) node, this.json);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.writer.writeExternalDocumentation((OpenApi31ExternalDocumentation) node, this.json);
	}

	@Override
	public void visitXML(OpenApiXML node) {
		this.writer.writeXML((OpenApi31XML) node, this.json);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.writer.writeParameter((OpenApi31Parameter) node, this.json);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.writer.writeSecurityScheme((OpenApi31SecurityScheme) node, this.json);
	}

	@Override
	public void visitPathItem(OpenApiPathItem node) {
		this.writer.writePathItem((OpenApi31PathItem) node, this.json);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.writer.writeSecurityRequirement((OpenApi31SecurityRequirement) node, this.json);
	}

	@Override
	public void visitExample(Example node) {
		this.writer.writeExample((OpenApi31Example) node, this.json);
	}

	@Override
	public void visitResponse(OpenApiResponse node) {
		this.writer.writeResponse((OpenApi31Response) node, this.json);
	}

	@Override
	public void visitContact(Contact node) {
		this.writer.writeContact((OpenApi31Contact) node, this.json);
	}

	@Override
	public void visitInfo(Info node) {
		this.writer.writeInfo((OpenApi31Info) node, this.json);
	}

	@Override
	public void visitOperation(Operation node) {
		this.writer.writeOperation((OpenApi31Operation) node, this.json);
	}

	@Override
	public void visitResponses(OpenApiResponses node) {
		this.writer.writeResponses((OpenApi31Responses) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((OpenApi31Document) node, this.json);
	}
}