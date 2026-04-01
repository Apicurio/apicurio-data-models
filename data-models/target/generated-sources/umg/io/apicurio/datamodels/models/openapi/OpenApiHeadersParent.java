package io.apicurio.datamodels.models.openapi;

import java.util.Map;

public interface OpenApiHeadersParent {

	public OpenApiHeader createHeader();

	public Map<String, OpenApiHeader> getHeaders();

	public void addHeader(String name, OpenApiHeader value);

	public void clearHeaders();

	public void removeHeader(String name);

	public void insertHeader(String name, OpenApiHeader value, int atIndex);
}