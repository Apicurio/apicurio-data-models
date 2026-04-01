package io.apicurio.datamodels.models.jsonschema.draft.draft7.visitors;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft7.JSDraft7Document;
import io.apicurio.datamodels.models.jsonschema.draft.draft7.JSDraft7JSchema;
import io.apicurio.datamodels.models.visitors.AbstractTraverser;
import io.apicurio.datamodels.models.visitors.Visitor;

public class JSDraft7Traverser extends AbstractTraverser implements JSDraft7Visitor {

	public JSDraft7Traverser(Visitor visitor) {
		super(visitor);
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
		node.accept(this.visitor);
		JSDraft7JSchema model = (JSDraft7JSchema) node;
		this.traverseUnion("type", model.getType());
		this.traverseUnion("not", model.getNot());
		this.traverseUnion("if", model.getIf());
		this.traverseUnion("then", model.getThen());
		this.traverseUnion("else", model.getElse());
		this.traverseUnion("items", model.getItems());
		this.traverseUnion("additionalItems", model.getAdditionalItems());
		this.traverseUnion("contains", model.getContains());
		this.traverseUnion("additionalProperties", model.getAdditionalProperties());
		this.traverseUnion("propertyNames", model.getPropertyNames());
	}

	@Override
	public void visitDocument(Document node) {
		node.accept(this.visitor);
		JSDraft7Document model = (JSDraft7Document) node;
		this.traverseUnion("type", model.getType());
		this.traverseUnion("not", model.getNot());
		this.traverseUnion("if", model.getIf());
		this.traverseUnion("then", model.getThen());
		this.traverseUnion("else", model.getElse());
		this.traverseUnion("items", model.getItems());
		this.traverseUnion("additionalItems", model.getAdditionalItems());
		this.traverseUnion("contains", model.getContains());
		this.traverseUnion("additionalProperties", model.getAdditionalProperties());
		this.traverseUnion("propertyNames", model.getPropertyNames());
	}
}