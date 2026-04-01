package io.apicurio.datamodels.models.openapi;

import java.util.Map;

public interface OpenApiExamplesParent {

	public OpenApiExample createExample();

	public Map<String, OpenApiExample> getExamples();

	public void addExample(String name, OpenApiExample value);

	public void clearExamples();

	public void removeExample(String name);

	public void insertExample(String name, OpenApiExample value, int atIndex);
}