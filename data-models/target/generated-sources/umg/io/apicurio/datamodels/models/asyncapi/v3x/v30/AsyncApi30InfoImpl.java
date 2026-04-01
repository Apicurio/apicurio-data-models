package io.apicurio.datamodels.models.asyncapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Contact;
import io.apicurio.datamodels.models.License;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xTag;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.visitors.AsyncApi30Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi30InfoImpl extends NodeImpl implements AsyncApi30Info {

	private String title;
	private String version;
	private String description;
	private String termsOfService;
	private Contact contact;
	private License license;
	private List<AsyncApi3xTag> tags;
	private AsyncApi3xExternalDocumentation externalDocs;
	private Map<String, JsonNode> extensions;

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String value) {
		this.title = value;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public void setVersion(String value) {
		this.version = value;
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
	public String getTermsOfService() {
		return termsOfService;
	}

	@Override
	public void setTermsOfService(String value) {
		this.termsOfService = value;
	}

	@Override
	public Contact getContact() {
		return contact;
	}

	@Override
	public void setContact(Contact value) {
		this.contact = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("contact");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi30Contact createContact() {
		AsyncApi30ContactImpl node = new AsyncApi30ContactImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public License getLicense() {
		return license;
	}

	@Override
	public void setLicense(License value) {
		this.license = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("license");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi30License createLicense() {
		AsyncApi30LicenseImpl node = new AsyncApi30LicenseImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApi30Tag createTag() {
		AsyncApi30TagImpl node = new AsyncApi30TagImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<AsyncApi3xTag> getTags() {
		return tags;
	}

	@Override
	public void addTag(AsyncApi3xTag value) {
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
	public void removeTag(AsyncApi3xTag value) {
		if (this.tags != null) {
			this.tags.remove(value);
		}
	}

	@Override
	public void insertTag(AsyncApi3xTag value, int atIndex) {
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
	public AsyncApi3xExternalDocumentation getExternalDocs() {
		return externalDocs;
	}

	@Override
	public void setExternalDocs(AsyncApi3xExternalDocumentation value) {
		this.externalDocs = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("externalDocs");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi30ExternalDocumentation createExternalDocumentation() {
		AsyncApi30ExternalDocumentationImpl node = new AsyncApi30ExternalDocumentationImpl();
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
		AsyncApi30Visitor viz = (AsyncApi30Visitor) visitor;
		viz.visitInfo(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi30InfoImpl();
	}
}