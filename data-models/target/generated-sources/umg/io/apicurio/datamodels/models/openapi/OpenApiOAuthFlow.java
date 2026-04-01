package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.OAuthFlow;
import java.util.Map;

public interface OpenApiOAuthFlow extends OAuthFlow {

	public Map<String, String> getScopes();

	public void setScopes(Map<String, String> value);
}