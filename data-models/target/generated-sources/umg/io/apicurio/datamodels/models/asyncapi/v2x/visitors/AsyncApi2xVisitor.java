package io.apicurio.datamodels.models.asyncapi.v2x.visitors;

import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelItem;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOneOfMessages;
import io.apicurio.datamodels.models.asyncapi.visitors.AsyncApiVisitor;

public interface AsyncApi2xVisitor extends AsyncApiVisitor {

	public void visitSecurityRequirement(SecurityRequirement node);

	public void visitOneOfMessages(AsyncApiOneOfMessages node);

	public void visitChannelItem(AsyncApiChannelItem node);
}