package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.Document;
import java.util.List;

public interface OpenApiDocument extends Document {

	public OpenApiPaths getPaths();

	public void setPaths(OpenApiPaths value);

	public OpenApiPaths createPaths();

	public OpenApiInfo getInfo();

	public void setInfo(OpenApiInfo value);

	public OpenApiInfo createInfo();

	public OpenApiTag createTag();

	public List<OpenApiTag> getTags();

	public void addTag(OpenApiTag value);

	public void clearTags();

	public void removeTag(OpenApiTag value);

	public void insertTag(OpenApiTag value, int atIndex);

	public OpenApiExternalDocumentation getExternalDocs();

	public void setExternalDocs(OpenApiExternalDocumentation value);

	public OpenApiExternalDocumentation createExternalDocumentation();
}