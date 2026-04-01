package io.apicurio.datamodels.models.jsonschema.modern.v202012.visitors;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.JS202012Document;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.JS202012JSchema;
import io.apicurio.datamodels.models.visitors.AbstractTraverser;
import io.apicurio.datamodels.models.visitors.Visitor;

public class JS202012Traverser extends AbstractTraverser implements JS202012Visitor {

	public JS202012Traverser(Visitor visitor) {
		super(visitor);
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
		node.accept(this.visitor);
		JS202012JSchema model = (JS202012JSchema) node;
		this.traverseUnion("type", model.getType());
		this.traverseUnion("not", model.getNot());
		this.traverseUnion("if", model.getIf());
		this.traverseUnion("then", model.getThen());
		this.traverseUnion("else", model.getElse());
		this.traverseUnion("contentSchema", model.getContentSchema());
		this.traverseUnion("items", model.getItems());
		this.traverseUnion("contains", model.getContains());
		this.traverseUnion("unevaluatedItems", model.getUnevaluatedItems());
		this.traverseUnion("additionalProperties", model.getAdditionalProperties());
		this.traverseUnion("propertyNames", model.getPropertyNames());
		this.traverseUnion("unevaluatedProperties", model.getUnevaluatedProperties());
	}

	@Override
	public void visitDocument(Document node) {
		node.accept(this.visitor);
		JS202012Document model = (JS202012Document) node;
		this.traverseUnion("type", model.getType());
		this.traverseUnion("not", model.getNot());
		this.traverseUnion("if", model.getIf());
		this.traverseUnion("then", model.getThen());
		this.traverseUnion("else", model.getElse());
		this.traverseUnion("contentSchema", model.getContentSchema());
		this.traverseUnion("items", model.getItems());
		this.traverseUnion("contains", model.getContains());
		this.traverseUnion("unevaluatedItems", model.getUnevaluatedItems());
		this.traverseUnion("additionalProperties", model.getAdditionalProperties());
		this.traverseUnion("propertyNames", model.getPropertyNames());
		this.traverseUnion("unevaluatedProperties", model.getUnevaluatedProperties());
	}
}