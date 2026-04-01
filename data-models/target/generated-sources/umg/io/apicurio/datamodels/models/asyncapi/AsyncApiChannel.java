package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.Node;
import java.util.List;
import java.util.Map;

public interface AsyncApiChannel extends Node {

	public AsyncApiReference createReference();

	public List<AsyncApiReference> getServers();

	public void addServer(AsyncApiReference value);

	public void clearServers();

	public void removeServer(AsyncApiReference value);

	public void insertServer(AsyncApiReference value, int atIndex);

	public String getTitle();

	public void setTitle(String value);

	public AsyncApiTag createTag();

	public List<AsyncApiTag> getTags();

	public void addTag(AsyncApiTag value);

	public void clearTags();

	public void removeTag(AsyncApiTag value);

	public void insertTag(AsyncApiTag value, int atIndex);

	public String getAddress();

	public void setAddress(String value);

	public String getDescription();

	public void setDescription(String value);

	public AsyncApiChannelBindings getBindings();

	public void setBindings(AsyncApiChannelBindings value);

	public AsyncApiChannelBindings createChannelBindings();

	public AsyncApiParameters getParameters();

	public void setParameters(AsyncApiParameters value);

	public AsyncApiParameters createParameters();

	public AsyncApiMessage createMessage();

	public Map<String, AsyncApiMessage> getMessages();

	public void addMessage(String name, AsyncApiMessage value);

	public void clearMessages();

	public void removeMessage(String name);

	public void insertMessage(String name, AsyncApiMessage value, int atIndex);

	public String getSummary();

	public void setSummary(String value);

	public AsyncApiExternalDocumentation getExternalDocs();

	public void setExternalDocs(AsyncApiExternalDocumentation value);

	public AsyncApiExternalDocumentation createExternalDocumentation();
}