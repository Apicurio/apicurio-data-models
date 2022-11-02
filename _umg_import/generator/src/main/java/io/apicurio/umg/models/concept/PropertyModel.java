package io.apicurio.umg.models.concept;

import lombok.Builder;
import lombok.Data;

/**
 * Models a single property in an entity or trait.
 */
@Builder
@Data
public class PropertyModel {

    private String name;

    private String collection;

    private String rawType;

    private PropertyType type;
}
