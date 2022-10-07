package io.apicurio.umg.models;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
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
	private final Collection<TraitModel> traits = new LinkedHashSet<>();
	private final Map<String, PropertyModel> properties = new LinkedHashMap<>();

	public String fullyQualifiedName() {
		return namespace.fullName() + "." + name;
	}

}
