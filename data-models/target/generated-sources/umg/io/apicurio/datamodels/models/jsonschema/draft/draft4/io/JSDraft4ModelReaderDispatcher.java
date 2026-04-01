package io.apicurio.datamodels.models.jsonschema.draft.draft4.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft4.JSDraft4Document;
import io.apicurio.datamodels.models.jsonschema.draft.draft4.JSDraft4JSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft4.visitors.JSDraft4Visitor;

public class JSDraft4ModelReaderDispatcher implements JSDraft4Visitor {

	private final ObjectNode json;
	private final JSDraft4ModelReader reader;

	public JSDraft4ModelReaderDispatcher(ObjectNode json, JSDraft4ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
		this.reader.readJSchema(this.json, (JSDraft4JSchema) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (JSDraft4Document) node);
	}
}