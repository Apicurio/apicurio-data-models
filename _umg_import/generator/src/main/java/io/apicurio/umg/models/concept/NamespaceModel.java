package io.apicurio.umg.models.concept;

import io.apicurio.umg.models.VisitorModel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class NamespaceModel {

    @EqualsAndHashCode.Include
    private NamespaceModel parent;
    @EqualsAndHashCode.Include
    @ToString.Include
    private String name;

    private final Map<String, NamespaceModel> children = new HashMap<>();
    private final Map<String, EntityModel> entities = new HashMap<>();
    private final Map<String, TraitModel> traits = new HashMap<>();
    private VisitorModel visitor;

    public String fullName() {
        return (parent != null ? parent.fullName() + "." : "") + name;
    }

    public boolean containsEntity(String entityName) {
        return entities.containsKey(entityName);
    }

    public boolean containsTrait(String traitName) {
        return traits.containsKey(traitName);
    }

    @Override
    public String toString() {
        return "Namespace<" + fullName() + ">";
    }
}
