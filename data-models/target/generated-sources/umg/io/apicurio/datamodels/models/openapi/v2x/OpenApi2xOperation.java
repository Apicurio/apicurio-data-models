package io.apicurio.datamodels.models.openapi.v2x;

import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import java.util.List;

public interface OpenApi2xOperation extends OpenApiOperation {

	public List<String> getProduces();

	public void setProduces(List<String> value);

	public List<String> getSchemes();

	public void setSchemes(List<String> value);

	public List<String> getConsumes();

	public void setConsumes(List<String> value);
}