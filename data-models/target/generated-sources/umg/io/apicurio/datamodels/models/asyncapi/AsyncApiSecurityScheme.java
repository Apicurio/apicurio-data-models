package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.SecurityScheme;

public interface AsyncApiSecurityScheme extends SecurityScheme {

	public AsyncApiOAuthFlows getFlows();

	public void setFlows(AsyncApiOAuthFlows value);

	public AsyncApiOAuthFlows createOAuthFlows();

	public String getOpenIdConnectUrl();

	public void setOpenIdConnectUrl(String value);

	public String getScheme();

	public void setScheme(String value);

	public String getBearerFormat();

	public void setBearerFormat(String value);
}