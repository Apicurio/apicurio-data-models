package io.apicurio.datamodels.models.openapi.v3x.v31;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.RootNodeImpl;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.openapi.OpenApiExternalDocumentation;
import io.apicurio.datamodels.models.openapi.OpenApiInfo;
import io.apicurio.datamodels.models.openapi.OpenApiPaths;
import io.apicurio.datamodels.models.openapi.OpenApiTag;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xComponents;
import io.apicurio.datamodels.models.openapi.v3x.v31.visitors.OpenApi31Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OpenApi31DocumentImpl extends RootNodeImpl implements OpenApi31Document {

	private String openapi;
	private String jsonSchemaDialect;
	private OpenApiInfo info;
	private List<Server> servers;
	private OpenApiPaths paths;
	private Map<String, OpenApi31PathItem> webhooks;
	private OpenApi3xComponents components;
	private List<SecurityRequirement> security;
	private List<OpenApiTag> tags;
	private OpenApiExternalDocumentation externalDocs;
	private Map<String, JsonNode> extensions;

	public OpenApi31DocumentImpl() {
		super(ModelType.OPENAPI31);
	}

	@Override
	public String getOpenapi() {
		return openapi;
	}

	@Override
	public void setOpenapi(String value) {
		this.openapi = value;
	}

	@Override
	public String getJsonSchemaDialect() {
		return jsonSchemaDialect;
	}

	@Override
	public void setJsonSchemaDialect(String value) {
		this.jsonSchemaDialect = value;
	}

	@Override
	public OpenApiInfo getInfo() {
		return info;
	}

	@Override
	public void setInfo(OpenApiInfo value) {
		this.info = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("info");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi31Info createInfo() {
		OpenApi31InfoImpl node = new OpenApi31InfoImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public OpenApi31Server createServer() {
		OpenApi31ServerImpl node = new OpenApi31ServerImpl();
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
	public OpenApiPaths getPaths() {
		return paths;
	}

	@Override
	public void setPaths(OpenApiPaths value) {
		this.paths = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("paths");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi31Paths createPaths() {
		OpenApi31PathsImpl node = new OpenApi31PathsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public OpenApi31PathItem createPathItem() {
		OpenApi31PathItemImpl node = new OpenApi31PathItemImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApi31PathItem> getWebhooks() {
		return webhooks;
	}

	@Override
	public void addWebhook(String name, OpenApi31PathItem value) {
		if (this.webhooks == null) {
			this.webhooks = new LinkedHashMap<>();
		}
		this.webhooks.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("webhooks");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearWebhooks() {
		if (this.webhooks != null) {
			this.webhooks.clear();
		}
	}

	@Override
	public void removeWebhook(String name) {
		if (this.webhooks != null) {
			this.webhooks.remove(name);
		}
	}

	@Override
	public void insertWebhook(String name, OpenApi31PathItem value, int atIndex) {
		if (this.webhooks == null) {
			this.webhooks = new LinkedHashMap<>();
			this.webhooks.put(name, value);
		} else {
			this.webhooks = DataModelUtil.insertMapEntry(this.webhooks, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("webhooks");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenApi3xComponents getComponents() {
		return components;
	}

	@Override
	public void setComponents(OpenApi3xComponents value) {
		this.components = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("components");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi31Components createComponents() {
		OpenApi31ComponentsImpl node = new OpenApi31ComponentsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public OpenApi31SecurityRequirement createSecurityRequirement() {
		OpenApi31SecurityRequirementImpl node = new OpenApi31SecurityRequirementImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<SecurityRequirement> getSecurity() {
		return security;
	}

	@Override
	public void addSecurity(SecurityRequirement value) {
		if (this.security == null) {
			this.security = new ArrayList<>();
		}
		this.security.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("security");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearSecurity() {
		if (this.security != null) {
			this.security.clear();
		}
	}

	@Override
	public void removeSecurity(SecurityRequirement value) {
		if (this.security != null) {
			this.security.remove(value);
		}
	}

	@Override
	public void insertSecurity(SecurityRequirement value, int atIndex) {
		if (this.security == null) {
			this.security = new ArrayList<>();
			this.security.add(value);
		} else {
			this.security = DataModelUtil.insertListEntry(this.security, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("security");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public OpenApi31Tag createTag() {
		OpenApi31TagImpl node = new OpenApi31TagImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<OpenApiTag> getTags() {
		return tags;
	}

	@Override
	public void addTag(OpenApiTag value) {
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
	public void removeTag(OpenApiTag value) {
		if (this.tags != null) {
			this.tags.remove(value);
		}
	}

	@Override
	public void insertTag(OpenApiTag value, int atIndex) {
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
	public OpenApiExternalDocumentation getExternalDocs() {
		return externalDocs;
	}

	@Override
	public void setExternalDocs(OpenApiExternalDocumentation value) {
		this.externalDocs = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("externalDocs");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi31ExternalDocumentation createExternalDocumentation() {
		OpenApi31ExternalDocumentationImpl node = new OpenApi31ExternalDocumentationImpl();
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
		OpenApi31Visitor viz = (OpenApi31Visitor) visitor;
		viz.visitDocument(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi31DocumentImpl();
	}
}