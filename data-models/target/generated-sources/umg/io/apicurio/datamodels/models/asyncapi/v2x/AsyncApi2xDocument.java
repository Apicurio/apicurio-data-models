package io.apicurio.datamodels.models.asyncapi.v2x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiDocument;
import java.util.List;

public interface AsyncApi2xDocument extends AsyncApiDocument {

	public AsyncApi2xTag createTag();

	public List<AsyncApi2xTag> getTags();

	public void addTag(AsyncApi2xTag value);

	public void clearTags();

	public void removeTag(AsyncApi2xTag value);

	public void insertTag(AsyncApi2xTag value, int atIndex);

	public AsyncApi2xExternalDocumentation getExternalDocs();

	public void setExternalDocs(AsyncApi2xExternalDocumentation value);

	public AsyncApi2xExternalDocumentation createExternalDocumentation();
}