package io.apicurio.datamodels.models.openapi.v3x.v31;

import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xReferenceable;

public interface OpenApi31Referenceable extends OpenApi3xReferenceable {

	public String getSummary();

	public void setSummary(String value);

	public String getDescription();

	public void setDescription(String value);
}