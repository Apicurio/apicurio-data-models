package io.apicurio.datamodels.models.asyncapi.v3x.v31;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.visitors.AsyncApi31Visitor;
import io.apicurio.datamodels.models.visitors.Visitor;

public class AsyncApi31ReferenceImpl extends NodeImpl implements AsyncApi31Reference {

	private String $ref;

	@Override
	public String get$ref() {
		return $ref;
	}

	@Override
	public void set$ref(String value) {
		this.$ref = value;
	}

	@Override
	public void accept(Visitor visitor) {
		AsyncApi31Visitor viz = (AsyncApi31Visitor) visitor;
		viz.visitReference(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi31ReferenceImpl();
	}
}