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

public class OpenApi30ModelReaderDispatcher implements OpenApi30Visitor {

	private final ObjectNode json;
	private final OpenApi30ModelReader reader;

	public OpenApi30ModelReaderDispatcher(ObjectNode json, OpenApi30ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitServer(Server node) {
		this.reader.readServer(this.json, (OpenApi30Server) node);
	}

	@Override
	public void visitLink(Link node) {
		this.reader.readLink(this.json, (OpenApi30Link) node);
	}

	@Override
	public void visitRequestBody(OpenApiRequestBody node) {
		this.reader.readRequestBody(this.json, (OpenApi30RequestBody) node);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.reader.readOAuthFlows(this.json, (OpenApi30OAuthFlows) node);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.reader.readServerVariable(this.json, (OpenApi30ServerVariable) node);
	}

	@Override
	public void visitMediaType(OpenApiMediaType node) {
		this.reader.readMediaType(this.json, (OpenApi30MediaType) node);
	}

	@Override
	public void visitEncoding(OpenApiEncoding node) {
		this.reader.readEncoding(this.json, (OpenApi30Encoding) node);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.reader.readOAuthFlow(this.json, (OpenApi30OAuthFlow) node);
	}

	@Override
	public void visitCallback(OpenApiCallback node) {
		this.reader.readCallback(this.json, (OpenApi30Callback) node);
	}

	@Override
	public void visitComponents(Components node) {
		this.reader.readComponents(this.json, (OpenApi30Components) node);
	}

	@Override
	public void visitDiscriminator(OpenApiDiscriminator node) {
		this.reader.readDiscriminator(this.json, (OpenApi30Discriminator) node);
	}

	@Override
	public void visitLicense(License node) {
		this.reader.readLicense(this.json, (OpenApi30License) node);
	}

	@Override
	public void visitPaths(OpenApiPaths node) {
		this.reader.readPaths(this.json, (OpenApi30Paths) node);
	}

	@Override
	public void visitTag(Tag node) {
		this.reader.readTag(this.json, (OpenApi30Tag) node);
	}

	@Override
	public void visitHeader(OpenApiHeader node) {
		this.reader.readHeader(this.json, (OpenApi30Header) node);
	}

	@Override
	public void visitSchema(Schema node) {
		this.reader.readSchema(this.json, (OpenApi30Schema) node);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.reader.readExternalDocumentation(this.json, (OpenApi30ExternalDocumentation) node);
	}

	@Override
	public void visitXML(OpenApiXML node) {
		this.reader.readXML(this.json, (OpenApi30XML) node);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.reader.readParameter(this.json, (OpenApi30Parameter) node);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.reader.readSecurityScheme(this.json, (OpenApi30SecurityScheme) node);
	}

	@Override
	public void visitPathItem(OpenApiPathItem node) {
		this.reader.readPathItem(this.json, (OpenApi30PathItem) node);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.reader.readSecurityRequirement(this.json, (OpenApi30SecurityRequirement) node);
	}

	@Override
	public void visitExample(Example node) {
		this.reader.readExample(this.json, (OpenApi30Example) node);
	}

	@Override
	public void visitResponse(OpenApiResponse node) {
		this.reader.readResponse(this.json, (OpenApi30Response) node);
	}

	@Override
	public void visitContact(Contact node) {
		this.reader.readContact(this.json, (OpenApi30Contact) node);
	}

	@Override
	public void visitInfo(Info node) {
		this.reader.readInfo(this.json, (OpenApi30Info) node);
	}

	@Override
	public void visitOperation(Operation node) {
		this.reader.readOperation(this.json, (OpenApi30Operation) node);
	}

	@Override
	public void visitResponses(OpenApiResponses node) {
		this.reader.readResponses(this.json, (OpenApi30Responses) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (OpenApi30Document) node);
	}
}