package io.apicurio.datamodels.models.openrpc.v1x.v13;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.openrpc.OpenRpcContentDescriptor;
import io.apicurio.datamodels.models.openrpc.OpenRpcError;
import io.apicurio.datamodels.models.openrpc.OpenRpcExample;
import io.apicurio.datamodels.models.openrpc.OpenRpcExamplePairing;
import io.apicurio.datamodels.models.openrpc.OpenRpcLink;
import io.apicurio.datamodels.models.openrpc.OpenRpcSchema;
import io.apicurio.datamodels.models.openrpc.OpenRpcTag;
import io.apicurio.datamodels.models.openrpc.v1x.v13.visitors.OpenRpc13Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenRpc13ComponentsImpl extends NodeImpl implements OpenRpc13Components {

	private Map<String, OpenRpcContentDescriptor> contentDescriptors;
	private Map<String, OpenRpcSchema> schemas;
	private Map<String, OpenRpcExample> examples;
	private Map<String, OpenRpcLink> links;
	private Map<String, OpenRpcError> errors;
	private Map<String, OpenRpcExamplePairing> examplePairings;
	private Map<String, OpenRpcTag> tags;
	private Map<String, JsonNode> extensions;

	@Override
	public OpenRpc13ContentDescriptor createContentDescriptor() {
		OpenRpc13ContentDescriptorImpl node = new OpenRpc13ContentDescriptorImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenRpcContentDescriptor> getContentDescriptors() {
		return contentDescriptors;
	}

	@Override
	public void addContentDescriptor(String name, OpenRpcContentDescriptor value) {
		if (this.contentDescriptors == null) {
			this.contentDescriptors = new LinkedHashMap<>();
		}
		this.contentDescriptors.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("contentDescriptors");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearContentDescriptors() {
		if (this.contentDescriptors != null) {
			this.contentDescriptors.clear();
		}
	}

	@Override
	public void removeContentDescriptor(String name) {
		if (this.contentDescriptors != null) {
			this.contentDescriptors.remove(name);
		}
	}

	@Override
	public void insertContentDescriptor(String name, OpenRpcContentDescriptor value, int atIndex) {
		if (this.contentDescriptors == null) {
			this.contentDescriptors = new LinkedHashMap<>();
			this.contentDescriptors.put(name, value);
		} else {
			this.contentDescriptors = DataModelUtil.insertMapEntry(this.contentDescriptors, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("contentDescriptors");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenRpc13Schema createSchema() {
		OpenRpc13SchemaImpl node = new OpenRpc13SchemaImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenRpcSchema> getSchemas() {
		return schemas;
	}

	@Override
	public void addSchema(String name, OpenRpcSchema value) {
		if (this.schemas == null) {
			this.schemas = new LinkedHashMap<>();
		}
		this.schemas.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("schemas");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearSchemas() {
		if (this.schemas != null) {
			this.schemas.clear();
		}
	}

	@Override
	public void removeSchema(String name) {
		if (this.schemas != null) {
			this.schemas.remove(name);
		}
	}

	@Override
	public void insertSchema(String name, OpenRpcSchema value, int atIndex) {
		if (this.schemas == null) {
			this.schemas = new LinkedHashMap<>();
			this.schemas.put(name, value);
		} else {
			this.schemas = DataModelUtil.insertMapEntry(this.schemas, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("schemas");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenRpc13Example createExample() {
		OpenRpc13ExampleImpl node = new OpenRpc13ExampleImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenRpcExample> getExamples() {
		return examples;
	}

	@Override
	public void addExample(String name, OpenRpcExample value) {
		if (this.examples == null) {
			this.examples = new LinkedHashMap<>();
		}
		this.examples.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("examples");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearExamples() {
		if (this.examples != null) {
			this.examples.clear();
		}
	}

	@Override
	public void removeExample(String name) {
		if (this.examples != null) {
			this.examples.remove(name);
		}
	}

	@Override
	public void insertExample(String name, OpenRpcExample value, int atIndex) {
		if (this.examples == null) {
			this.examples = new LinkedHashMap<>();
			this.examples.put(name, value);
		} else {
			this.examples = DataModelUtil.insertMapEntry(this.examples, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("examples");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenRpc13Link createLink() {
		OpenRpc13LinkImpl node = new OpenRpc13LinkImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenRpcLink> getLinks() {
		return links;
	}

	@Override
	public void addLink(String name, OpenRpcLink value) {
		if (this.links == null) {
			this.links = new LinkedHashMap<>();
		}
		this.links.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("links");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearLinks() {
		if (this.links != null) {
			this.links.clear();
		}
	}

	@Override
	public void removeLink(String name) {
		if (this.links != null) {
			this.links.remove(name);
		}
	}

	@Override
	public void insertLink(String name, OpenRpcLink value, int atIndex) {
		if (this.links == null) {
			this.links = new LinkedHashMap<>();
			this.links.put(name, value);
		} else {
			this.links = DataModelUtil.insertMapEntry(this.links, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("links");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenRpc13Error createError() {
		OpenRpc13ErrorImpl node = new OpenRpc13ErrorImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenRpcError> getErrors() {
		return errors;
	}

	@Override
	public void addError(String name, OpenRpcError value) {
		if (this.errors == null) {
			this.errors = new LinkedHashMap<>();
		}
		this.errors.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("errors");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearErrors() {
		if (this.errors != null) {
			this.errors.clear();
		}
	}

	@Override
	public void removeError(String name) {
		if (this.errors != null) {
			this.errors.remove(name);
		}
	}

	@Override
	public void insertError(String name, OpenRpcError value, int atIndex) {
		if (this.errors == null) {
			this.errors = new LinkedHashMap<>();
			this.errors.put(name, value);
		} else {
			this.errors = DataModelUtil.insertMapEntry(this.errors, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("errors");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenRpc13ExamplePairing createExamplePairing() {
		OpenRpc13ExamplePairingImpl node = new OpenRpc13ExamplePairingImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenRpcExamplePairing> getExamplePairings() {
		return examplePairings;
	}

	@Override
	public void addExamplePairing(String name, OpenRpcExamplePairing value) {
		if (this.examplePairings == null) {
			this.examplePairings = new LinkedHashMap<>();
		}
		this.examplePairings.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("examplePairings");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearExamplePairings() {
		if (this.examplePairings != null) {
			this.examplePairings.clear();
		}
	}

	@Override
	public void removeExamplePairing(String name) {
		if (this.examplePairings != null) {
			this.examplePairings.remove(name);
		}
	}

	@Override
	public void insertExamplePairing(String name, OpenRpcExamplePairing value, int atIndex) {
		if (this.examplePairings == null) {
			this.examplePairings = new LinkedHashMap<>();
			this.examplePairings.put(name, value);
		} else {
			this.examplePairings = DataModelUtil.insertMapEntry(this.examplePairings, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("examplePairings");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenRpc13Tag createTag() {
		OpenRpc13TagImpl node = new OpenRpc13TagImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenRpcTag> getTags() {
		return tags;
	}

	@Override
	public void addTag(String name, OpenRpcTag value) {
		if (this.tags == null) {
			this.tags = new LinkedHashMap<>();
		}
		this.tags.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("tags");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearTags() {
		if (this.tags != null) {
			this.tags.clear();
		}
	}

	@Override
	public void removeTag(String name) {
		if (this.tags != null) {
			this.tags.remove(name);
		}
	}

	@Override
	public void insertTag(String name, OpenRpcTag value, int atIndex) {
		if (this.tags == null) {
			this.tags = new LinkedHashMap<>();
			this.tags.put(name, value);
		} else {
			this.tags = DataModelUtil.insertMapEntry(this.tags, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("tags");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public Map<String, JsonNode> getExtensions() {
		return extensions;
	}

	@Override
	public void addExtension(String name, JsonNode value) {
		if (this.extensions == null) {
			this.extensions = new LinkedHashMap<>();
		}
		this.extensions.put(name, value);
	}

	@Override
	public void clearExtensions() {
		if (this.extensions != null) {
			this.extensions.clear();
		}
	}

	@Override
	public void removeExtension(String name) {
		if (this.extensions != null) {
			this.extensions.remove(name);
		}
	}

	@Override
	public void insertExtension(String name, JsonNode value, int atIndex) {
		if (this.extensions == null) {
			this.extensions = new LinkedHashMap<>();
			this.extensions.put(name, value);
		} else {
			this.extensions = DataModelUtil.insertMapEntry(this.extensions, name, value, atIndex);
		}
	}

	@Override
	public void accept(Visitor visitor) {
		OpenRpc13Visitor viz = (OpenRpc13Visitor) visitor;
		viz.visitComponents(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenRpc13ComponentsImpl();
	}
}