package io.apicurio.datamodels.models.jsonschema.draft;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaDocument;
import io.apicurio.datamodels.models.union.BooleanJSchemaJSchemaListUnion;
import io.apicurio.datamodels.models.union.BooleanJSchemaUnion;
import java.util.Map;

public interface JSDraftDocument extends JsonSchemaDocument {

	public BooleanJSchemaUnion getAdditionalItems();

	public void setAdditionalItems(BooleanJSchemaUnion value);

	public JSDraftJSchema createJSchema();

	public Map<String, JsonNode> getDependencies();

	public void setDependencies(Map<String, JsonNode> value);

	public Map<String, BooleanJSchemaUnion> getDefinitions();

	public void addDefinition(String name, BooleanJSchemaUnion value);

	public void clearDefinitions();

	public void removeDefinition(String name);

	public void insertDefinition(String name, BooleanJSchemaUnion value, int atIndex);

	public BooleanJSchemaJSchemaListUnion getItems();

	public void setItems(BooleanJSchemaJSchemaListUnion value);
}