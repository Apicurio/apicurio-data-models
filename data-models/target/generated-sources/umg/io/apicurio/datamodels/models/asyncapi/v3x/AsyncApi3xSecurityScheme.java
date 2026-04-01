package io.apicurio.datamodels.models.asyncapi.v3x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiSecurityScheme;
import java.util.List;

public interface AsyncApi3xSecurityScheme extends AsyncApiSecurityScheme {

	public List<String> getScopes();

	public void setScopes(List<String> value);
}