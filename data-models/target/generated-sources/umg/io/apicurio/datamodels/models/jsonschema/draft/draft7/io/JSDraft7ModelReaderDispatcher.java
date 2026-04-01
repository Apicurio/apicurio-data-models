package io.apicurio.datamodels.models.jsonschema.draft.draft7.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft7.JSDraft7Document;
import io.apicurio.datamodels.models.jsonschema.draft.draft7.JSDraft7JSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft7.visitors.JSDraft7Visitor;

public class JSDraft7ModelReaderDispatcher implements JSDraft7Visitor {

	private final ObjectNode json;
	private final JSDraft7ModelReader reader;

	public JSDraft7ModelReaderDispatcher(ObjectNode json, JSDraft7ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
		this.reader.readJSchema(this.json, (JSDraft7JSchema) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (JSDraft7Document) node);
	}
}