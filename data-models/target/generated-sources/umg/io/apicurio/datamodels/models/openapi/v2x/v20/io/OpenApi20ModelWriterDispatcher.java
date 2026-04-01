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

public class OpenApi20ModelWriterDispatcher implements OpenApi20Visitor {

	private final ObjectNode json;
	private final OpenApi20ModelWriter writer;

	public OpenApi20ModelWriterDispatcher(ObjectNode json, OpenApi20ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitParameterDefinitions(OpenApiParameterDefinitions node) {
		this.writer.writeParameterDefinitions((OpenApi20ParameterDefinitions) node, this.json);
	}

	@Override
	public void visitItems(OpenApiItems node) {
		this.writer.writeItems((OpenApi20Items) node, this.json);
	}

	@Override
	public void visitDefinitions(OpenApiDefinitions node) {
		this.writer.writeDefinitions((OpenApi20Definitions) node, this.json);
	}

	@Override
	public void visitResponseDefinitions(OpenApiResponseDefinitions node) {
		this.writer.writeResponseDefinitions((OpenApi20ResponseDefinitions) node, this.json);
	}

	@Override
	public void visitScopes(OpenApiScopes node) {
		this.writer.writeScopes((OpenApi20Scopes) node, this.json);
	}

	@Override
	public void visitSecurityDefinitions(OpenApiSecurityDefinitions node) {
		this.writer.writeSecurityDefinitions((OpenApi20SecurityDefinitions) node, this.json);
	}

	@Override
	public void visitLicense(License node) {
		this.writer.writeLicense((OpenApi20License) node, this.json);
	}

	@Override
	public void visitPaths(OpenApiPaths node) {
		this.writer.writePaths((OpenApi20Paths) node, this.json);
	}

	@Override
	public void visitTag(Tag node) {
		this.writer.writeTag((OpenApi20Tag) node, this.json);
	}

	@Override
	public void visitHeader(OpenApiHeader node) {
		this.writer.writeHeader((OpenApi20Header) node, this.json);
	}

	@Override
	public void visitSchema(Schema node) {
		this.writer.writeSchema((OpenApi20Schema) node, this.json);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.writer.writeExternalDocumentation((OpenApi20ExternalDocumentation) node, this.json);
	}

	@Override
	public void visitXML(OpenApiXML node) {
		this.writer.writeXML((OpenApi20XML) node, this.json);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.writer.writeParameter((OpenApi20Parameter) node, this.json);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.writer.writeSecurityScheme((OpenApi20SecurityScheme) node, this.json);
	}

	@Override
	public void visitPathItem(OpenApiPathItem node) {
		this.writer.writePathItem((OpenApi20PathItem) node, this.json);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.writer.writeSecurityRequirement((OpenApi20SecurityRequirement) node, this.json);
	}

	@Override
	public void visitExample(Example node) {
		this.writer.writeExample((OpenApi20Example) node, this.json);
	}

	@Override
	public void visitResponse(OpenApiResponse node) {
		this.writer.writeResponse((OpenApi20Response) node, this.json);
	}

	@Override
	public void visitContact(Contact node) {
		this.writer.writeContact((OpenApi20Contact) node, this.json);
	}

	@Override
	public void visitInfo(Info node) {
		this.writer.writeInfo((OpenApi20Info) node, this.json);
	}

	@Override
	public void visitOperation(Operation node) {
		this.writer.writeOperation((OpenApi20Operation) node, this.json);
	}

	@Override
	public void visitResponses(OpenApiResponses node) {
		this.writer.writeResponses((OpenApi20Responses) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((OpenApi20Document) node, this.json);
	}
}