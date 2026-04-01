package io.apicurio.datamodels.models.asyncapi.v3x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiInfo;
import java.util.List;

public interface AsyncApi3xInfo extends AsyncApiInfo {

	public AsyncApi3xTag createTag();

	public List<AsyncApi3xTag> getTags();

	public void addTag(AsyncApi3xTag value);

	public void clearTags();

	public void removeTag(AsyncApi3xTag value);

	public void insertTag(AsyncApi3xTag value, int atIndex);

	public AsyncApi3xExternalDocumentation getExternalDocs();

	public void setExternalDocs(AsyncApi3xExternalDocumentation value);

	public AsyncApi3xExternalDocumentation createExternalDocumentation();
}