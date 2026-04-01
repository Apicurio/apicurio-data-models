package io.apicurio.datamodels.models.openrpc.v1x.v13;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Contact;
import io.apicurio.datamodels.models.License;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.openrpc.v1x.v13.visitors.OpenRpc13Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenRpc13InfoImpl extends NodeImpl implements OpenRpc13Info {

	private String title;
	private String description;
	private String termsOfService;
	private Contact contact;
	private License license;
	private String version;
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
	public OpenRpc13Contact createContact() {
		OpenRpc13ContactImpl node = new OpenRpc13ContactImpl();
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
	public OpenRpc13License createLicense() {
		OpenRpc13LicenseImpl node = new OpenRpc13LicenseImpl();
		node.setParent(this);
		return node;
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
		viz.visitInfo(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenRpc13InfoImpl();
	}
}