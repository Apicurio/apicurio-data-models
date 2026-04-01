package io.apicurio.datamodels.models.openapi.v2x;

import io.apicurio.datamodels.models.openapi.OpenApiDocument;
import java.util.List;

public interface OpenApi2xDocument extends OpenApiDocument {

	public List<String> getProduces();

	public void setProduces(List<String> value);

	public OpenApi2xResponseDefinitions getResponses();

	public void setResponses(OpenApi2xResponseDefinitions value);

	public OpenApi2xResponseDefinitions createResponseDefinitions();

	public OpenApi2xParameterDefinitions getParameters();

	public void setParameters(OpenApi2xParameterDefinitions value);

	public OpenApi2xParameterDefinitions createParameterDefinitions();

	public List<String> getConsumes();

	public void setConsumes(List<String> value);

	public String getSwagger();

	public void setSwagger(String value);

	public OpenApi2xSecurityDefinitions getSecurityDefinitions();

	public void setSecurityDefinitions(OpenApi2xSecurityDefinitions value);

	public OpenApi2xSecurityDefinitions createSecurityDefinitions();

	public String getBasePath();

	public void setBasePath(String value);

	public List<String> getSchemes();

	public void setSchemes(List<String> value);

	public String getHost();

	public void setHost(String value);

	public OpenApi2xDefinitions getDefinitions();

	public void setDefinitions(OpenApi2xDefinitions value);

	public OpenApi2xDefinitions createDefinitions();
}