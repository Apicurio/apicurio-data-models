package io.apicurio.datamodels.models.openapi.v3x.v32.io;

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
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Callback;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Components;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Contact;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Discriminator;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Document;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Encoding;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Example;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32ExternalDocumentation;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Header;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Info;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32License;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Link;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32MediaType;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32OAuthFlow;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32OAuthFlows;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Operation;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Parameter;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32PathItem;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Paths;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32RequestBody;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Response;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Responses;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Schema;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32SecurityRequirement;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32SecurityScheme;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Server;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32ServerVariable;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Tag;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32XML;
import io.apicurio.datamodels.models.openapi.v3x.v32.visitors.OpenApi32Visitor;

public class OpenApi32ModelWriterDispatcher implements OpenApi32Visitor {

	private final ObjectNode json;
	private final OpenApi32ModelWriter writer;

	public OpenApi32ModelWriterDispatcher(ObjectNode json, OpenApi32ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitServer(Server node) {
		this.writer.writeServer((OpenApi32Server) node, this.json);
	}

	@Override
	public void visitLink(Link node) {
		this.writer.writeLink((OpenApi32Link) node, this.json);
	}

	@Override
	public void visitRequestBody(OpenApiRequestBody node) {
		this.writer.writeRequestBody((OpenApi32RequestBody) node, this.json);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.writer.writeOAuthFlows((OpenApi32OAuthFlows) node, this.json);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.writer.writeServerVariable((OpenApi32ServerVariable) node, this.json);
	}

	@Override
	public void visitMediaType(OpenApiMediaType node) {
		this.writer.writeMediaType((OpenApi32MediaType) node, this.json);
	}

	@Override
	public void visitEncoding(OpenApiEncoding node) {
		this.writer.writeEncoding((OpenApi32Encoding) node, this.json);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.writer.writeOAuthFlow((OpenApi32OAuthFlow) node, this.json);
	}

	@Override
	public void visitCallback(OpenApiCallback node) {
		this.writer.writeCallback((OpenApi32Callback) node, this.json);
	}

	@Override
	public void visitComponents(Components node) {
		this.writer.writeComponents((OpenApi32Components) node, this.json);
	}

	@Override
	public void visitDiscriminator(OpenApiDiscriminator node) {
		this.writer.writeDiscriminator((OpenApi32Discriminator) node, this.json);
	}

	@Override
	public void visitLicense(License node) {
		this.writer.writeLicense((OpenApi32License) node, this.json);
	}

	@Override
	public void visitPaths(OpenApiPaths node) {
		this.writer.writePaths((OpenApi32Paths) node, this.json);
	}

	@Override
	public void visitTag(Tag node) {
		this.writer.writeTag((OpenApi32Tag) node, this.json);
	}

	@Override
	public void visitHeader(OpenApiHeader node) {
		this.writer.writeHeader((OpenApi32Header) node, this.json);
	}

	@Override
	public void visitSchema(Schema node) {
		this.writer.writeSchema((OpenApi32Schema) node, this.json);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.writer.writeExternalDocumentation((OpenApi32ExternalDocumentation) node, this.json);
	}

	@Override
	public void visitXML(OpenApiXML node) {
		this.writer.writeXML((OpenApi32XML) node, this.json);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.writer.writeParameter((OpenApi32Parameter) node, this.json);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.writer.writeSecurityScheme((OpenApi32SecurityScheme) node, this.json);
	}

	@Override
	public void visitPathItem(OpenApiPathItem node) {
		this.writer.writePathItem((OpenApi32PathItem) node, this.json);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.writer.writeSecurityRequirement((OpenApi32SecurityRequirement) node, this.json);
	}

	@Override
	public void visitExample(Example node) {
		this.writer.writeExample((OpenApi32Example) node, this.json);
	}

	@Override
	public void visitResponse(OpenApiResponse node) {
		this.writer.writeResponse((OpenApi32Response) node, this.json);
	}

	@Override
	public void visitContact(Contact node) {
		this.writer.writeContact((OpenApi32Contact) node, this.json);
	}

	@Override
	public void visitInfo(Info node) {
		this.writer.writeInfo((OpenApi32Info) node, this.json);
	}

	@Override
	public void visitOperation(Operation node) {
		this.writer.writeOperation((OpenApi32Operation) node, this.json);
	}

	@Override
	public void visitResponses(OpenApiResponses node) {
		this.writer.writeResponses((OpenApi32Responses) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((OpenApi32Document) node, this.json);
	}
}