package io.apicurio.datamodels.models.openapi.v3x;

import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import java.util.List;

public interface OpenApi3xSchema extends OpenApiSchema {

	public OpenApi3xSchema getItems();

	public void setItems(OpenApi3xSchema value);

	public OpenApi3xSchema createSchema();

	public OpenApi3xSchema getNot();

	public void setNot(OpenApi3xSchema value);

	public List<OpenApi3xSchema> getOneOf();

	public void addOneOf(OpenApi3xSchema value);

	public void clearOneOf();

	public void removeOneOf(OpenApi3xSchema value);

	public void insertOneOf(OpenApi3xSchema value, int atIndex);

	public List<OpenApi3xSchema> getAnyOf();

	public void addAnyOf(OpenApi3xSchema value);

	public void clearAnyOf();

	public void removeAnyOf(OpenApi3xSchema value);

	public void insertAnyOf(OpenApi3xSchema value, int atIndex);

	public Boolean isDeprecated();

	public void setDeprecated(Boolean value);

	public OpenApi3xDiscriminator getDiscriminator();

	public void setDiscriminator(OpenApi3xDiscriminator value);

	public OpenApi3xDiscriminator createDiscriminator();

	public Boolean isWriteOnly();

	public void setWriteOnly(Boolean value);
}