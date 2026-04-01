package io.apicurio.datamodels.models.asyncapi.v3x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiOperation;
import java.util.List;

public interface AsyncApi3xOperation extends AsyncApiOperation {

	public String getTitle();

	public void setTitle(String value);

	public String getAction();

	public void setAction(String value);

	public AsyncApi3xSecurityScheme createSecurityScheme();

	public List<AsyncApi3xSecurityScheme> getSecurity();

	public void addSecurity(AsyncApi3xSecurityScheme value);

	public void clearSecurity();

	public void removeSecurity(AsyncApi3xSecurityScheme value);

	public void insertSecurity(AsyncApi3xSecurityScheme value, int atIndex);

	public AsyncApi3xReference createReference();

	public List<AsyncApi3xReference> getMessages();

	public void addMessage(AsyncApi3xReference value);

	public void clearMessages();

	public void removeMessage(AsyncApi3xReference value);

	public void insertMessage(AsyncApi3xReference value, int atIndex);

	public AsyncApi3xOperationReply getReply();

	public void setReply(AsyncApi3xOperationReply value);

	public AsyncApi3xOperationReply createOperationReply();

	public AsyncApi3xReference getChannel();

	public void setChannel(AsyncApi3xReference value);
}