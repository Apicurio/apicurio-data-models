package io.apicurio.umg.models;

import java.util.HashMap;
import java.util.Map;

import io.apicurio.umg.beans.beans.Specification;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TraitModel {
	
    private Specification spec;
    private NamespaceModel namespace;
	private String name;
	private final Map<String, PropertyModel> properties = new HashMap<>();

	public String fullyQualifiedName() {
		return namespace.fullName() + "." + name;
	}

}
