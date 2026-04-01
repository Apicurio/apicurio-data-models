package io.apicurio.datamodels.models.union;

import io.apicurio.datamodels.models.Visitable;

public interface Union extends Visitable {

	public Object unionValue();

	public boolean isEntity();

	public boolean isEntityList();

	public boolean isEntityMap();

}
