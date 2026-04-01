package io.apicurio.datamodels.models.jsonschema.modern.v201909.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.modern.v201909.JS201909Document;
import io.apicurio.datamodels.models.jsonschema.modern.v201909.JS201909JSchema;
import io.apicurio.datamodels.models.jsonschema.modern.v201909.visitors.JS201909Visitor;

public class JS201909ModelReaderDispatcher implements JS201909Visitor {

	private final ObjectNode json;
	private final JS201909ModelReader reader;

	public JS201909ModelReaderDispatcher(ObjectNode json, JS201909ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
		this.reader.readJSchema(this.json, (JS201909JSchema) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (JS201909Document) node);
	}
}