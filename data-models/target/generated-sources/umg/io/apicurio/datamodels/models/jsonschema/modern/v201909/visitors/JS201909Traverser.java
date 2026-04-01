package io.apicurio.datamodels.models.jsonschema.modern.v201909.visitors;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.modern.v201909.JS201909Document;
import io.apicurio.datamodels.models.jsonschema.modern.v201909.JS201909JSchema;
import io.apicurio.datamodels.models.visitors.AbstractTraverser;
import io.apicurio.datamodels.models.visitors.Visitor;

public class JS201909Traverser extends AbstractTraverser implements JS201909Visitor {

	public JS201909Traverser(Visitor visitor) {
		super(visitor);
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
		node.accept(this.visitor);
		JS201909JSchema model = (JS201909JSchema) node;
		this.traverseUnion("type", model.getType());
		this.traverseUnion("not", model.getNot());
		this.traverseUnion("if", model.getIf());
		this.traverseUnion("then", model.getThen());
		this.traverseUnion("else", model.getElse());
		this.traverseUnion("contentSchema", model.getContentSchema());
		this.traverseUnion("items", model.getItems());
		this.traverseUnion("additionalItems", model.getAdditionalItems());
		this.traverseUnion("contains", model.getContains());
		this.traverseUnion("unevaluatedItems", model.getUnevaluatedItems());
		this.traverseUnion("additionalProperties", model.getAdditionalProperties());
		this.traverseUnion("propertyNames", model.getPropertyNames());
		this.traverseUnion("unevaluatedProperties", model.getUnevaluatedProperties());
	}

	@Override
	public void visitDocument(Document node) {
		node.accept(this.visitor);
		JS201909Document model = (JS201909Document) node;
		this.traverseUnion("type", model.getType());
		this.traverseUnion("not", model.getNot());
		this.traverseUnion("if", model.getIf());
		this.traverseUnion("then", model.getThen());
		this.traverseUnion("else", model.getElse());
		this.traverseUnion("contentSchema", model.getContentSchema());
		this.traverseUnion("items", model.getItems());
		this.traverseUnion("additionalItems", model.getAdditionalItems());
		this.traverseUnion("contains", model.getContains());
		this.traverseUnion("unevaluatedItems", model.getUnevaluatedItems());
		this.traverseUnion("additionalProperties", model.getAdditionalProperties());
		this.traverseUnion("propertyNames", model.getPropertyNames());
		this.traverseUnion("unevaluatedProperties", model.getUnevaluatedProperties());
	}
}