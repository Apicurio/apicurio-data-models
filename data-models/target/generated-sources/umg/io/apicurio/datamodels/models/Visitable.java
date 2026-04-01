package io.apicurio.datamodels.models;

import io.apicurio.datamodels.models.visitors.Visitor;

public interface Visitable {

	public void accept(Visitor visitor);

}
