package io.apicurio.datamodels.models;
public interface Operation extends Node {

	public String getDescription();

	public void setDescription(String value);

	public String getSummary();

	public void setSummary(String value);

	public ExternalDocumentation getExternalDocs();

	public void setExternalDocs(ExternalDocumentation value);

	public ExternalDocumentation createExternalDocumentation();
}