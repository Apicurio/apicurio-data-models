package io.apicurio.datamodels.models.openapi.v3x.v32;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.openapi.v3x.v32.visitors.OpenApi32Visitor;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.Map;

public class OpenApi32DiscriminatorImpl extends NodeImpl implements OpenApi32Discriminator {

	private String propertyName;
	private Map<String, String> mapping;
	private String defaultMapping;

	@Override
	public String getPropertyName() {
		return propertyName;
	}

	@Override
	public void setPropertyName(String value) {
		this.propertyName = value;
	}

	@Override
	public Map<String, String> getMapping() {
		return mapping;
	}

	@Override
	public void setMapping(Map<String, String> value) {
		this.mapping = value;
	}

	@Override
	public String getDefaultMapping() {
		return defaultMapping;
	}

	@Override
	public void setDefaultMapping(String value) {
		this.defaultMapping = value;
	}

	@Override
	public void accept(Visitor visitor) {
		OpenApi32Visitor viz = (OpenApi32Visitor) visitor;
		viz.visitDiscriminator(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi32DiscriminatorImpl();
	}
}