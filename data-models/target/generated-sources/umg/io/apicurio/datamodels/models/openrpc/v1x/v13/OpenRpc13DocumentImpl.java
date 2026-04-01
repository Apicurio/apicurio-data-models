package io.apicurio.datamodels.models.openrpc.v1x.v13;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.RootNodeImpl;
import io.apicurio.datamodels.models.openrpc.OpenRpcComponents;
import io.apicurio.datamodels.models.openrpc.OpenRpcExternalDocumentation;
import io.apicurio.datamodels.models.openrpc.OpenRpcInfo;
import io.apicurio.datamodels.models.openrpc.OpenRpcMethod;
import io.apicurio.datamodels.models.openrpc.OpenRpcServer;
import io.apicurio.datamodels.models.openrpc.v1x.v13.visitors.OpenRpc13Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OpenRpc13DocumentImpl extends RootNodeImpl implements OpenRpc13Document {

	private String openrpc;
	private OpenRpcInfo info;
	private List<OpenRpcServer> servers;
	private List<OpenRpcMethod> methods;
	private OpenRpcComponents components;
	private OpenRpcExternalDocumentation externalDocs;
	private Map<String, JsonNode> extensions;

	public OpenRpc13DocumentImpl() {
		super(ModelType.OPENRPC13);
	}

	@Override
	public String getOpenrpc() {
		return openrpc;
	}

	@Override
	public void setOpenrpc(String value) {
		this.openrpc = value;
	}

	@Override
	public OpenRpcInfo getInfo() {
		return info;
	}

	@Override
	public void setInfo(OpenRpcInfo value) {
		this.info = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("info");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenRpc13Info createInfo() {
		OpenRpc13InfoImpl node = new OpenRpc13InfoImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public OpenRpc13Server createServer() {
		OpenRpc13ServerImpl node = new OpenRpc13ServerImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<OpenRpcServer> getServers() {
		return servers;
	}

	@Override
	public void addServer(OpenRpcServer value) {
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
	public void removeServer(OpenRpcServer value) {
		if (this.servers != null) {
			this.servers.remove(value);
		}
	}

	@Override
	public void insertServer(OpenRpcServer value, int atIndex) {
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
	public OpenRpc13Method createMethod() {
		OpenRpc13MethodImpl node = new OpenRpc13MethodImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<OpenRpcMethod> getMethods() {
		return methods;
	}

	@Override
	public void addMethod(OpenRpcMethod value) {
		if (this.methods == null) {
			this.methods = new ArrayList<>();
		}
		this.methods.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("methods");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearMethods() {
		if (this.methods != null) {
			this.methods.clear();
		}
	}

	@Override
	public void removeMethod(OpenRpcMethod value) {
		if (this.methods != null) {
			this.methods.remove(value);
		}
	}

	@Override
	public void insertMethod(OpenRpcMethod value, int atIndex) {
		if (this.methods == null) {
			this.methods = new ArrayList<>();
			this.methods.add(value);
		} else {
			this.methods = DataModelUtil.insertListEntry(this.methods, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("methods");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public OpenRpcComponents getComponents() {
		return components;
	}

	@Override
	public void setComponents(OpenRpcComponents value) {
		this.components = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("components");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenRpc13Components createComponents() {
		OpenRpc13ComponentsImpl node = new OpenRpc13ComponentsImpl();
		node.setParent(this);
		return node;
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
		viz.visitDocument(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenRpc13DocumentImpl();
	}
}