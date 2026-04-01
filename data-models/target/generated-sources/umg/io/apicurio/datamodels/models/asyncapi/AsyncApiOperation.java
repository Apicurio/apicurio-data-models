package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.Operation;
import java.util.List;

public interface AsyncApiOperation extends Operation {

	public AsyncApiOperationBindings getBindings();

	public void setBindings(AsyncApiOperationBindings value);

	public AsyncApiOperationBindings createOperationBindings();

	public AsyncApiOperationTrait createOperationTrait();

	public List<AsyncApiOperationTrait> getTraits();

	public void addTrait(AsyncApiOperationTrait value);

	public void clearTraits();

	public void removeTrait(AsyncApiOperationTrait value);

	public void insertTrait(AsyncApiOperationTrait value, int atIndex);

	public AsyncApiTag createTag();

	public List<AsyncApiTag> getTags();

	public void addTag(AsyncApiTag value);

	public void clearTags();

	public void removeTag(AsyncApiTag value);

	public void insertTag(AsyncApiTag value, int atIndex);
}