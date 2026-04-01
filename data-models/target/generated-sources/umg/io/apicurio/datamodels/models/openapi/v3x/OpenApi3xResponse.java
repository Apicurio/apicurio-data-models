package io.apicurio.datamodels.models.openapi.v3x;

import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import java.util.Map;

public interface OpenApi3xResponse extends OpenApiResponse {

	public OpenApi3xLink createLink();

	public Map<String, OpenApi3xLink> getLinks();

	public void addLink(String name, OpenApi3xLink value);

	public void clearLinks();

	public void removeLink(String name);

	public void insertLink(String name, OpenApi3xLink value, int atIndex);

	public OpenApi3xMediaType createMediaType();

	public Map<String, OpenApi3xMediaType> getContent();

	public void addContent(String name, OpenApi3xMediaType value);

	public void clearContent();

	public void removeContent(String name);

	public void insertContent(String name, OpenApi3xMediaType value, int atIndex);
}