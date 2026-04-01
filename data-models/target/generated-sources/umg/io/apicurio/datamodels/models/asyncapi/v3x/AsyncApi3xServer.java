package io.apicurio.datamodels.models.asyncapi.v3x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiServer;
import java.util.List;

public interface AsyncApi3xServer extends AsyncApiServer {

	public String getTitle();

	public void setTitle(String value);

	public AsyncApi3xTag createTag();

	public List<AsyncApi3xTag> getTags();

	public void addTag(AsyncApi3xTag value);

	public void clearTags();

	public void removeTag(AsyncApi3xTag value);

	public void insertTag(AsyncApi3xTag value, int atIndex);

	public String getPathname();

	public void setPathname(String value);

	public String getHost();

	public void setHost(String value);

	public AsyncApi3xSecurityScheme createSecurityScheme();

	public List<AsyncApi3xSecurityScheme> getSecurity();

	public void addSecurity(AsyncApi3xSecurityScheme value);

	public void clearSecurity();

	public void removeSecurity(AsyncApi3xSecurityScheme value);

	public void insertSecurity(AsyncApi3xSecurityScheme value, int atIndex);

	public String getSummary();

	public void setSummary(String value);

	public AsyncApi3xExternalDocumentation getExternalDocs();

	public void setExternalDocs(AsyncApi3xExternalDocumentation value);

	public AsyncApi3xExternalDocumentation createExternalDocumentation();
}