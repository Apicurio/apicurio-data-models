package io.apicurio.datamodels.models.openapi.v3x.v32;

import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xDiscriminator;

public interface OpenApi32Discriminator extends OpenApi3xDiscriminator {

	public String getDefaultMapping();

	public void setDefaultMapping(String value);
}