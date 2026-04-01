package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.Operation;
import java.util.List;

public interface OpenApiOperation extends Operation {

	public String getOperationId();

	public void setOperationId(String value);

	public Boolean isDeprecated();

	public void setDeprecated(Boolean value);

	public List<String> getTags();

	public void setTags(List<String> value);

	public OpenApiResponses getResponses();

	public void setResponses(OpenApiResponses value);

	public OpenApiResponses createResponses();
}