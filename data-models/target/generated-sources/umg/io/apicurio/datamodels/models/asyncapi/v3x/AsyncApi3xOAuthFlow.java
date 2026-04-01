package io.apicurio.datamodels.models.asyncapi.v3x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiOAuthFlow;
import java.util.Map;

public interface AsyncApi3xOAuthFlow extends AsyncApiOAuthFlow {

	public Map<String, String> getAvailableScopes();

	public void setAvailableScopes(Map<String, String> value);
}