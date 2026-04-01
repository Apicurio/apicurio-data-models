package io.apicurio.datamodels.models.visitors;

import io.apicurio.datamodels.models.Document;

public interface Visitor {

	public void visitDocument(Document node);
}