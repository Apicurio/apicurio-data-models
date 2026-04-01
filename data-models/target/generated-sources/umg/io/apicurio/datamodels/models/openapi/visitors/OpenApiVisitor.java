package io.apicurio.datamodels.models.openapi.visitors;

import io.apicurio.datamodels.models.Contact;
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
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.models.openapi.OpenApiPaths;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.models.openapi.OpenApiResponses;
import io.apicurio.datamodels.models.openapi.OpenApiXML;
import io.apicurio.datamodels.models.visitors.Visitor;

public interface OpenApiVisitor extends Visitor {

	public void visitLicense(License node);

	public void visitPaths(OpenApiPaths node);

	public void visitTag(Tag node);

	public void visitHeader(OpenApiHeader node);

	public void visitSchema(Schema node);

	public void visitExternalDocumentation(ExternalDocumentation node);

	public void visitXML(OpenApiXML node);

	public void visitParameter(Parameter node);

	public void visitSecurityScheme(SecurityScheme node);

	public void visitPathItem(OpenApiPathItem node);

	public void visitSecurityRequirement(SecurityRequirement node);

	public void visitExample(Example node);

	public void visitResponse(OpenApiResponse node);

	public void visitContact(Contact node);

	public void visitInfo(Info node);

	public void visitOperation(Operation node);

	public void visitResponses(OpenApiResponses node);
}