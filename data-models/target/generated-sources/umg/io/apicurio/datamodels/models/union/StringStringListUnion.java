package io.apicurio.datamodels.models.union;

import java.util.List;

public interface StringStringListUnion extends Union {

	public boolean isString();

	public String asString();

	public boolean isStringList();

	public List<String> asStringList();
}