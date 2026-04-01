package io.apicurio.datamodels.models.openapi.v2x.v20.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
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
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Contact;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Definitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Document;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Example;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20ExternalDocumentation;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Header;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Info;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Items;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20License;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Operation;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Parameter;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20ParameterDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20PathItem;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Paths;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Response;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20ResponseDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Responses;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Schema;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Scopes;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20SecurityDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20SecurityRequirement;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20SecurityScheme;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Tag;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20XML;
import io.apicurio.datamodels.models.openapi.v2x.v20.visitors.OpenApi20Visitor;

public class OpenApi20ModelReaderDispatcher implements OpenApi20Visitor {

	private final ObjectNode json;
	private final OpenApi20ModelReader reader;

	public OpenApi20ModelReaderDispatcher(ObjectNode json, OpenApi20ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitParameterDefinitions(OpenApiParameterDefinitions node) {
		this.reader.readParameterDefinitions(this.json, (OpenApi20ParameterDefinitions) node);
	}

	@Override
	public void visitItems(OpenApiItems node) {
		this.reader.readItems(this.json, (OpenApi20Items) node);
	}

	@Override
	public void visitDefinitions(OpenApiDefinitions node) {
		this.reader.readDefinitions(this.json, (OpenApi20Definitions) node);
	}

	@Override
	public void visitResponseDefinitions(OpenApiResponseDefinitions node) {
		this.reader.readResponseDefinitions(this.json, (OpenApi20ResponseDefinitions) node);
	}

	@Override
	public void visitScopes(OpenApiScopes node) {
		this.reader.readScopes(this.json, (OpenApi20Scopes) node);
	}

	@Override
	public void visitSecurityDefinitions(OpenApiSecurityDefinitions node) {
		this.reader.readSecurityDefinitions(this.json, (OpenApi20SecurityDefinitions) node);
	}

	@Override
	public void visitLicense(License node) {
		this.reader.readLicense(this.json, (OpenApi20License) node);
	}

	@Override
	public void visitPaths(OpenApiPaths node) {
		this.reader.readPaths(this.json, (OpenApi20Paths) node);
	}

	@Override
	public void visitTag(Tag node) {
		this.reader.readTag(this.json, (OpenApi20Tag) node);
	}

	@Override
	public void visitHeader(OpenApiHeader node) {
		this.reader.readHeader(this.json, (OpenApi20Header) node);
	}

	@Override
	public void visitSchema(Schema node) {
		this.reader.readSchema(this.json, (OpenApi20Schema) node);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.reader.readExternalDocumentation(this.json, (OpenApi20ExternalDocumentation) node);
	}

	@Override
	public void visitXML(OpenApiXML node) {
		this.reader.readXML(this.json, (OpenApi20XML) node);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.reader.readParameter(this.json, (OpenApi20Parameter) node);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.reader.readSecurityScheme(this.json, (OpenApi20SecurityScheme) node);
	}

	@Override
	public void visitPathItem(OpenApiPathItem node) {
		this.reader.readPathItem(this.json, (OpenApi20PathItem) node);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.reader.readSecurityRequirement(this.json, (OpenApi20SecurityRequirement) node);
	}

	@Override
	public void visitExample(Example node) {
		this.reader.readExample(this.json, (OpenApi20Example) node);
	}

	@Override
	public void visitResponse(OpenApiResponse node) {
		this.reader.readResponse(this.json, (OpenApi20Response) node);
	}

	@Override
	public void visitContact(Contact node) {
		this.reader.readContact(this.json, (OpenApi20Contact) node);
	}

	@Override
	public void visitInfo(Info node) {
		this.reader.readInfo(this.json, (OpenApi20Info) node);
	}

	@Override
	public void visitOperation(Operation node) {
		this.reader.readOperation(this.json, (OpenApi20Operation) node);
	}

	@Override
	public void visitResponses(OpenApiResponses node) {
		this.reader.readResponses(this.json, (OpenApi20Responses) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (OpenApi20Document) node);
	}
}