package io.apicurio.datamodels.models.jsonschema.draft.draft7.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft7.JSDraft7Document;
import io.apicurio.datamodels.models.jsonschema.draft.draft7.JSDraft7JSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft7.visitors.JSDraft7Visitor;

public class JSDraft7ModelWriterDispatcher implements JSDraft7Visitor {

	private final ObjectNode json;
	private final JSDraft7ModelWriter writer;

	public JSDraft7ModelWriterDispatcher(ObjectNode json, JSDraft7ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
		this.writer.writeJSchema((JSDraft7JSchema) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((JSDraft7Document) node, this.json);
	}
}