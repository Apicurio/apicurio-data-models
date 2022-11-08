package io.apicurio.umg.models.concept;

import java.util.LinkedHashMap;
import java.util.Map;

import io.apicurio.umg.beans.SpecificationVersion;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;

// TODO Put EntityModel and TraitModel under the same parent? (Similar to JavaEntityModel)
// See io.apicurio.umg.pipe.java.method.FactoryMethod
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TraitModel {

    private SpecificationVersion specVersion;
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
