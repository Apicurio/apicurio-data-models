package io.apicurio.datamodels.models.jsonschema.modern.v201909.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.modern.v201909.JS201909Document;
import io.apicurio.datamodels.models.jsonschema.modern.v201909.JS201909JSchema;
import io.apicurio.datamodels.models.jsonschema.modern.v201909.visitors.JS201909Visitor;

public class JS201909ModelWriterDispatcher implements JS201909Visitor {

	private final ObjectNode json;
	private final JS201909ModelWriter writer;

	public JS201909ModelWriterDispatcher(ObjectNode json, JS201909ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
		this.writer.writeJSchema((JS201909JSchema) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((JS201909Document) node, this.json);
	}
}