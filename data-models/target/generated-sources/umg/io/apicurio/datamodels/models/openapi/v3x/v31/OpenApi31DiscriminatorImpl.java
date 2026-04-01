package io.apicurio.datamodels.models.openapi.v3x.v31;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.openapi.v3x.v31.visitors.OpenApi31Visitor;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.Map;

public class OpenApi31DiscriminatorImpl extends NodeImpl implements OpenApi31Discriminator {

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
		OpenApi31Visitor viz = (OpenApi31Visitor) visitor;
		viz.visitDiscriminator(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi31DiscriminatorImpl();
	}
}