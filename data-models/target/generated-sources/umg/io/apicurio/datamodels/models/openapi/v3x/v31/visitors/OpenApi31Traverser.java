package io.apicurio.datamodels.models.openapi.v3x.v31.visitors;

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
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Document;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Encoding;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Header;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Info;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Link;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31MediaType;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31OAuthFlows;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Operation;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Parameter;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31PathItem;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Paths;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31RequestBody;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Response;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Responses;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Schema;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31SecurityScheme;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Server;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Tag;
import io.apicurio.datamodels.models.visitors.AbstractTraverser;
import io.apicurio.datamodels.models.visitors.Visitor;

public class OpenApi31Traverser extends AbstractTraverser implements OpenApi31Visitor {

	public OpenApi31Traverser(Visitor visitor) {
		super(visitor);
	}

	@Override
	public void visitServer(Server node) {
		node.accept(this.visitor);
		OpenApi31Server model = (OpenApi31Server) node;
		this.traverseMap("variables", model.getVariables());
	}

	@Override
	public void visitLink(Link node) {
		node.accept(this.visitor);
		OpenApi31Link model = (OpenApi31Link) node;
		this.traverseNode("server", model.getServer());
	}

	@Override
	public void visitRequestBody(OpenApiRequestBody node) {
		node.accept(this.visitor);
		OpenApi31RequestBody model = (OpenApi31RequestBody) node;
		this.traverseMap("content", model.getContent());
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		node.accept(this.visitor);
		OpenApi31OAuthFlows model = (OpenApi31OAuthFlows) node;
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
		OpenApi31MediaType model = (OpenApi31MediaType) node;
		this.traverseNode("schema", model.getSchema());
		this.traverseMap("examples", model.getExamples());
		this.traverseMap("encoding", model.getEncoding());
	}

	@Override
	public void visitEncoding(OpenApiEncoding node) {
		node.accept(this.visitor);
		OpenApi31Encoding model = (OpenApi31Encoding) node;
		this.traverseMap("headers", model.getHeaders());
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitCallback(OpenApiCallback node) {
		node.accept(this.visitor);
		OpenApi31Callback model = (OpenApi31Callback) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitComponents(Components node) {
		node.accept(this.visitor);
		OpenApi31Components model = (OpenApi31Components) node;
		this.traverseMap("schemas", model.getSchemas());
		this.traverseMap("responses", model.getResponses());
		this.traverseMap("parameters", model.getParameters());
		this.traverseMap("examples", model.getExamples());
		this.traverseMap("requestBodies", model.getRequestBodies());
		this.traverseMap("headers", model.getHeaders());
		this.traverseMap("securitySchemes", model.getSecuritySchemes());
		this.traverseMap("links", model.getLinks());
		this.traverseMap("callbacks", model.getCallbacks());
		this.traverseMap("pathItems", model.getPathItems());
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
		OpenApi31Paths model = (OpenApi31Paths) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitTag(Tag node) {
		node.accept(this.visitor);
		OpenApi31Tag model = (OpenApi31Tag) node;
		this.traverseNode("externalDocs", model.getExternalDocs());
	}

	@Override
	public void visitHeader(OpenApiHeader node) {
		node.accept(this.visitor);
		OpenApi31Header model = (OpenApi31Header) node;
		this.traverseNode("schema", model.getSchema());
		this.traverseMap("examples", model.getExamples());
		this.traverseMap("content", model.getContent());
	}

	@Override
	public void visitSchema(Schema node) {
		node.accept(this.visitor);
		OpenApi31Schema model = (OpenApi31Schema) node;
		this.traverseUnion("type", model.getType());
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
		OpenApi31Parameter model = (OpenApi31Parameter) node;
		this.traverseNode("schema", model.getSchema());
		this.traverseMap("examples", model.getExamples());
		this.traverseMap("content", model.getContent());
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		node.accept(this.visitor);
		OpenApi31SecurityScheme model = (OpenApi31SecurityScheme) node;
		this.traverseNode("flows", model.getFlows());
	}

	@Override
	public void visitPathItem(OpenApiPathItem node) {
		node.accept(this.visitor);
		OpenApi31PathItem model = (OpenApi31PathItem) node;
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
		OpenApi31Response model = (OpenApi31Response) node;
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
		OpenApi31Info model = (OpenApi31Info) node;
		this.traverseNode("contact", model.getContact());
		this.traverseNode("license", model.getLicense());
	}

	@Override
	public void visitOperation(Operation node) {
		node.accept(this.visitor);
		OpenApi31Operation model = (OpenApi31Operation) node;
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
		OpenApi31Responses model = (OpenApi31Responses) node;
		this.traverseNode("default", model.getDefault());
		this.traverseMappedNode(model);
	}

	@Override
	public void visitDocument(Document node) {
		node.accept(this.visitor);
		OpenApi31Document model = (OpenApi31Document) node;
		this.traverseNode("info", model.getInfo());
		this.traverseList("servers", model.getServers());
		this.traverseNode("paths", model.getPaths());
		this.traverseMap("webhooks", model.getWebhooks());
		this.traverseNode("components", model.getComponents());
		this.traverseList("security", model.getSecurity());
		this.traverseList("tags", model.getTags());
		this.traverseNode("externalDocs", model.getExternalDocs());
	}
}