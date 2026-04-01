package io.apicurio.datamodels.models.jsonschema.draft.draft4.visitors;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft4.JSDraft4Document;
import io.apicurio.datamodels.models.jsonschema.draft.draft4.JSDraft4JSchema;
import io.apicurio.datamodels.models.visitors.AbstractTraverser;
import io.apicurio.datamodels.models.visitors.Visitor;

public class JSDraft4Traverser extends AbstractTraverser implements JSDraft4Visitor {

	public JSDraft4Traverser(Visitor visitor) {
		super(visitor);
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
		node.accept(this.visitor);
		JSDraft4JSchema model = (JSDraft4JSchema) node;
		this.traverseUnion("type", model.getType());
		this.traverseUnion("not", model.getNot());
		this.traverseUnion("items", model.getItems());
		this.traverseUnion("additionalItems", model.getAdditionalItems());
		this.traverseUnion("additionalProperties", model.getAdditionalProperties());
	}

	@Override
	public void visitDocument(Document node) {
		node.accept(this.visitor);
		JSDraft4Document model = (JSDraft4Document) node;
		this.traverseUnion("type", model.getType());
		this.traverseUnion("not", model.getNot());
		this.traverseUnion("items", model.getItems());
		this.traverseUnion("additionalItems", model.getAdditionalItems());
		this.traverseUnion("additionalProperties", model.getAdditionalProperties());
	}
}