package io.apicurio.datamodels.models.jsonschema.draft.draft4;

import io.apicurio.datamodels.models.jsonschema.draft.JSDraftJSchema;

public interface JSDraft4JSchema extends JSDraftJSchema, JSDraft4Referenceable {

	public Boolean isExclusiveMinimum();

	public void setExclusiveMinimum(Boolean value);

	public Boolean isExclusiveMaximum();

	public void setExclusiveMaximum(Boolean value);

	public String get$ref();

	public void set$ref(String value);
}