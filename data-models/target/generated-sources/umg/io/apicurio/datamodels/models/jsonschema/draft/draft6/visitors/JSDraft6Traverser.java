package io.apicurio.datamodels.models.jsonschema.draft.draft6.visitors;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.JSDraft6Document;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.JSDraft6JSchema;
import io.apicurio.datamodels.models.visitors.AbstractTraverser;
import io.apicurio.datamodels.models.visitors.Visitor;

public class JSDraft6Traverser extends AbstractTraverser implements JSDraft6Visitor {

	public JSDraft6Traverser(Visitor visitor) {
		super(visitor);
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
		node.accept(this.visitor);
		JSDraft6JSchema model = (JSDraft6JSchema) node;
		this.traverseUnion("type", model.getType());
		this.traverseUnion("not", model.getNot());
		this.traverseUnion("items", model.getItems());
		this.traverseUnion("additionalItems", model.getAdditionalItems());
		this.traverseUnion("contains", model.getContains());
		this.traverseUnion("additionalProperties", model.getAdditionalProperties());
		this.traverseUnion("propertyNames", model.getPropertyNames());
	}

	@Override
	public void visitDocument(Document node) {
		node.accept(this.visitor);
		JSDraft6Document model = (JSDraft6Document) node;
		this.traverseUnion("type", model.getType());
		this.traverseUnion("not", model.getNot());
		this.traverseUnion("items", model.getItems());
		this.traverseUnion("additionalItems", model.getAdditionalItems());
		this.traverseUnion("contains", model.getContains());
		this.traverseUnion("additionalProperties", model.getAdditionalProperties());
		this.traverseUnion("propertyNames", model.getPropertyNames());
	}
}