package io.apicurio.datamodels.models.union;

import io.apicurio.datamodels.models.Schema;
import java.util.List;

public interface SchemaSchemaListUnion extends Union {

	public boolean isSchema();

	public Schema asSchema();

	public boolean isSchemaList();

	public List<Schema> asSchemaList();
}