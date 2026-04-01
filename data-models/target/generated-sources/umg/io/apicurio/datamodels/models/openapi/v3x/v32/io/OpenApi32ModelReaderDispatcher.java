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

public class OpenApi32ModelReaderDispatcher implements OpenApi32Visitor {

	private final ObjectNode json;
	private final OpenApi32ModelReader reader;

	public OpenApi32ModelReaderDispatcher(ObjectNode json, OpenApi32ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitServer(Server node) {
		this.reader.readServer(this.json, (OpenApi32Server) node);
	}

	@Override
	public void visitLink(Link node) {
		this.reader.readLink(this.json, (OpenApi32Link) node);
	}

	@Override
	public void visitRequestBody(OpenApiRequestBody node) {
		this.reader.readRequestBody(this.json, (OpenApi32RequestBody) node);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.reader.readOAuthFlows(this.json, (OpenApi32OAuthFlows) node);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.reader.readServerVariable(this.json, (OpenApi32ServerVariable) node);
	}

	@Override
	public void visitMediaType(OpenApiMediaType node) {
		this.reader.readMediaType(this.json, (OpenApi32MediaType) node);
	}

	@Override
	public void visitEncoding(OpenApiEncoding node) {
		this.reader.readEncoding(this.json, (OpenApi32Encoding) node);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.reader.readOAuthFlow(this.json, (OpenApi32OAuthFlow) node);
	}

	@Override
	public void visitCallback(OpenApiCallback node) {
		this.reader.readCallback(this.json, (OpenApi32Callback) node);
	}

	@Override
	public void visitComponents(Components node) {
		this.reader.readComponents(this.json, (OpenApi32Components) node);
	}

	@Override
	public void visitDiscriminator(OpenApiDiscriminator node) {
		this.reader.readDiscriminator(this.json, (OpenApi32Discriminator) node);
	}

	@Override
	public void visitLicense(License node) {
		this.reader.readLicense(this.json, (OpenApi32License) node);
	}

	@Override
	public void visitPaths(OpenApiPaths node) {
		this.reader.readPaths(this.json, (OpenApi32Paths) node);
	}

	@Override
	public void visitTag(Tag node) {
		this.reader.readTag(this.json, (OpenApi32Tag) node);
	}

	@Override
	public void visitHeader(OpenApiHeader node) {
		this.reader.readHeader(this.json, (OpenApi32Header) node);
	}

	@Override
	public void visitSchema(Schema node) {
		this.reader.readSchema(this.json, (OpenApi32Schema) node);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.reader.readExternalDocumentation(this.json, (OpenApi32ExternalDocumentation) node);
	}

	@Override
	public void visitXML(OpenApiXML node) {
		this.reader.readXML(this.json, (OpenApi32XML) node);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.reader.readParameter(this.json, (OpenApi32Parameter) node);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.reader.readSecurityScheme(this.json, (OpenApi32SecurityScheme) node);
	}

	@Override
	public void visitPathItem(OpenApiPathItem node) {
		this.reader.readPathItem(this.json, (OpenApi32PathItem) node);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.reader.readSecurityRequirement(this.json, (OpenApi32SecurityRequirement) node);
	}

	@Override
	public void visitExample(Example node) {
		this.reader.readExample(this.json, (OpenApi32Example) node);
	}

	@Override
	public void visitResponse(OpenApiResponse node) {
		this.reader.readResponse(this.json, (OpenApi32Response) node);
	}

	@Override
	public void visitContact(Contact node) {
		this.reader.readContact(this.json, (OpenApi32Contact) node);
	}

	@Override
	public void visitInfo(Info node) {
		this.reader.readInfo(this.json, (OpenApi32Info) node);
	}

	@Override
	public void visitOperation(Operation node) {
		this.reader.readOperation(this.json, (OpenApi32Operation) node);
	}

	@Override
	public void visitResponses(OpenApiResponses node) {
		this.reader.readResponses(this.json, (OpenApi32Responses) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (OpenApi32Document) node);
	}
}