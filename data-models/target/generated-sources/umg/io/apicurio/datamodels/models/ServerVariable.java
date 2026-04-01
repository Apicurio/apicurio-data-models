package io.apicurio.datamodels.models;

import java.util.List;

public interface ServerVariable extends Node {

	public String getDescription();

	public void setDescription(String value);

	public String getDefault();

	public void setDefault(String value);

	public List<String> getEnum();

	public void setEnum(List<String> value);
}