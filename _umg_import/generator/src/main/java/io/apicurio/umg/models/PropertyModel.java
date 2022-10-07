package io.apicurio.umg.models;

import lombok.Builder;
import lombok.Data;

/**
 * Models a single property in an entity or trait.
 */
@Builder
@Data
public class PropertyModel {
	
	private String name;
	private PropertyType type;

}
