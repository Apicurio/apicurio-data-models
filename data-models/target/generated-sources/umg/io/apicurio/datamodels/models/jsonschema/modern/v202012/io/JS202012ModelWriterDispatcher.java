package io.apicurio.datamodels.models.jsonschema.modern.v202012.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.JS202012Document;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.JS202012JSchema;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.visitors.JS202012Visitor;

public class JS202012ModelWriterDispatcher implements JS202012Visitor {

	private final ObjectNode json;
	private final JS202012ModelWriter writer;

	public JS202012ModelWriterDispatcher(ObjectNode json, JS202012ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
		this.writer.writeJSchema((JS202012JSchema) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((JS202012Document) node, this.json);
	}
}