package io.apicurio.datamodels.models.openapi.v2x;

import io.apicurio.datamodels.models.openapi.OpenApiSecurityScheme;

public interface OpenApi2xSecurityScheme extends OpenApiSecurityScheme {

	public String getTokenUrl();

	public void setTokenUrl(String value);

	public OpenApi2xScopes getScopes();

	public void setScopes(OpenApi2xScopes value);

	public OpenApi2xScopes createScopes();

	public String getAuthorizationUrl();

	public void setAuthorizationUrl(String value);

	public String getFlow();

	public void setFlow(String value);
}