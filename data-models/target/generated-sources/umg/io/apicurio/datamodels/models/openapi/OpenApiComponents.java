package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.Components;
import java.util.Map;

public interface OpenApiComponents extends Components {

	public OpenApiRequestBody createRequestBody();

	public Map<String, OpenApiRequestBody> getRequestBodies();

	public void addRequestBody(String name, OpenApiRequestBody value);

	public void clearRequestBodies();

	public void removeRequestBody(String name);

	public void insertRequestBody(String name, OpenApiRequestBody value, int atIndex);

	public OpenApiSchema createSchema();

	public Map<String, OpenApiSchema> getSchemas();

	public void addSchema(String name, OpenApiSchema value);

	public void clearSchemas();

	public void removeSchema(String name);

	public void insertSchema(String name, OpenApiSchema value, int atIndex);

	public OpenApiLink createLink();

	public Map<String, OpenApiLink> getLinks();

	public void addLink(String name, OpenApiLink value);

	public void clearLinks();

	public void removeLink(String name);

	public void insertLink(String name, OpenApiLink value, int atIndex);

	public OpenApiResponse createResponse();

	public Map<String, OpenApiResponse> getResponses();

	public void addResponse(String name, OpenApiResponse value);

	public void clearResponses();

	public void removeResponse(String name);

	public void insertResponse(String name, OpenApiResponse value, int atIndex);

	public OpenApiExample createExample();

	public Map<String, OpenApiExample> getExamples();

	public void addExample(String name, OpenApiExample value);

	public void clearExamples();

	public void removeExample(String name);

	public void insertExample(String name, OpenApiExample value, int atIndex);

	public OpenApiHeader createHeader();

	public Map<String, OpenApiHeader> getHeaders();

	public void addHeader(String name, OpenApiHeader value);

	public void clearHeaders();

	public void removeHeader(String name);

	public void insertHeader(String name, OpenApiHeader value, int atIndex);

	public OpenApiParameter createParameter();

	public Map<String, OpenApiParameter> getParameters();

	public void addParameter(String name, OpenApiParameter value);

	public void clearParameters();

	public void removeParameter(String name);

	public void insertParameter(String name, OpenApiParameter value, int atIndex);

	public OpenApiCallback createCallback();

	public Map<String, OpenApiCallback> getCallbacks();

	public void addCallback(String name, OpenApiCallback value);

	public void clearCallbacks();

	public void removeCallback(String name);

	public void insertCallback(String name, OpenApiCallback value, int atIndex);

	public OpenApiSecurityScheme createSecurityScheme();

	public Map<String, OpenApiSecurityScheme> getSecuritySchemes();

	public void addSecurityScheme(String name, OpenApiSecurityScheme value);

	public void clearSecuritySchemes();

	public void removeSecurityScheme(String name);

	public void insertSecurityScheme(String name, OpenApiSecurityScheme value, int atIndex);
}