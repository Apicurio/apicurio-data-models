package io.apicurio.datamodels.models.openrpc.v1x.v13;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.openrpc.OpenRpcContentDescriptor;
import io.apicurio.datamodels.models.openrpc.OpenRpcError;
import io.apicurio.datamodels.models.openrpc.OpenRpcExamplePairing;
import io.apicurio.datamodels.models.openrpc.OpenRpcExternalDocumentation;
import io.apicurio.datamodels.models.openrpc.OpenRpcLink;
import io.apicurio.datamodels.models.openrpc.OpenRpcTag;
import io.apicurio.datamodels.models.openrpc.v1x.v13.visitors.OpenRpc13Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OpenRpc13MethodImpl extends NodeImpl implements OpenRpc13Method {

	private String $ref;
	private String name;
	private List<OpenRpcTag> tags;
	private String summary;
	private String description;
	private OpenRpcExternalDocumentation externalDocs;
	private List<OpenRpcContentDescriptor> params;
	private OpenRpcContentDescriptor result;
	private Boolean deprecated;
	private List<Server> servers;
	private List<OpenRpcError> errors;
	private List<OpenRpcLink> links;
	private String paramStructure;
	private List<OpenRpcExamplePairing> examples;
	private Map<String, JsonNode> extensions;

	@Override
	public String get$ref() {
		return $ref;
	}

	@Override
	public void set$ref(String value) {
		this.$ref = value;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String value) {
		this.name = value;
	}

	@Override
	public OpenRpc13Tag createTag() {
		OpenRpc13TagImpl node = new OpenRpc13TagImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<OpenRpcTag> getTags() {
		return tags;
	}

	@Override
	public void addTag(OpenRpcTag value) {
		if (this.tags == null) {
			this.tags = new ArrayList<>();
		}
		this.tags.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("tags");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearTags() {
		if (this.tags != null) {
			this.tags.clear();
		}
	}

	@Override
	public void removeTag(OpenRpcTag value) {
		if (this.tags != null) {
			this.tags.remove(value);
		}
	}

	@Override
	public void insertTag(OpenRpcTag value, int atIndex) {
		if (this.tags == null) {
			this.tags = new ArrayList<>();
			this.tags.add(value);
		} else {
			this.tags = DataModelUtil.insertListEntry(this.tags, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("tags");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public String getSummary() {
		return summary;
	}

	@Override
	public void setSummary(String value) {
		this.summary = value;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String value) {
		this.description = value;
	}

	@Override
	public OpenRpcExternalDocumentation getExternalDocs() {
		return externalDocs;
	}

	@Override
	public void setExternalDocs(OpenRpcExternalDocumentation value) {
		this.externalDocs = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("externalDocs");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenRpc13ExternalDocumentation createExternalDocumentation() {
		OpenRpc13ExternalDocumentationImpl node = new OpenRpc13ExternalDocumentationImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public OpenRpc13ContentDescriptor createContentDescriptor() {
		OpenRpc13ContentDescriptorImpl node = new OpenRpc13ContentDescriptorImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<OpenRpcContentDescriptor> getParams() {
		return params;
	}

	@Override
	public void addParam(OpenRpcContentDescriptor value) {
		if (this.params == null) {
			this.params = new ArrayList<>();
		}
		this.params.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("params");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearParams() {
		if (this.params != null) {
			this.params.clear();
		}
	}

	@Override
	public void removeParam(OpenRpcContentDescriptor value) {
		if (this.params != null) {
			this.params.remove(value);
		}
	}

	@Override
	public void insertParam(OpenRpcContentDescriptor value, int atIndex) {
		if (this.params == null) {
			this.params = new ArrayList<>();
			this.params.add(value);
		} else {
			this.params = DataModelUtil.insertListEntry(this.params, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("params");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public OpenRpcContentDescriptor getResult() {
		return result;
	}

	@Override
	public void setResult(OpenRpcContentDescriptor value) {
		this.result = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("result");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public Boolean isDeprecated() {
		return deprecated;
	}

	@Override
	public void setDeprecated(Boolean value) {
		this.deprecated = value;
	}

	@Override
	public OpenRpc13Server createServer() {
		OpenRpc13ServerImpl node = new OpenRpc13ServerImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<Server> getServers() {
		return servers;
	}

	@Override
	public void addServer(Server value) {
		if (this.servers == null) {
			this.servers = new ArrayList<>();
		}
		this.servers.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("servers");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearServers() {
		if (this.servers != null) {
			this.servers.clear();
		}
	}

	@Override
	public void removeServer(Server value) {
		if (this.servers != null) {
			this.servers.remove(value);
		}
	}

	@Override
	public void insertServer(Server value, int atIndex) {
		if (this.servers == null) {
			this.servers = new ArrayList<>();
			this.servers.add(value);
		} else {
			this.servers = DataModelUtil.insertListEntry(this.servers, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("servers");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public OpenRpc13Error createError() {
		OpenRpc13ErrorImpl node = new OpenRpc13ErrorImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<OpenRpcError> getErrors() {
		return errors;
	}

	@Override
	public void addError(OpenRpcError value) {
		if (this.errors == null) {
			this.errors = new ArrayList<>();
		}
		this.errors.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("errors");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearErrors() {
		if (this.errors != null) {
			this.errors.clear();
		}
	}

	@Override
	public void removeError(OpenRpcError value) {
		if (this.errors != null) {
			this.errors.remove(value);
		}
	}

	@Override
	public void insertError(OpenRpcError value, int atIndex) {
		if (this.errors == null) {
			this.errors = new ArrayList<>();
			this.errors.add(value);
		} else {
			this.errors = DataModelUtil.insertListEntry(this.errors, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("errors");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public OpenRpc13Link createLink() {
		OpenRpc13LinkImpl node = new OpenRpc13LinkImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<OpenRpcLink> getLinks() {
		return links;
	}

	@Override
	public void addLink(OpenRpcLink value) {
		if (this.links == null) {
			this.links = new ArrayList<>();
		}
		this.links.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("links");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearLinks() {
		if (this.links != null) {
			this.links.clear();
		}
	}

	@Override
	public void removeLink(OpenRpcLink value) {
		if (this.links != null) {
			this.links.remove(value);
		}
	}

	@Override
	public void insertLink(OpenRpcLink value, int atIndex) {
		if (this.links == null) {
			this.links = new ArrayList<>();
			this.links.add(value);
		} else {
			this.links = DataModelUtil.insertListEntry(this.links, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("links");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public String getParamStructure() {
		return paramStructure;
	}

	@Override
	public void setParamStructure(String value) {
		this.paramStructure = value;
	}

	@Override
	public OpenRpc13ExamplePairing createExamplePairing() {
		OpenRpc13ExamplePairingImpl node = new OpenRpc13ExamplePairingImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<OpenRpcExamplePairing> getExamples() {
		return examples;
	}

	@Override
	public void addExample(OpenRpcExamplePairing value) {
		if (this.examples == null) {
			this.examples = new ArrayList<>();
		}
		this.examples.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("examples");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearExamples() {
		if (this.examples != null) {
			this.examples.clear();
		}
	}

	@Override
	public void removeExample(OpenRpcExamplePairing value) {
		if (this.examples != null) {
			this.examples.remove(value);
		}
	}

	@Override
	public void insertExample(OpenRpcExamplePairing value, int atIndex) {
		if (this.examples == null) {
			this.examples = new ArrayList<>();
			this.examples.add(value);
		} else {
			this.examples = DataModelUtil.insertListEntry(this.examples, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("examples");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
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
		viz.visitMethod(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenRpc13MethodImpl();
	}
}