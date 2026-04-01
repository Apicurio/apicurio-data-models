package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.Node;
import java.util.List;

public interface AsyncApiOperationTrait extends Node {

	public AsyncApiTag createTag();

	public List<AsyncApiTag> getTags();

	public void addTag(AsyncApiTag value);

	public void clearTags();

	public void removeTag(AsyncApiTag value);

	public void insertTag(AsyncApiTag value, int atIndex);

	public AsyncApiOperationBindings getBindings();

	public void setBindings(AsyncApiOperationBindings value);

	public AsyncApiOperationBindings createOperationBindings();

	public String getDescription();

	public void setDescription(String value);

	public String getSummary();

	public void setSummary(String value);

	public AsyncApiExternalDocumentation getExternalDocs();

	public void setExternalDocs(AsyncApiExternalDocumentation value);

	public AsyncApiExternalDocumentation createExternalDocumentation();
}