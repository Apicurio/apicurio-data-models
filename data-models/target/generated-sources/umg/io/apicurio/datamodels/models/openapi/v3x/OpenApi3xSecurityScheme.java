package io.apicurio.datamodels.models.openapi.v3x;

import io.apicurio.datamodels.models.openapi.OpenApiSecurityScheme;

public interface OpenApi3xSecurityScheme extends OpenApiSecurityScheme {

	public OpenApi3xOAuthFlows getFlows();

	public void setFlows(OpenApi3xOAuthFlows value);

	public OpenApi3xOAuthFlows createOAuthFlows();

	public String getOpenIdConnectUrl();

	public void setOpenIdConnectUrl(String value);

	public String getScheme();

	public void setScheme(String value);

	public String getBearerFormat();

	public void setBearerFormat(String value);
}