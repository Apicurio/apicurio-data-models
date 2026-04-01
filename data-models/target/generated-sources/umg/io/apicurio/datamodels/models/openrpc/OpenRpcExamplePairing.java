package io.apicurio.datamodels.models.openrpc;

import io.apicurio.datamodels.models.Node;
import java.util.List;

public interface OpenRpcExamplePairing extends Node {

	public String getName();

	public void setName(String value);

	public OpenRpcExample createExample();

	public List<OpenRpcExample> getParams();

	public void addParam(OpenRpcExample value);

	public void clearParams();

	public void removeParam(OpenRpcExample value);

	public void insertParam(OpenRpcExample value, int atIndex);

	public String getDescription();

	public void setDescription(String value);

	public OpenRpcExample getResult();

	public void setResult(OpenRpcExample value);

	public String getSummary();

	public void setSummary(String value);
}