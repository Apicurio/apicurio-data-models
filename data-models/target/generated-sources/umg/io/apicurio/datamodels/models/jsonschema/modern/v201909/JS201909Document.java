package io.apicurio.datamodels.models.jsonschema.modern.v201909;

import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.jsonschema.modern.JSModernDocument;
import io.apicurio.datamodels.models.union.BooleanJSchemaJSchemaListUnion;
import io.apicurio.datamodels.models.union.BooleanJSchemaUnion;

public interface JS201909Document extends RootNode, JSModernDocument {

	public String get$recursiveRef();

	public void set$recursiveRef(String value);

	public Boolean is$recursiveAnchor();

	public void set$recursiveAnchor(Boolean value);

	public BooleanJSchemaJSchemaListUnion getItems();

	public void setItems(BooleanJSchemaJSchemaListUnion value);

	public JS201909JSchema createJSchema();

	public BooleanJSchemaUnion getAdditionalItems();

	public void setAdditionalItems(BooleanJSchemaUnion value);
}