package io.apicurio.datamodels.models.openapi.v3x.v30.visitors;

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
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Encoding;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Header;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Info;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Link;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30MediaType;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30OAuthFlows;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Operation;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Parameter;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30PathItem;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Paths;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30RequestBody;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Response;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Responses;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Schema;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30SecurityScheme;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Server;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Tag;
import io.apicurio.datamodels.models.visitors.AbstractTraverser;
import io.apicurio.datamodels.models.visitors.Visitor;

public class OpenApi30Traverser extends AbstractTraverser implements OpenApi30Visitor {

	public OpenApi30Traverser(Visitor visitor) {
		super(visitor);
	}

	@Override
	public void visitServer(Server node) {
		node.accept(this.visitor);
		OpenApi30Server model = (OpenApi30Server) node;
		this.traverseMap("variables", model.getVariables());
	}

	@Override
	public void visitLink(Link node) {
		node.accept(this.visitor);
		OpenApi30Link model = (OpenApi30Link) node;
		this.traverseNode("server", model.getServer());
	}

	@Override
	public void visitRequestBody(OpenApiRequestBody node) {
		node.accept(this.visitor);
		OpenApi30RequestBody model = (OpenApi30RequestBody) node;
		this.traverseMap("content", model.getContent());
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		node.accept(this.visitor);
		OpenApi30OAuthFlows model = (OpenApi30OAuthFlows) node;
		this.traverseNode("implicit", model.getImplicit());
		this.traverseNode("password", model.getPassword());
		this.traverseNode("clientCredentials", model.getClientCredentials());
		this.traverseNode("authorizationCode", model.getAuthorizationCode());
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitMediaType(OpenApiMediaType node) {
		node.accept(this.visitor);
		OpenApi30MediaType model = (OpenApi30MediaType) node;
		this.traverseNode("schema", model.getSchema());
		this.traverseMap("examples", model.getExamples());
		this.traverseMap("encoding", model.getEncoding());
	}

	@Override
	public void visitEncoding(OpenApiEncoding node) {
		node.accept(this.visitor);
		OpenApi30Encoding model = (OpenApi30Encoding) node;
		this.traverseMap("headers", model.getHeaders());
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitCallback(OpenApiCallback node) {
		node.accept(this.visitor);
		OpenApi30Callback model = (OpenApi30Callback) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitComponents(Components node) {
		node.accept(this.visitor);
		OpenApi30Components model = (OpenApi30Components) node;
		this.traverseMap("schemas", model.getSchemas());
		this.traverseMap("responses", model.getResponses());
		this.traverseMap("parameters", model.getParameters());
		this.traverseMap("examples", model.getExamples());
		this.traverseMap("requestBodies", model.getRequestBodies());
		this.traverseMap("headers", model.getHeaders());
		this.traverseMap("securitySchemes", model.getSecuritySchemes());
		this.traverseMap("links", model.getLinks());
		this.traverseMap("callbacks", model.getCallbacks());
	}

	@Override
	public void visitDiscriminator(OpenApiDiscriminator node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitLicense(License node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitPaths(OpenApiPaths node) {
		node.accept(this.visitor);
		OpenApi30Paths model = (OpenApi30Paths) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitTag(Tag node) {
		node.accept(this.visitor);
		OpenApi30Tag model = (OpenApi30Tag) node;
		this.traverseNode("externalDocs", model.getExternalDocs());
	}

	@Override
	public void visitHeader(OpenApiHeader node) {
		node.accept(this.visitor);
		OpenApi30Header model = (OpenApi30Header) node;
		this.traverseNode("schema", model.getSchema());
		this.traverseMap("examples", model.getExamples());
		this.traverseMap("content", model.getContent());
	}

	@Override
	public void visitSchema(Schema node) {
		node.accept(this.visitor);
		OpenApi30Schema model = (OpenApi30Schema) node;
		this.traverseNode("items", model.getItems());
		this.traverseList("allOf", model.getAllOf());
		this.traverseMap("properties", model.getProperties());
		this.traverseUnion("additionalProperties", model.getAdditionalProperties());
		this.traverseNode("xml", model.getXml());
		this.traverseNode("externalDocs", model.getExternalDocs());
		this.traverseList("oneOf", model.getOneOf());
		this.traverseList("anyOf", model.getAnyOf());
		this.traverseNode("not", model.getNot());
		this.traverseNode("discriminator", model.getDiscriminator());
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitXML(OpenApiXML node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitParameter(Parameter node) {
		node.accept(this.visitor);
		OpenApi30Parameter model = (OpenApi30Parameter) node;
		this.traverseNode("schema", model.getSchema());
		this.traverseMap("examples", model.getExamples());
		this.traverseMap("content", model.getContent());
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		node.accept(this.visitor);
		OpenApi30SecurityScheme model = (OpenApi30SecurityScheme) node;
		this.traverseNode("flows", model.getFlows());
	}

	@Override
	public void visitPathItem(OpenApiPathItem node) {
		node.accept(this.visitor);
		OpenApi30PathItem model = (OpenApi30PathItem) node;
		this.traverseNode("get", model.getGet());
		this.traverseNode("put", model.getPut());
		this.traverseNode("post", model.getPost());
		this.traverseNode("delete", model.getDelete());
		this.traverseNode("options", model.getOptions());
		this.traverseNode("head", model.getHead());
		this.traverseNode("patch", model.getPatch());
		this.traverseNode("trace", model.getTrace());
		this.traverseList("servers", model.getServers());
		this.traverseList("parameters", model.getParameters());
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitExample(Example node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitResponse(OpenApiResponse node) {
		node.accept(this.visitor);
		OpenApi30Response model = (OpenApi30Response) node;
		this.traverseMap("headers", model.getHeaders());
		this.traverseMap("content", model.getContent());
		this.traverseMap("links", model.getLinks());
	}

	@Override
	public void visitContact(Contact node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitInfo(Info node) {
		node.accept(this.visitor);
		OpenApi30Info model = (OpenApi30Info) node;
		this.traverseNode("contact", model.getContact());
		this.traverseNode("license", model.getLicense());
	}

	@Override
	public void visitOperation(Operation node) {
		node.accept(this.visitor);
		OpenApi30Operation model = (OpenApi30Operation) node;
		this.traverseNode("externalDocs", model.getExternalDocs());
		this.traverseList("parameters", model.getParameters());
		this.traverseNode("requestBody", model.getRequestBody());
		this.traverseNode("responses", model.getResponses());
		this.traverseMap("callbacks", model.getCallbacks());
		this.traverseList("security", model.getSecurity());
		this.traverseList("servers", model.getServers());
	}

	@Override
	public void visitResponses(OpenApiResponses node) {
		node.accept(this.visitor);
		OpenApi30Responses model = (OpenApi30Responses) node;
		this.traverseNode("default", model.getDefault());
		this.traverseMappedNode(model);
	}

	@Override
	public void visitDocument(Document node) {
		node.accept(this.visitor);
		OpenApi30Document model = (OpenApi30Document) node;
		this.traverseNode("info", model.getInfo());
		this.traverseList("servers", model.getServers());
		this.traverseNode("paths", model.getPaths());
		this.traverseNode("components", model.getComponents());
		this.traverseList("security", model.getSecurity());
		this.traverseList("tags", model.getTags());
		this.traverseNode("externalDocs", model.getExternalDocs());
	}
}