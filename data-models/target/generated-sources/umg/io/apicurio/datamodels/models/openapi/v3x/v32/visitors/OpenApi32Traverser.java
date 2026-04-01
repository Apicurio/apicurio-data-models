package io.apicurio.datamodels.models.openapi.v3x.v32.visitors;

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
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Document;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Encoding;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Header;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Info;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Link;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32MediaType;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32OAuthFlows;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Operation;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Parameter;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32PathItem;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Paths;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32RequestBody;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Response;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Responses;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Schema;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32SecurityScheme;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Server;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Tag;
import io.apicurio.datamodels.models.visitors.AbstractTraverser;
import io.apicurio.datamodels.models.visitors.Visitor;

public class OpenApi32Traverser extends AbstractTraverser implements OpenApi32Visitor {

	public OpenApi32Traverser(Visitor visitor) {
		super(visitor);
	}

	@Override
	public void visitServer(Server node) {
		node.accept(this.visitor);
		OpenApi32Server model = (OpenApi32Server) node;
		this.traverseMap("variables", model.getVariables());
	}

	@Override
	public void visitLink(Link node) {
		node.accept(this.visitor);
		OpenApi32Link model = (OpenApi32Link) node;
		this.traverseNode("server", model.getServer());
	}

	@Override
	public void visitRequestBody(OpenApiRequestBody node) {
		node.accept(this.visitor);
		OpenApi32RequestBody model = (OpenApi32RequestBody) node;
		this.traverseMap("content", model.getContent());
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		node.accept(this.visitor);
		OpenApi32OAuthFlows model = (OpenApi32OAuthFlows) node;
		this.traverseNode("implicit", model.getImplicit());
		this.traverseNode("password", model.getPassword());
		this.traverseNode("clientCredentials", model.getClientCredentials());
		this.traverseNode("authorizationCode", model.getAuthorizationCode());
		this.traverseNode("deviceAuthorization", model.getDeviceAuthorization());
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitMediaType(OpenApiMediaType node) {
		node.accept(this.visitor);
		OpenApi32MediaType model = (OpenApi32MediaType) node;
		this.traverseNode("schema", model.getSchema());
		this.traverseNode("itemSchema", model.getItemSchema());
		this.traverseMap("examples", model.getExamples());
		this.traverseMap("encoding", model.getEncoding());
	}

	@Override
	public void visitEncoding(OpenApiEncoding node) {
		node.accept(this.visitor);
		OpenApi32Encoding model = (OpenApi32Encoding) node;
		this.traverseMap("headers", model.getHeaders());
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitCallback(OpenApiCallback node) {
		node.accept(this.visitor);
		OpenApi32Callback model = (OpenApi32Callback) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitComponents(Components node) {
		node.accept(this.visitor);
		OpenApi32Components model = (OpenApi32Components) node;
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
		this.traverseMap("mediaTypes", model.getMediaTypes());
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
		OpenApi32Paths model = (OpenApi32Paths) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitTag(Tag node) {
		node.accept(this.visitor);
		OpenApi32Tag model = (OpenApi32Tag) node;
		this.traverseNode("externalDocs", model.getExternalDocs());
	}

	@Override
	public void visitHeader(OpenApiHeader node) {
		node.accept(this.visitor);
		OpenApi32Header model = (OpenApi32Header) node;
		this.traverseNode("schema", model.getSchema());
		this.traverseMap("examples", model.getExamples());
		this.traverseMap("content", model.getContent());
	}

	@Override
	public void visitSchema(Schema node) {
		node.accept(this.visitor);
		OpenApi32Schema model = (OpenApi32Schema) node;
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
		OpenApi32Parameter model = (OpenApi32Parameter) node;
		this.traverseNode("schema", model.getSchema());
		this.traverseMap("examples", model.getExamples());
		this.traverseMap("content", model.getContent());
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		node.accept(this.visitor);
		OpenApi32SecurityScheme model = (OpenApi32SecurityScheme) node;
		this.traverseNode("flows", model.getFlows());
	}

	@Override
	public void visitPathItem(OpenApiPathItem node) {
		node.accept(this.visitor);
		OpenApi32PathItem model = (OpenApi32PathItem) node;
		this.traverseNode("get", model.getGet());
		this.traverseNode("put", model.getPut());
		this.traverseNode("post", model.getPost());
		this.traverseNode("delete", model.getDelete());
		this.traverseNode("options", model.getOptions());
		this.traverseNode("head", model.getHead());
		this.traverseNode("patch", model.getPatch());
		this.traverseNode("trace", model.getTrace());
		this.traverseNode("query", model.getQuery());
		this.traverseMap("additionalOperations", model.getAdditionalOperations());
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
		OpenApi32Response model = (OpenApi32Response) node;
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
		OpenApi32Info model = (OpenApi32Info) node;
		this.traverseNode("contact", model.getContact());
		this.traverseNode("license", model.getLicense());
	}

	@Override
	public void visitOperation(Operation node) {
		node.accept(this.visitor);
		OpenApi32Operation model = (OpenApi32Operation) node;
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
		OpenApi32Responses model = (OpenApi32Responses) node;
		this.traverseNode("default", model.getDefault());
		this.traverseMappedNode(model);
	}

	@Override
	public void visitDocument(Document node) {
		node.accept(this.visitor);
		OpenApi32Document model = (OpenApi32Document) node;
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