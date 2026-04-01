package io.apicurio.datamodels.models.jsonschema.visitors;

import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.visitors.Visitor;

public interface JsonSchemaVisitor extends Visitor {

	public void visitJSchema(JsonSchemaJSchema node);
}