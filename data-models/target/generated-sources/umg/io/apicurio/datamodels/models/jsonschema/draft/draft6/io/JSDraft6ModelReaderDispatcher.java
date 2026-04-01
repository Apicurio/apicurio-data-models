package io.apicurio.datamodels.models.jsonschema.draft.draft6.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.JSDraft6Document;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.JSDraft6JSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.visitors.JSDraft6Visitor;

public class JSDraft6ModelReaderDispatcher implements JSDraft6Visitor {

	private final ObjectNode json;
	private final JSDraft6ModelReader reader;

	public JSDraft6ModelReaderDispatcher(ObjectNode json, JSDraft6ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
		this.reader.readJSchema(this.json, (JSDraft6JSchema) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (JSDraft6Document) node);
	}
}