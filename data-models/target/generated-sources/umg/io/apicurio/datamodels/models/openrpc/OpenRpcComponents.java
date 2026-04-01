package io.apicurio.datamodels.models.openrpc;

import io.apicurio.datamodels.models.Components;
import java.util.Map;

public interface OpenRpcComponents extends Components {

	public OpenRpcSchema createSchema();

	public Map<String, OpenRpcSchema> getSchemas();

	public void addSchema(String name, OpenRpcSchema value);

	public void clearSchemas();

	public void removeSchema(String name);

	public void insertSchema(String name, OpenRpcSchema value, int atIndex);

	public OpenRpcLink createLink();

	public Map<String, OpenRpcLink> getLinks();

	public void addLink(String name, OpenRpcLink value);

	public void clearLinks();

	public void removeLink(String name);

	public void insertLink(String name, OpenRpcLink value, int atIndex);

	public OpenRpcExample createExample();

	public Map<String, OpenRpcExample> getExamples();

	public void addExample(String name, OpenRpcExample value);

	public void clearExamples();

	public void removeExample(String name);

	public void insertExample(String name, OpenRpcExample value, int atIndex);

	public OpenRpcExamplePairing createExamplePairing();

	public Map<String, OpenRpcExamplePairing> getExamplePairings();

	public void addExamplePairing(String name, OpenRpcExamplePairing value);

	public void clearExamplePairings();

	public void removeExamplePairing(String name);

	public void insertExamplePairing(String name, OpenRpcExamplePairing value, int atIndex);

	public OpenRpcTag createTag();

	public Map<String, OpenRpcTag> getTags();

	public void addTag(String name, OpenRpcTag value);

	public void clearTags();

	public void removeTag(String name);

	public void insertTag(String name, OpenRpcTag value, int atIndex);

	public OpenRpcError createError();

	public Map<String, OpenRpcError> getErrors();

	public void addError(String name, OpenRpcError value);

	public void clearErrors();

	public void removeError(String name);

	public void insertError(String name, OpenRpcError value, int atIndex);

	public OpenRpcContentDescriptor createContentDescriptor();

	public Map<String, OpenRpcContentDescriptor> getContentDescriptors();

	public void addContentDescriptor(String name, OpenRpcContentDescriptor value);

	public void clearContentDescriptors();

	public void removeContentDescriptor(String name);

	public void insertContentDescriptor(String name, OpenRpcContentDescriptor value, int atIndex);
}