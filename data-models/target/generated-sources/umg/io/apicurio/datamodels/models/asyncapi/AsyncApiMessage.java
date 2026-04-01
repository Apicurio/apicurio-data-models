package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.Node;
import java.util.List;

public interface AsyncApiMessage extends Node {

	public String getSummary();

	public void setSummary(String value);

	public String getName();

	public void setName(String value);

	public String getTitle();

	public void setTitle(String value);

	public AsyncApiTag createTag();

	public List<AsyncApiTag> getTags();

	public void addTag(AsyncApiTag value);

	public void clearTags();

	public void removeTag(AsyncApiTag value);

	public void insertTag(AsyncApiTag value, int atIndex);

	public String getDescription();

	public void setDescription(String value);

	public AsyncApiCorrelationID getCorrelationId();

	public void setCorrelationId(AsyncApiCorrelationID value);

	public AsyncApiCorrelationID createCorrelationID();

	public String getContentType();

	public void setContentType(String value);

	public AsyncApiMessageBindings getBindings();

	public void setBindings(AsyncApiMessageBindings value);

	public AsyncApiMessageBindings createMessageBindings();

	public AsyncApiExternalDocumentation getExternalDocs();

	public void setExternalDocs(AsyncApiExternalDocumentation value);

	public AsyncApiExternalDocumentation createExternalDocumentation();

	public AsyncApiMessageTrait createMessageTrait();

	public List<AsyncApiMessageTrait> getTraits();

	public void addTrait(AsyncApiMessageTrait value);

	public void clearTraits();

	public void removeTrait(AsyncApiMessageTrait value);

	public void insertTrait(AsyncApiMessageTrait value, int atIndex);
}