package io.apicurio.datamodels.models.openrpc;

import io.apicurio.datamodels.models.Node;
import java.util.List;

public interface OpenRpcMethod extends Node {

	public String getName();

	public void setName(String value);

	public OpenRpcTag createTag();

	public List<OpenRpcTag> getTags();

	public void addTag(OpenRpcTag value);

	public void clearTags();

	public void removeTag(OpenRpcTag value);

	public void insertTag(OpenRpcTag value, int atIndex);

	public Boolean isDeprecated();

	public void setDeprecated(Boolean value);

	public String getParamStructure();

	public void setParamStructure(String value);

	public OpenRpcContentDescriptor getResult();

	public void setResult(OpenRpcContentDescriptor value);

	public OpenRpcContentDescriptor createContentDescriptor();

	public OpenRpcLink createLink();

	public List<OpenRpcLink> getLinks();

	public void addLink(OpenRpcLink value);

	public void clearLinks();

	public void removeLink(OpenRpcLink value);

	public void insertLink(OpenRpcLink value, int atIndex);

	public String getDescription();

	public void setDescription(String value);

	public OpenRpcExamplePairing createExamplePairing();

	public List<OpenRpcExamplePairing> getExamples();

	public void addExample(OpenRpcExamplePairing value);

	public void clearExamples();

	public void removeExample(OpenRpcExamplePairing value);

	public void insertExample(OpenRpcExamplePairing value, int atIndex);

	public List<OpenRpcContentDescriptor> getParams();

	public void addParam(OpenRpcContentDescriptor value);

	public void clearParams();

	public void removeParam(OpenRpcContentDescriptor value);

	public void insertParam(OpenRpcContentDescriptor value, int atIndex);

	public OpenRpcError createError();

	public List<OpenRpcError> getErrors();

	public void addError(OpenRpcError value);

	public void clearErrors();

	public void removeError(OpenRpcError value);

	public void insertError(OpenRpcError value, int atIndex);

	public String getSummary();

	public void setSummary(String value);

	public OpenRpcExternalDocumentation getExternalDocs();

	public void setExternalDocs(OpenRpcExternalDocumentation value);

	public OpenRpcExternalDocumentation createExternalDocumentation();
}