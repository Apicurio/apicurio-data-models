package io.apicurio.umg.models;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import io.apicurio.umg.beans.beans.Specification;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;

@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EntityModel {

	private Specification spec;
	@Include
	private NamespaceModel namespace;
	@Include
	private String name;
	private final Collection<TraitModel> traits = new HashSet<>();
	private final Map<String, PropertyModel> properties = new HashMap<>();

	public String fullyQualifiedName() {
		return namespace.fullName() + "." + name;
	}

}
