package io.apicurio.datamodels.models.openapi.v3x.v30;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.openapi.v3x.v30.visitors.OpenApi30Visitor;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.Map;

public class OpenApi30DiscriminatorImpl extends NodeImpl implements OpenApi30Discriminator {

	private String propertyName;
	private Map<String, String> mapping;

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
	public void accept(Visitor visitor) {
		OpenApi30Visitor viz = (OpenApi30Visitor) visitor;
		viz.visitDiscriminator(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi30DiscriminatorImpl();
	}
}