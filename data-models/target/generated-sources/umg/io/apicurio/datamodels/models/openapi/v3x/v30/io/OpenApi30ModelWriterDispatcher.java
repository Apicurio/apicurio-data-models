package io.apicurio.datamodels.models.openapi.v3x.v30.io;

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
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Callback;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Components;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Contact;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Discriminator;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Encoding;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Example;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30ExternalDocumentation;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Header;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Info;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30License;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Link;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30MediaType;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30OAuthFlow;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30OAuthFlows;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Operation;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Parameter;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30PathItem;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Paths;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30RequestBody;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Response;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Responses;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Schema;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30SecurityRequirement;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30SecurityScheme;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Server;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30ServerVariable;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Tag;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30XML;
import io.apicurio.datamodels.models.openapi.v3x.v30.visitors.OpenApi30Visitor;

public class OpenApi30ModelWriterDispatcher implements OpenApi30Visitor {

	private final ObjectNode json;
	private final OpenApi30ModelWriter writer;

	public OpenApi30ModelWriterDispatcher(ObjectNode json, OpenApi30ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitServer(Server node) {
		this.writer.writeServer((OpenApi30Server) node, this.json);
	}

	@Override
	public void visitLink(Link node) {
		this.writer.writeLink((OpenApi30Link) node, this.json);
	}

	@Override
	public void visitRequestBody(OpenApiRequestBody node) {
		this.writer.writeRequestBody((OpenApi30RequestBody) node, this.json);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.writer.writeOAuthFlows((OpenApi30OAuthFlows) node, this.json);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.writer.writeServerVariable((OpenApi30ServerVariable) node, this.json);
	}

	@Override
	public void visitMediaType(OpenApiMediaType node) {
		this.writer.writeMediaType((OpenApi30MediaType) node, this.json);
	}

	@Override
	public void visitEncoding(OpenApiEncoding node) {
		this.writer.writeEncoding((OpenApi30Encoding) node, this.json);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.writer.writeOAuthFlow((OpenApi30OAuthFlow) node, this.json);
	}

	@Override
	public void visitCallback(OpenApiCallback node) {
		this.writer.writeCallback((OpenApi30Callback) node, this.json);
	}

	@Override
	public void visitComponents(Components node) {
		this.writer.writeComponents((OpenApi30Components) node, this.json);
	}

	@Override
	public void visitDiscriminator(OpenApiDiscriminator node) {
		this.writer.writeDiscriminator((OpenApi30Discriminator) node, this.json);
	}

	@Override
	public void visitLicense(License node) {
		this.writer.writeLicense((OpenApi30License) node, this.json);
	}

	@Override
	public void visitPaths(OpenApiPaths node) {
		this.writer.writePaths((OpenApi30Paths) node, this.json);
	}

	@Override
	public void visitTag(Tag node) {
		this.writer.writeTag((OpenApi30Tag) node, this.json);
	}

	@Override
	public void visitHeader(OpenApiHeader node) {
		this.writer.writeHeader((OpenApi30Header) node, this.json);
	}

	@Override
	public void visitSchema(Schema node) {
		this.writer.writeSchema((OpenApi30Schema) node, this.json);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.writer.writeExternalDocumentation((OpenApi30ExternalDocumentation) node, this.json);
	}

	@Override
	public void visitXML(OpenApiXML node) {
		this.writer.writeXML((OpenApi30XML) node, this.json);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.writer.writeParameter((OpenApi30Parameter) node, this.json);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.writer.writeSecurityScheme((OpenApi30SecurityScheme) node, this.json);
	}

	@Override
	public void visitPathItem(OpenApiPathItem node) {
		this.writer.writePathItem((OpenApi30PathItem) node, this.json);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.writer.writeSecurityRequirement((OpenApi30SecurityRequirement) node, this.json);
	}

	@Override
	public void visitExample(Example node) {
		this.writer.writeExample((OpenApi30Example) node, this.json);
	}

	@Override
	public void visitResponse(OpenApiResponse node) {
		this.writer.writeResponse((OpenApi30Response) node, this.json);
	}

	@Override
	public void visitContact(Contact node) {
		this.writer.writeContact((OpenApi30Contact) node, this.json);
	}

	@Override
	public void visitInfo(Info node) {
		this.writer.writeInfo((OpenApi30Info) node, this.json);
	}

	@Override
	public void visitOperation(Operation node) {
		this.writer.writeOperation((OpenApi30Operation) node, this.json);
	}

	@Override
	public void visitResponses(OpenApiResponses node) {
		this.writer.writeResponses((OpenApi30Responses) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((OpenApi30Document) node, this.json);
	}
}