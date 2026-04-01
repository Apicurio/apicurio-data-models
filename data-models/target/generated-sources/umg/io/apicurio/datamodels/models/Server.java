package io.apicurio.datamodels.models;

import java.util.Map;

public interface Server extends Node {

	public ServerVariable createServerVariable();

	public Map<String, ServerVariable> getVariables();

	public void addVariable(String name, ServerVariable value);

	public void clearVariables();

	public void removeVariable(String name);

	public void insertVariable(String name, ServerVariable value, int atIndex);

	public String getDescription();

	public void setDescription(String value);
}