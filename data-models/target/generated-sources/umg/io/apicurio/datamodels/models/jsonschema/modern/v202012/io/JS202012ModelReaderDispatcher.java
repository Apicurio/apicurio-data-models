package io.apicurio.datamodels.models.jsonschema.modern.v202012.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.JS202012Document;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.JS202012JSchema;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.visitors.JS202012Visitor;

public class JS202012ModelReaderDispatcher implements JS202012Visitor {

	private final ObjectNode json;
	private final JS202012ModelReader reader;

	public JS202012ModelReaderDispatcher(ObjectNode json, JS202012ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
		this.reader.readJSchema(this.json, (JS202012JSchema) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (JS202012Document) node);
	}
}