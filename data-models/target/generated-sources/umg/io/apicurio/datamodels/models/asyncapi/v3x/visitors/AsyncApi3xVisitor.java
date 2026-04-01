package io.apicurio.datamodels.models.asyncapi.v3x.visitors;

import io.apicurio.datamodels.models.asyncapi.AsyncApiChannel;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageExample;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMultiFormatSchema;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationReply;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationReplyAddress;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperations;
import io.apicurio.datamodels.models.asyncapi.AsyncApiReference;
import io.apicurio.datamodels.models.asyncapi.visitors.AsyncApiVisitor;

public interface AsyncApi3xVisitor extends AsyncApiVisitor {

	public void visitChannel(AsyncApiChannel node);

	public void visitReference(AsyncApiReference node);

	public void visitMultiFormatSchema(AsyncApiMultiFormatSchema node);

	public void visitOperationReplyAddress(AsyncApiOperationReplyAddress node);

	public void visitOperationReply(AsyncApiOperationReply node);

	public void visitMessageExample(AsyncApiMessageExample node);

	public void visitOperations(AsyncApiOperations node);
}