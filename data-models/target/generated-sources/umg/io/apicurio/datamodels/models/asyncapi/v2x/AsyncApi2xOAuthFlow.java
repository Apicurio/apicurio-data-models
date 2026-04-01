package io.apicurio.datamodels.models.asyncapi.v2x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiOAuthFlow;
import java.util.Map;

public interface AsyncApi2xOAuthFlow extends AsyncApiOAuthFlow {

	public Map<String, String> getScopes();

	public void setScopes(Map<String, String> value);
}