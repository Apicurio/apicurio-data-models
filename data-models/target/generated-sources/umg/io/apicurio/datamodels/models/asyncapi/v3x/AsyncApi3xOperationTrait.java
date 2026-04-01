package io.apicurio.datamodels.models.asyncapi.v3x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;
import java.util.List;

public interface AsyncApi3xOperationTrait extends AsyncApiOperationTrait {

	public String getTitle();

	public void setTitle(String value);

	public AsyncApi3xSecurityScheme createSecurityScheme();

	public List<AsyncApi3xSecurityScheme> getSecurity();

	public void addSecurity(AsyncApi3xSecurityScheme value);

	public void clearSecurity();

	public void removeSecurity(AsyncApi3xSecurityScheme value);

	public void insertSecurity(AsyncApi3xSecurityScheme value, int atIndex);
}