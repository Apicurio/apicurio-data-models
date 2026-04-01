package io.apicurio.datamodels.models;
public interface Tag extends Node {

	public String getName();

	public void setName(String value);

	public String getDescription();

	public void setDescription(String value);

	public ExternalDocumentation getExternalDocs();

	public void setExternalDocs(ExternalDocumentation value);

	public ExternalDocumentation createExternalDocumentation();
}