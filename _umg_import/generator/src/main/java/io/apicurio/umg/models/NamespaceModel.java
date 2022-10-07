package io.apicurio.umg.models;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NamespaceModel {
	
	private NamespaceModel parent;
	private final Map<String, NamespaceModel> children = new HashMap<>();
	private String name;
	private final Map<String, EntityModel> entities = new HashMap<>();
	
	public String fullName() {
		return (parent != null ? parent.fullName() + "." : "") + name;
	}

}
