package io.apicurio.datamodels.models.asyncapi.v2x.v22.visitors;

import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageExample;
import io.apicurio.datamodels.models.asyncapi.v2x.visitors.AsyncApi2xVisitor;

public interface AsyncApi22Visitor extends AsyncApi2xVisitor {

	public void visitMessageExample(AsyncApiMessageExample node);
}