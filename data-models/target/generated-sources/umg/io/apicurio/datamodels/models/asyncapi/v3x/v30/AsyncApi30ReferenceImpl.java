package io.apicurio.datamodels.models.asyncapi.v3x.v30;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.visitors.AsyncApi30Visitor;
import io.apicurio.datamodels.models.visitors.Visitor;

public class AsyncApi30ReferenceImpl extends NodeImpl implements AsyncApi30Reference {

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
		AsyncApi30Visitor viz = (AsyncApi30Visitor) visitor;
		viz.visitReference(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi30ReferenceImpl();
	}
}