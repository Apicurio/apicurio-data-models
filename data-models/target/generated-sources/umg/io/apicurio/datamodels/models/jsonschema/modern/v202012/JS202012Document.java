package io.apicurio.datamodels.models.jsonschema.modern.v202012;

import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.jsonschema.modern.JSModernDocument;
import io.apicurio.datamodels.models.union.BooleanJSchemaUnion;
import java.util.List;

public interface JS202012Document extends RootNode, JSModernDocument {

	public String get$dynamicRef();

	public void set$dynamicRef(String value);

	public String get$dynamicAnchor();

	public void set$dynamicAnchor(String value);

	public JS202012JSchema createJSchema();

	public List<BooleanJSchemaUnion> getPrefixItems();

	public void addPrefixItem(BooleanJSchemaUnion value);

	public void clearPrefixItems();

	public void removePrefixItem(BooleanJSchemaUnion value);

	public void insertPrefixItem(BooleanJSchemaUnion value, int atIndex);

	public BooleanJSchemaUnion getItems();

	public void setItems(BooleanJSchemaUnion value);
}