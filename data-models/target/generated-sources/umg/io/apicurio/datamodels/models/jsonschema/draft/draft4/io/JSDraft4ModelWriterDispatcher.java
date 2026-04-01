package io.apicurio.datamodels.models.jsonschema.draft.draft4.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft4.JSDraft4Document;
import io.apicurio.datamodels.models.jsonschema.draft.draft4.JSDraft4JSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft4.visitors.JSDraft4Visitor;

public class JSDraft4ModelWriterDispatcher implements JSDraft4Visitor {

	private final ObjectNode json;
	private final JSDraft4ModelWriter writer;

	public JSDraft4ModelWriterDispatcher(ObjectNode json, JSDraft4ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
		this.writer.writeJSchema((JSDraft4JSchema) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((JSDraft4Document) node, this.json);
	}
}