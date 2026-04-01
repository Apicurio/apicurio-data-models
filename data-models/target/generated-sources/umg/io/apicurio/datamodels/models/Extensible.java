package io.apicurio.datamodels.models;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;

public interface Extensible {

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}