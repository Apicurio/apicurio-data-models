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

public class OpenApi31ModelReaderDispatcher implements OpenApi31Visitor {

	private final ObjectNode json;
	private final OpenApi31ModelReader reader;

	public OpenApi31ModelReaderDispatcher(ObjectNode json, OpenApi31ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitServer(Server node) {
		this.reader.readServer(this.json, (OpenApi31Server) node);
	}

	@Override
	public void visitLink(Link node) {
		this.reader.readLink(this.json, (OpenApi31Link) node);
	}

	@Override
	public void visitRequestBody(OpenApiRequestBody node) {
		this.reader.readRequestBody(this.json, (OpenApi31RequestBody) node);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.reader.readOAuthFlows(this.json, (OpenApi31OAuthFlows) node);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.reader.readServerVariable(this.json, (OpenApi31ServerVariable) node);
	}

	@Override
	public void visitMediaType(OpenApiMediaType node) {
		this.reader.readMediaType(this.json, (OpenApi31MediaType) node);
	}

	@Override
	public void visitEncoding(OpenApiEncoding node) {
		this.reader.readEncoding(this.json, (OpenApi31Encoding) node);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.reader.readOAuthFlow(this.json, (OpenApi31OAuthFlow) node);
	}

	@Override
	public void visitCallback(OpenApiCallback node) {
		this.reader.readCallback(this.json, (OpenApi31Callback) node);
	}

	@Override
	public void visitComponents(Components node) {
		this.reader.readComponents(this.json, (OpenApi31Components) node);
	}

	@Override
	public void visitDiscriminator(OpenApiDiscriminator node) {
		this.reader.readDiscriminator(this.json, (OpenApi31Discriminator) node);
	}

	@Override
	public void visitLicense(License node) {
		this.reader.readLicense(this.json, (OpenApi31License) node);
	}

	@Override
	public void visitPaths(OpenApiPaths node) {
		this.reader.readPaths(this.json, (OpenApi31Paths) node);
	}

	@Override
	public void visitTag(Tag node) {
		this.reader.readTag(this.json, (OpenApi31Tag) node);
	}

	@Override
	public void visitHeader(OpenApiHeader node) {
		this.reader.readHeader(this.json, (OpenApi31Header) node);
	}

	@Override
	public void visitSchema(Schema node) {
		this.reader.readSchema(this.json, (OpenApi31Schema) node);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.reader.readExternalDocumentation(this.json, (OpenApi31ExternalDocumentation) node);
	}

	@Override
	public void visitXML(OpenApiXML node) {
		this.reader.readXML(this.json, (OpenApi31XML) node);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.reader.readParameter(this.json, (OpenApi31Parameter) node);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.reader.readSecurityScheme(this.json, (OpenApi31SecurityScheme) node);
	}

	@Override
	public void visitPathItem(OpenApiPathItem node) {
		this.reader.readPathItem(this.json, (OpenApi31PathItem) node);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.reader.readSecurityRequirement(this.json, (OpenApi31SecurityRequirement) node);
	}

	@Override
	public void visitExample(Example node) {
		this.reader.readExample(this.json, (OpenApi31Example) node);
	}

	@Override
	public void visitResponse(OpenApiResponse node) {
		this.reader.readResponse(this.json, (OpenApi31Response) node);
	}

	@Override
	public void visitContact(Contact node) {
		this.reader.readContact(this.json, (OpenApi31Contact) node);
	}

	@Override
	public void visitInfo(Info node) {
		this.reader.readInfo(this.json, (OpenApi31Info) node);
	}

	@Override
	public void visitOperation(Operation node) {
		this.reader.readOperation(this.json, (OpenApi31Operation) node);
	}

	@Override
	public void visitResponses(OpenApiResponses node) {
		this.reader.readResponses(this.json, (OpenApi31Responses) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (OpenApi31Document) node);
	}
}