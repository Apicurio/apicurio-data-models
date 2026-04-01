package io.apicurio.datamodels.models.jsonschema.draft.draft6.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.JSDraft6Document;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.JSDraft6JSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.visitors.JSDraft6Visitor;

public class JSDraft6ModelWriterDispatcher implements JSDraft6Visitor {

	private final ObjectNode json;
	private final JSDraft6ModelWriter writer;

	public JSDraft6ModelWriterDispatcher(ObjectNode json, JSDraft6ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
		this.writer.writeJSchema((JSDraft6JSchema) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((JSDraft6Document) node, this.json);
	}
}