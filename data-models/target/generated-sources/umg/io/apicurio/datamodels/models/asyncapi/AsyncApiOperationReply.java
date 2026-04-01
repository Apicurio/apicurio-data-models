package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.Node;
import java.util.List;

public interface AsyncApiOperationReply extends Node {

	public AsyncApiReference createReference();

	public List<AsyncApiReference> getMessages();

	public void addMessage(AsyncApiReference value);

	public void clearMessages();

	public void removeMessage(AsyncApiReference value);

	public void insertMessage(AsyncApiReference value, int atIndex);

	public AsyncApiOperationReplyAddress getAddress();

	public void setAddress(AsyncApiOperationReplyAddress value);

	public AsyncApiOperationReplyAddress createOperationReplyAddress();

	public AsyncApiReference getChannel();

	public void setChannel(AsyncApiReference value);
}