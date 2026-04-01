package io.apicurio.datamodels.models.openapi.v2x.v20.visitors;

import io.apicurio.datamodels.models.Contact;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Example;
import io.apicurio.datamodels.models.ExternalDocumentation;
import io.apicurio.datamodels.models.Info;
import io.apicurio.datamodels.models.License;
import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.models.Tag;
import io.apicurio.datamodels.models.openapi.OpenApiDefinitions;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.OpenApiItems;
import io.apicurio.datamodels.models.openapi.OpenApiParameterDefinitions;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.models.openapi.OpenApiPaths;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.models.openapi.OpenApiResponseDefinitions;
import io.apicurio.datamodels.models.openapi.OpenApiResponses;
import io.apicurio.datamodels.models.openapi.OpenApiScopes;
import io.apicurio.datamodels.models.openapi.OpenApiSecurityDefinitions;
import io.apicurio.datamodels.models.openapi.OpenApiXML;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Definitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Document;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Header;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Info;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Items;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Operation;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Parameter;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20ParameterDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20PathItem;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Paths;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Response;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20ResponseDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Responses;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Schema;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20SecurityDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20SecurityScheme;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Tag;
import io.apicurio.datamodels.models.visitors.AbstractTraverser;
import io.apicurio.datamodels.models.visitors.Visitor;

public class OpenApi20Traverser extends AbstractTraverser implements OpenApi20Visitor {

	public OpenApi20Traverser(Visitor visitor) {
		super(visitor);
	}

	@Override
	public void visitParameterDefinitions(OpenApiParameterDefinitions node) {
		node.accept(this.visitor);
		OpenApi20ParameterDefinitions model = (OpenApi20ParameterDefinitions) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitItems(OpenApiItems node) {
		node.accept(this.visitor);
		OpenApi20Items model = (OpenApi20Items) node;
		this.traverseNode("items", model.getItems());
	}

	@Override
	public void visitDefinitions(OpenApiDefinitions node) {
		node.accept(this.visitor);
		OpenApi20Definitions model = (OpenApi20Definitions) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitResponseDefinitions(OpenApiResponseDefinitions node) {
		node.accept(this.visitor);
		OpenApi20ResponseDefinitions model = (OpenApi20ResponseDefinitions) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitScopes(OpenApiScopes node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitSecurityDefinitions(OpenApiSecurityDefinitions node) {
		node.accept(this.visitor);
		OpenApi20SecurityDefinitions model = (OpenApi20SecurityDefinitions) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitLicense(License node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitPaths(OpenApiPaths node) {
		node.accept(this.visitor);
		OpenApi20Paths model = (OpenApi20Paths) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitTag(Tag node) {
		node.accept(this.visitor);
		OpenApi20Tag model = (OpenApi20Tag) node;
		this.traverseNode("externalDocs", model.getExternalDocs());
	}

	@Override
	public void visitHeader(OpenApiHeader node) {
		node.accept(this.visitor);
		OpenApi20Header model = (OpenApi20Header) node;
		this.traverseNode("items", model.getItems());
	}

	@Override
	public void visitSchema(Schema node) {
		node.accept(this.visitor);
		OpenApi20Schema model = (OpenApi20Schema) node;
		this.traverseUnion("items", model.getItems());
		this.traverseList("allOf", model.getAllOf());
		this.traverseMap("properties", model.getProperties());
		this.traverseUnion("additionalProperties", model.getAdditionalProperties());
		this.traverseNode("xml", model.getXml());
		this.traverseNode("externalDocs", model.getExternalDocs());
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
		OpenApi20Parameter model = (OpenApi20Parameter) node;
		this.traverseNode("schema", model.getSchema());
		this.traverseNode("items", model.getItems());
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		node.accept(this.visitor);
		OpenApi20SecurityScheme model = (OpenApi20SecurityScheme) node;
		this.traverseNode("scopes", model.getScopes());
	}

	@Override
	public void visitPathItem(OpenApiPathItem node) {
		node.accept(this.visitor);
		OpenApi20PathItem model = (OpenApi20PathItem) node;
		this.traverseNode("get", model.getGet());
		this.traverseNode("put", model.getPut());
		this.traverseNode("post", model.getPost());
		this.traverseNode("delete", model.getDelete());
		this.traverseNode("options", model.getOptions());
		this.traverseNode("head", model.getHead());
		this.traverseNode("patch", model.getPatch());
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
		OpenApi20Response model = (OpenApi20Response) node;
		this.traverseNode("schema", model.getSchema());
		this.traverseMap("headers", model.getHeaders());
		this.traverseNode("examples", model.getExamples());
	}

	@Override
	public void visitContact(Contact node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitInfo(Info node) {
		node.accept(this.visitor);
		OpenApi20Info model = (OpenApi20Info) node;
		this.traverseNode("contact", model.getContact());
		this.traverseNode("license", model.getLicense());
	}

	@Override
	public void visitOperation(Operation node) {
		node.accept(this.visitor);
		OpenApi20Operation model = (OpenApi20Operation) node;
		this.traverseNode("externalDocs", model.getExternalDocs());
		this.traverseList("parameters", model.getParameters());
		this.traverseNode("responses", model.getResponses());
		this.traverseList("security", model.getSecurity());
	}

	@Override
	public void visitResponses(OpenApiResponses node) {
		node.accept(this.visitor);
		OpenApi20Responses model = (OpenApi20Responses) node;
		this.traverseNode("default", model.getDefault());
		this.traverseMappedNode(model);
	}

	@Override
	public void visitDocument(Document node) {
		node.accept(this.visitor);
		OpenApi20Document model = (OpenApi20Document) node;
		this.traverseNode("info", model.getInfo());
		this.traverseNode("paths", model.getPaths());
		this.traverseNode("definitions", model.getDefinitions());
		this.traverseNode("parameters", model.getParameters());
		this.traverseNode("responses", model.getResponses());
		this.traverseNode("securityDefinitions", model.getSecurityDefinitions());
		this.traverseList("security", model.getSecurity());
		this.traverseList("tags", model.getTags());
		this.traverseNode("externalDocs", model.getExternalDocs());
	}
}