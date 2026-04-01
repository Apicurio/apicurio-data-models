package io.apicurio.datamodels.models.jsonschema.draft.draft4;

import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.jsonschema.draft.JSDraftDocument;

public interface JSDraft4Document extends RootNode, JSDraftDocument {

	public String getId();

	public void setId(String value);

	public Boolean isExclusiveMinimum();

	public void setExclusiveMinimum(Boolean value);

	public Boolean isExclusiveMaximum();

	public void setExclusiveMaximum(Boolean value);
}