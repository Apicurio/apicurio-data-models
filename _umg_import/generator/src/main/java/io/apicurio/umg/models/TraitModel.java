package io.apicurio.umg.models;

import java.util.LinkedHashMap;
import java.util.Map;

import io.apicurio.umg.beans.Specification;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;

@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TraitModel {

    private Specification spec;
    @Include
    private NamespaceModel namespace;
    @Include
    private String name;
    private TraitModel parent;
    private final Map<String, PropertyModel> properties = new LinkedHashMap<>();
    private boolean transparent;
    private boolean leaf;

    public String fullyQualifiedName() {
        return namespace.fullName() + "." + name;
    }

    public void addProperty(PropertyModel property) {
        this.properties.put(property.getName(), property);
    }

    public boolean hasProperty(String propertyName) {
        return this.properties.containsKey(propertyName);
    }
}
